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

package org.maxgamer.quickshop.bukkit.integration.towny;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.utils.ShopPlotUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.bukkit.integration.IntegrateStage;
import org.maxgamer.quickshop.bukkit.integration.IntegratedPlugin;
import org.maxgamer.quickshop.bukkit.integration.IntegrationStage;
import org.maxgamer.quickshop.util.Util;

import java.util.List;

@SuppressWarnings("DuplicatedCode")
@IntegrationStage(loadStage = IntegrateStage.onEnableAfter)
public class TownyIntegration implements IntegratedPlugin {
    private final List<TownyFlags> createFlags;

    private final List<TownyFlags> tradeFlags;

    private final boolean ignoreDisabledWorlds;

    public TownyIntegration(QuickShop plugin) {
        createFlags =
                TownyFlags.deserialize(plugin.getConfig().getStringList("integration.towny.create"));
        tradeFlags =
                TownyFlags.deserialize(plugin.getConfig().getStringList("integration.towny.trade"));
        ignoreDisabledWorlds = plugin.getConfig().getBoolean("integration.towny.ignore-disabled-worlds");
    }

    @Override
    public @NotNull String getName() {
        return "Towny";
    }

    @Override
    public boolean canCreateShopHere(@NotNull Player player, @NotNull Location location) {
        if (ignoreDisabledWorlds && !TownyAPI.getInstance().isTownyWorld(location.getWorld())) {
            Util.debugLog("This world disabled Towny.");
            return true;
        }
        for (TownyFlags flag : createFlags) {
            switch (flag) {
                case OWN:
                    if (!ShopPlotUtil.doesPlayerOwnShopPlot(player, location)) {
                        return false;
                    }
                    break;
                case MODIFY:
                    if (!ShopPlotUtil.doesPlayerHaveAbilityToEditShopPlot(player, location)) {
                        return false;
                    }
                    break;
                case SHOPTYPE:
                    if (!ShopPlotUtil.isShopPlot(location)) {
                        return false;
                    }
                default:
                    // Ignore
            }
        }
        return true;
    }

    @Override
    public boolean canTradeShopHere(@NotNull Player player, @NotNull Location location) {
        if (ignoreDisabledWorlds && !TownyAPI.getInstance().isTownyWorld(location.getWorld())) {
            Util.debugLog("This world disabled Towny.");
            return true;
        }
        for (TownyFlags flag : tradeFlags) {
            switch (flag) {
                case OWN:
                    if (!ShopPlotUtil.doesPlayerOwnShopPlot(player, location)) {
                        return false;
                    }
                    break;
                case MODIFY:
                    if (!ShopPlotUtil.doesPlayerHaveAbilityToEditShopPlot(player, location)) {
                        return false;
                    }
                    break;
                case SHOPTYPE:
                    if (!ShopPlotUtil.isShopPlot(location)) {
                        return false;
                    }
                default:
                    // Ignore
            }
        }
        return true;
    }

    @Override
    public void load() {
    }

    @Override
    public void unload() {
    }

}
