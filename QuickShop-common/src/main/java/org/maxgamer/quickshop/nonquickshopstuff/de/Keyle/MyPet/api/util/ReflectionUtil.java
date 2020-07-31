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

package org.maxgamer.quickshop.nonquickshopstuff.de.Keyle.MyPet.api.util;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil {

    public static Class getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception ignored) {
            return Class.class;
        }
    }

    @Nullable
    public static Method getMethod(Class<?> clazz, String method, Class<?>... parameterTypes) {
        try {
            Method m = clazz.getDeclaredMethod(method, parameterTypes);
            m.setAccessible(true);
            return m;
        } catch (Exception ignored) {
            return null;
        }
    }

    @Nullable
    public static Object getFieldValue(Class<?> clazz, Object target, String field) {
        try {
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            return f.get(target);
        } catch (Exception ignored) {
            return null;
        }
    }

    @Nullable
    public static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean setFieldValue(Field field, Object target, Object value) {
        try {
            field.set(target, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean setFieldValue(String fieldName, Object target, Object value) {
        try {
            Field field = getField(target.getClass(), fieldName);
            if (field != null) {
                field.set(target, value);
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    @Nullable
    public static Field getField(Class<?> clazz, String field) {
        try {
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            return f;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void setFinalStaticValue(Field field, Object newValue)
            throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    public static boolean isTypeOf(Class<?> clazz, Class<?> superClass) {
        if (!clazz.equals(superClass)) {
            clazz = clazz.getSuperclass();
            return !clazz.equals(Object.class) && isTypeOf(clazz, superClass);
        }
        return true;
    }

}
