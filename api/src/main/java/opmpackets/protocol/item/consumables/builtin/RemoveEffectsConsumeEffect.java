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

package opmpackets.protocol.item.consumables.builtin;

import opmpackets.protocol.item.consumables.ConsumeEffect;
import opmpackets.protocol.item.consumables.ConsumeEffectTypes;
import opmpackets.protocol.mapper.MappedEntitySet;
import opmpackets.protocol.potion.PotionType;
import opmpackets.protocol.potion.PotionTypes;
import opmpackets.wrapper.PacketWrapper;

public class RemoveEffectsConsumeEffect extends ConsumeEffect<RemoveEffectsConsumeEffect> {

    private final MappedEntitySet<PotionType> effects;

    public RemoveEffectsConsumeEffect(MappedEntitySet<PotionType> effects) {
        super(ConsumeEffectTypes.REMOVE_EFFECTS);
        this.effects = effects;
    }

    public static RemoveEffectsConsumeEffect read(PacketWrapper<?> wrapper) {
        MappedEntitySet<PotionType> effects = MappedEntitySet.read(wrapper, PotionTypes.getRegistry());
        return new RemoveEffectsConsumeEffect(effects);
    }

    public static void write(PacketWrapper<?> wrapper, RemoveEffectsConsumeEffect effect) {
        MappedEntitySet.write(wrapper, effect.effects);
    }

    public MappedEntitySet<PotionType> getEffects() {
        return this.effects;
    }
}
