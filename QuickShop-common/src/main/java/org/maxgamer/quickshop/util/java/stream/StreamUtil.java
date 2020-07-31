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

package org.maxgamer.quickshop.util.java.stream;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {
    /**
     * Read the InputStream to the byte array.
     *
     * @param filePath Target file
     * @return Byte array
     */
    @Nullable
    public static byte[] inputStream2ByteArray(@NotNull String filePath) {
        try {

            InputStream in = new FileInputStream(filePath);
            byte[] data = toByteArray(in);
            in.close();
            return data;
        } catch (IOException e) {
            return null;
        }
    }

    private static byte[] toByteArray(@NotNull InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * Read the InputStream to the byte array.
     *
     * @param inputStream Target stream
     * @return Byte array
     */
    @Nullable
    public static byte[] inputStream2ByteArray(@NotNull InputStream inputStream) {
        try {
            byte[] data = toByteArray(inputStream);
            inputStream.close();
            return data;
        } catch (IOException e) {
            return null;
        }
    }
}
