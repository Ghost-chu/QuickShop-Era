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

package org.maxgamer.quickshop.bukkit.util.bukkit.item;

import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.util.Util;

import java.text.DecimalFormat;

public class ItemStack {
    /**
     * Gets the ItemStack is Tool.
     *
     * @param item The ItemStack of tools to check
     * @return The percentage 'health' the tool has. (Opposite of total damage)
     */
    public static boolean isTool(@NotNull org.bukkit.inventory.ItemStack item) {
        return item.getItemMeta() instanceof Damageable; //TODO Can we migrate to the NamespacedKey or Tag?
    }

    /**
     * Gets the percentage (Without trailing %) damage on a tool.
     *
     * @param item The ItemStack of tools to check
     * @return The percentage 'health' the tool has. (Opposite of total damage)
     */
    @NotNull
    public static String getToolPercentage(@NotNull org.bukkit.inventory.ItemStack item) {
        if (!isTool(item)) {
            Util.debugLog(item.getType().name() + " not Damageable.");
            return "Error: NaN";
        }
        double dura = ((Damageable) item.getItemMeta()).getDamage();
        double max = item.getType().getMaxDurability();
        DecimalFormat formatter = new DecimalFormat("0");
        return formatter.format((1 - dura / max) * 100.0);
    }
}
