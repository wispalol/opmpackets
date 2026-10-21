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

package opmpackets.wrapper.play.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.item.ItemStack;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.wrapper.PacketWrapper;

public class WrapperPlayServerSetCursorItem extends PacketWrapper<WrapperPlayServerSetCursorItem> {

    private ItemStack stack;

    public WrapperPlayServerSetCursorItem(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerSetCursorItem(ItemStack stack) {
        super(PacketType.Play.Server.SET_CURSOR_ITEM);
        this.stack = stack;
    }

    @Override
    public void read() {
        this.stack = this.readItemStack();
    }

    @Override
    public void write() {
        this.writeItemStack(this.stack);
    }

    @Override
    public void copy(WrapperPlayServerSetCursorItem wrapper) {
        this.stack = wrapper.stack;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }
}
