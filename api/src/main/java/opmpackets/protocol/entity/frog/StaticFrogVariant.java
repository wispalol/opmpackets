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

package opmpackets.protocol.entity.frog;

import opmpackets.protocol.mapper.AbstractMappedEntity;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.mappings.TypesBuilderData;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public class StaticFrogVariant extends AbstractMappedEntity implements FrogVariant {

    private final ResourceLocation assetId;

    public StaticFrogVariant(ResourceLocation assetId) {
        this(null, assetId);
    }

    @ApiStatus.Internal
    public StaticFrogVariant(@Nullable TypesBuilderData data, ResourceLocation assetId) {
        super(data);
        this.assetId = assetId;
    }

    @Override
    public FrogVariant copy(@Nullable TypesBuilderData newData) {
        return new StaticFrogVariant(newData, this.assetId);
    }

    @Override
    public ResourceLocation getAssetId() {
        return this.assetId;
    }

    @Override
    public boolean deepEquals(@Nullable Object obj) {
        if (!(obj instanceof StaticFrogVariant)) return false;
        StaticFrogVariant that = (StaticFrogVariant) obj;
        return this.assetId.equals(that.assetId);
    }

    @Override
    public int deepHashCode() {
        return Objects.hash(this.assetId);
    }
}
