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

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.shop.Shop;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.data.cache.Cache;

import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class ShopProtectionListener extends ProtectionListenerBase {

    private final boolean useEnhanceProtection;

    private final boolean sendProtectionAlert;

    public ShopProtectionListener(@NotNull QuickShop plugin, @Nullable Cache cache) {
        super(plugin, cache);
        this.sendProtectionAlert = plugin.getConfig().getBoolean("send-shop-protection-alert", false);
        useEnhanceProtection = plugin.getConfig().getBoolean("shop.enchance-shop-protect");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockExplode(BlockExplodeEvent e) {

        for (int i = 0, a = e.blockList().size(); i < a; i++) {
            final Block b = e.blockList().get(i);
            Shop shop = getShopNature(b.getLocation(), true);
            if (shop == null) {
                shop = getShopNextTo(b.getLocation());
            }
            if (shop != null) {
                if (plugin.getConfig().getBoolean("protect.explode")) {
                    e.setCancelled(true);
                } else {
                    plugin.log("Deleting shop " + shop + " request by block break (explode).");
                    shop.delete();
                }
            }
        }
    }

    /**
     * Gets the shop a sign is attached to
     *
     * @param loc The location of the sign
     * @return The shop
     */
    @Nullable
    private Shop getShopNextTo(@NotNull Location loc) {
        final Block b = Util.getAttached(loc.getBlock());
        // Util.getAttached(b)
        if (b == null) {
            return null;
        }

        return getShopNature(b.getLocation(), false);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent e) {
        if (!useEnhanceProtection) {
            return;
        }

        final Shop shop = getShopNature(e.getToBlock().getLocation(), true);

        if (shop == null) {
            return;
        }

        e.setCancelled(true);
    }

    // Protect Redstone active shop
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        if (!useEnhanceProtection) {
            return;
        }

        final Shop shop = getShopRedstone(event.getBlock().getLocation(), true);

        if (shop == null) {
            return;
        }

        event.setNewCurrent(event.getOldCurrent());
        // plugin.getLogger().warning("[Exploit Alert] a Redstone tried to active of " + shop);
        // Util.debugLog(ChatColor.RED + "[QuickShop][Exploit alert] Redstone was activated on the
        // following shop " + shop);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockSpread(BlockSpreadEvent e) {
        if (!useEnhanceProtection) {
            return;
        }

        final Block newBlock = e.getNewState().getBlock();
        final Shop thisBlockShop = getShopNature(newBlock.getLocation(), true);

        if (thisBlockShop == null) {
            return;
        }
        final Shop underBlockShop =
                getShopNature(newBlock.getRelative(BlockFace.DOWN).getLocation(), true);
        if (underBlockShop == null) {
            return;
        }
        e.setCancelled(true);
    }

    /*
     * Handles shops breaking through explosions
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplode(EntityExplodeEvent e) {

        for (int i = 0, a = e.blockList().size(); i < a; i++) {
            final Block b = e.blockList().get(i);
            final Shop shop = getShopNature(b.getLocation(), true);

            if (shop == null) {
                continue;
            }
            if (plugin.getConfig().getBoolean("protect.explode")) {
                e.setCancelled(true);
            } else {
                plugin.log("Deleting shop " + shop + " request by block break (explode).");
                shop.delete();
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onInventoryMove(InventoryMoveItemEvent event) {
        if (!plugin.getConfig().getBoolean("protect.hopper")) {
            return;
        }
        final Location loc = event.getSource().getLocation();

        if (loc == null) {
            return;
        }
        final Shop shop = getShopRedstone(loc, true);

        if (shop == null) {
            return;
        }

        event.setCancelled(true);

        final Location location = event.getInitiator().getLocation();

        if (location == null) {
            return;
        }

        final InventoryHolder holder = event.getInitiator().getHolder();

        if (holder instanceof Entity) {
            ((Entity) holder).remove();
        } else if (holder instanceof Block) {
            location.getBlock().breakNaturally();
        } else {
            Util.debugLog("Unknown location = " + loc);
        }

        if (sendProtectionAlert) {
            MsgUtil.sendGlobalAlert("[DisplayGuard] Defened a item steal action at" + location);
        }
    }

    // Protect Entity pickup shop
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMobChangeBlock(EntityChangeBlockEvent event) {
        if (!useEnhanceProtection) {
            return;
        }

        final Shop shop = getShopNature(event.getBlock().getLocation(), true);

        if (shop == null) {
            return;
        }

        if (plugin.getConfig().getBoolean("protect.entity")) {
            event.setCancelled(true);
            return;
        }
        plugin.log("Deleting shop " + shop + " request by mob changing.");
        shop.delete();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onStructureGrow(StructureGrowEvent event) {
        if (!useEnhanceProtection) {
            return;
        }

        for (BlockState blockstate : event.getBlocks()) {
            final Shop shop = getShopNature(blockstate.getLocation(), true);

            if (shop == null) {
                continue;
            }

            event.setCancelled(true);
            return;
            // plugin.getLogger().warning("[Exploit Alert] a StructureGrowing tried to break the shop of "
            // + shop);
            // Util.sendMessageToOps(ChatColor.RED + "[QuickShop][Exploit alert] A StructureGrowing tried
            // to break the shop of " + shop);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSponging(SpongeAbsorbEvent event) {
        if (!useEnhanceProtection) {
            return;
        }
        List<BlockState> blocks = event.getBlocks();
        for (BlockState block : blocks) {
            if (getShopNature(block.getLocation(), true) != null) {
                event.setCancelled(true);
            }
        }
    }

}
