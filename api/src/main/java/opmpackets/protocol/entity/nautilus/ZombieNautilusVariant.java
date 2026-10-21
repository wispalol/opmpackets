/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2025 wispalol and contributors
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

package opmpackets.protocol.entity.nautilus;

import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTString;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.adventure.AdventureIndexUtil;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.util.Index;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * @versions 1.21.11+
 */
@NullMarked
public interface ZombieNautilusVariant extends MappedEntity, CopyableEntity<ZombieNautilusVariant>, DeepComparableEntity {

    ModelType getModelType();

    ResourceLocation getAssetId();

    static ZombieNautilusVariant read(PacketWrapper<?> wrapper) {
        return wrapper.readMappedEntity(ZombieNautilusVariants.getRegistry());
    }

    static void write(PacketWrapper<?> wrapper, ZombieNautilusVariant variant) {
        wrapper.writeMappedEntity(variant);
    }

    static ZombieNautilusVariant decode(NBT nbt, ClientVersion version, @Nullable TypesBuilderData data) {
        NBTCompound compound = (NBTCompound) nbt;
        String modelTypeString = compound.getStringTagValueOrNull("model");
        ModelType modelType = modelTypeString != null ? ModelType.getByName(modelTypeString) : ModelType.NORMAL;
        ResourceLocation assetId = new ResourceLocation(compound.getStringTagValueOrThrow("asset_id"));
        return new StaticZombieNautilusVariant(data, modelType, assetId);
    }

    static NBT encode(ZombieNautilusVariant variant, ClientVersion version) {
        NBTCompound compound = new NBTCompound();
        compound.setTag("model", new NBTString(variant.getModelType().getName()));
        compound.setTag("asset_id", new NBTString(variant.getAssetId().toString()));
        return compound;
    }

    enum ModelType {

        NORMAL("normal"),
        WARM("warm"),
        ;

        private static final Index<String, ModelType> NAME_INDEX = Index.create(
                ModelType.class, ModelType::getName);

        private final String name;

        ModelType(String name) {
            this.name = name;
        }

        public static ModelType getByName(String name) {
            return AdventureIndexUtil.indexValueOrThrow(NAME_INDEX, name);
        }

        public String getName() {
            return this.name;
        }
    }
}
