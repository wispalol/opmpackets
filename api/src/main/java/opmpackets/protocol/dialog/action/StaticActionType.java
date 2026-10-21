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

package opmpackets.protocol.dialog.action;

import opmpackets.protocol.mapper.AbstractMappedEntity;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.util.NbtMapDecoder;
import opmpackets.protocol.util.NbtMapEncoder;
import opmpackets.util.mappings.TypesBuilderData;
import opmpackets.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class StaticActionType<T extends Action> extends AbstractMappedEntity implements ActionType<T> {

    private final NbtMapDecoder<T> decoder;
    private final NbtMapEncoder<T> encoder;

    @ApiStatus.Internal
    public StaticActionType(@Nullable TypesBuilderData data, NbtMapDecoder<T> decoder, NbtMapEncoder<T> encoder) {
        super(data);
        this.decoder = decoder;
        this.encoder = encoder;
    }

    @Override
    public T decode(NBTCompound compound, PacketWrapper<?> wrapper) {
        return this.decoder.decode(compound, wrapper);
    }

    @Override
    public void encode(NBTCompound compound, PacketWrapper<?> wrapper, T action) {
        this.encoder.encode(compound, wrapper, action);
    }
}
