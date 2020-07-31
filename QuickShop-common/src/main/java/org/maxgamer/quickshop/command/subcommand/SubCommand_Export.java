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

package org.maxgamer.quickshop.command.subcommand;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.command.CommandProcesser;
import org.maxgamer.quickshop.util.MsgUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.UUID;

@AllArgsConstructor
public class SubCommand_Export implements CommandProcesser {

    private final QuickShop plugin;

    @Override
    @SneakyThrows
    public synchronized void onCommand(
            @NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] cmdArg) {
        if (!(sender instanceof ConsoleCommandSender)) {
            return;
        }
        File file = new File(plugin.getDataFolder(), "export.txt");
        if (file.exists()) {
            Files.move(file.toPath(), new File(file.getParentFile(), file.getName() + UUID.randomUUID().toString().replace("-", "")).toPath());
        }
        file.createNewFile();

        new BukkitRunnable() {
            @SneakyThrows
            @Override
            public void run() {
                StringBuilder finalReport = new StringBuilder();
                plugin
                        .getShopLoader()
                        .getOriginShopsInDatabase()
                        .forEach((shop -> finalReport.append(shop).append("\n")));
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(file, false));
                outputStream.write(finalReport.toString());
                outputStream.close();
                MsgUtil.sendMessage(sender, "Done.");
            }
        }.runTaskAsynchronously(plugin);


    }

}
