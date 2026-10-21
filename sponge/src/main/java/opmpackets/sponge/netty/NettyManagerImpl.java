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

package opmpackets.sponge.netty;

import opmpackets.netty.NettyManager;
import opmpackets.netty.buffer.ByteBufAllocationOperator;
import opmpackets.netty.buffer.ByteBufOperator;
import opmpackets.netty.channel.ChannelOperator;
import opmpackets.sponge.netty.buffer.ByteBufAllocationOperatorModernImpl;
import opmpackets.sponge.netty.buffer.ByteBufOperatorModernImpl;
import opmpackets.sponge.netty.channel.ChannelOperatorModernImpl;

public class NettyManagerImpl implements NettyManager {
    private static final ByteBufOperator BYTE_BUF_OPERATOR = new ByteBufOperatorModernImpl();
    private static final ByteBufAllocationOperator BYTE_BUF_ALLOCATION_OPERATOR = new ByteBufAllocationOperatorModernImpl();
    private static final ChannelOperator CHANNEL_OPERATOR = new ChannelOperatorModernImpl();

    @Override
    public ChannelOperator getChannelOperator() {
        return CHANNEL_OPERATOR;
    }

    @Override
    public ByteBufOperator getByteBufOperator() {
        return BYTE_BUF_OPERATOR;
    }

    @Override
    public ByteBufAllocationOperator getByteBufAllocationOperator() {
        return BYTE_BUF_ALLOCATION_OPERATOR;
    }
}
