package opmpackets.event.simple;

import opmpackets.event.PacketSendEvent;
import opmpackets.exception.PacketProcessException;
import opmpackets.manager.server.ServerVersion;
import opmpackets.netty.buffer.ByteBufHelper;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.packettype.PacketTypeCommon;
import opmpackets.protocol.player.User;
import org.jetbrains.annotations.UnknownNullability;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PacketHandshakeSendEvent extends PacketSendEvent {

    public PacketHandshakeSendEvent(
            Object channel, User user,
            @UnknownNullability Object player, Object rawByteBuf,
            boolean autoProtocolTranslation
    ) throws PacketProcessException {
        super(channel, user, player, rawByteBuf, autoProtocolTranslation);
    }

    protected PacketHandshakeSendEvent(
            int packetId, PacketTypeCommon packetType, ServerVersion serverVersion,
            Object channel, User user,
            @UnknownNullability Object player, Object byteBuf
    ) throws PacketProcessException {
        super(packetId, packetType, serverVersion, channel, user, player, byteBuf);
    }

    @Override
    public PacketHandshakeSendEvent clone() {
        Object clonedBuffer = ByteBufHelper.retainedDuplicate(getByteBuf());
        return new PacketHandshakeSendEvent(getPacketId(), getPacketType(), getServerVersion(),
                getChannel(), getUser(), getPlayer(), clonedBuffer);
    }

    public PacketType.Handshaking.Client getPacketType() {
        return (PacketType.Handshaking.Client) super.getPacketType();
    }
}
