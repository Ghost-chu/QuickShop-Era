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

package org.maxgamer.quickshop.util.language.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.bukkit.util.bukkit.nms.ReflectFactory;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.file.Copied;
import org.maxgamer.quickshop.util.mojangapi.AssetJson;
import org.maxgamer.quickshop.util.mojangapi.MojangAPI;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MojangGameLanguageImpl extends BukkitGameLanguageImpl implements GameLanguage {
    private final QuickShop plugin;
    private @Nullable
    final JsonObject lang;

    @SneakyThrows
    public MojangGameLanguageImpl(@NotNull QuickShop plugin, @NotNull String languageCode) {
        super(plugin);
        this.plugin = plugin;
        final GameLanguageLoadThread loadThread = new GameLanguageLoadThread();
        languageCode = languageCode.replace("-", "_");
        loadThread.setLanguageCode(languageCode.toLowerCase());
        loadThread.setMainThreadWaiting(true); // Told thread we're waiting it
        loadThread.setPlugin(plugin); // Transfer instance
        loadThread.start();
        int count = 0;
        while (count < 7) {
            if (loadThread.isAlive()) {
                count++;
                Thread.sleep(1000);
                if (count >= 20) {
                    Util.debugLog(
                            "No longer waiting file downloading because it now timed out, now downloading in background.");
                    plugin
                            .getLogger()
                            .info(
                                    "No longer waiting file downloading because it now timed out, now downloading in background, please reset itemi18n.yml, potioni18n.yml and enchi18n.yml after download completed.");
                }
            } else {
                break;
            }
        }
        this.lang = loadThread.getLang(); // Get the Lang whatever thread running or died.
        loadThread.setMainThreadWaiting(
                false); // Told thread it now move to background, thread should told user reset files.

    }

    @Override
    public @NotNull String getName() {
        return "Mojang API";
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    @Override
    public @NotNull String getItem(@NotNull ItemStack itemStack) {
        return getItem(itemStack.getType());
    }

    @Override
    public @NotNull String getItem(@NotNull Material material) {
        if (lang == null) {
            return super.getItem(material);
        }
        JsonElement element = lang.get("item.minecraft." + material.name().toLowerCase());
        if (element == null) {
            return getBlock(material);
        } else {
            return element.getAsString();
        }
    }

    /**
     * Get block only translations, if not found, it WON'T call getItem()
     *
     * @param material The material
     * @return The translations for material
     */
    @NotNull
    public String getBlock(@NotNull Material material) {
        if (lang == null) {
            return super.getItem(material);
        }
//        try {
//            return lang.get("block.minecraft." + material.name().toLowerCase()).getAsString();
//        } catch (NullPointerException e) {
//            return super.getItem(material);
//        }

        JsonElement jsonElement = lang.get("block.minecraft." + material.name().toLowerCase());
        if (jsonElement == null) {
            return super.getItem(material);
        }
        return jsonElement.getAsString();
    }

    @Override
    public @NotNull String getPotion(@NotNull PotionEffectType potionEffectType) {
        if (lang == null) {
            return super.getPotion(potionEffectType);
        }
//        try {
//            return lang.get("effect.minecraft." + potionEffectType.getName().toLowerCase()).getAsString();
//        } catch (NullPointerException e) {
//            return super.getPotion(potionEffectType);
//        }
        JsonElement jsonElement = lang.get("effect.minecraft." + potionEffectType.getName().toLowerCase());
        if (jsonElement == null) {
            return super.getPotion(potionEffectType);
        }
        return jsonElement.getAsString();
    }

    @Override
    public @NotNull String getEnchantment(@NotNull Enchantment enchantment) {
        if (lang == null) {
            return super.getEnchantment(enchantment);
        }
        JsonElement jsonElement = lang.get("enchantment.minecraft." + enchantment.getKey().getKey().toLowerCase());
        if (jsonElement == null) {
            return super.getEnchantment(enchantment);
        }
        return jsonElement.getAsString();

    }

    @Override
    public @NotNull String getEntity(@NotNull EntityType entityType) {
        if (lang == null) {
            return super.getEntity(entityType);
        }
//        try {
//            return lang.get("entity.minecraft." + entityType.name().toLowerCase()).getAsString();
//        } catch (NullPointerException e) {
//            return super.getEntity(entityType);
//        }
        JsonElement jsonElement = lang.get("entity.minecraft." + entityType.name().toLowerCase());
        if (jsonElement == null) {
            return super.getEntity(entityType);
        }
        return jsonElement.getAsString();
    }
}

@Getter
@Setter
//TODO Refactor: After two months what i write it and now i can't understand these code...
class GameLanguageLoadThread extends Thread {
    private JsonObject lang;

    private String languageCode;

    private boolean mainThreadWaiting;

    private QuickShop plugin;

    public void run() {
        boolean failed = false;
        try {
            File cacheFile = new File(Util.getCacheFolder(), "lang.cache"); // Load cache file
            if (!cacheFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                cacheFile.createNewFile();
            }
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.load(new File(Util.getCacheFolder(), "lang.cache"));
            boolean needUpdateCache = false;
            /* The cache data, if it all matches, we doesn't need connect to internet to download files again. */
            String cachingServerVersion = yamlConfiguration.getString("ver");
            String cachingLanguageHash = yamlConfiguration.getString("hash");
            String cachingLanguageName = yamlConfiguration.getString("lang");
            /* If language name is default, use computer language */
            if ("default".equals(languageCode)) {
                Locale locale = Locale.getDefault();
                languageCode = locale.getLanguage() + "_" + locale.getCountry();
            }
            if (!languageCode.equals(cachingLanguageName)) {
                cachingLanguageName = languageCode;
                needUpdateCache = true;
            }
            String languageCode1 = languageCode.toLowerCase();
            String serverVersion = ReflectFactory.getServerVersion();
            if (!serverVersion.equals(cachingServerVersion)) {
                cachingServerVersion = serverVersion;
                needUpdateCache = true;
            }
            if (cachingLanguageHash == null || cachingLanguageHash.isEmpty()) {
                needUpdateCache = true;
            }
            if (needUpdateCache) {
                MojangAPI mojangAPI = new MojangAPI(plugin);
                String assetJson = mojangAPI.getAssetIndexJson(cachingServerVersion);
                if (assetJson != null) {
                    AssetJson versionJson = new AssetJson(assetJson);
                    String hash = versionJson.getLanguageHash(languageCode1);
                    if (hash == null) {
                        plugin
                                .getLogger()
                                .warning("Cannot get file hash for language " + languageCode1 + ", fallback to en_us...");
                        languageCode1 = "en_us";
                        hash = versionJson.getLanguageHash(languageCode1);
                    }
                    if (hash != null) {
                        cachingLanguageHash = hash;
                        String langJson = mojangAPI.downloadTextFileFromMojang(hash);
                        if (langJson != null) {
                            new Copied(new File(Util.getCacheFolder(), hash))
                                    .accept(new ByteArrayInputStream(langJson.getBytes(StandardCharsets.UTF_8)));
                        } else {
                            Util.debugLog("Cannot download file.");
                            plugin
                                    .getLogger()
                                    .warning(
                                            "Cannot download require files, some items/blocks/potions/enchs language will use default English name.");
                            failed = true;
                        }
                    } else {
                        Util.debugLog("Cannot get file hash for language " + languageCode1);
                        plugin
                                .getLogger()
                                .warning(
                                        "Cannot download require files, some items/blocks/potions/enchs language will use default English name.");
                        failed = true;
                    }
                } else {
                    Util.debugLog("Cannot get version json.");
                    plugin
                            .getLogger()
                            .warning(
                                    "Cannot download require files, some items/blocks/potions/enchs language will use default English name.");
                    failed = true;
                }
            }
            yamlConfiguration.set("ver", cachingServerVersion);
            yamlConfiguration.set("hash", cachingLanguageHash);
            yamlConfiguration.set("lang", cachingLanguageName);
            yamlConfiguration.save(cacheFile);
            String json = null;
            if (cachingLanguageHash != null) {
                json = Util.readToString(new File(Util.getCacheFolder(), cachingLanguageHash));
            } else {
                Util.debugLog("Caching LanguageHash is null");
            }
            if (json != null && !json.isEmpty()) {
                lang = new JsonParser().parse(json).getAsJsonObject();
            } else {
                Util.debugLog("json is null");
            }
        } catch (Exception e) {
            plugin.getSentryErrorReporter().ignoreThrow();
            e.printStackTrace();
            failed = true;
        }
        if (!this.mainThreadWaiting) {

            if (!failed) {

                plugin
                        .getLogger()
                        .info(
                                "Download completed, please execute /qs reset lang to generate localized language files.");
            } else {
                plugin
                        .getLogger()
                        .info(
                                "Failed to download required files, we will try again when plugin next loading.");
            }
        }
    }

}
