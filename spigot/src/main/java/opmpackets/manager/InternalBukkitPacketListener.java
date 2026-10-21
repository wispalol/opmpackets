package opmpackets.manager;

import opmpackets.PacketEvents;
import opmpackets.PacketEventsAPI;
import opmpackets.event.PacketReceiveEvent;
import opmpackets.event.PacketSendEvent;
import opmpackets.protocol.ConnectionState;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.util.LogManager;
import opmpackets.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import opmpackets.wrapper.login.server.WrapperLoginServerLoginSuccess;
import opmpackets.injector.SpigotChannelInjector;
import opmpackets.manager.player.PlayerManagerImpl;
import opmpackets.util.protocolsupport.ProtocolSupportUtil;
import opmpackets.util.viaversion.ViaVersionUtil;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.UUID;

@ApiStatus.Internal
public class InternalBukkitPacketListener extends opmpackets.manager.InternalPacketListener {

    @Override
    public void onPacketSend(PacketSendEvent event) {
        super.onPacketSend(event);

        // process after generic internal listener has processed this packet
        if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
            WrapperLoginServerLoginSuccess packet = new WrapperLoginServerLoginSuccess(event);
            this.tryUpdatePlayerReference(event, event.getUser(), packet.getUserProfile().getUUID());
        } else if (event.getPacketType() == PacketType.Play.Server.JOIN_GAME) {
            // try to update player reference again
            this.tryUpdatePlayerReference(event, event.getUser(), event.getUser().getUUID());
        }
    }

    private void tryUpdatePlayerReference(PacketSendEvent event, User user, UUID playerId) {
        PacketEventsAPI<?> api = PacketEvents.getAPI();
        Map<UUID, WeakReference<Player>> map = ((PlayerManagerImpl) api.getPlayerManager()).joiningPlayers;
        WeakReference<Player> playerRef = map.remove(playerId);
        Player player = playerRef != null ? playerRef.get() : null;

        // we don't care whether this player is null or not; if it is null,
        // our bukkit listener may have already handled everything or will be handling it
        if (player != null) {
            ((SpigotChannelInjector) api.getInjector()).updatePlayer(user, player);
            if (api.getLogManager().isDebug()) {
                api.getLogManager().debug("Updated player reference on packet handling for " + player.getUniqueId());
            }
            // update player object in current packet event
            event.setPlayer(player);
        }
    }

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
            } else if (ProtocolSupportUtil.isAvailable()) {
                clientVersion = ClientVersion.getById(ProtocolSupportUtil.getProtocolVersion(user.getAddress()));
                feature = "ProtocolSupport";
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
