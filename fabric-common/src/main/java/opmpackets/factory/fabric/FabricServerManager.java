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

package opmpackets.factory.fabric;

import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.util.mappings.GlobalRegistryHolder;
import opmpackets.impl.netty.manager.server.ServerManagerAbstract;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class FabricServerManager extends ServerManagerAbstract {

    private final ServerVersion version;

    public FabricServerManager(String mcVersion) {
        this.version = resolveVersion(mcVersion);
    }

    private static ServerVersion resolveVersion(String mcVersion) {
        for (ServerVersion version : ServerVersion.reversedValues()) {
            if (mcVersion.contains(version.getReleaseName())) {
                return version;
            }
        }
        throw new IllegalStateException("PacketEvents doesn't support Minecraft version: " + mcVersion
                + "; if you believe this is an error, please report it to it PacketEvents");
    }

    @Override
    public ServerVersion getVersion() {
        return this.version;
    }

    @Override
    public Object getRegistryCacheKey(User user, ClientVersion version) {
        return GlobalRegistryHolder.getGlobalRegistryCacheKey(user, version);
    }
}
