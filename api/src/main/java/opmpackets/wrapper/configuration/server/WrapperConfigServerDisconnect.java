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

package opmpackets.wrapper.configuration.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.wrapper.PacketWrapper;
import net.kyori.adventure.text.Component;

public class WrapperConfigServerDisconnect extends PacketWrapper<WrapperConfigServerDisconnect> {

    private Component reason;

    public WrapperConfigServerDisconnect(PacketSendEvent event) {
        super(event);
    }

    public WrapperConfigServerDisconnect(Component reason) {
        super(PacketType.Configuration.Server.DISCONNECT);
        this.reason = reason;
    }

    @Override
    public void read() {
        this.reason = this.readComponent();
    }

    @Override
    public void write() {
        this.writeComponent(this.reason);
    }

    @Override
    public void copy(WrapperConfigServerDisconnect wrapper) {
        this.reason = wrapper.reason;
    }

    public Component getReason() {
        return this.reason;
    }

    public void setReason(Component reason) {
        this.reason = reason;
    }
}
