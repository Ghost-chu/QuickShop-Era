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
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;

@AllArgsConstructor
public class SubCommand_About implements CommandProcesser {
    private final QuickShop plugin;

    @Override
    public void onCommand(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        MsgUtil.sendMessage(sender,
                ChatColor.AQUA + "QuickShop " + ChatColor.YELLOW + QuickShop.getFork());
        MsgUtil.sendMessage(sender,
                ChatColor.AQUA
                        + "Ver "
                        + ChatColor.YELLOW
                        + ">> "
                        + ChatColor.GREEN
                        + QuickShop.getVersion());
        if (QuickShop.getVersion().toUpperCase().contains("LTS")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.lts", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("STABLE")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.stable", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("QV")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.qualityverifyed", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("BETA")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.unstable", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("ALPHA")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.unstable", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("EARLY ACCESS")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.unstable", sender));
        } else if (QuickShop.getVersion().toUpperCase().contains("SNAPSHOT")) {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA
                            + "Release "
                            + ChatColor.YELLOW
                            + ">> "
                            + ChatColor.GREEN
                            + MsgUtil.getMessage("updatenotify.label.unstable", sender));
        } else {
            MsgUtil.sendMessage(sender,
                    ChatColor.AQUA + "Release " + ChatColor.YELLOW + ">> " + ChatColor.GREEN + "[Main Line]");
        }
        MsgUtil.sendMessage(sender,
                ChatColor.AQUA
                        + "Dev "
                        + ChatColor.YELLOW
                        + ">> "
                        + ChatColor.GREEN
                        + Util.list2String(plugin.getDescription().getAuthors()));
        MsgUtil.sendMessage(sender, ChatColor.GOLD + "Powered by Bukkit Common Studio");
        MsgUtil.sendMessage(sender, ChatColor.RED + "Made with ❤");
    }


}
