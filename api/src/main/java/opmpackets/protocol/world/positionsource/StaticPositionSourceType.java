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

package opmpackets.protocol.world.positionsource;

import opmpackets.protocol.mapper.AbstractMappedEntity;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public class StaticPositionSourceType<T extends PositionSource> extends AbstractMappedEntity implements PositionSourceType<T> {

    private final PacketWrapper.Reader<T> reader;
    private final PacketWrapper.Writer<T> writer;
    private final PositionSourceTypes.Decoder<T> decoder;
    private final PositionSourceTypes.Encoder<T> encoder;

    @ApiStatus.Internal
    public StaticPositionSourceType(
            @Nullable TypesBuilderData data,
            PacketWrapper.Reader<T> reader,
            PacketWrapper.Writer<T> writer,
            PositionSourceTypes.Decoder<T> decoder,
            PositionSourceTypes.Encoder<T> encoder
    ) {
        super(data);
        this.reader = reader;
        this.writer = writer;
        this.decoder = decoder;
        this.encoder = encoder;
    }

    @Override
    public T read(PacketWrapper<?> wrapper) {
        return this.reader.apply(wrapper);
    }

    @Override
    public void write(PacketWrapper<?> wrapper, T source) {
        this.writer.accept(wrapper, source);
    }

    @Override
    public T decode(NBTCompound compound, ClientVersion version) {
        return this.decoder.decode(compound, version);
    }

    @Override
    public void encode(T source, ClientVersion version, NBTCompound compound) {
        this.encoder.encode(source, version, compound);
    }
}
