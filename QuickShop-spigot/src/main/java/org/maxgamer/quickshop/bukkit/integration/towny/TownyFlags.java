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

package org.maxgamer.quickshop.bukkit.integration.towny;

import java.util.ArrayList;
import java.util.List;

public enum TownyFlags {
    OWN,
    MODIFY,
    SHOPTYPE;

    public static List<TownyFlags> deserialize(List<String> list) {
        List<TownyFlags> result = new ArrayList<>();
        list.forEach(v -> result.add(TownyFlags.valueOf(v.toUpperCase())));
        return result;
    }

    public static List<String> serialize(List<TownyFlags> list) {
        List<String> result = new ArrayList<>();
        list.forEach(v -> result.add(v.name()));
        return result;
    }
}
