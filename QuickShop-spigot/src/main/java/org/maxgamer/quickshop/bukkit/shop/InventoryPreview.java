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

package org.maxgamer.quickshop.bukkit.shop;

import com.google.common.collect.Lists;
import com.sk89q.worldedit.entity.Player;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.bukkit.event.ShopInventoryPreviewEvent;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.holder.QuickShopPreviewInventoryHolder;

/**
 * A class to create a GUI item preview quickly
 */
@EqualsAndHashCode
@ToString
public class InventoryPreview implements Listener {

    private final ItemStack itemStack;
    private final com.sk89q.worldedit.entity.Player player;
    @Nullable
    private Inventory inventory;

    /**
     * Create a preview item GUI for a player.
     *
     * @param itemStack The item you want create.
     * @param player    Target player.
     * @param plugin    The plugin instance.
     */
    public InventoryPreview(@NotNull QuickShop plugin, @NotNull ItemStack itemStack, @NotNull Player player) {
        this.itemStack = itemStack.clone();
        this.player = player;
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Lists.newArrayList(plugin.getPreviewProtectionLore()));
        this.itemStack.setItemMeta(itemMeta);
    }

    @Deprecated
    public static boolean isPreviewItem(@Nullable ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!stack.hasItemMeta() || !stack.getItemMeta().hasLore()) {
            return false;
        }
        for (String string : stack.getItemMeta().getLore()) {
            if (QuickShop.instance.getPreviewProtectionLore().equals(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Open the preview GUI for player.
     */
    public void show() {
        if (inventory != null) // Not inited
        {
            close();
        }
        if (itemStack == null) // Null pointer exception
        {
            return;
        }
        if (player == null) // Null pointer exception
        {
            return;
        }
        if (player.isSleeping()) // Bed bug
        {
            return;
        }
        ShopInventoryPreviewEvent shopInventoryPreview =
                new ShopInventoryPreviewEvent(player, itemStack);
        Bukkit.getPluginManager().callEvent(shopInventoryPreview);
        if (shopInventoryPreview.isCancelled()) {
            Util.debugLog("Inventory preview was canceled by a plugin.");
            return;
        }
        final int size = 9;
        inventory = Bukkit.createInventory(new QuickShopPreviewInventoryHolder(), size, MsgUtil.getMessage("menu.preview", player));
        for (int i = 0; i < size; i++) {
            inventory.setItem(i, itemStack);
        }
        player.openInventory(inventory);
    }

    public void close() {
        if (inventory == null) {
            return;
        }
        for (HumanEntity player : inventory.getViewers()) {
            player.closeInventory();
        }
        inventory = null; // Destory
    }

}
