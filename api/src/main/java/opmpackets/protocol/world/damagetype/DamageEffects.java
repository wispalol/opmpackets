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

package opmpackets.protocol.world.damagetype;

import opmpackets.protocol.util.CodecNameable;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecs;
import net.kyori.adventure.util.Index;
import org.jspecify.annotations.NullMarked;

@NullMarked
public enum DamageEffects implements CodecNameable {

    HURT("hurt"),
    THORNS("thorns"),
    DROWNING("drowning"),
    BURNING("burning"),
    POKING("poking"),
    FREEZING("freezing");

    public static final Index<String, DamageEffects> ID_INDEX = Index.create(DamageEffects.class, DamageEffects::getId);
    public static final NbtCodec<DamageEffects> CODEC = NbtCodecs.forEnum(values());

    private final String id;

    DamageEffects(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getCodecName() {
        return this.id;
    }
}
