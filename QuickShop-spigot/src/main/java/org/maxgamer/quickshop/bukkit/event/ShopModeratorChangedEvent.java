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
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.shop.Shop;
import org.maxgamer.quickshop.shop.ShopModerator;

/**
 * Calling when moderator was changed, Can't cancel
 */
public class ShopModeratorChangedEvent extends QSEvent {

    @Getter
    @NotNull
    private final ShopModerator moderator;

    @Getter
    @NotNull
    private final Shop shop;

    /**
     * Will call when shop price was changed.
     *
     * @param shop          Target shop
     * @param shopModerator The shop moderator
     */
    public ShopModeratorChangedEvent(@NotNull Shop shop, @NotNull ShopModerator shopModerator) {
        this.shop = shop;
        this.moderator = shopModerator;
    }

}
