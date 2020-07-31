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

package org.maxgamer.quickshop.nonquickshopstuff.me.lucko.spark.common.util;

public enum TypeDescriptors {
    ;

    /**
     * Returns the Java type corresponding to the given type descriptor.
     *
     * @param typeDescriptor a type descriptor.
     * @return the Java type corresponding to the given type descriptor.
     */
    public static String getJavaType(String typeDescriptor) {
        return getJavaType(typeDescriptor.toCharArray(), 0);
    }

    private static String getJavaType(char[] buf, int offset) {
        int len;
        switch (buf[offset]) {
            case 'V':
                return "void";
            case 'Z':
                return "boolean";
            case 'C':
                return "char";
            case 'B':
                return "byte";
            case 'S':
                return "short";
            case 'I':
                return "int";
            case 'F':
                return "float";
            case 'J':
                return "long";
            case 'D':
                return "double";
            case '[': // array
                len = 1;
                while (buf[offset + len] == '[') {
                    len++;
                }

                StringBuilder sb = new StringBuilder(getJavaType(buf, offset + len));
                for (int i = len; i > 0; --i) {
                    sb.append("[]");
                }
                return sb.toString();
            case 'L': // object
                len = 1;
                while (buf[offset + len] != ';') {
                    len++;
                }
                return new String(buf, offset + 1, len - 1);
            default:
                return new String(buf, offset, buf.length - offset);
        }
    }
}
