package opmpackets.sponge.manager;

import opmpackets.PacketEvents;
import opmpackets.event.PacketReceiveEvent;
import opmpackets.manager.InternalPacketListener;
import opmpackets.protocol.ConnectionState;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.util.LogManager;
import opmpackets.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import opmpackets.sponge.util.viaversion.ViaVersionUtil;

public class InternalSpongePacketListener extends InternalPacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Handshaking.Client.HANDSHAKE) {
            User user = event.getUser();
            WrapperHandshakingClientHandshake packet = new WrapperHandshakingClientHandshake(event);
            ClientVersion clientVersion = packet.getClientVersion();
            ConnectionState state = packet.getNextConnectionState();

            String feature;
            if (ViaVersionUtil.isAvailable()) {
                clientVersion = ClientVersion.getById(ViaVersionUtil.getProtocolVersion(user));
                feature = "ViaVersion";
            } else {
                feature = null;
            }

            LogManager logger = PacketEvents.getAPI().getLogManager();
            if (logger.isDebug()) {
                logger.debug("Processed handshake for " + event.getAddress() + ": "
                        + state.name() + " / " + packet.getClientVersion().getReleaseName()
                        + (feature != null ? " (using " + feature + ")" : ""));
            }

            user.setClientVersion(clientVersion);
            user.setConnectionState(state);
        } else {
            super.onPacketReceive(event);
        }
    }
}
