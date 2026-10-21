/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2026 retrooper and contributors
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
import opmpackets.protocol.packettype.PacketType;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.PacketWrapper;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * Mojang name: ServerboundSetGameRulePacket
 *
 * @versions 26.1+
 */
@NullMarked
public class WrapperPlayClientSetGameRule extends PacketWrapper<WrapperPlayClientSetGameRule> {

    private @MonotonicNonNull List<Entry> entries;

    public WrapperPlayClientSetGameRule(PacketReceiveEvent event) {
        super(event);
    }

    public WrapperPlayClientSetGameRule(List<Entry> entries) {
        super(PacketType.Play.Client.SET_GAME_RULE);
        this.entries = entries;
    }

    @Override
    public void read() {
        this.entries = this.readList(Entry::read);
    }

    @Override
    public void write() {
        this.writeList(this.entries, Entry::write);
    }

    @Override
    public void copy(WrapperPlayClientSetGameRule wrapper) {
        this.entries = wrapper.entries;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public static final class Entry {

        private final ResourceLocation gameRule;
        private final String value;

        public Entry(ResourceLocation gameRule, String value) {
            this.gameRule = gameRule;
            this.value = value;
        }

        public static Entry read(PacketWrapper<?> wrapper) {
            ResourceLocation gameRule = ResourceLocation.read(wrapper);
            String value = wrapper.readString();
            return new Entry(gameRule, value);
        }

        public static void write(PacketWrapper<?> wrapper, Entry entry) {
            ResourceLocation.write(wrapper, entry.gameRule);
            wrapper.writeString(entry.value);
        }

        public ResourceLocation getGameRule() {
            return this.gameRule;
        }

        public String getValue() {
            return this.value;
        }
    }
}
