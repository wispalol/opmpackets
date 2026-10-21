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

package opmpackets;

import opmpackets.PacketEvents;
import opmpackets.manager.server.ServerManager;
import opmpackets.factory.fabric.FabricClientPlayerManager;
import opmpackets.factory.fabric.FabricPacketEventsAPI;
import opmpackets.factory.fabric.FabricServerManager;
import opmpackets.impl.netty.manager.player.PlayerManagerAbstract;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.SharedConstants;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PacketEventsClientMod implements PreLaunchEntrypoint {

    public static FabricPacketEventsAPI constructApi(String modid) {
        return new FabricPacketEventsAPI(modid, EnvType.CLIENT) {
            @Override
            protected ServerManager constructServerManager() {
                SharedConstants.tryDetectVersion();
                String version = SharedConstants.getCurrentVersion().id();
                return new FabricServerManager(version);
            }

            @Override
            protected PlayerManagerAbstract constructPlayerManager() {
                return new FabricClientPlayerManager();
            }
        };
    }

    @Override
    public void onPreLaunch() {
        PacketEvents.setAPI(constructApi(PacketEventsMod.MOD_ID));
        PacketEvents.getAPI().load();
    }
}
