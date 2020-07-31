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

package org.maxgamer.quickshop.util.debug;

import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DebugUtil {
    private static final List<String> debugLogs = Collections.synchronizedList(new LinkedList<>());
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static boolean devMode;
    private static boolean disableDebugLogger;

    static {
        //Load up the default values if this util calls before setup.
        devMode = false;
        disableDebugLogger = false;
    }

    /**
     * Initialize the DebugUtil and settings.
     *
     * @param _devMode            DevMode is enabled.
     * @param _disableDebugLogger DebugLogger is disabled.
     */
    public static void init(boolean _devMode, boolean _disableDebugLogger) {
        devMode = _devMode;
        disableDebugLogger = _disableDebugLogger;
    }

    /**
     * Print debug log when plugin running on dev mode.
     *
     * @param logs logs
     */
    public static void debugLog(@NotNull String... logs) {
        if (disableDebugLogger) {
            return;
        }
        lock.writeLock().lock();
        if (debugLogs.size() >= 2000) {
            debugLogs.clear();
        }
        if (!devMode) {
            for (String log : logs) {
                debugLogs.add("[DEBUG] " + log);
            }
            lock.writeLock().unlock();
            return;
        }
        final StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        final String className = stackTraceElement.getClassName();
        final String methodName = stackTraceElement.getMethodName();
        final int codeLine = stackTraceElement.getLineNumber();
        for (String log : logs) {
            debugLogs.add("[DEBUG] [" + className + "] [" + methodName + "] (" + codeLine + ") " + log);
            QuickShop.getInstance().getLogger().info("[DEBUG] [" + className + "] [" + methodName + "] (" + codeLine + ") " + log);
        }
        lock.writeLock().unlock();
    }

    /**
     * Get the plugin is under dev-mode(debug mode)
     *
     * @return under dev-mode
     */
    public static boolean isDevMode() {
        return devMode;
    }

    @NotNull
    public List<String> getDebugLogs() {
        lock.readLock().lock();
        List<String> strings = new ArrayList<>(debugLogs);
        lock.readLock().unlock();
        return strings;
    }

}
