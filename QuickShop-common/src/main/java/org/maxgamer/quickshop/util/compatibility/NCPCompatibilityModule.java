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

package org.maxgamer.quickshop.util.compatibility;

import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.util.Util;

@AllArgsConstructor
public class NCPCompatibilityModule implements CompatibilityModule {
    private final QuickShop plugin;

    /**
     * Gets the CompatibilityModule provider name
     *
     * @return Provider name
     */
    @Override
    public @NotNull String getName() {
        return "NoCheatPlus";
    }

    /**
     * Gets the CompatibilityModule provider plugin instance
     *
     * @return Provider Plugin instance
     */
    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    /**
     * Calls CompatibilityModule to toggle the detection status for playerb between on and off
     *
     * @param player   The player
     * @param checking On or Off
     */
    @Override
    public void toggle(@NotNull Player player, boolean checking) {
        if (checking) {
            Util.debugLog(
                    "Calling NoCheatPlus ignore "
                            + player.getName()
                            + " cheats detection until we finished permission checks.");

            NCPExemptionManager.unexempt(player);
        } else {
            Util.debugLog(
                    "Calling NoCheatPlus continue follow " + player.getName() + " cheats detection.");
            NCPExemptionManager.exemptPermanently(player);
        }
    }
}
