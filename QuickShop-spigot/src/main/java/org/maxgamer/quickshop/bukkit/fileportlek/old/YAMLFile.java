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

package org.maxgamer.quickshop.bukkit.fileportlek.old;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStreamReader;

public class YAMLFile extends FileEnvelope {

    public YAMLFile(@NotNull final Plugin plugin, @NotNull final File file, @NotNull final String resourcePath, boolean loadDefault) {
        super(plugin, file, resourcePath.endsWith(".yml") ? resourcePath : resourcePath + ".yml", loadDefault);
    }

    public YAMLFile(@NotNull final Plugin plugin, @NotNull final String fileName) {
        this(plugin, "", fileName);
    }

    public YAMLFile(@NotNull final Plugin plugin, @NotNull final String resourcePath, @NotNull final String fileName) {
        this(plugin, new File(plugin.getDataFolder().getAbsolutePath() + (resourcePath.startsWith("/") ? resourcePath : "/" + resourcePath), fileName.endsWith(".yml") ? fileName : fileName + ".yml"), resourcePath.isEmpty() ? fileName : resourcePath.endsWith("/") ? resourcePath + fileName : resourcePath + "/" + fileName);
    }

    public YAMLFile(@NotNull final Plugin plugin, @NotNull final File file, @NotNull final String resourcePath) {
        super(plugin, file, resourcePath.endsWith(".yml") ? resourcePath : resourcePath + ".yml", true);
    }

    @Override
    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadDefault) {
            fileConfiguration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(getInputStream())));
        }
    }

}
