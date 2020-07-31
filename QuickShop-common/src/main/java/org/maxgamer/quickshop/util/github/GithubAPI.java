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

package org.maxgamer.quickshop.util.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.nonquickshopstuff.com.sk89q.worldedit.util.net.HttpRequest;

import java.io.IOException;
import java.net.URL;

public class GithubAPI {
    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Nullable
    public ReleaseJsonContainer.AssetsBean getLatestRelease() throws IOException {
        String json =
                HttpRequest.get(
                        new URL(
                                "https://api.github.com/repos/Ghost-chu/QuickShop-Reremake/releases/latest"))
                        .execute()
                        .returnContent()
                        .asString("UTF-8");
        ReleaseJsonContainer result = gson.fromJson(json, ReleaseJsonContainer.class);
        for (ReleaseJsonContainer.AssetsBean asset : result.getAssets()) {
            if (asset.getName().contains("original-")) {
                continue;
            }
            if (asset.getName().contains("-javadoc")) {
                continue;
            }
            if (asset.getName().contains("-sources")) {
                continue;
            }
            if (asset.getName().contains("-shaded")) {
                continue;
            }
            if (asset.getName().contains("Document") || asset.getName().contains("document")) {
                continue;
            }
            return asset;
        }
        return null;
    }

}
