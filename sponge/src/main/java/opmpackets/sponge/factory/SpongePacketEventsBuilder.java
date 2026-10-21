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

package opmpackets.sponge.factory;

import opmpackets.PacketEvents;
import opmpackets.PacketEventsAPI;
import opmpackets.injector.ChannelInjector;
import opmpackets.manager.player.PlayerManager;
import opmpackets.manager.protocol.ProtocolManager;
import opmpackets.manager.server.ServerManager;
import opmpackets.netty.NettyManager;
import opmpackets.protocol.player.User;
import opmpackets.settings.PacketEventsSettings;
import opmpackets.util.PEVersions;
import opmpackets.sponge.InternalSpongeListener;
import opmpackets.sponge.injector.SpongeChannelInjector;
import opmpackets.sponge.injector.connection.ServerConnectionInitializer;
import opmpackets.sponge.manager.InternalSpongePacketListener;
import opmpackets.sponge.manager.player.PlayerManagerImpl;
import opmpackets.sponge.manager.protocol.ProtocolManagerImpl;
import opmpackets.sponge.manager.server.ServerManagerImpl;
import opmpackets.sponge.netty.NettyManagerImpl;
import opmpackets.sponge.util.SpongeReflectionUtil;
import opmpackets.sponge.util.viaversion.CustomPipelineUtil;
import opmpackets.sponge.util.viaversion.ViaVersionUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.plugin.PluginContainer;

import java.util.Locale;

public class SpongePacketEventsBuilder {

    private static PacketEventsAPI<PluginContainer> API_INSTANCE;

    public static void clearBuildCache() {
        API_INSTANCE = null;
    }

    public static PacketEventsAPI<PluginContainer> build(PluginContainer plugin) {
        if (API_INSTANCE == null) {
            API_INSTANCE = buildNoCache(plugin);
        }
        return API_INSTANCE;
    }

    public static PacketEventsAPI<PluginContainer> build(PluginContainer plugin, PacketEventsSettings settings) {
        if (API_INSTANCE == null) {
            API_INSTANCE = buildNoCache(plugin, settings);
        }
        return API_INSTANCE;
    }

    public static PacketEventsAPI<PluginContainer> buildNoCache(PluginContainer plugin) {
        return buildNoCache(plugin, new PacketEventsSettings());
    }

    public static PacketEventsAPI<PluginContainer> buildNoCache(PluginContainer plugin, PacketEventsSettings inSettings) {
        return new PacketEventsAPI<>() {
            private final PacketEventsSettings settings = inSettings;
            private final ProtocolManager protocolManager = new ProtocolManagerImpl();
            private final ServerManager serverManager = new ServerManagerImpl();
            private final PlayerManager playerManager = new PlayerManagerImpl();
            private final NettyManager nettyManager = new NettyManagerImpl();
            private final SpongeChannelInjector injector = new SpongeChannelInjector();
            private boolean loaded;
            private boolean initialized;
            private boolean terminated;
            private boolean lateBind = false;

            @Override
            public void load() {
                if (loaded) return;

                //Resolve server version and cache
                String id = plugin.metadata().id().toLowerCase(Locale.ROOT);
                PacketEvents.IDENTIFIER = "pe-" + id;
                PacketEvents.ENCODER_NAME = "pe-encoder-" + id;
                PacketEvents.DECODER_NAME = "pe-decoder-" + id;
                PacketEvents.CONNECTION_HANDLER_NAME = "pe-connection-handler-" + id;
                PacketEvents.SERVER_CHANNEL_HANDLER_NAME = "pe-connection-initializer-" + id;
                PacketEvents.TIMEOUT_HANDLER_NAME = "pe-timeout-handler-" + id;
                try {
                    SpongeReflectionUtil.init();
                    CustomPipelineUtil.init();
                } catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
                super.load();

                // Server hasn't bound to the port yet.
                lateBind = !injector.isServerBound();
                // If late-bind is enabled, we will inject a bit later.
                if (!lateBind) {
                    injector.inject();
                }

                loaded = true;

                this.getLogManager().info("Loaded packetevents v" + PEVersions.RAW + ("packetevents".equals(id) ? "" : " for " + plugin.metadata().id()));
            }

            @Override
            protected void registerInternalListener() {
                this.getEventManager().registerListener(new InternalSpongePacketListener());
            }

            @Override
            public boolean isLoaded() {
                return loaded;
            }

            @Override
            public void init() {
                // Load if we haven't loaded already
                load();

                if (initialized) return;

                Sponge.eventManager().registerListeners(plugin, new InternalSpongeListener());

                if (lateBind) {
                    //If late-bind is enabled, we still need to inject (after all plugins enabled).
                    Runnable lateBindTask = () -> {
                        if (injector.isServerBound()) {
                            injector.inject();
                        }
                    };

                    Sponge.server().scheduler().submit(Task.builder().plugin(plugin).execute(lateBindTask).build());
                }

                // Let people override this, at their own risk
                if (!"true".equalsIgnoreCase(System.getenv("PE_IGNORE_INCOMPATIBILITY"))) {
                    // PacketEvents is now enabled, we can now check
                    ViaVersionUtil.checkIfViaIsPresent();
                }

                initialized = true;
            }

            @Override
            public boolean isInitialized() {
                return initialized;
            }

            @Override
            public void terminate() {
                if (initialized) {
                    super.terminate();
                    for (User user : this.protocolManager.getUsers()) {
                        ServerConnectionInitializer.destroyHandlers(user.getChannel());
                    }
                    initialized = false;
                    terminated = true;
                }
            }

            @Override
            public boolean isTerminated() {
                return terminated;
            }

            @Override
            public PluginContainer getPlugin() {
                return plugin;
            }

            @Override
            public ProtocolManager getProtocolManager() {
                return protocolManager;
            }

            @Override
            public ServerManager getServerManager() {
                return serverManager;
            }

            @Override
            public PlayerManager getPlayerManager() {
                return playerManager;
            }

            @Override
            public PacketEventsSettings getSettings() {
                return settings;
            }

            @Override
            public NettyManager getNettyManager() {
                return nettyManager;
            }

            @Override
            public ChannelInjector getInjector() {
                return injector;
            }
        };
    }
}
