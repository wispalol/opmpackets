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

package opmpackets.protocol.item.consumables;

import opmpackets.wrapper.PacketWrapper;

public abstract class ConsumeEffect<T extends ConsumeEffect<?>> {

    protected final ConsumeEffectType<T> type;

    protected ConsumeEffect(ConsumeEffectType<T> type) {
        this.type = type;
    }

    public static ConsumeEffect<?> readFull(PacketWrapper<?> wrapper) {
        ConsumeEffectType<?> type = wrapper.readMappedEntity(ConsumeEffectTypes.getRegistry());
        return type.read(wrapper);
    }

    @SuppressWarnings("unchecked") // I hate generics
    public static <T extends ConsumeEffect<?>> void writeFull(PacketWrapper<?> wrapper, ConsumeEffect<T> effect) {
        wrapper.writeMappedEntity(effect.getType());
        ((ConsumeEffectType<ConsumeEffect<?>>) effect.getType()).write(wrapper, effect);
    }

    public ConsumeEffectType<T> getType() {
        return this.type;
    }
}
