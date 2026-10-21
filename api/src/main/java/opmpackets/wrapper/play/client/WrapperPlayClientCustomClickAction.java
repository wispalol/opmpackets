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

package opmpackets.wrapper.play.client;

import opmpackets.event.PacketReceiveEvent;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.common.client.WrapperCommonClientCustomClickAction;
import org.jspecify.annotations.Nullable;

public class WrapperPlayClientCustomClickAction extends WrapperCommonClientCustomClickAction<WrapperPlayClientCustomClickAction> {

    public WrapperPlayClientCustomClickAction(PacketReceiveEvent event) {
        super(event);
    }

    public WrapperPlayClientCustomClickAction(ResourceLocation id, @Nullable NBT payload) {
        super(PacketType.Play.Client.CUSTOM_CLICK_ACTION, id, payload);
    }
}
