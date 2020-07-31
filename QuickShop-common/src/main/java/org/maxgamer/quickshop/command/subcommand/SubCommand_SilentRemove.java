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
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.shop.Shop;
import org.maxgamer.quickshop.util.MsgUtil;

@AllArgsConstructor
public class SubCommand_SilentRemove implements CommandProcesser {

    private final QuickShop plugin;

    @Override
    public void onCommand(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (cmdArg.length < 4) {
            return;
        }

        final Player p = (Player) sender;
        final Shop shop =
                plugin
                        .getShopManager()
                        .getShop(
                                new Location(
                                        plugin.getServer().getWorld(cmdArg[0]),
                                        Integer.parseInt(cmdArg[1]),
                                        Integer.parseInt(cmdArg[2]),
                                        Integer.parseInt(cmdArg[3])));

        if (shop == null) {
            MsgUtil.sendMessage(sender, MsgUtil.getMessage("not-looking-at-shop", sender));
            return;
        }

        if (!shop.getModerator().isModerator(p.getUniqueId())
                && !QuickShop.getPermissionManager().hasPermission(sender, "quickshop.other.destroy")) {
            MsgUtil.sendMessage(sender, ChatColor.RED + MsgUtil.getMessage("no-permission", sender));
            return;
        }

        //shop.onUnload();
        plugin.log("Deleting shop " + shop + " request by /qs silentremove (control panel) command.");
        shop.delete();
    }
}
