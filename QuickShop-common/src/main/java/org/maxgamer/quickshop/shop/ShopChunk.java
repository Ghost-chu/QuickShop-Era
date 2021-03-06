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

package org.maxgamer.quickshop.shop;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.crossplatform.type.world.CrossPlatformWorld;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ShopChunk {
    @NotNull
    private final String world;

    private final int x;

    private final int z;

    public boolean isSame(@NotNull CrossPlatformWorld world, int x, int z) {
        return isSame(world.getName(), x, z);
    }

    public boolean isSame(@NotNull String world, int x, int z) {
        return this.x == x && this.z == z && this.world.equals(world);
    }

}
