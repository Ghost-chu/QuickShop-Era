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

package org.maxgamer.quickshop.util.java.clazz;

import org.jetbrains.annotations.NotNull;

public class ClassUtil {
    /**
     * Return the Class name.
     *
     * @param c The class to get name
     * @return The class prefix
     */
    @NotNull
    public static String getClassPrefix(@NotNull Class<?> c) {
        String callClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String customClassName = c.getSimpleName();
        return "[" + callClassName + "-" + customClassName + "] ";
    }

    /**
     * Return the Class name.
     *
     * @return The class prefix
     */
    @NotNull
    public static String getClassPrefix() {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        try {
            Class<?> c = Class.forName(className);
            className = c.getSimpleName();
            if (!c.getSimpleName().isEmpty()) {
                className = c.getSimpleName();
            }
        } catch (ClassNotFoundException e) {
            // Ignore
        }
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "[" + className + "-" + methodName + "] ";
    }

    /**
     * Get this class available or not
     *
     * @param qualifiedName class qualifiedName
     * @return boolean Available
     */
    public static boolean isClassAvailable(@NotNull String qualifiedName) {
        try {
            Class.forName(qualifiedName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
