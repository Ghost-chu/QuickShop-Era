/*
 *     Copyright (c) 2020, Bukkit Commons Studio.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package org.maxgamer.quickshop.shop;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sk89q.worldedit.world.World;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.crossplatform.type.item.CrossPlatformItemStack;
import org.maxgamer.quickshop.crossplatform.type.location.CrossPlatformLocation;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.logger.LoggerUtil;
import org.maxgamer.quickshop.util.misc.JsonUtil;
import org.maxgamer.quickshop.util.time.Timer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * A class allow plugin load shops fast and simply.
 */
public abstract class ShopLoader {
    private final List<Long> loadTimes = new ArrayList<>();

    private final Map<Timer, Double> costCache = new HashMap<>();
    /* This may contains broken shop, must use null check before load it. */
    private final List<Shop> shopsInDatabase = new CopyOnWriteArrayList<>();
    private final List<ShopDatabaseInfoOrigin> originShopsInDatabase = new CopyOnWriteArrayList<>();
    private int errors;
    private final int loadAfterChunkLoaded = 0;
    private final int loadAfterWorldLoaded = 0;
    private int totalLoaded = 0;
    private final Logger logger = LoggerUtil.getLogger();
    //private final WarningSender warningSender;

    /**
     * The shop load allow plugin load shops fast and simply.
     *
     * @param plugin Plugin main class
     */
    public ShopLoader() {
        //this.warningSender = new WarningSender(plugin, 15000);
    }

    public void loadShops() {
        loadShops(null);
    }

    /**
     * Load all shops
     *
     * @param worldName The world name
     */
    public abstract void loadShops(@Nullable String worldName);

    private void printLoadMetrics(int loadAfterChunkLoaded, int loadAfterWorldLoaded, int loadFailedShops) {
        if (loadFailedShops == 0) {
            logger.info(loadAfterChunkLoaded
                    + " shops will load after chunk have loaded, "
                    + loadAfterWorldLoaded
                    + " shops will load after the world has loaded.");
        } else {
            logger.warning(loadAfterChunkLoaded
                    + " shops will load after chunk have loaded, "
                    + loadAfterWorldLoaded
                    + " shops will load after the world has loaded, "
                    + " shops load fails.");
        }
    }

    private void printSuccessMetrics(int totalLoaded, long totalUsedTime, long avgPerShop) {
        logger
                .info(
                        "Successfully loaded "
                                + totalLoaded
                                + " shops! (Used "
                                + totalUsedTime
                                + "ms, Avg "
                                + avgPerShop
                                + "ms per shop)");
    }

    private void singleShopLoaded(@NotNull Timer singleShopLoadTimer) {
        totalLoaded++;
        long singleShopLoadTime = singleShopLoadTimer.endTimer();
        loadTimes.add(singleShopLoadTime);
        Util.debugLog("Loaded shop used time " + singleShopLoadTime + "ms");
//        if (singleShopLoadTime > 1500) {
//            warningSender.sendWarn("Database performance bottleneck: Detected slow database, it may mean bad network connection, slow database server or database fault. Please check the database!");
//        }
    }

    private double costCalc(@NotNull Timer timer) {
        costCache.putIfAbsent(timer, (double) timer.getTimer());
        return timer.getTimer() - costCache.get(timer);
    }

    public abstract boolean shopNullCheck(@Nullable Shop shop);

    private @NotNull Long mean(Long[] m) {
        long sum = 0;
        for (Long aM : m) {
            sum += aM;
        }
        if (m.length == 0) {
            return sum;
        }
        return sum / m.length;
    }

    private void exceptionHandler(@NotNull Exception ex, @Nullable CrossPlatformLocation shopLocation) {
        errors++;
        logger.warning("##########FAILED TO LOAD SHOP##########");
        logger.warning("  >> Error Info:");
        String err = ex.getMessage();
        if (err == null) {
            err = "null";
        }
        logger.warning(err);
        logger.warning("  >> Error Trace");
        ex.printStackTrace();
        logger.warning("  >> Target Location Info");
        logger.warning("Location: " + ((shopLocation == null) ? "NULL" : shopLocation.toString()));
        logger.warning(
                "Block: " + ((shopLocation == null) ? "NULL" : shopLocation.getBlock().getType().getName()));
        logger.warning("  >> Database Info");
        try {
            logger.warning("Connected: " + plugin.getDatabase().getConnection().isClosed());
        } catch (SQLException | NullPointerException e) {
            logger.warning("Connected: " + "FALSE - Failed to load status.");
        }

        try {
            logger.warning("Readonly: " + plugin.getDatabase().getConnection().isReadOnly());
        } catch (SQLException | NullPointerException e) {
            logger.warning("Readonly: " + "FALSE - Failed to load status.");
        }

        try {
            logger.warning("ClientInfo: " + plugin.getDatabase().getConnection().getClientInfo());
        } catch (SQLException | NullPointerException e) {
            logger.warning("ClientInfo: " + "FALSE - Failed to load status.");
        }

        logger.warning("#######################################");
        if (errors > 10) {
            logger.severe(
                    "QuickShop detected too many errors when loading shops, you should backup your shop database and ask the developer for help");
        }
    }

