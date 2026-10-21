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

import opmpackets.protocol.entity.llama.LlamaVariant;
import opmpackets.protocol.entity.llama.LlamaVariants;
import opmpackets.wrapper.PacketWrapper;

import java.util.Objects;

public class LlamaVariantComponent {

    private LlamaVariant variant;

    public LlamaVariantComponent(LlamaVariant variant) {
        this.variant = variant;
    }

    public static LlamaVariantComponent read(PacketWrapper<?> wrapper) {
        LlamaVariant type = wrapper.readMappedEntity(LlamaVariants.getRegistry());
        return new LlamaVariantComponent(type);
    }

    public static void write(PacketWrapper<?> wrapper, LlamaVariantComponent component) {
        wrapper.writeMappedEntity(component.variant);
    }

    public LlamaVariant getVariant() {
        return this.variant;
    }

    public void setVariant(LlamaVariant variant) {
        this.variant = variant;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LlamaVariantComponent)) return false;
        LlamaVariantComponent that = (LlamaVariantComponent) obj;
        return this.variant.equals(that.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.variant);
    }
}
