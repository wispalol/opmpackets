/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2024 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package opmpackets.sponge.util;

import opmpackets.PacketEvents;
import opmpackets.protocol.item.ItemStack;
import opmpackets.protocol.world.states.WrappedBlockState;
import org.spongepowered.api.block.BlockState;

public final class SpongeConversionUtil {

    public static ItemStack fromSpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        return SpongeReflectionUtil.decodeSpongeItemStack(itemStack);
    }

    public static org.spongepowered.api.item.inventory.ItemStack toSpongeItemStack(ItemStack itemStack) {
        return SpongeReflectionUtil.encodeSpongeItemStack(itemStack);
    }

    public static WrappedBlockState fromSpongeBlockState(BlockState blockState) {
        return WrappedBlockState.getByString(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion(), blockState.asString());
    }

    public static BlockState toSpongeBlockState(WrappedBlockState blockState) {
        return BlockState.fromString(blockState.toString());
    }
}