    public synchronized void recoverFromFile(@NotNull String fileContent) {
        logger.info("Processing the shop data...");
        String[] shopsPlain = fileContent.split("\n");
        logger.info("Recovering shops...");
        Gson gson = JsonUtil.getGson();
        int total = shopsPlain.length;
        for (int i = 0; i < total; i++) {
            String shopStr = shopsPlain[i].trim();
            boolean success = false;
            try {
                ShopDatabaseInfoOrigin shopDatabaseInfoOrigin = gson.fromJson(shopStr, ShopDatabaseInfoOrigin.class);
                originShopsInDatabase.add(shopDatabaseInfoOrigin);
                ShopDatabaseInfo data = new ShopDatabaseInfo(shopDatabaseInfoOrigin);
                Shop shop =
                        new ContainerShop(plugin,
                                data.getLocation(),
                                data.getPrice(),
                                data.getItem(),
                                data.getModerators(),
                                data.isUnlimited(),
                                data.getType(),
                                data.getExtra());
                shopsInDatabase.add(shop);
                if (shopNullCheck(shop)) {
                    continue;
                }
                // Load to RAM
                plugin.getDatabaseHelper().createShop(shop, null, null);
                plugin.getShopManager().loadShop(data.getWorld().getName(), shop);
                shop.update();


                success = true;
            } catch (JsonSyntaxException ignore) {
            }
            logger.info("Processed " + i + "/" + total + " - [" + success + "]");
        }
    }

    @NotNull
    public List<Shop> getShopsInDatabase() {
        return new ArrayList<>(shopsInDatabase);
    }

    @NotNull
    public List<ShopDatabaseInfoOrigin> getOriginShopsInDatabase() {
        return new ArrayList<>(originShopsInDatabase);
    }

    @Getter
    @Setter
    public class ShopDatabaseInfo {
        private CrossPlatformItemStack item;

        private CrossPlatformLocation location;

        private ShopModerator moderators;

        private double price;

        private ShopType type;

        private boolean unlimited;

        private World world;

        private int x;

        private int y;

        private int z;

        private Map<String, Map<String, String>> extra;

        ShopDatabaseInfo(ShopDatabaseInfoOrigin origin) {
            try {
                this.x = origin.getX();
                this.y = origin.getY();
                this.z = origin.getZ();
                this.price = origin.getPrice();
                this.unlimited = origin.isUnlimited();
                this.type = ShopType.fromID(origin.getType());
                this.world = Bukkit.getWorld(origin.getWorld());
                this.item = deserializeItem(origin.getItem());
                this.moderators = deserializeModerator(origin.getModerators());
                this.location = new CrossPlatformLocation(world.getName(), x, y, z);
                //noinspection unchecked
                this.extra = JsonUtil.getGson().fromJson(origin.getExtra(), Map.class);
                if (this.extra == null) {
                    this.extra = new ConcurrentHashMap<>();
                }
            } catch (Exception ex) {
                exceptionHandler(ex, this.location);
            }
        }

        private @Nullable CrossPlatformItemStack deserializeItem(@NotNull String itemConfig) {
            try {
                return Util.deserialize(itemConfig);
            } catch (Exception e) {
                e.printStackTrace();
                logger.warning(
                        "Failed load shop data, because target config can't deserialize the ItemStack.");
                Util.debugLog("Failed to load data to the ItemStack: " + itemConfig);
                return null;
            }
        }

        private @Nullable ShopModerator deserializeModerator(@NotNull String moderatorJson) {
            ShopModerator shopModerator;
            if (Util.isUUID(moderatorJson)) {
                Util.debugLog("Updating old shop data... for " + moderatorJson);
                shopModerator = new ShopModerator(UUID.fromString(moderatorJson)); // New one
            } else {
                try {
                    shopModerator = ShopModerator.deserialize(moderatorJson);
                } catch (JsonSyntaxException ex) {
                    Util.debugLog("Updating old shop data... for " + moderatorJson);
                    //noinspection deprecation
                    moderatorJson = Bukkit.getOfflinePlayer(moderatorJson).getUniqueId().toString();
                    shopModerator = new ShopModerator(UUID.fromString(moderatorJson)); // New one
                }
            }
            return shopModerator;
        }

    }

    @Getter
    @Setter
    public class ShopDatabaseInfoOrigin {
        private String item;

        private String moderators;

        private double price;

        private int type;

        private boolean unlimited;

        private String world;

        private int x;

        private int y;

        private int z;

        private String extra;

        ShopDatabaseInfoOrigin(ResultSet rs) {
            try {
                this.x = rs.getInt("x");
                this.y = rs.getInt("y");
                this.z = rs.getInt("z");
                this.world = rs.getString("world");
                this.item = rs.getString("itemConfig");
                this.moderators = rs.getString("owner");
                this.price = rs.getDouble("price");
                this.type = rs.getInt("type");
                this.unlimited = rs.getBoolean("unlimited");
                this.extra = rs.getString("extra");
                //handle old shops
                if (extra == null) {
                    extra = "{}";
                }
            } catch (SQLException sqlex) {
                exceptionHandler(sqlex, null);
            }
        }

        ShopDatabaseInfoOrigin(int x, int y, int z, String world, String itemConfig, String owner, double price, int type, boolean unlimited, String extra) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.world = world;
            this.item = itemConfig;
            this.moderators = owner;
            this.price = price;
            this.type = type;
            this.unlimited = unlimited;
            this.extra = extra;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }

}
