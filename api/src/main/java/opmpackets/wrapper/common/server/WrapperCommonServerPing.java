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

package opmpackets.wrapper.common.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.wrapper.PacketWrapper;
import opmpackets.wrapper.play.client.WrapperPlayClientPong;

/**
 * This packet is currently used by mods to synchronize the client with the server.
 * It is not used by the vanilla server.
 * Most likely added as a replacement to the removed Window Confirmation packet.
 *
 * @see WrapperPlayClientPong
 */
public class WrapperCommonServerPing<T extends WrapperCommonServerPing<T>> extends PacketWrapper<T> {

    private int id;

    public WrapperCommonServerPing(PacketSendEvent event) {
        super(event);
    }

    public WrapperCommonServerPing(PacketTypeCommon packetType, int id) {
        super(packetType);
        this.id = id;
    }

    @Override
    public void read() {
        this.id = this.readInt();
    }

    @Override
    public void write() {
        this.writeInt(this.id);
    }

    @Override
    public void copy(T wrapper) {
        this.id = wrapper.getId();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
