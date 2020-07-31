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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.crossplatform.type.block.CrossPlatformBlock;
import org.maxgamer.quickshop.crossplatform.type.entity.CrossPlatformPlayer;
import org.maxgamer.quickshop.crossplatform.type.item.CrossPlatformItemStack;
import org.maxgamer.quickshop.crossplatform.type.location.CrossPlatformLocation;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Shop {
    /**
     * Add x ItemStack to the shop inventory
     *
     * @param paramItemStack The ItemStack you want add
     * @param paramInt       How many you want add
     */
    void add(CrossPlatformItemStack paramItemStack, int paramInt);

    /**
     * Add new staff to the moderators
     *
     * @param player New staff
     * @return Success
     */
    boolean addStaff(UUID player);

    /**
     * Execute buy action for player with x items.
     *
     * @param paramPlayer Target player
     * @param paramInt    How many buyed?
     */
    void buy(CrossPlatformPlayer paramPlayer, int paramInt);

    /**
     * Check the display location, and teleport, respawn if needs.
     */
    void checkDisplay();

    /**
     * Empty moderators team.
     */
    void clearStaffs();

    /**
     * Clone new shop object. Not a deep clone.
     *
     * @return New shop object
     */
    @NotNull
    Shop clone();

    /**
     * Remove a staff from moderators
     *
     * @param player Staff
     * @return Success
     */
    boolean delStaff(UUID player);

    /**
     * Delete shop from ram, and database.
     */
    void delete();

    /**
     * Delete shop from ram or ram and database
     *
     * @param memoryOnly true = only delete from ram, false = delete from both ram and database
     */
    void delete(boolean memoryOnly);

//    /**
//     * Check shop is or not attacked the target block
//     *
//     * @param paramBlock Target block
//     * @return isAttached
//     */
//    boolean isAttached(CrossPla paramBlock);

    /**
     * Check the target ItemStack is matches with this shop's item.
     *
     * @param paramItemStack Target ItemStack.
     * @return Matches
     */
    boolean matches(CrossPlatformItemStack paramItemStack);

    /**
     * Execute codes when player click the shop will did things
     */
    void onClick();

    /**
     * Load shop to the world
     */
    void onLoad();

    /**
     * Unload shop from world
     */
    void onUnload();

    /**
     * Get shop's owner name, it will return owner name or Admin Shop(i18n) when it is unlimited
     *
     * @param forceUsername Force returns username of shop
     * @return owner name
     */
    @NotNull
    String ownerName(boolean forceUsername);

    /**
     * Get shop's owner name, it will return owner name or Admin Shop(i18n) when it is unlimited
     *
     * @return owner name
     */
    @NotNull
    String ownerName();

    /**
     * Remove x ItemStack from the shop inventory
     *
     * @param paramItemStack Want removed ItemStack
     * @param paramInt       Want remove how many
     */
    void remove(CrossPlatformItemStack paramItemStack, int paramInt);

    /**
     * Execute sell action for player with x items.
     *
     * @param paramPlayer Target player
     * @param paramInt    How many sold?
     */
    void sell(CrossPlatformPlayer paramPlayer, int paramInt);

    /**
     * Generate new sign texts on shop's sign.
     */
    void setSignText();

    /**
     * Update shop data to database
     */
    void update();

    /**
     * Get shop's item durability, if have.
     *
     * @return Shop's item durability
     */
    short getDurability();

    /**
     * Get shop item's ItemStack
     *
     * @return The shop's ItemStack
     */
    @NotNull
    CrossPlatformItemStack getItem();

    /**
     * Set shop item's ItemStack
     *
     * @param item ItemStack to set
     */
    void setItem(@NotNull CrossPlatformItemStack item);

    /**
     * Refresh shop sign and display item
     */
    void refresh();

    /**
     * Set texts on shop's sign
     *
     * @param paramArrayOfString The texts you want set
     */
    void setSignText(String[] paramArrayOfString);

    /**
     * Get shop's location
     *
     * @return Shop's location
     */
    @NotNull
    CrossPlatformLocation getLocation();

    /**
     * Return this shop's moderators
     *
     * @return Shop moderators
     */
    @NotNull
    ShopModerator getModerator();

    /**
     * Set new shop's moderators
     *
     * @param shopModerator New moderators team you want set
     */
    void setModerator(@NotNull ShopModerator shopModerator);

    /**
     * Get shop's owner UUID
     *
     * @return Shop's owner UUID, can use Bukkit.getOfflinePlayer to convert to the OfflinePlayer.
     */
    @NotNull
    UUID getOwner();

    /**
     * Set new owner to the shop's owner
     *
     * @param paramString New owner UUID
     */
    void setOwner(UUID paramString);

    /**
     * Get shop's price
     *
     * @return Price
     */
    double getPrice();

    /**
     * Set shop's new price
     *
     * @param paramDouble New price
     */
    void setPrice(double paramDouble);

    /**
     * Get shop remaining space.
     *
     * @return Remaining space.
     */
    int getRemainingSpace();

    /**
     * Get shop remaining stock.
     *
     * @return Remaining stock.
     */
    int getRemainingStock();

    /**
     * Get shop type
     *
     * @return shop type
     */
    @NotNull
    ShopType getShopType();

    /**
     * Set new shop type for this shop
     *
     * @param paramShopType New shop type
     */
    void setShopType(ShopType paramShopType);

    /**
     * Get shop signs, may have multi signs
     *
     * @return Signs for the shop
     */
    @NotNull
    List<CrossPlatformBlock> getSigns();

    /**
     * Directly get all staffs.
     *
     * @return staffs
     */
    @NotNull
    List<UUID> getStaffs();

    /**
     * Get shop is or not in buying mode
     *
     * @return yes or no
     */
    boolean isBuying();

    /**
     * Get this container shop is loaded or unloaded.
     *
     * @return Loaded
     */
    boolean isLoaded();

    /**
     * Get shop is or not in selling mode
     *
     * @return yes or no
     */
    boolean isSelling();

    /**
     * Get shop is or not in Unlimited Mode (Admin Shop)
     *
     * @return yes or not
     */
    boolean isUnlimited();

    /**
     * Set shop is or not Unlimited Mode (Admin Shop)
     *
     * @param paramBoolean status
     */
    void setUnlimited(boolean paramBoolean);

    /**
     * Whether Shop is valid
     *
     * @return status
     */
    boolean isValid();

    /**
     * Whether Shop is deleted
     *
     * @return status
     */
    boolean isDeleted();

    /**
     * Get the shop display entity
     *
     * @return The entity for shop display.
     */
    @Nullable
    DisplayItem getDisplay();

    /**
     * Gets the shop last changes timestamp
     *
     * @return The time stamp
     */
    long getLastChangedAt();

    /**
     * Save the plugin extra data to Json format
     *
     * @return The json string
     */
    @NotNull
    String saveExtraToJson();

    /**
     * Gets the plugin's k-v map to storage the data.
     * It is spilt by plugin name, different name have different map, the data won't conflict.
     * But if you plugin name is too common, add a prefix will be a good idea.
     *
     * @param pluginName Plugin name
     * @return The data table
     */
    @NotNull
    Map<String, String> getExtra(@NotNull String pluginName);

    /**
     * Save the extra data to the shop.
     *
     * @param pluginName Plugin name
     * @param data       The data table
     */
    void setExtra(@NotNull String pluginName, Map<String, String> data);

    /**
     * Gets shop status is stacking shop
     *
     * @return The shop stacking status
     */
    boolean isStackingShop();


}
