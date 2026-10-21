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
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CopyToClipboardClickEvent implements ClickEvent {

    private final String value;

    public CopyToClipboardClickEvent(String value) {
        this.value = value;
    }

    public static CopyToClipboardClickEvent decode(NBTCompound compound, PacketWrapper<?> wrapper) {
        String value = compound.getStringTagValueOrThrow("value");
        return new CopyToClipboardClickEvent(value);
    }

    public static void encode(NBTCompound compound, PacketWrapper<?> wrapper, CopyToClipboardClickEvent clickEvent) {
        compound.setTag("value", new NBTString(clickEvent.value));
    }

    @Override
    public ClickEventAction<?> getAction() {
        return ClickEventActions.COPY_TO_CLIPBOARD;
    }

    @Override
    public net.kyori.adventure.text.event.ClickEvent asAdventure() {
        return net.kyori.adventure.text.event.ClickEvent.copyToClipboard(this.value);
    }

    public String getValue() {
        return this.value;
    }
}
