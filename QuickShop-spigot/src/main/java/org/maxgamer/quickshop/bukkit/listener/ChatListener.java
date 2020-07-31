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

package org.maxgamer.quickshop.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.util.Util;

/**
 * @author Netherfoam
 */
public class ChatListener extends QSListener {

    public ChatListener(QuickShop plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent e) {

        if (e.isCancelled() && plugin.getConfig().getBoolean("shop.ignore-cancel-chat-event")) {
            Util.debugLog("Ignored a chat event (Canceled by another plugin.)");
            return;
        }

        if (!plugin.getShopManager().getActions().containsKey(e.getPlayer().getUniqueId())) {
            return;
        }
        // Fix stupid chat plugin will add a weird space before or after the number we want.
        plugin.getShopManager().handleChat(e.getPlayer(), e.getMessage().trim());
        e.setCancelled(true);
    }

}
