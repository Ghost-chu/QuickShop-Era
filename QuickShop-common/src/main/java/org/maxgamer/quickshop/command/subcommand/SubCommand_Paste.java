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

package org.maxgamer.quickshop.command.subcommand;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.paste.Paste;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class SubCommand_Paste implements CommandProcesser {

    private final QuickShop plugin;

    @Override
    public void onCommand(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        // do actions
        new BukkitRunnable() {
            @Override
            public void run() {
                MsgUtil.sendMessage(sender, "Please wait, we're uploading the data to the pastebin...");
                final Paste paste = new Paste(plugin);
                final String pasteText = paste.genNewPaste();
                String pasteResult = paste.paste(pasteText);
                if (pasteResult != null) {
                    MsgUtil.sendMessage(sender, pasteResult);
                    plugin.log(pasteResult);
                } else {
                    MsgUtil.sendMessage(sender, "The paste failed, saving the paste at local location...");
                    File file = new File(plugin.getDataFolder(), "paste");
                    file.mkdirs();
                    file =
                            new File(file, "paste-" + UUID.randomUUID().toString().replaceAll("-", "") + ".txt");
                    try {
                        boolean createResult = file.createNewFile();
                        Util.debugLog("Create paste file: " + file.getCanonicalPath() + " " + createResult);

                        FileWriter fwriter = new FileWriter(file);
                        fwriter.write(pasteText);
                        fwriter.flush();
                        fwriter.close();
                        MsgUtil.sendMessage(sender, "Paste was saved to your server at: " + file.getAbsolutePath());
                    } catch (IOException e) {
                        plugin.getSentryErrorReporter().ignoreThrow();
                        e.printStackTrace();
                        MsgUtil.sendMessage(sender, "Saving failed, output to console...");
                        plugin.getLogger().info(pasteText);
                    }
                }
            }
        }.runTaskAsynchronously(plugin);
    }


}
