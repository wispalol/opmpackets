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
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.PacketWrapper;
import org.jetbrains.annotations.Nullable;

public class WrapperPlayServerSelectAdvancementsTab extends PacketWrapper<WrapperPlayServerSelectAdvancementsTab> {
    private @Nullable ResourceLocation identifier;

    public WrapperPlayServerSelectAdvancementsTab(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerSelectAdvancementsTab(@Nullable ResourceLocation identifier) {
        super(PacketType.Play.Server.SELECT_ADVANCEMENTS_TAB);
        this.identifier = identifier;
    }

    @Override
    public void read() {
        identifier = readOptional(PacketWrapper::readIdentifier);
    }

    @Override
    public void write() {
        writeOptional(identifier, PacketWrapper::writeIdentifier);
    }

    @Override
    public void copy(WrapperPlayServerSelectAdvancementsTab wrapper) {
        this.identifier = wrapper.identifier;
    }

    public @Nullable ResourceLocation getIdentifier() {
        return identifier;
    }

    public void setIdentifier(@Nullable ResourceLocation identifier) {
        this.identifier = identifier;
    }
}
