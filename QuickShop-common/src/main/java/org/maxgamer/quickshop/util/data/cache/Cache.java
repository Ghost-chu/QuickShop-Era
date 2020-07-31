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

package org.maxgamer.quickshop.util.data.cache;

import com.google.common.cache.CacheBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.crossplatform.type.location.CrossPlatformLocation;
import org.maxgamer.quickshop.shop.Shop;

import java.util.concurrent.TimeUnit;

public class Cache {
    private final com.google.common.cache.Cache<CrossPlatformLocation, CacheContainer> accessCaching = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).initialCapacity(500).build();

    public Cache() {

    }

    public long getCachingSize() {
        return accessCaching.size();
    }


    /**
     * Gets shop from plugin caching
     *
     * @param location        The shop location that you want to get
     * @param includeAttached Include attached shops
     * @param missProcessor   When nothing founded in cache, CacheProcessor#miss will be called
     * @return The shop, null for no shops found in caching and memory
     */
    @Nullable
    public Shop getCaching(@NotNull CrossPlatformLocation location, boolean includeAttached, @NotNull CacheProcessor missProcessor) {
        CacheContainer container;
        container = accessCaching.getIfPresent(location);
        if (container == null) {
            return missProcessor.miss(location, includeAttached);
        } else {
            return container.getShop();
        }
    }

    /**
     * Update and invalidate the caching
     *
     * @param location The location that you want to update
     * @param shop     null for invalidate and Shop object for update
     */
    public void setCache(@NotNull CrossPlatformLocation location, @Nullable Shop shop) {
        if (shop == null) {
            accessCaching.invalidate(location);
            return;
        }
        accessCaching.put(location, new CacheContainer(shop, System.currentTimeMillis()));
    }
}

class CacheContainer {
    @NotNull
    private final Shop shop;

    private final long time;

    public CacheContainer(@NotNull Shop shop, long time) {
        this.shop = shop;
        this.time = time;
    }

    /**
     * Gets container created at.
     *
     * @return The timestamp
     */
    public long getTime() {
        return time;
    }

    /**
     * Gets container shop
     *
     * @return The shop
     */
    @NotNull
    public Shop getShop() {
        return shop;
    }
}

