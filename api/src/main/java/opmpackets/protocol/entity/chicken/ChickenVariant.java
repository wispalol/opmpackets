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

package opmpackets.protocol.entity.chicken;

import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.util.CodecNameable;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecException;
import opmpackets.protocol.util.NbtCodecs;
import opmpackets.protocol.util.NbtMapCodec;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.adventure.AdventureIndexUtil;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.util.Index;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * @versions 1.21.5+
 */
@NullMarked
public interface ChickenVariant extends MappedEntity, CopyableEntity<ChickenVariant>, DeepComparableEntity {

    NbtCodec<ChickenVariant> CODEC = new NbtMapCodec<ChickenVariant>() {
        @Override
        public ChickenVariant decode(NBTCompound tag, PacketWrapper<?> wrapper) throws NbtCodecException {
            ModelType modelType = tag.getOr("model", ModelType.CODEC, ModelType.NORMAL, wrapper);
            ResourceLocation assetId = tag.getOrThrow("asset_id", ResourceLocation.CODEC, wrapper);
            ResourceLocation babyAssetId = wrapper.getServerVersion().isOlderThan(ServerVersion.V_26_1) ? assetId
                    : tag.getOrThrow("baby_asset_id", ResourceLocation.CODEC, wrapper);
            return new StaticChickenVariant(modelType, assetId, babyAssetId);
        }

        @Override
        public void encode(NBTCompound tag, PacketWrapper<?> wrapper, ChickenVariant value) throws NbtCodecException {
            tag.set("model", value.getModelType(), ModelType.CODEC, wrapper);
            tag.set("asset_id", value.getAssetId(), ResourceLocation.CODEC, wrapper);
            if (wrapper.getServerVersion().isNewerThan(ServerVersion.V_26_1)) {
                tag.set("baby_asset_id", value.getBabyAssetId(), ResourceLocation.CODEC, wrapper);
            }
        }
    }.codec();

    ModelType getModelType();

    ResourceLocation getAssetId();

    /**
     * @versions 26.1+
     */
    ResourceLocation getBabyAssetId();

    static ChickenVariant read(PacketWrapper<?> wrapper) {
        return wrapper.readMappedEntity(ChickenVariants.getRegistry());
    }

    static void write(PacketWrapper<?> wrapper, ChickenVariant variant) {
        wrapper.writeMappedEntity(variant);
    }

    @Deprecated
    static ChickenVariant decode(NBT tag, ClientVersion version, @Nullable TypesBuilderData data) {
        return CODEC.decode(tag, PacketWrapper.createDummyWrapper(version)).copy(data);
    }

    @Deprecated
    static NBT encode(ChickenVariant variant, ClientVersion version) {
        return CODEC.encode(PacketWrapper.createDummyWrapper(version), variant);
    }

    enum ModelType implements CodecNameable {

        NORMAL("normal"),
        COLD("cold"),
        ;

        private static final Index<String, ModelType> NAME_INDEX = Index.create(
                ModelType.class, ModelType::getName);
        public static final NbtCodec<ModelType> CODEC = NbtCodecs.forEnum(values());

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

        @Override
        public String getCodecName() {
            return this.name;
        }
    }
}
