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

package opmpackets.protocol.entity.fox;

import opmpackets.util.mappings.VersionedRegistry;
import org.jetbrains.annotations.ApiStatus;

public final class FoxVariants {

    private static final VersionedRegistry<FoxVariant> REGISTRY =
            new VersionedRegistry<>("fox_variant");

    private FoxVariants() {
    }

    @ApiStatus.Internal
    public static FoxVariant define(String name) {
        return REGISTRY.define(name, StaticFoxVariant::new);
    }

    public static VersionedRegistry<FoxVariant> getRegistry() {
        return REGISTRY;
    }

    public static final FoxVariant RED = define("red");
    public static final FoxVariant SNOW = define("snow");

    static {
        REGISTRY.unloadMappings();
    }
}
