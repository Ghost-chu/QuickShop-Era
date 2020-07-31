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

package org.maxgamer.quickshop.database;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.logger.LoggerUtil;
import org.maxgamer.quickshop.util.text.WarningSender;
import org.maxgamer.quickshop.util.time.Timer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Queued database manager. Use queue to solve run SQL make server lagg issue.
 */
public class DatabaseManager {

    private final Queue<DatabaseTask> sqlQueue = new LinkedBlockingQueue<>();

    @NotNull
    private final Database database;

    @NotNull
    private final QuickShop plugin;

    @NotNull
    private final WarningSender warningSender;
    private final boolean useQueue;
    private final Logger logger = LoggerUtil.getLogger();
    @Nullable
    private java.util.Timer task;

    /**
     * Queued database manager. Use queue to solve run SQL make server lagg issue.
     *
     * @param plugin plugin main class
     * @param db     database
     */
    public DatabaseManager(@NotNull QuickShop plugin, @NotNull Database db, boolean useQueue, int commitInterval) {
        this.plugin = plugin;
        this.warningSender = new WarningSender(plugin, 600000);
        this.database = db;
        this.useQueue = useQueue;

        if (!useQueue) {
            return;
        }
        task = new java.util.Timer("QuickShop-DatabaseManager-Task");
        task.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                plugin.getDatabaseManager().runTask();
            }
        }, 0, commitInterval * 1000);
    }

    /**
     * Internal method, runTasks in queue.
     */
    private synchronized void runTask() { // synchronized for QUICKSHOP-WX
        try {
            Connection connection = this.database.getConnection();
            //start our commit
            connection.setAutoCommit(false);
            Timer ctimer = new Timer(true);
            while (true) {
                if (!connection.isValid(3000)) {
                    warningSender.sendWarn("Database connection may lost, we are trying reconnecting, if this message appear too many times, you should check your database file(sqlite) and internet connection(mysql).");
                    //connection isn't stable, let autocommit on
                    connection = database.getConnection();
                    continue; // Waiting next crycle and hope it success reconnected.
                }

                Timer timer = new Timer(true);
                DatabaseTask task = sqlQueue.poll();
                if (task == null) {
                    break;
                }
                Util.debugLog("Executing the SQL task: " + task);

                task.run(connection);
                long tookTime = timer.endTimer();
                if (tookTime > 300) {
                    warningSender.sendWarn(
                            "Database performance warning: It took too long time ("
                                    + tookTime
                                    + "ms) to execute the task, it may cause the network connection with MySQL server or just MySQL server too slow, change to a better MySQL server or switch to a local SQLite database!");
                }
            }
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
            }
            long tookTime = ctimer.endTimer();
            if (tookTime > 5500) {
                warningSender.sendWarn(
                        "Database performance warning: It took too long time ("
                                + tookTime
                                + "ms) to execute the task, it may cause the network connection with MySQL server or just MySQL server too slow, change to a better MySQL server or switch to a local SQLite database!");
            }
            connection.close();
        } catch (SQLException sqle) {
            plugin.getSentryErrorReporter().ignoreThrow();
            logger.log(Level.WARNING, "Database connection may lost, we are trying reconnecting, if this message appear too many times, you should check your database file(sqlite) and internet connection(mysql).", sqle);
        }

//        try {
//            this.database.getConnection().commit();
//        } catch (SQLException e) {
//            try {
//                this.database.getConnection().rollback();
//            } catch (SQLException ignored) {
//            }
//        }
    }

    /**
     * Add DatabaseTask to queue waiting flush to database,
     *
     * @param task The DatabaseTask you want add in queue.
     */
    public void add(DatabaseTask task) {
        if (useQueue) {
            sqlQueue.offer(task);
        } else {
            task.run(database.getConnection());
        }
    }

    /**
     * Unload the DatabaseManager, run at onDisable()
     */
    public void unInit() {
        logger.info("Please wait for the data to flush its data...");
        if (task != null) {
            task.cancel();
        }
        runTask();
    }

}