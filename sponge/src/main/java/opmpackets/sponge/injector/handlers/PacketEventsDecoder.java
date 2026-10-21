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

package opmpackets.sponge.injector.handlers;

import opmpackets.PacketEvents;
import opmpackets.exception.PacketProcessException;
import opmpackets.netty.buffer.ByteBufHelper;
import opmpackets.protocol.ConnectionState;
import opmpackets.protocol.player.User;
import opmpackets.util.ExceptionUtil;
import opmpackets.util.PacketEventsImplHelper;
import opmpackets.wrapper.play.server.WrapperPlayServerDisconnect;
import opmpackets.sponge.injector.connection.ServerConnectionInitializer;
import opmpackets.sponge.util.SpongeReflectionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;
import java.util.UUID;

public class PacketEventsDecoder extends MessageToMessageDecoder<ByteBuf> {

    public User user;
    public UUID player;
    public boolean hasBeenRelocated;

    public PacketEventsDecoder(User user) {
        this.user = user;
    }

    public PacketEventsDecoder(PacketEventsDecoder decoder) {
        user = decoder.user;
        player = decoder.player;
        hasBeenRelocated = decoder.hasBeenRelocated;
    }

    public void read(ChannelHandlerContext ctx, ByteBuf input, List<Object> out) throws Exception {
        PacketEventsImplHelper.handleServerBoundPacket(ctx.channel(), user, player == null ? null : Sponge.server().player(player).orElse(null), input, true);
        out.add(ByteBufHelper.retain(input));
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        if (buffer.isReadable()) {
            read(ctx, buffer, out);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        //Check if the minecraft server will already print our exception for us.
        //Don't print errors during handshake
        if (ExceptionUtil.isException(cause, PacketProcessException.class)
                && !SpongeReflectionUtil.isMinecraftServerInstanceDebugging()
                && (user != null && user.getDecoderState() != ConnectionState.HANDSHAKING)) {
            if (PacketEvents.getAPI().getSettings().isFullStackTraceEnabled()) {
                cause.printStackTrace();
            } else {
                PacketEvents.getAPI().getLogManager().warn(cause.getMessage());
            }

            if (PacketEvents.getAPI().getSettings().isKickOnPacketExceptionEnabled()) {
                try {
                    user.sendPacket(new WrapperPlayServerDisconnect(Component.text("Invalid packet")));
                } catch (Exception ignored) { // There may (?) be an exception if the player is in the wrong state...
                    // Do nothing.
                }
                user.closeConnection();
                if (player != null) {
                    Sponge.server().scheduler().submit(Task.builder().plugin((PluginContainer) PacketEvents.getAPI().getPlugin())
                            .execute(() -> Sponge.server().player(player).get().kick(Component.text("Invalid packet"))).build());
                }

                if (user != null && user.getProfile().getName() != null) {
                    PacketEvents.getAPI().getLogManager().warn("Disconnected " + user.getProfile().getName() + " due to an invalid packet!");
                }
            }
        }
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object event) throws Exception {
        // Via changes the order of handlers in this event, so we must respond to Via changing their stuff
        ServerConnectionInitializer.relocateHandlers(ctx.channel(), this, user);
        super.userEventTriggered(ctx, event);
    }

}
