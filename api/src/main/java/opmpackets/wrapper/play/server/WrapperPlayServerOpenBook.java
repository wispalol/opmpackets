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
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.player.InteractionHand;
import opmpackets.wrapper.PacketWrapper;

public class WrapperPlayServerOpenBook extends PacketWrapper<WrapperPlayServerOpenBook> {
    private InteractionHand hand;

    public WrapperPlayServerOpenBook(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerOpenBook(InteractionHand hand) {
        super(PacketType.Play.Server.OPEN_BOOK);
        this.hand = hand;
    }

    @Override
    public void read() {
        hand = InteractionHand.values()[readVarInt()];
    }

    @Override
    public void write() {
        writeVarInt(hand.ordinal());
    }

    @Override
    public void copy(WrapperPlayServerOpenBook wrapper) {
        hand = wrapper.hand;
    }

    public InteractionHand getHand() {
        return hand;
    }

    public void setHand(InteractionHand hand) {
        this.hand = hand;
    }
}
