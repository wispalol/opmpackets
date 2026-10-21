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

package opmpackets.test;

import opmpackets.protocol.component.ComponentTypes;
import opmpackets.protocol.component.StaticComponentMap;
import opmpackets.protocol.item.type.ItemTypes;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.test.base.BaseDummyAPITest;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ItemBaseComponentTest extends BaseDummyAPITest {

    @Test
    @DisplayName("Test loading of item component data")
    public void testItemLoading() {
        assertDoesNotThrow(ItemTypes::getRegistry);
    }

    @Test
    @DisplayName("Test loaded item component data")
    public void testItemComponents() {
        StaticComponentMap stoneComponents = ItemTypes.STONE.getComponents(ClientVersion.V_1_20_5);
        assertEquals(64, stoneComponents.get(ComponentTypes.MAX_STACK_SIZE));

        StaticComponentMap oakBoatComponents = ItemTypes.OAK_BOAT.getComponents(ClientVersion.V_1_20_5);
        assertEquals(1, oakBoatComponents.get(ComponentTypes.MAX_STACK_SIZE));

        StaticComponentMap airComponents1205 = ItemTypes.AIR.getComponents(ClientVersion.V_1_20_5);
        StaticComponentMap airComponents1212 = ItemTypes.AIR.getComponents(ClientVersion.V_1_21_2);
        assertNotEquals(airComponents1212, airComponents1205);
        assertNull(airComponents1205.get(ComponentTypes.ITEM_NAME));
        assertEquals(Component.translatable("block.minecraft.air"),
                airComponents1212.get(ComponentTypes.ITEM_NAME));
    }
}
