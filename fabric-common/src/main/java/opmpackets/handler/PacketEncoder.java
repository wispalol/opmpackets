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

package opmpackets.handler;

import opmpackets.protocol.PacketSide;
import opmpackets.protocol.player.User;
import opmpackets.util.PacketEventsImplHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
@ApiStatus.Internal
public class PacketEncoder extends ChannelOutboundHandlerAdapter {

    private final PacketSide side;
    public User user;
    public @Nullable Object player;

    public PacketEncoder(PacketSide side, User user) {
        this.side = side;
        this.user = user;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof ByteBuf in)) {
            ctx.write(msg, promise);
            return;
        }
        if (!in.isReadable()) {
            in.release();
            return;
        }

        PacketEventsImplHelper.handlePacket(ctx.channel(),
                this.user, this.player, in, false, this.side);
        if (in.isReadable()) {
            ctx.write(in, promise);
        }
    }
}
