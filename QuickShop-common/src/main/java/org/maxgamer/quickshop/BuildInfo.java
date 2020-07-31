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

package org.maxgamer.quickshop;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Getter
public class BuildInfo {
    private final String buildId;
    private final String buildTag;
    private final String buildUrl;
    private final String gitCommit;
    private final String gitBranch;
    private final String pomVersion;
    private final String pomGruopId;
    private final String pomArtifactId;

    public BuildInfo(@NotNull QuickShop plugin) {
        InputStream inputStream = plugin.getResource("BUILDINFO");
        if (inputStream == null) {
            buildId = "Unknown";
            buildTag = "Unknown";
            buildUrl = "Unknown";
            gitCommit = "Custom Build";
            gitBranch = "Unknown";
            pomVersion = "Unknown";
            pomGruopId = "Unknown";
            pomArtifactId = "Unknown";
            return;
        }
        YamlConfiguration buildInfo = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
        buildId = buildInfo.getString("build-id", "Unknown");
        buildTag = buildInfo.getString("build-tag", "Unknown");
        buildUrl = buildInfo.getString("build-url", "Unknown");
        gitCommit = buildInfo.getString("git-commit", "Invalid");
        gitBranch = buildInfo.getString("git-branch", "Unknown");
        pomVersion = buildInfo.getString("pom-version", "Unknown");
        pomGruopId = buildInfo.getString("pom-groupid", "Unknown");
        pomArtifactId = buildInfo.getString("pom-artifactid", "Unknown");
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }
    }
}
