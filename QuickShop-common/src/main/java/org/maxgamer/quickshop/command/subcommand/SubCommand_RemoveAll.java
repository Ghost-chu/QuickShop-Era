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
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.shop.Shop;
import org.maxgamer.quickshop.util.MsgUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.maxgamer.quickshop.util.Util.getPlayerList;

@AllArgsConstructor
public class SubCommand_RemoveAll implements CommandProcesser {

    private final QuickShop plugin;

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (cmdArg.length == 1) {
            //copy it first
            List<Shop> tempList = new ArrayList<>(plugin.getShopManager().getAllShops());
            OfflinePlayer shopOwner = null;
            for (OfflinePlayer player : plugin.getServer().getOfflinePlayers()) {
                if (player.getName() != null && player.getName().equalsIgnoreCase(cmdArg[0])) {
                    shopOwner = player;
                    break;
                }
            }
            if (shopOwner == null) {
                MsgUtil.sendMessage(sender, MsgUtil.getMessage("unknown-player", null));
                return;
            }
            int i = 0;
            for (Shop shop : tempList) {
                if (shop.getOwner().equals(shopOwner.getUniqueId())) {
                    plugin.log("Deleting shop " + shop + " request by /qs removeall command.");
                    shop.delete();
                    i++;
                }
            }

            MsgUtil.sendMessage(sender, MsgUtil.getMessage("command.some-shops-removed", sender, Integer.toString(i)));
        } else {
            MsgUtil.sendMessage(sender, MsgUtil.getMessage("command.no-owner-given", sender));
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        return cmdArg.length <= 1 ? getPlayerList() : Collections.emptyList();
    }
}
