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

package opmpackets.protocol.stats;

import opmpackets.PacketEvents;
import opmpackets.manager.server.ServerVersion;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTString;
import opmpackets.protocol.nbt.serializer.SequentialNBTReader;
import opmpackets.util.adventure.AdventureSerializer;
import opmpackets.util.mappings.MappingHelper;
import net.kyori.adventure.text.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private static final Map<String, Statistic> STATISTIC_MAP = new HashMap<>();

    public static Statistic getById(String id) {
        return STATISTIC_MAP.get(id);
    }

    static {
        ServerVersion version = PacketEvents.getAPI().getServerManager().getVersion();

        if (version.isOlderThan(ServerVersion.V_1_12_2)) {

            try (final SequentialNBTReader.Compound rootMapping = MappingHelper.decompress("mappings/data/statistics")) {
                rootMapping.skipOne(); //Skip version
                SequentialNBTReader.Compound mapping = (SequentialNBTReader.Compound) rootMapping.next().getValue();

                if (version.isOlderThanOrEquals(ServerVersion.V_1_8_3)) {
                    mapping.skipOne(); // Skip to version 1.8
                }

                SequentialNBTReader.Compound toLoad = (SequentialNBTReader.Compound) mapping.next().getValue();

                for (Map.Entry<String, NBT> entry : toLoad) {
                    String value = ((NBTString) entry.getValue()).getValue();
                    Statistic statistic = new Statistic() {
                        private Component cachedDisplay;

                        @Override
                        public String getId() {
                            return entry.getKey();
                        }

                        @Override
                        public Component display() {
                            if (cachedDisplay == null) {
                                cachedDisplay = AdventureSerializer.serializer().fromJson(value);
                            }
                            return cachedDisplay;
                        }

                        @Override
                        public boolean equals(Object obj) {
                            if (obj instanceof Statistic) {
                                return ((Statistic) obj).getId().equals(this.getId());
                            }
                            return false;
                        }
                    };

                    STATISTIC_MAP.put(entry.getKey(), statistic);
                }
            } catch (IOException e) {
                throw new RuntimeException("Cannot load statistics mappings", e);
            }
        }
    }

}
