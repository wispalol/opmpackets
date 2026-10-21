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

package opmpackets.protocol.mapper;

import opmpackets.protocol.player.ClientVersion;
import opmpackets.util.mappings.IRegistry;
import opmpackets.util.mappings.IRegistryHolder;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface MappedEntityRefSet<T extends MappedEntity> {

    default MappedEntitySet<T> resolve(PacketWrapper<?> wrapper, IRegistry<T> registry) {
        ClientVersion version = wrapper.getServerVersion().toClientVersion();
        return this.resolve(version, wrapper.replaceRegistry(registry));
    }

    default MappedEntitySet<T> resolve(ClientVersion version, IRegistryHolder registryHolder, IRegistry<T> registry) {
        IRegistry<T> replacedRegistry = registryHolder.getRegistryOr(registry, version);
        return this.resolve(version, replacedRegistry);
    }

    MappedEntitySet<T> resolve(ClientVersion version, IRegistry<T> registry);

    boolean isEmpty();
}
