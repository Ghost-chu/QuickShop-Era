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

package org.maxgamer.quickshop.bukkit.event;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.shop.DisplayItem;
import org.maxgamer.quickshop.shop.DisplayType;
import org.maxgamer.quickshop.shop.Shop;

/**
 * This event is called after DisplayItem removed
 */
public class ShopDisplayItemDespawnEvent extends QSEvent implements Cancellable {

    @Getter
    @NotNull
    private final Shop shop;

    @Getter
    @NotNull
    private final ItemStack itemStack;

    @Getter
    @NotNull
    private final DisplayType displayType;

    private boolean cancelled;

    /**
     * This event is called before the shop display item created
     *
     * @param shop      Target shop
     * @param itemStack Target itemstacck
     */
    @Deprecated
    public ShopDisplayItemDespawnEvent(@NotNull Shop shop, @NotNull ItemStack itemStack) {
        this(shop, itemStack, DisplayItem.getNowUsing());
    }

    /**
     * This event is called before the shop display item created
     *
     * @param shop        Target shop
     * @param itemStack   Target itemstacck
     * @param displayType The displayType
     */
    public ShopDisplayItemDespawnEvent(
            @NotNull Shop shop, @NotNull ItemStack itemStack, @NotNull DisplayType displayType) {
        this.shop = shop;
        this.itemStack = itemStack;
        this.displayType = displayType;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
