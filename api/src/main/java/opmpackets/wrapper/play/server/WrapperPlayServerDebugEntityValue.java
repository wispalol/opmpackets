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

package opmpackets.wrapper.play.server;

import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.debug.DebugSubscription;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

/**
 * Mojang name: ClientboundDebugEntityValuePacket
 *
 * @versions 1.21.9+
 */
@NullMarked
public class WrapperPlayServerDebugEntityValue extends PacketWrapper<WrapperPlayServerDebugEntityValue> {

    private int entityId;
    private DebugSubscription.Update<?> update;

    public WrapperPlayServerDebugEntityValue(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerDebugEntityValue(int entityId, DebugSubscription.Update<?> update) {
        super(PacketType.Play.Server.DEBUG_ENTITY_VALUE);
        this.entityId = entityId;
        this.update = update;
    }

    @Override
    public void read() {
        this.entityId = this.readVarInt();
        this.update = DebugSubscription.Update.read(this);
    }

    @Override
    public void write() {
        this.writeVarInt(this.entityId);
        DebugSubscription.Update.write(this, this.update);
    }

    @Override
    public void copy(WrapperPlayServerDebugEntityValue wrapper) {
        this.entityId = wrapper.entityId;
        this.update = wrapper.update;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public DebugSubscription.Update<?> getUpdate() {
        return this.update;
    }

    public void setUpdate(DebugSubscription.Update<?> update) {
        this.update = update;
    }
}
