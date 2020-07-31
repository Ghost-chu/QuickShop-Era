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

package org.maxgamer.quickshop.bukkit.util.bukkit.block;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DirectionUtil {
    /**
     * Fetches the block which the given sign is attached to
     *
     * @param b The block which is attached
     * @return The block the sign is attached to
     */
    @Nullable
    public static Block getAttached(@NotNull Block b) {
        final BlockData blockData = b.getBlockData();
        if (blockData instanceof Directional) {
            final Directional directional = (Directional) blockData;
            return b.getRelative(directional.getFacing().getOppositeFace());
        } else {
            return null;
        }
    }
}
