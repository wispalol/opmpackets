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

package opmpackets.util;

import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.player.Equipment;
import opmpackets.protocol.world.chunk.LightData;
import opmpackets.wrapper.PacketWrapper;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkData;
import opmpackets.wrapper.play.server.WrapperPlayServerDestroyEntities;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityEquipment;
import opmpackets.wrapper.play.server.WrapperPlayServerSetCursorItem;
import opmpackets.wrapper.play.server.WrapperPlayServerSetPlayerInventory;
import opmpackets.wrapper.play.server.WrapperPlayServerSetSlot;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateLight;

import java.util.Collections;

public class PacketTransformationUtil {
    public static PacketWrapper<?>[] transform(PacketWrapper<?> wrapper) {
        if (wrapper instanceof WrapperPlayServerDestroyEntities) {
            WrapperPlayServerDestroyEntities destroyEntities = (WrapperPlayServerDestroyEntities) wrapper;
            int len = destroyEntities.getEntityIds().length;
            if (wrapper.getServerVersion() == ServerVersion.V_1_17 && len > 1) {
                //Transform into multiple packets
                PacketWrapper<?>[] output = new PacketWrapper[len];
                for (int i = 0; i < len; i++) {
                    int entityId = destroyEntities.getEntityIds()[i];
                    output[i] = new WrapperPlayServerDestroyEntities(entityId);
                }
                return output;
            }
        } else if (wrapper instanceof WrapperPlayServerEntityEquipment) {
            WrapperPlayServerEntityEquipment entityEquipment = (WrapperPlayServerEntityEquipment) wrapper;
            int len = entityEquipment.getEquipment().size();
            if (entityEquipment.getServerVersion().isOlderThan(ServerVersion.V_1_16) && len > 1) {
                //Transform into multiple packets. Actually a mistake on the user's end.
                PacketWrapper<?>[] output = new PacketWrapper[len];
                for (int i = 0; i < len; i++) {
                    Equipment equipment = entityEquipment.getEquipment().get(i);
                    output[i] = new WrapperPlayServerEntityEquipment(entityEquipment.getEntityId(), Collections.singletonList(equipment));
                }
                return output;
            }
        } else if (wrapper instanceof WrapperPlayServerChunkData) {
            WrapperPlayServerChunkData chunkData = (WrapperPlayServerChunkData) wrapper;
            LightData lightData = chunkData.getLightData();

            if (chunkData.getServerVersion().isOlderThan(ServerVersion.V_1_18) && lightData != null) {
                //Transform into chunk data & light data packets
                PacketWrapper<?>[] output = new PacketWrapper[2];
                output[0] = new WrapperPlayServerUpdateLight(
                        chunkData.getColumn().getX(),
                        chunkData.getColumn().getZ(),
                        lightData
                );
                // Light packet must be sent first
                output[1] = chunkData;

                return output;
            }
        } else if (wrapper instanceof WrapperPlayServerSetSlot) {
            // some plugins will probably fail to update this correctly, so just transform it when sending
            WrapperPlayServerSetSlot setSlot = (WrapperPlayServerSetSlot) wrapper;
            if (setSlot.getSlot() == -1) { // transform to cursor item
                if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
                    wrapper = new WrapperPlayServerSetCursorItem(setSlot.getItem());
                }
            } else if (setSlot.getWindowId() == -2) { // transform to player inventory set
                if (wrapper.getServerVersion().isNewerThanOrEquals(ServerVersion.V_1_21_2)) {
                    wrapper = new WrapperPlayServerSetPlayerInventory(setSlot.getSlot(), setSlot.getItem());
                }
            }
        }
        return new PacketWrapper<?>[]{wrapper};
    }
}
