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

package opmpackets.wrapper.configuration.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.util.KnownPack;
import opmpackets.wrapper.PacketWrapper;

import java.util.List;

public class WrapperConfigServerSelectKnownPacks extends PacketWrapper<WrapperConfigServerSelectKnownPacks> {

    private List<KnownPack> knownPacks;

    public WrapperConfigServerSelectKnownPacks(PacketSendEvent event) {
        super(event);
    }

    public WrapperConfigServerSelectKnownPacks(List<KnownPack> knownPacks) {
        super(PacketType.Configuration.Server.SELECT_KNOWN_PACKS);
        this.knownPacks = knownPacks;
    }

    @Override
    public void read() {
        this.knownPacks = this.readList(PacketWrapper::readKnownPack);
    }

    @Override
    public void write() {
        this.writeList(this.knownPacks, PacketWrapper::writeKnownPack);
    }

    @Override
    public void copy(WrapperConfigServerSelectKnownPacks wrapper) {
        this.knownPacks = wrapper.knownPacks;
    }

    public List<KnownPack> getKnownPacks() {
        return this.knownPacks;
    }

    public void setKnownPacks(List<KnownPack> knownPacks) {
        this.knownPacks = knownPacks;
    }
}
