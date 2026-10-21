/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2022 retrooper and contributors
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

package opmpackets.protocol.item.enchantment.type;

import opmpackets.protocol.component.EnchantEffectComponentTypes;
import opmpackets.protocol.component.IComponentMap;
import opmpackets.protocol.component.StaticComponentMap;
import opmpackets.protocol.item.enchantment.EnchantmentDefinition;
import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.mapper.MappedEntityRefSet;
import opmpackets.protocol.mapper.MappedEntitySet;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public interface EnchantmentType extends MappedEntity, CopyableEntity<EnchantmentType>, DeepComparableEntity {

    Component getDescription();

    EnchantmentDefinition getDefinition();

    MappedEntitySet<EnchantmentType> getExclusiveSet();

    MappedEntityRefSet<EnchantmentType> getExclusiveRefSet();

    StaticComponentMap getEffects();

    @Deprecated
    static EnchantmentType decode(NBT nbt, ClientVersion version, @Nullable TypesBuilderData data) {
        return decode(nbt, PacketWrapper.createDummyWrapper(version), data);
    }

    static EnchantmentType decode(NBT nbt, PacketWrapper<?> wrapper, @Nullable TypesBuilderData data) {
        NBTCompound compound = (NBTCompound) nbt;
        Component description = compound.getOrThrow("description", wrapper.getSerializers(), wrapper);
        EnchantmentDefinition definition = EnchantmentDefinition.decode(compound, wrapper);
        MappedEntityRefSet<EnchantmentType> exclusiveSet = Optional.ofNullable(compound.getTagOrNull("exclusive_set"))
                .map(tag -> MappedEntitySet.<EnchantmentType>decodeRefSet(tag, wrapper))
                .orElseGet(MappedEntitySet::createEmpty);
        StaticComponentMap effects = Optional.ofNullable(compound.getTagOrNull("effects"))
                .map(tag -> IComponentMap.decode(tag, wrapper,
                        EnchantEffectComponentTypes.getRegistry()))
                .orElse(StaticComponentMap.EMPTY);
        return new StaticEnchantmentType(data, description, definition, exclusiveSet, effects);
    }

    @Deprecated
    static NBT encode(EnchantmentType type, ClientVersion version) {
        return encode(type, PacketWrapper.createDummyWrapper(version));
    }

    static NBT encode(EnchantmentType type, PacketWrapper<?> wrapper) {
        NBTCompound compound = new NBTCompound();
        EnchantmentDefinition.encode(compound, wrapper, type.getDefinition());
        compound.set("description", type.getDescription(), wrapper.getSerializers(), wrapper);
        if (!type.getExclusiveRefSet().isEmpty()) {
            compound.set("exclusive_set", type.getExclusiveRefSet(), MappedEntitySet::encodeRefSet, wrapper);
        }
        if (!type.getEffects().isEmpty()) {
            compound.set("effects", type.getEffects(), IComponentMap::encode, wrapper);
        }
        return compound;
    }
}
