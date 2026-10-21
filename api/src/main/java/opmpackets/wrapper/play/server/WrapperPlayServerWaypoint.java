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
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.world.waypoint.TrackedWaypoint;
import opmpackets.wrapper.PacketWrapper;

public class WrapperPlayServerWaypoint extends PacketWrapper<WrapperPlayServerWaypoint> {

    private Operation operation;
    private TrackedWaypoint waypoint;

    public WrapperPlayServerWaypoint(PacketSendEvent event) {
        super(event);
    }

    public WrapperPlayServerWaypoint(Operation operation, TrackedWaypoint waypoint) {
        super(PacketType.Play.Server.WAYPOINT);
        this.operation = operation;
        this.waypoint = waypoint;
    }

    @Override
    public void read() {
        this.operation = this.readEnum(Operation.class);
        // always reads the full waypoint, even if it will be removed...
        this.waypoint = TrackedWaypoint.read(this);
    }

    @Override
    public void write() {
        this.writeEnum(this.operation);
        // always writes the full waypoint, even if it will be removed...
        TrackedWaypoint.write(this, this.waypoint);
    }

    @Override
    public void copy(WrapperPlayServerWaypoint wrapper) {
        this.operation = wrapper.operation;
        this.waypoint = wrapper.waypoint;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public TrackedWaypoint getWaypoint() {
        return this.waypoint;
    }

    public void setWaypoint(TrackedWaypoint waypoint) {
        this.waypoint = waypoint;
    }

    public enum Operation {
        TRACK,
        UNTRACK,
        UPDATE,
    }
}
