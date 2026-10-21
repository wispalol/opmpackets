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

import opmpackets.protocol.world.states.type.StateType;
import opmpackets.protocol.world.states.type.StateTypes;
import opmpackets.test.base.BaseDummyAPITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnsureValidStateTypesTest extends BaseDummyAPITest {

    @Test
    @DisplayName("Verify non-null material type")
    public void testStateTypesMaterialType() {
        for (StateType stateType : StateTypes.values()) {
            assertNotNull(stateType.getMaterialType(),
                    () -> stateType.getName() + " has no material type");
        }
    }
}
