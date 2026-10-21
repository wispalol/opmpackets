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

package opmpackets.wrapper.login.client;

import opmpackets.event.PacketReceiveEvent;
import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.common.client.WrapperCommonCookieResponse;
import org.jetbrains.annotations.Nullable;

public class WrapperLoginClientCookieResponse extends WrapperCommonCookieResponse<WrapperLoginClientCookieResponse> {

    @Deprecated
    public WrapperLoginClientCookieResponse(PacketSendEvent event) {
        super(event);
    }

    public WrapperLoginClientCookieResponse(PacketReceiveEvent event) {
        super(event);
    }

    public WrapperLoginClientCookieResponse(ResourceLocation key, byte @Nullable [] payload) {
        super(PacketType.Login.Client.COOKIE_RESPONSE, key, payload);
    }
}
