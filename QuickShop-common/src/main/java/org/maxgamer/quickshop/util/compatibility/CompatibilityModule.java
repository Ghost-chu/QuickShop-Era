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

public interface CompatibilityModule {
    /**
     * Gets the CompatibilityModule provider name
     *
     * @return Provider name
     */
    @NotNull String getName();

    /**
     * Gets the CompatibilityModule provider plugin instance
     *
     * @return Provider Plugin instance
     */
    @NotNull String getPluginName();

    /**
     * Calls CompatibilityModule to toggle the detection status for playerb between on and off
     *
     * @param player   The player
     * @param checking On or Off
     */
    void toggle(@NotNull CrossPlatformPlayer player, boolean checking);
}
