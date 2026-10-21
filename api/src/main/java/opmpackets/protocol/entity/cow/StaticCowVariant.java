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

package opmpackets.protocol.entity.cow;

import opmpackets.protocol.mapper.AbstractMappedEntity;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.mappings.TypesBuilderData;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * @versions 1.21.5+
 */
@NullMarked
public class StaticCowVariant extends AbstractMappedEntity implements CowVariant {

    private final ModelType modelType;
    private final ResourceLocation assetId;
    /**
     * @versions 26.1+
     */
    private final ResourceLocation babyAssetId;

    /**
     * @versions 1.21.5-1.21.11
     */
    @ApiStatus.Obsolete
    public StaticCowVariant(ModelType modelType, ResourceLocation assetId) {
        this(null, modelType, assetId, assetId);
    }

    /**
     * @versions 26.1+
     */
    public StaticCowVariant(ModelType modelType, ResourceLocation assetId, ResourceLocation babyAssetId) {
        this(null, modelType, assetId, babyAssetId);
    }

    @ApiStatus.Internal
    public StaticCowVariant(
            @Nullable TypesBuilderData data, ModelType modelType,
            ResourceLocation assetId, ResourceLocation babyAssetId
    ) {
        super(data);
        this.modelType = modelType;
        this.assetId = assetId;
        this.babyAssetId = babyAssetId;
    }

    @Override
    public CowVariant copy(@Nullable TypesBuilderData newData) {
        return new StaticCowVariant(newData, this.modelType, this.assetId, this.babyAssetId);
    }

    @Override
    public ModelType getModelType() {
        return this.modelType;
    }

    @Override
    public ResourceLocation getAssetId() {
        return this.assetId;
    }

    @Override
    public ResourceLocation getBabyAssetId() {
        return this.babyAssetId;
    }

    @Override
    public boolean deepEquals(@Nullable Object obj) {
        if (!(obj instanceof StaticCowVariant)) return false;
        StaticCowVariant that = (StaticCowVariant) obj;
        if (!this.modelType.equals(that.modelType)) return false;
        if (!this.assetId.equals(that.assetId)) return false;
        return this.babyAssetId.equals(that.babyAssetId);
    }

    @Override
    public int deepHashCode() {
        return Objects.hash(this.modelType, this.assetId, this.babyAssetId);
    }
}
