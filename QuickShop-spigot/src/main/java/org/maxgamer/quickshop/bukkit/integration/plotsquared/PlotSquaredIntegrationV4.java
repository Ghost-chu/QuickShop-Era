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

package org.maxgamer.quickshop.bukkit.integration.plotsquared;

import com.github.intellectualsites.plotsquared.plot.flag.BooleanFlag;
import com.github.intellectualsites.plotsquared.plot.flag.Flags;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.bukkit.integration.IntegrateStage;
import org.maxgamer.quickshop.bukkit.integration.IntegratedPlugin;
import org.maxgamer.quickshop.bukkit.integration.IntegrationStage;
import org.maxgamer.quickshop.util.Util;

@SuppressWarnings("DuplicatedCode")
@IntegrationStage(loadStage = IntegrateStage.onEnableAfter)
public class PlotSquaredIntegrationV4 implements IntegratedPlugin {
    private final QuickShop plugin;
    private final boolean whiteList;
    private BooleanFlag createFlag;
    private BooleanFlag tradeFlag;

    public PlotSquaredIntegrationV4(QuickShop plugin) {
        this.plugin = plugin;
        this.whiteList = plugin.getConfig().getBoolean("integration.plotsquared.whitelist-mode");
        // PlotAPI plotAPI = new PlotAPI();
    }

    @Override
    public @NotNull String getName() {
        return "PlotSquared";
    }

    @Override
    public boolean canCreateShopHere(@NotNull Player player, @NotNull Location location) {
        com.github.intellectualsites.plotsquared.plot.object.Location pLocation =
                new com.github.intellectualsites.plotsquared.plot.object.Location(
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ());
        Plot plot = pLocation.getPlot();
        if (plot == null) {
            return !whiteList;
        }
        return this.createFlag.isTrue(plot);
    }

    @Override
    public boolean canTradeShopHere(@NotNull Player player, @NotNull Location location) {
        com.github.intellectualsites.plotsquared.plot.object.Location pLocation =
                new com.github.intellectualsites.plotsquared.plot.object.Location(
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ());
        Plot plot = pLocation.getPlot();
        if (plot == null) {
            return !whiteList;
        }
        return this.tradeFlag.isFalse(plot);
    }

    @Override
    public boolean canDeleteShopHere(@NotNull Player player, @NotNull Location location) {
        return false;
    }

    @Override
    public void load() {
        this.createFlag = new BooleanFlag("quickshop-create");
        this.tradeFlag = new BooleanFlag("quickshop-trade");
        Flags.registerFlag(this.createFlag);
        Flags.registerFlag(this.tradeFlag);
        plugin.getLogger().info(ChatColor.GREEN + getName() + " flags register successfully.");
        Util.debugLog("Success register " + getName() + " flags.");
    }

    @Override
    public void unload() {
    }

}
