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

import com.griefcraft.lwc.LWCPlugin;
import com.griefcraft.model.Protection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.bukkit.event.ProtectionCheckStatus;
import org.maxgamer.bukkit.event.ShopProtectionCheckEvent;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.holder.Result;
import org.primesoft.blockshub.BlocksHubBukkit;

public class PermissionChecker {
    private final QuickShop plugin;

    private final boolean usePermissionChecker;

    public PermissionChecker(@NotNull QuickShop plugin) {
        this.plugin = plugin;
        usePermissionChecker = this.plugin.getConfig().getBoolean("shop.protection-checking");
    }

    /**
     * Check player can build in target location
     *
     * @param player   Target player
     * @param location Target location
     * @return Result represent if you can build there
     */
    public Result canBuild(@NotNull Player player, @NotNull Location location) {
        return canBuild(player, location.getBlock());
    }

    /**
     * Check player can build in target block
     *
     * @param player Target player
     * @param block  Target block
     * @return Result represent if you can build there
     */
    public Result canBuild(@NotNull Player player, @NotNull Block block) {

        if (plugin.getLwcPlugin() != null) {
            LWCPlugin lwc = (LWCPlugin) plugin.getLwcPlugin();
            Protection protection = lwc.getLWC().findProtection(block.getLocation());
            if (protection != null && !protection.isOwner(player)) {
                Util.debugLog("LWC reporting player no permission to access this block.");
                return new Result("LWC");
            }

        }

        if (plugin.getBlockHubPlugin() != null) {
            BlocksHubBukkit blocksHubBukkit = (BlocksHubBukkit) plugin.getBlockHubPlugin();
            boolean bhCanBuild = blocksHubBukkit.getApi().hasAccess(player.getUniqueId(), blocksHubBukkit.getApi().getWorld(block.getWorld().getName()), block.getX(), block.getY(), block.getZ());
            if (plugin.getConfig().getBoolean("plugin.BlockHub.only")) {
                Util.debugLog("BlockHub only mode response: " + bhCanBuild);
                return new Result("BlockHub");
            } else {
                if (!bhCanBuild) {
                    Util.debugLog("BlockHub reporting player no permission to access this region.");
                    return new Result("BlockHub");
                }
            }
        }
        if (!usePermissionChecker) {
            return Result.SUCCESS;
        }
        final Result isCanBuild = new Result();

        BlockBreakEvent beMainHand;
        // beMainHand = new BlockPlaceEvent(block, block.getState(), block.getRelative(0, -1, 0),
        // player.getInventory()
        // getItemInMainHand(), player, true, EquipmentSlot.HAND);

        beMainHand = new BlockBreakEvent(block, player) {

            @Override
            public void setCancelled(boolean cancel) {
                //tracking cancel plugin
                if (cancel && !isCancelled()) {
                    Util.debugLog("An plugin blocked the protection checking event! See this stacktrace:");
                    for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
                        Util.debugLog(element.getClassName() + element.getMethodName() + element.getLineNumber());
                    }
                    isCanBuild.setMessage(Thread.currentThread().getStackTrace()[2].getClassName());
                    out:
                    for (StackTraceElement element : Thread.currentThread().getStackTrace()) {

                        for (RegisteredListener listener : getHandlerList().getRegisteredListeners()) {
                            if (listener.getListener().getClass().getName().equals(element.getClassName())) {
                                isCanBuild.setResult(false);
                                isCanBuild.setMessage(listener.getPlugin().getName());
                                break out;
                            }
                        }
                    }
                }
                super.setCancelled(cancel);
            }
        };
        // Call for event for protection check start
        Bukkit.getPluginManager()
                .callEvent(
                        new ShopProtectionCheckEvent(
                                block.getLocation(), player, ProtectionCheckStatus.BEGIN, beMainHand));
        beMainHand.setDropItems(false);
        beMainHand.setExpToDrop(0);

        //register a listener to cancel test event
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler(priority = EventPriority.HIGHEST)
            public void onTestEvent(BlockBreakEvent event) {
                if (event.equals(beMainHand)) {
                    // Call for event for protection check end
                    Bukkit.getPluginManager().callEvent(
                            new ShopProtectionCheckEvent(
                                    block.getLocation(), player, ProtectionCheckStatus.END, beMainHand));
                    if (!event.isCancelled()) {
                        //Ensure this test will no be logged by some plugin
                        beMainHand.setCancelled(true);
                        isCanBuild.setResult(true);
                    }
                    HandlerList.unregisterAll(this);
                }
            }
        }, plugin);
        plugin.getCompatibilityTool().toggleProtectionListeners(false, player);
        Bukkit.getPluginManager().callEvent(beMainHand);
        plugin.getCompatibilityTool().toggleProtectionListeners(true, player);

        return isCanBuild;
    }

}
