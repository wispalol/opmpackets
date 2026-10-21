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

package opmpackets.protocol.component.builtin;

import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.entity.type.EntityType;
import opmpackets.protocol.entity.type.EntityTypes;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public final class TypedEntityData {

    private static final EntityType FALLBACK_TYPE = EntityTypes.PIG;

    /**
     * @versions 1.21.9+
     */
    private final EntityType type;
    private final NBTCompound compound;

    public TypedEntityData(NBTCompound compound) {
        this(FALLBACK_TYPE, compound);
    }

    public TypedEntityData(EntityType type, NBTCompound compound) {
        this.type = type;
        this.compound = compound;
    }

    public static TypedEntityData read(PacketWrapper<?> wrapper) {
        EntityType type = wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_9)
                ? wrapper.readMappedEntity(EntityTypes.getRegistry()) : FALLBACK_TYPE;
        NBTCompound compound = wrapper.readNBT();
        return new TypedEntityData(type, compound);
    }

    public static void write(PacketWrapper<?> wrapper, TypedEntityData data) {
        if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_9)) {
            wrapper.writeMappedEntity(data.type);
        }
        wrapper.writeNBT(data.compound);
    }

    public EntityType getType() {
        return this.type;
    }

    public NBTCompound getCompound() {
        return this.compound;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        TypedEntityData that = (TypedEntityData) obj;
        if (!this.type.equals(that.type)) return false;
        return this.compound.equals(that.compound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.compound);
    }
}
