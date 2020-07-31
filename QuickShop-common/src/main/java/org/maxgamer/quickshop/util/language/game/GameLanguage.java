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

package org.maxgamer.quickshop.util.language.game;

import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.crossplatform.type.item.CrossPlatformItemStack;
import org.maxgamer.quickshop.crossplatform.type.material.CrossPlatformMaterial;

public interface GameLanguage {
    @NotNull String getName();

    @NotNull String getPluginName();

    @NotNull String getItem(@NotNull CrossPlatformItemStack itemStack);

    @NotNull String getItem(@NotNull CrossPlatformMaterial material);

    @NotNull String getPotion(@NotNull CrossPlatformPotionEffectType potionEffectType);

    @NotNull String getEnchantment(@NotNull CrossPlatformEnchantment enchantment);

    @NotNull String getEntity(@NotNull CrossPlatformEntityType entityType);
}
