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

package org.maxgamer.quickshop.shop;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.crossplatform.type.block.CrossPlatformBlock;
import org.maxgamer.quickshop.crossplatform.type.item.CrossPlatformItemStack;
import org.maxgamer.quickshop.crossplatform.type.location.CrossPlatformLocation;

/**
 * A class contains shop's infomations
 */
@EqualsAndHashCode
@ToString
public class Info {
    private final CrossPlatformBlock last;
    private final CrossPlatformLocation loc;
    private final long lastChangedAt;
    private ShopAction action;
    private CrossPlatformItemStack item;
    private Shop shop;

    public Info(
            @NotNull CrossPlatformLocation loc,
            @NotNull ShopAction action,
            @Nullable CrossPlatformItemStack item,
            @Nullable CrossPlatformBlock last) {
        this.loc = loc;
        this.action = action;
        this.last = last;
        if (item != null) {
            this.item = (CrossPlatformItemStack) item.crossPlatformClone();
        }
        this.lastChangedAt = System.currentTimeMillis();
    }

    public Info(
            @NotNull CrossPlatformLocation loc,
            @NotNull ShopAction action,
            @Nullable CrossPlatformItemStack item,
            @Nullable CrossPlatformBlock last,
            @Nullable Shop shop) {
        this.loc = loc;
        this.action = action;
        this.last = last;
        if (item != null) {
            this.item = (CrossPlatformItemStack) item.crossPlatformClone();
        }
        if (shop != null) {
            this.shop = shop.clone();
            this.lastChangedAt = shop.getLastChangedAt();
        } else {
            this.lastChangedAt = System.currentTimeMillis();
        }
    }

    /**
     * @return ShopAction action, Get shop action.
     */
    public @NotNull ShopAction getAction() {
        return this.action;
    }

    public void setAction(@NotNull ShopAction action) {
        this.action = action;
    }

    /**
     * @return ItemStack iStack, Get Shop's selling/buying item's ItemStack.
     */
    public @NotNull CrossPlatformItemStack getItem() {
        return this.item;
    }

    /*
     * public Material getMaterial(){ return this.item.getType(); } public byte
     * getData(){ return this.getData(); }
     */

    /**
     * @return Location loc, Get shop's location,
     */
    public @NotNull CrossPlatformLocation getLocation() {
        return this.loc;
    }

    /**
     * @return Block signBlock, Get block of shop's sign, may return the null.
     */
    public @Nullable CrossPlatformBlock getSignBlock() {
        return this.last;
    }

    /**
     * Get shop is or not has changed.
     *
     * @param shop, The need checked with this shop.
     * @return hasChanged
     */
    boolean hasChanged(@NotNull Shop shop) {
        if (this.shop.isUnlimited() != shop.isUnlimited()) {
            return true;
        }
        if (this.shop.getShopType() != shop.getShopType()) {
            return true;
        }
        if (!this.shop.getOwner().equals(shop.getOwner())) {
            return true;
        }
        if (this.shop.getPrice() != shop.getPrice()) {
            return true;
        }
        if (!this.shop.getLocation().equals(shop.getLocation())) {
            return true;
        }
        if (this.lastChangedAt != shop.getLastChangedAt()) {
            return false;
        }
        return !this.shop.matches(shop.getItem());
    }

}
