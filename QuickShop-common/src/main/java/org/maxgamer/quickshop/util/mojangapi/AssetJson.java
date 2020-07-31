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

package org.maxgamer.quickshop.util.mojangapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;

public class AssetJson {
    private static final String pathTemplate = "minecraft/lang/{0}.json";

    @NotNull
    private final String gameAssets;

    public AssetJson(@NotNull String json) {
        this.gameAssets = json;
    }

    @Nullable
    public String getLanguageHash(@NotNull String languageCode) {
        languageCode = languageCode.replace("-", "_").toLowerCase().trim();
        JsonObject json = new JsonParser().parse(this.gameAssets).getAsJsonObject();
        if (json == null || json.isJsonNull()) {
            Util.debugLog("Cannot parse the json: " + this.gameAssets);
            return null;
        }
        JsonElement obje = json.get("objects");
        if (obje == null) {
            Util.debugLog("Json element is null for json " + this.gameAssets);
            return null;
        }
        JsonObject objs = obje.getAsJsonObject();
        if (objs == null || objs.isJsonNull()) {
            Util.debugLog("Json object is null.");
            return null;
        }
        JsonObject langObj = objs.getAsJsonObject(MsgUtil.fillArgs(pathTemplate, languageCode));
        if (langObj == null || langObj.isJsonNull()) {
            Util.debugLog("Cannot find request path.");
            Util.debugLog(this.gameAssets);
            return null;
        }
        JsonPrimitive hashObj = langObj.getAsJsonPrimitive("hash");
        if (hashObj == null || hashObj.isJsonNull()) {
            Util.debugLog("Cannot get hash.");
            return null;
        }
        return hashObj.getAsString();
    }

}
