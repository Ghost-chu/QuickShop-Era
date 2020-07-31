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

package org.maxgamer.quickshop.watcher;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.shop.Shop;
import org.maxgamer.quickshop.util.Util;

@AllArgsConstructor
public class DisplayAutoDespawnWatcher extends BukkitRunnable {
    private final QuickShop plugin;

    @Override
    public void run() {
        int range = plugin.getConfig().getInt("shop.display-despawn-range");

        for (Shop shop : plugin.getShopManager().getLoadedShops()) {
            if (shop.getDisplay() != null) {
                // Check the range has player?
                boolean anyPlayerInRegion = false;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if ((player.getWorld() == shop.getLocation().getWorld()) && (player.getLocation().distance(shop.getLocation()) < range)) {
                        anyPlayerInRegion = true;
                        break;
                    }
                }
                if (anyPlayerInRegion) {
                    if (!shop.getDisplay().isSpawned()) {
                        Util.debugLog(
                                "Respawning the shop "
                                        + shop
                                        + " the display, cause it was despawned and a player close it");
                        Bukkit.getScheduler().runTask(plugin, shop::checkDisplay);
                    }
                } else if (shop.getDisplay().isSpawned()) {
                    removeDisplayItemDelayed(shop);
                }
            }
        }
    }

    public boolean removeDisplayItemDelayed(Shop shop) {
        if (shop.getDisplay() != null) {
            if (shop.getDisplay().isPendingRemoval()) {
                // Actually remove the pending display
                Util.debugLog("Removing the shop " + shop + " the display, cause nobody can see it");
                Bukkit.getScheduler().runTask(plugin, () -> shop.getDisplay().remove());
                return true;
            } else {
                // Delayed to next calling
                Util.debugLog("Pending to remove the shop " + shop + " the display, cause nobody can see it");
                shop.getDisplay().pendingRemoval();
                return false;
            }
        }
        return false;
    }

}
