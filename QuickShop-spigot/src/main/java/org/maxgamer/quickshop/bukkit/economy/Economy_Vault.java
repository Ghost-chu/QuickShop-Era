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


import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.economy.EconomyCore;
import org.maxgamer.quickshop.util.Util;

import java.util.Objects;
import java.util.UUID;

public class Economy_Vault implements EconomyCore, Listener {

    private final QuickShop plugin;
    private final UUID taxAccountUUID;
    private final boolean allowLoan;
    @Getter
    @Setter
    @Nullable
    private net.milkbowl.vault.economy.Economy vault;

    public Economy_Vault(@NotNull QuickShop plugin) {
        this.plugin = plugin;
        this.allowLoan = plugin.getConfig().getBoolean("shop.allow-economy-loan");
        Util.debugLog("Loading caching tax account...");
        //noinspection deprecation
        this.taxAccountUUID = Bukkit.getOfflinePlayer(Objects.requireNonNull(plugin.getConfig().getString("tax-account", "tax"))).getUniqueId();
        setupEconomy();
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider;
        try {
            economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        } catch (Exception e) {
            return false;
        }

        if (economyProvider != null) {
            this.vault = economyProvider.getProvider();
        }

        if (this.vault == null) {
            return false;
        }

        if (this.vault.getName() == null || this.vault.getName().isEmpty()) {
            plugin
                    .getLogger()
                    .warning(
                            "Current economy plugin not correct process all request, this usually cause by irregular code, you should report this issue to your economy plugin author or use other economy plugin.");
            plugin
                    .getLogger()
                    .warning(
                            "This is technical information, please send this to economy plugin author: "
                                    + "VaultEconomyProvider.getName() return a null or empty.");
        } else {
            plugin.getLogger().info("Using economy system: " + this.vault.getName());
        }
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Util.debugLog("Economy service listener was registered.");
        return true;
    }

    @EventHandler
    public void onServiceRegister(ServiceRegisterEvent event) {
        if (!(event.getProvider() instanceof net.milkbowl.vault.economy.Economy)) {
            return;
        }
        setupEconomy();
    }

    @EventHandler
    public void onServiceUnregister(ServiceUnregisterEvent event) {
        if (!(event.getProvider() instanceof net.milkbowl.vault.economy.Economy)) {
            return;
        }
        setupEconomy();
    }

    @Override
    public boolean deposit(@NotNull UUID name, double amount) {
        if (!checkValid()) {
            return false;
        }
        OfflinePlayer p = Bukkit.getOfflinePlayer(name);
        try {
            return Objects.requireNonNull(this.vault).depositPlayer(p, amount).transactionSuccess();
        } catch (Exception t) {
            plugin.getSentryErrorReporter().ignoreThrow();
            if (p.getName() == null) {
                if (this.taxAccountUUID.equals(name)) {
                    plugin.getLogger().warning("Deposit failed and player name is NULL, you should check if your tax account in config.yml are existed on the server. Provider (" + getProviderName() + ")");
                } else {
                    plugin.getLogger().warning("Deposit failed and player name is NULL, Player uuid: " + name + ". Provider (" + getProviderName() + ")");
                }
                return false;
            }
            t.printStackTrace();
            plugin
                    .getLogger()
                    .warning(
                            "This seems not QuickShop fault, you should contact with your economy plugin author. ("
                                    + getProviderName()
                                    + ")");
            return false;
        }
    }

    @Override
    public String format(double balance) {
        if (!checkValid()) {
            return "Error";
        }
        try {
            String formatedBalance = Objects.requireNonNull(this.vault).format(balance);
            if (formatedBalance == null) // Stupid Ecosystem
            {
                return formatInternal(balance);
            }
            return formatedBalance;
        } catch (Exception e) {
            return formatInternal(balance);
        }
    }

    private String formatInternal(double balance) {
        if (!checkValid()) {
            return "Error";
        }

        return Util.format(balance, true);
    }

    @Override
    public double getBalance(@NotNull UUID name) {
        if (!checkValid()) {
            return 0.0;
        }

        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);

        if (offlinePlayer.getName() == null) {
            return 0.0;
        }

        try {
            return Objects.requireNonNull(this.vault).getBalance(offlinePlayer);
        } catch (Exception t) {
            plugin.getSentryErrorReporter().ignoreThrow();
            t.printStackTrace();
            plugin
                    .getLogger()
                    .warning(
                            "This seems not QuickShop fault, you should contact with your economy plugin author. ("
                                    + getProviderName()
                                    + ")");
            return 0.0;
        }
    }

    @Override
    public boolean transfer(@NotNull UUID from, @NotNull UUID to, double amount) {
        if (!checkValid()) {
            return false;
        }
        if (this.getBalance(from) >= amount) {
            if (this.withdraw(from, amount)) {
                if (this.deposit(to, amount)) {
                    this.deposit(from, amount);
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean withdraw(@NotNull UUID name, double amount) {
        if (!checkValid()) {
            return false;
        }
        OfflinePlayer p = Bukkit.getOfflinePlayer(name);
        try {
            if ((!allowLoan) && (getBalance(name) < amount)) {
                return false;
            }
            return Objects.requireNonNull(this.vault).withdrawPlayer(p, amount).transactionSuccess();
        } catch (Exception t) {
            plugin.getSentryErrorReporter().ignoreThrow();
            if (p.getName() == null) {
                if (this.taxAccountUUID.equals(name)) {
                    plugin.getLogger().warning("Withdraw failed and player name is NULL, you should check if your tax account in config.yml are existed on the server. Provider: " + getProviderName());
                } else {
                    plugin.getLogger().warning("Withdraw failed and player name is NULL, Player uuid: " + name + ", Provider: " + getProviderName());
                }
                return false;
            }
            t.printStackTrace();
            plugin
                    .getLogger()
                    .warning(
                            "This seems not QuickShop fault, you should contact with your economy plugin author. ("
                                    + getProviderName()
                                    + ")");
            return false;
        }
    }

    @Override
    public boolean isValid() {
        return this.vault != null;
    }

    @Override
    public @NotNull String getName() {
        return "BuiltIn-Vault";
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    public boolean checkValid() {
        if (this.vault == null) {
            Bukkit.getPluginManager().disablePlugin(plugin);
            plugin.getLogger().severe("FATAL: Economy system not ready.");
            return false;
        } else {
            return true;
        }
    }

    public String getProviderName() {
        if (this.vault == null) {
            return "Provider not found.";
        }
        return String.valueOf(this.vault.getName());
    }

}
