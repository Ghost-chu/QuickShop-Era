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
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.util.*;

@AllArgsConstructor
public class SubCommand_Debug implements CommandProcesser {

    private final QuickShop plugin;
    private final List<String> tabCompleteList = Collections.unmodifiableList(Arrays.asList("debug", "dev", "devmode", "handlerlist", "jvm"));

    @Override
    public void onCommand(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (cmdArg.length < 1) {
            switchDebug(sender);
            return;
        }

        switch (cmdArg[0]) {
            case "debug":
            case "dev":
            case "devmode":
                switchDebug(sender);
                break;
            case "handlerlist":
                if (cmdArg.length < 2) {
                    MsgUtil.sendMessage(sender, "You must given a event class");
                    break;
                }

                printHandlerList(sender, cmdArg[1]);
                break;
            case "jvm":
                RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

                List<String> arguments = runtimeMxBean.getInputArguments();
                MsgUtil.sendMessage(sender,
                        ChatColor.GOLD + "Arguments: " + ChatColor.AQUA + Util.list2String(arguments));
                MsgUtil.sendMessage(sender, ChatColor.GOLD + "Name: " + ChatColor.AQUA + runtimeMxBean.getName());
                MsgUtil.sendMessage(sender,
                        ChatColor.GOLD + "VM Name: " + ChatColor.AQUA + runtimeMxBean.getVmName());
                MsgUtil.sendMessage(sender,
                        ChatColor.GOLD + "Uptime: " + ChatColor.AQUA + runtimeMxBean.getUptime());
                MsgUtil.sendMessage(sender,
                        ChatColor.GOLD + "JVM Ver: " + ChatColor.AQUA + runtimeMxBean.getVmVersion());
                Map<String, String> sys = runtimeMxBean.getSystemProperties();
                List<String> sysData = new ArrayList<>();
                sys.keySet().forEach(key -> sysData.add(key + "=" + sys.get(key)));
                MsgUtil.sendMessage(sender,
                        ChatColor.GOLD + "Sys Pro: " + ChatColor.AQUA + Util.list2String(sysData));
                break;
            default:
                MsgUtil.sendMessage(sender, "Error, no correct args given.");
                break;
        }
    }

    @NotNull
    @Override
    public List<String> onTabComplete(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        return tabCompleteList;
    }

    public void switchDebug(@NotNull CommandSender sender) {
        final boolean debug = plugin.getConfig().getBoolean("dev-mode");

        if (debug) {
            plugin.getConfig().set("dev-mode", false);
            plugin.saveConfig();
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            plugin.getServer().getPluginManager().enablePlugin(plugin);
            MsgUtil.sendMessage(sender, MsgUtil.getMessage("command.now-nolonger-debuging", sender));
            return;
        }

        plugin.getConfig().set("dev-mode", true);
        plugin.saveConfig();
        plugin.getServer().getPluginManager().disablePlugin(plugin);
        plugin.getServer().getPluginManager().enablePlugin(plugin);
        MsgUtil.sendMessage(sender, MsgUtil.getMessage("command.now-debuging", sender));
    }

    public void printHandlerList(@NotNull CommandSender sender, String event) {
        try {
            final Class<?> clazz = Class.forName(event);
            final Method method = clazz.getMethod("getHandlerList");
            final Object[] obj = new Object[0];
            final HandlerList list = (HandlerList) method.invoke(null, obj);

            for (RegisteredListener listener1 : list.getRegisteredListeners()) {
                MsgUtil.sendMessage(sender,
                        ChatColor.AQUA
                                + listener1.getPlugin().getName()
                                + ChatColor.YELLOW
                                + " # "
                                + ChatColor.GREEN
                                + listener1.getListener().getClass().getCanonicalName());
            }
        } catch (Exception th) {
            MsgUtil.sendMessage(sender, "ERR " + th.getMessage());
            th.printStackTrace();
        }
    }

}
