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

package opmpackets.protocol.chat.clickevent;

import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTString;
import opmpackets.wrapper.PacketWrapper;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Removed in 1.9
 */
@NullMarked
@ApiStatus.Obsolete
public class TwitchUserInfoClickEvent implements ClickEvent {

    private final String value;

    public TwitchUserInfoClickEvent(String value) {
        this.value = value;
    }

    public static TwitchUserInfoClickEvent decode(NBTCompound compound, PacketWrapper<?> wrapper) {
        String value = compound.getStringTagValueOrThrow("value");
        return new TwitchUserInfoClickEvent(value);
    }

    public static void encode(NBTCompound compound, PacketWrapper<?> wrapper, TwitchUserInfoClickEvent clickEvent) {
        compound.setTag("value", new NBTString(clickEvent.value));
    }

    @Override
    public ClickEventAction<?> getAction() {
        return ClickEventActions.TWITCH_USER_INFO;
    }

    @Override
    public net.kyori.adventure.text.event.ClickEvent asAdventure() {
        throw new UnsupportedOperationException();
    }

    public String getValue() {
        return this.value;
    }
}
