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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.shop.DisplayItem;
import org.maxgamer.quickshop.shop.DisplayType;
import org.maxgamer.quickshop.shop.Shop;

@AllArgsConstructor
public class DisplayItemAPI {
    private final QuickShop plugin;

    /**
     * Checks is a display item
     *
     * @param itemStack The itemstack
     * @return yes or no
     */
    public static boolean isDisplayItem(@NotNull ItemStack itemStack) {
        return DisplayItem.checkIsGuardItemStack(itemStack);
    }

    /**
     * Check is a shop's display item
     *
     * @param itemStack The itemstack
     * @param shop      The itemstack
     * @return yes or no
     */
    public static boolean isShopDisplayItem(@NotNull ItemStack itemStack, @NotNull Shop shop) {
        return DisplayItem.checkIsTargetShopDisplay(itemStack, shop);
    }

    /**
     * Gets the display type now using
     *
     * @return The type of display now using
     */
    public static DisplayType getNowUsing() {
        return DisplayItem.getNowUsing();
    }
}
