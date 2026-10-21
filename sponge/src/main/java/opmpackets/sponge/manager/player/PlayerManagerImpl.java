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
package opmpackets.sponge.manager.player;

import opmpackets.PacketEvents;
import opmpackets.manager.player.PlayerManager;
import opmpackets.manager.protocol.ProtocolManager;
import opmpackets.netty.channel.ChannelHelper;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.sponge.util.SpongeReflectionUtil;
import opmpackets.sponge.util.viaversion.ViaVersionUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.network.ServerConnectionState;
import org.spongepowered.api.network.ServerSideConnection;

import java.util.UUID;

public class PlayerManagerImpl implements PlayerManager {
    @Override
    public int getPing(@NotNull Object player) {
        final ServerSideConnection connection = ((ServerPlayer) player).connection();
        return connection.state().map(state -> {
            if (state instanceof ServerConnectionState.Game) {
                final ServerConnectionState.Game game = (ServerConnectionState.Game) state;
                return game.latency();
            }
            return -1;
        }).orElse(-1);
    }

    @Override
    public @NotNull ClientVersion getClientVersion(@NotNull Object p) {
        ServerPlayer player = (ServerPlayer) p;
        User user = getUser(player);
        if (user == null) return ClientVersion.UNKNOWN;
        if (user.getClientVersion() == null) {
            int protocolVersion;
            if (ViaVersionUtil.isAvailable()) {
                protocolVersion = ViaVersionUtil.getProtocolVersion(player);
                PacketEvents.getAPI().getLogManager().debug("Requested ViaVersion for " + player.name() + "'s protocol version. Protocol version: " + protocolVersion);
            } else {
                //No protocol translation plugins available, the client must be the same version as the server.
                protocolVersion = PacketEvents.getAPI().getServerManager().getVersion().getProtocolVersion();
                PacketEvents.getAPI().getLogManager().debug("No protocol translation plugins are available. We will assume " + user.getName() + "'s protocol version is the same as the server's protocol version. Protocol version: " + protocolVersion);
            }
            ClientVersion version = ClientVersion.getById(protocolVersion);
            user.setClientVersion(version);
        }
        return user.getClientVersion();
    }

    @Override
    public Object getChannel(@NotNull Object player) {
        UUID uuid = ((ServerPlayer) player).uniqueId();
        ProtocolManager protocolManager = PacketEvents.getAPI().getProtocolManager();
        Object channel = protocolManager.getChannel(uuid);
        if (channel == null) {
            channel = SpongeReflectionUtil.getChannel((ServerPlayer) player);
            // This is removed from the HashMap on channel close
            // So if the channel is already closed, there will be a memory leak if we add an offline player
            if (channel != null) {
                synchronized (channel) {
                    if (ChannelHelper.isOpen(channel)) {
                        protocolManager.setChannel(uuid, channel);
                    }
                }
            }
        }
        return channel;
    }

    @Override
    public User getUser(@NotNull Object player) {
        ServerPlayer p = (ServerPlayer) player;
        Object channel = getChannel(p);

        if (channel == null) return null;
        return PacketEvents.getAPI().getProtocolManager().getUser(channel);
    }
}
