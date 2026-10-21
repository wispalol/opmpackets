/*
 * This file is part of opmpackets - https://github.com/wispalol/opmpackets
 * Copyright (C) 2022 retrooper and contributors
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

package opmpackets.protocol.item.type;

import opmpackets.PacketEvents;
import opmpackets.protocol.component.StaticComponentMap;
import opmpackets.protocol.item.type.ItemTypes.ItemAttribute;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.world.states.type.StateType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface ItemType extends MappedEntity {

    int getMaxAmount();

    int getMaxDurability();

    default boolean isMusicDisc() {
        return this.hasAttribute(ItemAttribute.MUSIC_DISC);
    }

    ItemType getCraftRemainder();

    @Nullable
    StateType getPlacedType();

    Set<ItemAttribute> getAttributes();

    default boolean hasAttribute(ItemAttribute attribute) {
        return this.getAttributes().contains(attribute);
    }

    @Deprecated
    default StaticComponentMap getComponents() {
        return this.getComponents(PacketEvents.getAPI().getServerManager().getVersion().toClientVersion());
    }

    default StaticComponentMap getComponents(ClientVersion clientVersion) {
        return StaticComponentMap.EMPTY;
    }
}
