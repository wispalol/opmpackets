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

package opmpackets.protocol.player;

import opmpackets.protocol.util.CodecNameable;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecs;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

@NullMarked
public enum PlayerModelType implements CodecNameable {

    SLIM("slim"),
    WIDE("wide"),
    ;

    public static final NbtCodec<PlayerModelType> CODEC = NbtCodecs.forEnum(values());

    private final String codecName;

    PlayerModelType(String codecName) {
        this.codecName = codecName;
    }

    public static PlayerModelType read(PacketWrapper<?> wrapper) {
        return wrapper.readBoolean() ? SLIM : WIDE;
    }

    public static void write(PacketWrapper<?> wrapper, PlayerModelType type) {
        wrapper.writeBoolean(type == SLIM);
    }

    @Override
    public String getCodecName() {
        return this.codecName;
    }
}
