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

package org.maxgamer.quickshop.permission;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.crossplatform.type.sender.CrossPlatformCommandSender;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.logger.LoggerUtil;

import java.util.logging.Logger;

@Getter
public class PermissionManager {

    private final PermissionProvider provider;
    private final Logger logger = LoggerUtil.getLogger();

    /**
     * The manager to call permission providers
     *
     * @param plugin Instance
     */
    public PermissionManager() {
        provider = new BukkitPermsProvider();
        logger.info("Selected permission provider: " + provider.getName());
    }

    /**
     * Check the permission for sender
     *
     * @param sender     The CommandSender you want check
     * @param permission The permission node wait to check
     * @return The result of check
     */
    public boolean hasPermission(@NotNull CrossPlatformCommandSender sender, @NotNull String permission) {
        try {
            boolean result = provider.hasPermission(sender, permission);
            if (Util.isDevMode()) {
                try {
                    PermissionInfomationContainer container = provider.getDebugInfo(sender, permission);
                    Util.debugLog("Permission Node: " + container.getPermission() + "; Result: " + result + "; Sender: " + container.getSender().getName());
//                    Util.debugLog("Result: " + result);
//                    Util.debugLog("Sender: " + container.getSender().getName());
//                    Util.debugLog("Permission Node: " + container.getPermission());
//                    //                    Util.debugLog("Primary Group: " + container.getGroupName());
//                    //                    Util.debugLog("Other infos: " + container.getOtherInfos());
                } catch (Exception th) {
                    th.printStackTrace();
                    Util.debugLog("Exception throwed when getting debug messages.");
                }
            }
            return result;
        } catch (Exception th) {
            plugin.getSentryErrorReporter().ignoreThrow();
            th.printStackTrace();
            logger.info(
                    "A error happend, if you believe this is QuickShop problem, please report to us on Issue Tracker or Discord.");
            return false;
        }
    }

}
