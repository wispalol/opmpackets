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

package opmpackets.protocol.world.chunk.reader.impl;

import opmpackets.protocol.world.chunk.BaseChunk;
import opmpackets.protocol.world.chunk.impl.v1_16.Chunk_v1_9;
import opmpackets.protocol.world.chunk.reader.ChunkReader;
import opmpackets.protocol.world.dimension.DimensionType;
import opmpackets.wrapper.PacketWrapper;

import java.util.BitSet;

public class ChunkReader_v1_9 implements ChunkReader {

    @Override
    public BaseChunk[] read(
            DimensionType dimensionType, BitSet chunkMask, BitSet secondaryChunkMask, boolean fullChunk,
            boolean hasBlockLight, boolean hasSkyLight, int chunkSize, int arrayLength, PacketWrapper<?> wrapper
    ) {
        BaseChunk[] chunks = new BaseChunk[chunkSize];

        for (int index = 0; index < chunks.length; ++index) {
            if (chunkMask.get(index)) {
                chunks[index] = Chunk_v1_9.read(wrapper, hasBlockLight, hasSkyLight);
            }
        }

        return chunks;
    }
}
