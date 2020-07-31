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
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.shop.Shop;

/**
 * Calling when shop sign update, Can't cancel
 */
public class ShopSignUpdatedEvent extends QSEvent {

    @Getter
    @NotNull
    private final Shop shop;

    @Getter
    @NotNull
    private final Sign sign;

    /**
     * Will call when shop price was changed.
     *
     * @param shop Target shop
     * @param sign Updated sign
     */
    public ShopSignUpdatedEvent(@NotNull Shop shop, @NotNull Sign sign) {
        this.shop = shop;
        this.sign = sign;
    }

}
