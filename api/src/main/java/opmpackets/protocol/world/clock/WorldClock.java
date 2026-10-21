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

package opmpackets.protocol.world.clock;

import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecs;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

/**
 * @versions 26.1+
 */
@NullMarked
public interface WorldClock extends MappedEntity, CopyableEntity<WorldClock>, DeepComparableEntity {

    NbtCodec<WorldClock> CODEC = NbtCodecs.forRegistry(WorldClocks.getRegistry());
    NbtCodec<WorldClock> DIRECT_CODEC = NbtCodecs.<WorldClock>forUnit(StaticWorldClock::new).codec();

    static WorldClock read(PacketWrapper<?> wrapper) {
        return wrapper.readMappedEntity(WorldClocks.getRegistry());
    }

    static void write(PacketWrapper<?> wrapper, WorldClock clock) {
        wrapper.writeMappedEntity(clock);
    }
}
