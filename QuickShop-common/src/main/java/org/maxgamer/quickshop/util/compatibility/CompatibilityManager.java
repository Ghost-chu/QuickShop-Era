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

import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.crossplatform.type.entity.CrossPlatformPlayer;
import org.maxgamer.quickshop.util.Util;

import java.util.HashSet;
import java.util.Set;

public class CompatibilityManager {
    private final Set<CompatibilityModule> registeredModules = new HashSet<>(5);

    public CompatibilityManager() {
    }

    /**
     * Switch the compatibility mode on or off, set false to disable all we known incompatiable plugin
     * listener, set true to enable back all disabled plugin liseners. WIP
     *
     * @param status true=turn on closed listeners, false=turn off all turned on listeners.
     * @param player The player to check the listeners
     */
    public void toggleProtectionListeners(boolean status, @NotNull CrossPlatformPlayer player) {
        for (CompatibilityModule module : this.registeredModules) {
            try {
                module.toggle(player, status);
            } catch (Exception e) {
                unregister(module);
                Util.debugLog("Unregistered module " + module.getName() + " for an error: " + e.getMessage());
            }
        }
    }

    public void clear() {
        registeredModules.clear();
    }

    public void register(@NotNull CompatibilityModule module) {
        registeredModules.add(module);
    }

    public void unregister(@NotNull CompatibilityModule module) {
        registeredModules.remove(module);
    }
}
