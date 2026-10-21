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

import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTEnd;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.adventure.NbtTagHolder;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class CustomClickEvent implements ClickEvent {

    private final ResourceLocation id;
    private final @Nullable NBT payload;

    public CustomClickEvent(ResourceLocation id, @Nullable NBT payload) {
        this.id = id;
        this.payload = payload;
    }

    public static CustomClickEvent decode(NBTCompound compound, PacketWrapper<?> wrapper) {
        ResourceLocation id = compound.getOrThrow("id", ResourceLocation::decode, wrapper);
        NBT payload = compound.getTagOrNull("payload");
        return new CustomClickEvent(id, payload);
    }

    public static void encode(NBTCompound compound, PacketWrapper<?> wrapper, CustomClickEvent clickEvent) {
        compound.set("id", clickEvent.id, ResourceLocation::encode, wrapper);
        if (clickEvent.payload != null) {
            compound.setTag("payload", clickEvent.payload);
        }
    }

    @Override
    public ClickEventAction<?> getAction() {
        return ClickEventActions.CUSTOM;
    }

    @Override
    public net.kyori.adventure.text.event.ClickEvent asAdventure() {
        return net.kyori.adventure.text.event.ClickEvent.custom(
                this.id.key(), new NbtTagHolder(this.payload != null ? this.payload : NBTEnd.INSTANCE));
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public @Nullable NBT getPayload() {
        return this.payload;
    }
}
