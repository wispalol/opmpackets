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

import java.util.HashSet;
import java.util.Set;

public class WrapperPlayServerUpdateEnabledFeatures extends PacketWrapper<WrapperPlayServerUpdateEnabledFeatures> {
    private Set<ResourceLocation> features;
    public WrapperPlayServerUpdateEnabledFeatures(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerUpdateEnabledFeatures(Set<ResourceLocation> features) {
        super(PacketType.Play.Server.UPDATE_ENABLED_FEATURES);
        this.features = features;
    }

    @Override
    public void read() {
        features = readCollection(HashSet::new, PacketWrapper::readIdentifier);
    }

    @Override
    public void write() {
        writeCollection(features, PacketWrapper::writeIdentifier);
    }

    @Override
    public void copy(WrapperPlayServerUpdateEnabledFeatures wrapper) {
        this.features = wrapper.features;
    }

    public Set<ResourceLocation> getFeatures() {
        return features;
    }

    public void setFeatures(Set<ResourceLocation> features) {
        this.features = features;
    }
}
