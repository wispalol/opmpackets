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

package opmpackets.protocol.entity.wolfvariant;

import opmpackets.protocol.mapper.MappedEntitySet;
import opmpackets.protocol.world.biome.Biome;
import opmpackets.protocol.world.biome.Biomes;
import opmpackets.resources.ResourceLocation;
import opmpackets.util.mappings.VersionedRegistry;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;

/**
 * @versions 1.20.5+
 */
public final class WolfVariants {

    private static final VersionedRegistry<WolfVariant> REGISTRY = new VersionedRegistry<>("wolf_variant");

    private WolfVariants() {
    }

    @ApiStatus.Internal
    public static WolfVariant define(String name, MappedEntitySet<Biome> biomes) {
        return define(name, "wolf_" + name, biomes);
    }

    @SuppressWarnings("PatternValidation")
    @ApiStatus.Internal
    public static WolfVariant define(String name, String assetId, MappedEntitySet<Biome> biomes) {
        WolfAssetSet assets = WolfAssetSet.getOrThrow(ResourceLocation.VANILLA_NAMESPACE,
                "entity/wolf/" + assetId, "");
        WolfAssetSet babyAssets = WolfAssetSet.getOrThrow(ResourceLocation.VANILLA_NAMESPACE,
                "entity/wolf/" + assetId, "_baby");
        return define(name, assets, babyAssets, biomes);
    }

    @ApiStatus.Internal
    public static WolfVariant define(
            String name, WolfAssetSet assets, WolfAssetSet babyAssets, MappedEntitySet<Biome> biomes
    ) {
        return REGISTRY.define(name, data -> new StaticWolfVariant(
                data, assets, babyAssets, biomes));
    }

    public static VersionedRegistry<WolfVariant> getRegistry() {
        return REGISTRY;
    }

    public static final WolfVariant PALE = define("pale", "wolf",
            new MappedEntitySet<>(Collections.singletonList(Biomes.TAIGA)));
    public static final WolfVariant SPOTTED = define("spotted",
            new MappedEntitySet<>(ResourceLocation.minecraft("is_savanna")));
    public static final WolfVariant SNOWY = define("snowy",
            new MappedEntitySet<>(Collections.singletonList(Biomes.GROVE)));
    public static final WolfVariant BLACK = define("black",
            new MappedEntitySet<>(Collections.singletonList(Biomes.OLD_GROWTH_PINE_TAIGA)));
    public static final WolfVariant ASHEN = define("ashen",
            new MappedEntitySet<>(Collections.singletonList(Biomes.SNOWY_TAIGA)));
    public static final WolfVariant RUSTY = define("rusty",
            new MappedEntitySet<>(ResourceLocation.minecraft("is_jungle")));
    public static final WolfVariant WOODS = define("woods",
            new MappedEntitySet<>(Collections.singletonList(Biomes.FOREST)));
    public static final WolfVariant CHESTNUT = define("chestnut",
            new MappedEntitySet<>(Collections.singletonList(Biomes.OLD_GROWTH_SPRUCE_TAIGA)));
    public static final WolfVariant STRIPED = define("striped",
            new MappedEntitySet<>(ResourceLocation.minecraft("is_badlands")));

    static {
        REGISTRY.unloadMappings();
    }
}
