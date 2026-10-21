/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2023 retrooper and contributors
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

package opmpackets.protocol.attribute;

import opmpackets.PacketEvents;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecs;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.MathUtil;

public interface Attribute extends MappedEntity {

    NbtCodec<Attribute> CODEC = NbtCodecs.forRegistry(Attributes.getRegistry());

    @Override
    default ResourceLocation getName() {
        return this.getName(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion());
    }

    ResourceLocation getName(ClientVersion version);

    default double sanitizeValue(double value) {
        return this.sanitizeValue(value, PacketEvents.getAPI().getServerManager().getVersion().toClientVersion());
    }

    default double sanitizeValue(double value, ClientVersion version) {
        if (!Double.isNaN(value)) {
            return MathUtil.clamp(value, this.getMinValue(), this.getMaxValue());
        }
        return this.getMinValue();
    }

    double getDefaultValue();

    double getMinValue();

    double getMaxValue();
}
