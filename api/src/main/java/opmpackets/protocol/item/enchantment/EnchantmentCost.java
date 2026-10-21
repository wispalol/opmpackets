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

package opmpackets.protocol.item.enchantment;

import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTInt;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.wrapper.PacketWrapper;

import java.util.Objects;

public final class EnchantmentCost {

    private final int base;
    private final int perLevelAboveFirst;

    public EnchantmentCost(int base, int perLevelAboveFirst) {
        this.base = base;
        this.perLevelAboveFirst = perLevelAboveFirst;
    }

    @Deprecated
    public static EnchantmentCost decode(NBT nbt, ClientVersion version) {
        return decode(nbt, PacketWrapper.createDummyWrapper(version));
    }

    public static EnchantmentCost decode(NBT nbt, PacketWrapper<?> wrapper) {
        NBTCompound compound = (NBTCompound) nbt;
        int base = compound.getNumberTagOrThrow("base").getAsInt();
        int perLevelAboveFirst = compound.getNumberTagOrThrow("per_level_above_first").getAsInt();
        return new EnchantmentCost(base, perLevelAboveFirst);
    }

    @Deprecated
    public static NBT encode(EnchantmentCost cost, ClientVersion version) {
        return encode(PacketWrapper.createDummyWrapper(version), cost);
    }

    public static NBT encode(PacketWrapper<?> wrapper, EnchantmentCost cost) {
        NBTCompound compound = new NBTCompound();
        compound.setTag("base", new NBTInt(cost.base));
        compound.setTag("per_level_above_first", new NBTInt(cost.perLevelAboveFirst));
        return compound;
    }

    public int getBase() {
        return this.base;
    }

    public int getPerLevelAboveFirst() {
        return this.perLevelAboveFirst;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EnchantmentCost)) return false;
        EnchantmentCost that = (EnchantmentCost) obj;
        if (this.base != that.base) return false;
        return this.perLevelAboveFirst == that.perLevelAboveFirst;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.base, this.perLevelAboveFirst);
    }

    @Override
    public String toString() {
        return "EnchantmentCost{base=" + this.base + ", perLevelAboveFirst=" + this.perLevelAboveFirst + '}';
    }
}
