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

package opmpackets.protocol.world.painting;

import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTInt;
import opmpackets.protocol.nbt.NBTString;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecException;
import opmpackets.protocol.util.NbtMapCodec;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface PaintingVariant extends MappedEntity, CopyableEntity<PaintingVariant>, DeepComparableEntity {

    NbtCodec<PaintingVariant> CODEC = new NbtMapCodec<PaintingVariant>() {
        @Override
        public PaintingVariant decode(NBTCompound tag, PacketWrapper<?> wrapper) throws NbtCodecException {
            int width = tag.getNumberTagOrThrow("width").getAsInt();
            int height = tag.getNumberTagOrThrow("height").getAsInt();
            ResourceLocation assetId = new ResourceLocation(tag.getStringTagValueOrThrow("asset_id"));
            Component title = tag.getOrNull("title", wrapper.getSerializers(), wrapper);
            Component author = tag.getOrNull("author", wrapper.getSerializers(), wrapper);
            return new StaticPaintingVariant(width, height, assetId, title, author);
        }

        @Override
        public void encode(NBTCompound tag, PacketWrapper<?> wrapper, PaintingVariant value) throws NbtCodecException {
            tag.setTag("width", new NBTInt(value.getWidth()));
            tag.setTag("height", new NBTInt(value.getHeight()));
            tag.setTag("asset_id", new NBTString(value.getAssetId().toString()));
            if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
                Component title = value.getTitle();
                if (title != null) {
                    tag.set("title", title, wrapper.getSerializers(), wrapper);
                }
                Component author = value.getAuthor();
                if (author != null) {
                    tag.set("author", author, wrapper.getSerializers(), wrapper);
                }
            }
        }
    }.codec();

    @Contract(pure = true)
    int getWidth();

    @Contract(pure = true)
    int getHeight();

    @Contract(pure = true)
    ResourceLocation getAssetId();

    /**
     * @versions 1.21.2+
     */
    @Contract(pure = true)
    @Nullable Component getTitle();

    /**
     * @versions 1.21.2+
     */
    @Contract(pure = true)
    @Nullable Component getAuthor();

    static PaintingVariant read(PacketWrapper<?> wrapper) {
        if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21)) {
            return wrapper.readMappedEntityOrDirect(PaintingVariants.getRegistry(), PaintingVariant::readDirect);
        }
        return wrapper.readMappedEntity(PaintingVariants.getRegistry());
    }

    static void write(PacketWrapper<?> wrapper, PaintingVariant variant) {
        if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21)) {
            wrapper.writeMappedEntityOrDirect(variant, PaintingVariant::writeDirect);
        } else {
            wrapper.writeMappedEntity(variant);
        }
    }

    static PaintingVariant readDirect(PacketWrapper<?> wrapper) {
        int width = wrapper.readVarInt();
        int height = wrapper.readVarInt();
        ResourceLocation assetId = wrapper.readIdentifier();
        Component title = null;
        Component author = null;
        if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
            title = wrapper.readOptional(PacketWrapper::readComponent);
            author = wrapper.readOptional(PacketWrapper::readComponent);
        }
        return new StaticPaintingVariant(width, height, assetId, title, author);
    }

    static void writeDirect(PacketWrapper<?> wrapper, PaintingVariant variant) {
        wrapper.writeVarInt(variant.getWidth());
        wrapper.writeVarInt(variant.getHeight());
        wrapper.writeIdentifier(variant.getAssetId());
        if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
            wrapper.writeOptional(variant.getTitle(), PacketWrapper::writeComponent);
            wrapper.writeOptional(variant.getAuthor(), PacketWrapper::writeComponent);
        }
    }

    @Deprecated
    static PaintingVariant decode(NBT nbt, ClientVersion version, @Nullable TypesBuilderData data) {
        return CODEC.decode(nbt, PacketWrapper.createDummyWrapper(version)).copy(data);
    }

    @Deprecated
    static NBT encode(PaintingVariant variant, ClientVersion version) {
        return CODEC.encode(PacketWrapper.createDummyWrapper(version), variant);
    }
}
