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

package org.maxgamer.quickshop.util.updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.nonquickshopstuff.com.sk89q.worldedit.util.net.HttpRequest;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.github.GithubAPI;
import org.maxgamer.quickshop.util.github.ReleaseJsonContainer;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: This is a shit, need refactor
public class Updater {

    private static final Pattern pattern = Pattern.compile("([0-9]*\\.)+[0-9]*");

    /**
     * Check new update
     *
     * @return True=Have a new update; False=No new update or check update failed.
     */
    public synchronized static UpdateInfomation checkUpdate() {
        if (!QuickShop.getInstance().getConfig().getBoolean("updater")) {
            return new UpdateInfomation(false, null);
        }
        try {

            String localPluginVersion = QuickShop.getInstance().getDescription().getVersion();
            String spigotPluginVersion =
                    HttpRequest.get(new URL("https://api.spigotmc.org/legacy/update.php?resource=62575"))
                            .execute()
                            .expectResponseCode(200)
                            .returnContent()
                            .asString("UTF-8")
                            .trim();
            if (!spigotPluginVersion.isEmpty() && !spigotPluginVersion.equals(localPluginVersion)) {
                Util.debugLog(spigotPluginVersion);
                return new UpdateInfomation(
                        spigotPluginVersion.toLowerCase().contains("beta"), spigotPluginVersion);
            }
            return new UpdateInfomation(false, spigotPluginVersion);
        } catch (IOException e) {
            MsgUtil.sendMessage(Bukkit.getConsoleSender(),
                    ChatColor.RED
                            + "[QuickShop] Failed to check for an update on SpigotMC.org! It might be an internet issue or the SpigotMC host is down. If you want disable the update checker, you can disable in config.yml, but we still high-recommend check for updates on SpigotMC.org often.");
            return new UpdateInfomation(false, null);
        }
    }

    public synchronized static byte[] downloadUpdatedJar() throws Exception {
        @Nullable String uurl;
        long uurlSize;
        ReleaseJsonContainer.AssetsBean bean =
                Objects.requireNonNull(new GithubAPI().getLatestRelease());
        uurl = bean.getBrowser_download_url();
        uurlSize = bean.getSize();


        if (uurl == null) {
            throw new IOException("Failed read the URL, cause it is empty.");
        }
        QuickShop.getInstance().getLogger().info("Downloading from " + uurl);

        InputStream is =
                HttpRequest.get(new URL(uurl))
                        .header("User-Agent", "QuickShop-" + QuickShop.getFork() + " " + QuickShop.getVersion())
                        .execute()
                        .getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        long downloaded = 0;
        if (is == null) {
            throw new IOException("Failed downloading: Cannot open connection with remote server.");
        }
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
            downloaded += len;
            Util.debugLog("File Downloader:  " + downloaded + "/" + uurlSize + " bytes.");
        }
        Util.debugLog("Downloaded: " + downloaded + " Server:" + uurlSize);
        if (!(uurlSize < 1) && downloaded != uurlSize) {
            Util.debugLog("Size not match, download may broken.");
            QuickShop.getInstance().getLogger().info("Failed to download update: Size not match, downloaded: " + downloaded + " excepted: " + uurlSize);
            throw new IOException("Size not match, download mayb broken, aborting.");
        }
        Util.debugLog("Download complete.");
        return os.toByteArray();
    }

    public synchronized static void replaceTheJar(byte[] data) throws RuntimeException, IOException {
        File pluginFolder = new File("plugins");
        if (!pluginFolder.exists()) {
            throw new RuntimeException("Can't find the plugins folder.");
        }
        if (!pluginFolder.isDirectory()) {
            throw new RuntimeException("Plugins not a folder.");
        }
        File[] plugins = pluginFolder.listFiles();
        if (plugins == null) {
            throw new IOException("Can't get the files in plugins folder");
        }
        File quickshop = null;
        for (File plugin : plugins) {
            try {
                PluginDescriptionFile desc =
                        QuickShop.getInstance().getPluginLoader().getPluginDescription(plugin);
                if (!desc.getName().equals(QuickShop.getInstance().getDescription().getName())) {
                    continue;
                }
                Util.debugLog("Selected: " + plugin.getPath());
                quickshop = plugin;
                break;
            } catch (InvalidDescriptionException e) { // Ignore }
            }
        }
        if (quickshop == null) {
            throw new RuntimeException("Failed to get QuickShop Jar File.");
        }

        OutputStream outputStream = new FileOutputStream(quickshop, false);
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }

    public synchronized static boolean hasUpdate(String versionRemote) {
        return hasUpdate(QuickShop.getVersion(), versionRemote);
    }

    private static int[] parseStringArray(String[] input, int targetLength) {
        int[] result = new int[targetLength];
        for (int i = 0; i < targetLength; i++) {
            if (i >= input.length) {
                result[i] = 0;
            } else {
                result[i] = Integer.parseInt(input[i]);
            }
        }
        return result;
    }

    public synchronized static boolean hasUpdate(String versionLocal, String versionRemote) {
        if (versionRemote == null) {
            return false;
        }
        if (!versionRemote.equals(versionLocal)) {
            Matcher matcher = pattern.matcher(versionRemote);
            if (matcher.find()) {
                String result = matcher.group(0);
                if (result != null && !result.isEmpty()) {
                    String[] remote = matcher.group(0).split("\\.");
                    String[] local = versionLocal.split("\\.");
                    int maxLength = Math.max(remote.length, local.length);
                    int[] parsedRemote = parseStringArray(remote, maxLength);
                    int[] parsedLocal = parseStringArray(local, maxLength);
                    for (int i = 0; i < parsedRemote.length; i++) {
                        if (parsedLocal[i] != parsedRemote[i]) {
                            return parsedRemote[i] > parsedLocal[i];
                        }
                    }
                }
            }
        } else {
            //equals, so no update
            return false;
        }
        // no recognize, forced update
        return true;
    }
}
