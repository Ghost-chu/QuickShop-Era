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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShopProtectionFlag {
    private static final String mark = "QuickShop DisplayItem";
    private final String itemStackString;
    private final String shopLocation;

    public ShopProtectionFlag(@NotNull String shopLocation, @NotNull String itemStackString) {
        this.shopLocation = shopLocation;
        this.itemStackString = itemStackString;
    }

    public static String getDefaultMark() {
        return mark;
    }

    public static String getMark() {
        return mark;
    }
}
