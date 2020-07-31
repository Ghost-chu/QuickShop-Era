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

package org.maxgamer.quickshop.bukkit.util.bukkit.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class EventBusUtil {
    /**
     * Call a event and check it is cancelled.
     *
     * @param event The event implement the Cancellable interface.
     * @return The event is cancelled.
     */
    public static boolean fireCancellableEvent(@NotNull Cancellable event) {
        if (!(event instanceof Event)) {
            throw new IllegalArgumentException("Cancellable must is event implement");
        }
        Bukkit.getPluginManager().callEvent((Event) event);
        return event.isCancelled();
    }
}
