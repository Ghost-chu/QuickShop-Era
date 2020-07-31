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

package org.maxgamer.quickshop.nonquickshopstuff.de.Keyle.MyPet.api.util;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Colorizer {

    private static final Map<String, String> colorCodes = new HashMap<>();

    static {
        for (ChatColor color : ChatColor.values()) {
            colorCodes.put(color.name().replace("_", ""), String.valueOf(color.getChar()));
            colorCodes.put(color.name(), String.valueOf(color.getChar()));
        }
    }

    @Deprecated
    public static String setColors(String text) {
        for (Map.Entry<String, String> color : colorCodes.entrySet()) {
            text = text.replaceAll("(?i)<" + color.getKey() + ">", ChatColor.COLOR_CHAR + color.getValue());
        }
        text = text.replaceAll("(?i)<([0-9a-fk-or])>", ChatColor.COLOR_CHAR + "$1");
        text = ChatColor.translateAlternateColorCodes('&', text);
        return text;
    }

    @Deprecated
    public static String stripColors(String text) {
        for (String color : colorCodes.keySet()) {
            text = text.replaceAll("(?i)<" + color + ">", "");
        }
        text = text.replaceAll("(?i)<[0-9a-fk-or]>", "");
        text = ChatColor.stripColor(text);
        return text;
    }

}
