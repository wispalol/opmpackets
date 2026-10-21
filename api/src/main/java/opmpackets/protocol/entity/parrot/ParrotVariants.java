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

package opmpackets.protocol.entity.parrot;

import opmpackets.util.mappings.VersionedRegistry;
import org.jetbrains.annotations.ApiStatus;

public final class ParrotVariants {

    private static final VersionedRegistry<ParrotVariant> REGISTRY =
            new VersionedRegistry<>("parrot_variant");

    private ParrotVariants() {
    }

    @ApiStatus.Internal
    public static ParrotVariant define(String name) {
        return REGISTRY.define(name, StaticParrotVariant::new);
    }

    public static VersionedRegistry<ParrotVariant> getRegistry() {
        return REGISTRY;
    }

    public static final ParrotVariant RED_BLUE = define("red_blue");
    public static final ParrotVariant BLUE = define("blue");
    public static final ParrotVariant GREEN = define("green");
    public static final ParrotVariant YELLOW_BLUE = define("yellow_blue");
    public static final ParrotVariant GRAY = define("gray");

    static {
        REGISTRY.unloadMappings();
    }
}
