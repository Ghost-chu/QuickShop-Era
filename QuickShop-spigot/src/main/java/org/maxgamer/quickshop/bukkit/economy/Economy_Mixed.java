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

package org.maxgamer.quickshop.bukkit.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.economy.EconomyCore;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;

import java.util.UUID;

public class Economy_Mixed implements EconomyCore {
    private final EconomyCore core;
    private final QuickShop plugin;

    public Economy_Mixed(@NotNull QuickShop plugin) {
        this.plugin = plugin;
        core = new Economy_Vault(plugin);
    }

    @Override
    public boolean deposit(@NotNull UUID name, double amount) {
        if (getBalance(name) < amount) {
            return false;
        }
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                MsgUtil.fillArgs(
                        plugin.getConfig().getString("mixedeconomy.deposit"),
                        Bukkit.getOfflinePlayer(name).getName(),
                        String.valueOf(amount)));
        return true;
    }

    @Override
    public String format(double balance) {
        return Util.format(balance);
    }

    @Override
    public double getBalance(@NotNull UUID name) {
        return core.getBalance(name);
    }

    @Override
    public boolean transfer(@NotNull UUID from, @NotNull UUID to, double amount) {
        boolean result;
        result = withdraw(from, amount);
        if (!result) {
            deposit(from, amount);
        }
        result = deposit(to, amount);
        if (!result) {
            withdraw(to, amount);
        }
        return true;
    }

    @Override
    public boolean withdraw(@NotNull UUID name, double amount) {
        if (getBalance(name) > amount) {
            return false;
        }
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                MsgUtil.fillArgs(
                        plugin.getConfig().getString("mixedeconomy.withdraw"),
                        Bukkit.getOfflinePlayer(name).getName(),
                        String.valueOf(amount)));
        return true;
    }

    @Override
    public boolean isValid() {
        return core.isValid();
    }

    @Override
    public @NotNull String getName() {
        return "BuiltIn-Mixed";
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this.plugin;
    }

}
