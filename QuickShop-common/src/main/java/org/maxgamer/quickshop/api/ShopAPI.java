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

package org.maxgamer.quickshop.api;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.crossplatform.type.chunk.CrossPlatformChunk;
import org.maxgamer.quickshop.crossplatform.type.location.CrossPlatformLocation;
import org.maxgamer.quickshop.crossplatform.type.world.CrossPlatformWorld;
import org.maxgamer.quickshop.shop.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class ShopAPI {
    private final QuickShop plugin;

    /**
     * Gets shop from location
     *
     * @param location The location
     * @return The shop in that location, may be null
     */
    public @Nullable Shop getShop(@NotNull CrossPlatformLocation location) {
        return plugin.getShopManager().getShop(location, false);
    }

    /**
     * Gets shop from location with caching
     *
     * @param location The location
     * @return The shop in that location, may be null
     */
    public @Nullable Shop getShopWithCaching(@NotNull CrossPlatformLocation location) {
        if (plugin.getShopCache() == null) {
            return getShop(location);
        }
        return plugin.getShopCache().getCaching(location, false);
    }

    /**
     * Gets shops in chunk
     *
     * @param chunk The chunk
     * @return The shops in chunk, may be null
     */
    public @Nullable Map<CrossPlatformLocation, Shop> getShop(@NotNull CrossPlatformChunk chunk) {
        return plugin.getShopManager().getShops(chunk);
    }

    /**
     * Gets shop from location
     *
     * @param location The location
     * @return The shop in that location, may be null
     */
    public @Nullable Shop getShopIncludeAttached(@NotNull CrossPlatformLocation location) {
        return plugin.getShopManager().getShopIncludeAttached(location);
    }

    /**
     * Gets shop from location and use caching
     *
     * @param location The location
     * @return The shop in that location, may be null
     */
    public @Nullable Shop getShopIncludeAttachedWithCaching(@NotNull CrossPlatformLocation location) {
        if (plugin.getShopCache() == null) {
            return getShopIncludeAttached(location);
        }
        return plugin.getShopCache().getCaching(location, true);
    }

    /**
     * Gets a list of copy for loaded shops
     *
     * @return The copy of loaded shops
     */
    public @NotNull List<Shop> getLoadedShops() {
        return new ArrayList<>(plugin.getShopManager().getLoadedShops());
    }

    /**
     * Gets a list of copy a player's all shops
     * This is a expensive action, please caching the result
     *
     * @param uuid Player UUID
     * @return The list of player's all shops
     */
    public @NotNull List<Shop> getPlayerAllShops(@NotNull UUID uuid) {
        return new ArrayList<>(plugin.getShopManager().getPlayerAllShops(uuid));
    }

    /**
     * Gets a list of copy all shops on this server
     * This is a expensive action, please caching the result
     *
     * @return The list of all shops on server
     */
    public @NotNull List<Shop> getAllShops() {
        return new ArrayList<>(plugin.getShopManager().getAllShops());
    }

    /**
     * Gets a list of copy all shops in world
     * This is a expensive action, please caching the result
     *
     * @param world The world that you want get shops
     * @return The list of all shops in world
     */
    public @NotNull List<Shop> getShopsInWorld(@NotNull CrossPlatformWorld world) {
        return new ArrayList<>(plugin.getShopManager().getShopsInWorld(world));
    }


}
