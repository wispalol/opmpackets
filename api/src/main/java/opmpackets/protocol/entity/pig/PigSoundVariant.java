/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2026 retrooper and contributors
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

package opmpackets.protocol.entity.pig;

import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecException;
import opmpackets.protocol.util.NbtMapCodec;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

/**
 * @versions 26.1
 */
@NullMarked
public interface PigSoundVariant extends MappedEntity, CopyableEntity<PigSoundVariant>, DeepComparableEntity {

    NbtCodec<PigSoundVariant> CODEC = new NbtMapCodec<PigSoundVariant>() {
        @Override
        public PigSoundVariant decode(NBTCompound tag, PacketWrapper<?> wrapper) throws NbtCodecException {
            PigSoundSet adultSounds = tag.getOrThrow("adult_sounds", PigSoundSet.CODEC, wrapper);
            PigSoundSet babySounds = tag.getOrThrow("baby_sounds", PigSoundSet.CODEC, wrapper);
            return new StaticPigSoundVariant(adultSounds, babySounds);
        }

        @Override
        public void encode(NBTCompound tag, PacketWrapper<?> wrapper, PigSoundVariant value) throws NbtCodecException {
            tag.set("adult_sounds", value.getAdultSounds(), PigSoundSet.CODEC, wrapper);
            tag.set("baby_sounds", value.getBabySounds(), PigSoundSet.CODEC, wrapper);
        }
    }.codec();

    static PigSoundVariant read(PacketWrapper<?> wrapper) {
        return wrapper.readMappedEntity(PigSoundVariants.getRegistry());
    }

    static void write(PacketWrapper<?> wrapper, PigSoundVariant variant) {
        wrapper.writeMappedEntity(variant);
    }

    PigSoundSet getAdultSounds();

    PigSoundSet getBabySounds();
}
