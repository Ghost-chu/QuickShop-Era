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

package org.maxgamer.quickshop.crossplatform;

public enum Platform {
    UNKNOWN(-1, "unknown"),
    BUKKIT(0, "bukkit");

    private final int id;
    private final String name;

    Platform(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Platform fromId(int id) {
        for (Platform platform : Platform.values()) {
            if (platform.id == id) {
                return platform;
            }
        }
        return Platform.UNKNOWN;
    }

    public Platform fromName(String name) {
        for (Platform platform : Platform.values()) {
            if (platform.name.equalsIgnoreCase(name)) {
                return platform;
            }
        }
        return Platform.UNKNOWN;
    }
}
