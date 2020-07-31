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

import lombok.NonNull;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FacingUtil {
    private static final List<BlockFace> verticalFacing = Collections.unmodifiableList(Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST));

    /**
     * return the right side for given blockFace
     *
     * @param blockFace given blockFace
     * @return the right side for given blockFace, UP and DOWN will return itself
     */
    @NotNull
    public static BlockFace getRightSide(@NonNull BlockFace blockFace) {
        switch (blockFace) {
            case EAST:
                return BlockFace.SOUTH;
            case NORTH:
                return BlockFace.EAST;
            case SOUTH:
                return BlockFace.WEST;
            case WEST:
                return BlockFace.NORTH;
            default:
                return blockFace;
        }
    }


    /**
     * Get vertical BlockFace list
     *
     * @return vertical BlockFace list (unmodifiable)
     */
    @NotNull
    public static List<BlockFace> getVerticalFacing() {
        return verticalFacing;
    }
}
