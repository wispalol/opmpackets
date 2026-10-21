/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2022 retrooper and contributors
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

package opmpackets.impl.netty.manager.player;

import opmpackets.PacketEvents;
import opmpackets.manager.player.PlayerManager;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerManagerAbstract implements PlayerManager {
    @Override
    public abstract int getPing(@NotNull Object player);

    @Override
    public abstract Object getChannel(@NotNull Object player);

    @Override
    public @NotNull ClientVersion getClientVersion(@NotNull Object player) {
        return getUser(player).getClientVersion();
    }

    @Override
    public User getUser(@NotNull Object player) {
        Object channel = getChannel(player);
        return PacketEvents.getAPI().getProtocolManager().getUser(channel);
    }
}