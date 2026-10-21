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

package opmpackets.wrapper.play.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.wrapper.PacketWrapper;

public class WrapperPlayServerNBTQueryResponse extends PacketWrapper<WrapperPlayServerNBTQueryResponse> {
    private int transactionId;
    private NBTCompound tag;

    public WrapperPlayServerNBTQueryResponse(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerNBTQueryResponse(int transactionId, NBTCompound tag) {
        super(PacketType.Play.Server.NBT_QUERY_RESPONSE);
        this.transactionId = transactionId;
        this.tag = tag;
    }

    @Override
    public void read() {
        transactionId = readVarInt();
        tag = readNBT();
    }

    @Override
    public void write() {
        writeVarInt(transactionId);
        writeNBT(tag);
    }

    @Override
    public void copy(WrapperPlayServerNBTQueryResponse wrapper) {
        transactionId = wrapper.transactionId;
        tag = wrapper.tag;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public NBTCompound getTag() {
        return tag;
    }

    public void setTag(NBTCompound tag) {
        this.tag = tag;
    }
}
