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

package opmpackets.protocol.item.banner;

import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTString;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface BannerPattern extends MappedEntity, CopyableEntity<BannerPattern>, DeepComparableEntity {

    ResourceLocation getAssetId();

    String getTranslationKey();

    static BannerPattern read(PacketWrapper<?> wrapper) {
        return wrapper.readMappedEntityOrDirect(BannerPatterns.getRegistry(), BannerPattern::readDirect);
    }

    static void write(PacketWrapper<?> wrapper, BannerPattern pattern) {
        wrapper.writeMappedEntityOrDirect(pattern, BannerPattern::writeDirect);
    }

    static BannerPattern readDirect(PacketWrapper<?> wrapper) {
        ResourceLocation assetId = wrapper.readIdentifier();
        String translationKey = wrapper.readString();
        return new StaticBannerPattern(assetId, translationKey);
    }

    static void writeDirect(PacketWrapper<?> wrapper, BannerPattern pattern) {
        wrapper.writeIdentifier(pattern.getAssetId());
        wrapper.writeString(pattern.getTranslationKey());
    }

    static BannerPattern decode(NBT nbt, ClientVersion version, @Nullable TypesBuilderData data) {
        NBTCompound compound = (NBTCompound) nbt;
        ResourceLocation assetId = new ResourceLocation(compound.getStringTagValueOrThrow("asset_id"));
        String translationKey = compound.getStringTagValueOrThrow("translation_key");
        return new StaticBannerPattern(data, assetId, translationKey);
    }

    static NBT encode(BannerPattern bannerPattern, ClientVersion version) {
        NBTCompound compound = new NBTCompound();
        compound.setTag("asset_id", new NBTString(bannerPattern.getAssetId().toString()));
        compound.setTag("translation_key", new NBTString(bannerPattern.getTranslationKey()));
        return compound;
    }
}
