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
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShopInventoryPreviewEvent extends QSEvent implements Cancellable {

    @Getter
    @NotNull
    private final ItemStack itemStack;

    @Getter
    @NotNull
    private final Player player;

    private boolean cancelled;

    /**
     * Build a event when player using GUI preview
     *
     * @param player    Target plugin
     * @param itemStack The preview item, with preview flag.
     */
    public ShopInventoryPreviewEvent(@NotNull Player player, @NotNull ItemStack itemStack) {
        this.player = player;
        this.itemStack = itemStack;
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