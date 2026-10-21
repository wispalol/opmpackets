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
import opmpackets.protocol.packettype.PacketType;
import opmpackets.util.adventure.AdventureSerializer;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.text.Component;

public class WrapperPlayServerActionBar extends PacketWrapper<WrapperPlayServerActionBar> {
    private Component actionBarText;

    public WrapperPlayServerActionBar(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerActionBar(Component actionBarText) {
        super(PacketType.Play.Server.ACTION_BAR);
        this.actionBarText = actionBarText;
    }

    @Override
    public void read() {
        this.actionBarText = this.readComponent();
    }

    @Override
    public void write() {
        this.writeComponent(this.actionBarText);
    }

    @Override
    public void copy(WrapperPlayServerActionBar wrapper) {
        this.actionBarText = wrapper.actionBarText;
    }

    public Component getActionBarText() {
        return actionBarText;
    }

    public void setActionBarText(Component actionBarText) {
        this.actionBarText = actionBarText;
    }
}
