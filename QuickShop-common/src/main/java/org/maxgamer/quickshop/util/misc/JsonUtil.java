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

package org.maxgamer.quickshop.util.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtil {

    private static final Gson gson = new Gson();
    private static final Gson outputGson = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson humanReadableGson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private JsonUtil() {
    }

    public static Gson getGson() {
        return gson;
    }

    public static Gson getOutputGson() {
        return outputGson;
    }

    public static Gson getHumanReadableGson() {
        return humanReadableGson;
    }


}
