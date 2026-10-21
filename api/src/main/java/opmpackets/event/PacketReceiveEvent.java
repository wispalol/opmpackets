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

package opmpackets.event;

import opmpackets.exception.PacketProcessException;
import opmpackets.manager.server.ServerVersion;
import opmpackets.netty.buffer.ByteBufHelper;
import opmpackets.protocol.PacketSide;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.protocol.player.User;
import org.jetbrains.annotations.UnknownNullability;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PacketReceiveEvent extends ProtocolPacketEvent {

    protected PacketReceiveEvent(
            Object channel, User user,
            @UnknownNullability Object player, Object rawByteBuf,
            boolean autoProtocolTranslation
    ) throws PacketProcessException {
        super(PacketSide.CLIENT, channel, user, player, rawByteBuf, autoProtocolTranslation);
    }

    protected PacketReceiveEvent(
            int packetID, PacketTypeCommon packetType, ServerVersion serverVersion,
            Object channel, User user,
            @UnknownNullability Object player, Object byteBuf
    ) throws PacketProcessException {
        super(packetID, packetType, serverVersion,
                channel, user, player, byteBuf);
    }

    @Override
    public void call(PacketListenerCommon listener) {
        listener.onPacketReceive(this);
    }

    @Override
    public PacketReceiveEvent clone() {
        Object clonedBuffer = ByteBufHelper.retainedDuplicate(getByteBuf());
        return new PacketReceiveEvent(getPacketId(), getPacketType(), getServerVersion(),
                getChannel(), getUser(), getPlayer(), clonedBuffer);
    }
}
