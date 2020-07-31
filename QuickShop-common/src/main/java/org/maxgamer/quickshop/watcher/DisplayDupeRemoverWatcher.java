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

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.shop.DisplayItem;

import java.util.LinkedList;
import java.util.Queue;

public class DisplayDupeRemoverWatcher extends BukkitRunnable {
    private final Queue<DisplayItem> checkQueue = new LinkedList<>();

    @Override
    public void run() {
        for (DisplayItem displayItem : checkQueue) {
            displayItem.removeDupe();
        }
    }

    public void add(@NotNull DisplayItem displayItem) {
        checkQueue.offer(displayItem);
    }

}
