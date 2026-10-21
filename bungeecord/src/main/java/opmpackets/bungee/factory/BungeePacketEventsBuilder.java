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

package opmpackets.bungee.factory;

import opmpackets.PacketEvents;
import opmpackets.PacketEventsAPI;
import opmpackets.injector.ChannelInjector;
import opmpackets.manager.player.PlayerManager;
import opmpackets.manager.protocol.ProtocolManager;
import opmpackets.manager.server.ServerManager;
import opmpackets.manager.server.ServerVersion;
import opmpackets.netty.NettyManager;
import opmpackets.netty.channel.ChannelHelper;
import opmpackets.protocol.ConnectionState;
import opmpackets.protocol.ProtocolVersion;
import opmpackets.protocol.packettype.PacketType;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.protocol.player.UserProfile;
import opmpackets.settings.PacketEventsSettings;
import opmpackets.util.PEVersions;
import opmpackets.impl.netty.NettyManagerImpl;
import opmpackets.impl.netty.manager.player.PlayerManagerAbstract;
import opmpackets.impl.netty.manager.protocol.ProtocolManagerAbstract;
import opmpackets.impl.netty.manager.server.ServerManagerAbstract;
import opmpackets.injector.BungeePipelineInjector;
import opmpackets.processor.InternalBungeeProcessor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.ProtocolConstants;
import org.bstats.bungeecord.Metrics;
import org.bstats.charts.SimplePie;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BungeePacketEventsBuilder {
    private static PacketEventsAPI<Plugin> INSTANCE;

    public static void clearBuildCache() {
        INSTANCE = null;
    }

    public static PacketEventsAPI<Plugin> build(Plugin plugin) {
        if (INSTANCE == null) {
            INSTANCE = buildNoCache(plugin);
        }
        return INSTANCE;
    }

    public static PacketEventsAPI<Plugin> build(Plugin plugin, PacketEventsSettings settings) {
        if (INSTANCE == null) {
            INSTANCE = buildNoCache(plugin, settings);
        }
        return INSTANCE;
    }

    public static PacketEventsAPI<Plugin> buildNoCache(Plugin plugin) {
        return buildNoCache(plugin, new PacketEventsSettings());
    }

    public static PacketEventsAPI<Plugin> buildNoCache(Plugin plugin, PacketEventsSettings inSettings) {
        return new PacketEventsAPI<Plugin>() {
            private final PacketEventsSettings settings = inSettings;
            // TODO: Implement platform version
            private final ProtocolManager protocolManager = new ProtocolManagerAbstract() {
                @Override
                public ProtocolVersion getPlatformVersion() {
                    return ProtocolVersion.UNKNOWN;
                }
            };
            private final ServerManager serverManager = new ServerManagerAbstract() {
                private ServerVersion version;

                @Override
                public ServerVersion getVersion() {
                    if (version == null) {
                        version = ServerVersion.getById(ProtocolConstants.SUPPORTED_VERSION_IDS.get(0));
                        if (version == null) {
                            getLogManager().warn("PacketEvents currently does not support the protocol version " + ProtocolConstants.SUPPORTED_VERSION_IDS.get(0) + " but will act as if the minecraft version were " + ServerVersion.getLatest().getReleaseName() + "!");
                            version = ServerVersion.getLatest();
                        }
                    }
                    return version;
                }

                @Override
                public Object getRegistryCacheKey(User user, ClientVersion version) {
                    ProxiedPlayer player = ProxyServer.getInstance().getPlayer(user.getUUID());
                    if (player == null) {
                        return null;
                    }
                    Server server = player.getServer();
                    if (server == null) {
                        // seems to be null during server switch or on join,
                        // but only for some specific bungee forks?
                        // BungeeCord would be a lot safer if they were to use nullability annotations...
                        return null;
                    }
                    return Objects.hash(server.getInfo(), version);
                }
            };

            private final PlayerManagerAbstract playerManager = new PlayerManagerAbstract() {
                @Override
                public int getPing(@NotNull Object player) {
                    return ((ProxiedPlayer) player).getPing();
                }

                @Override
                public Object getChannel(@NotNull Object player) {
                    return PacketEvents.getAPI().getProtocolManager().getChannel(((ProxiedPlayer) player).getUniqueId());
                }

                @Override
                public User getUser(@NotNull Object player) {
                    ProxiedPlayer p = (ProxiedPlayer) player;
                    Object channel = getChannel(p);
                    User user = PacketEvents.getAPI().getProtocolManager().getUser(channel);

                    // Creating a user that is offline will memory leak
                    if (channel == null) {
                        return null;
                    }

                    if (user == null) {
                        PacketEvents.getAPI().getLogManager().warn("User is null?");
                        user = new User(channel, ConnectionState.PLAY, null, new UserProfile(p.getUniqueId(), p.getName()));

                        synchronized (channel) {
                            if (!ChannelHelper.isOpen(channel)) {
                                return null;
                            }

                            PacketEvents.getAPI().getProtocolManager().setUser(channel, user);
                            PacketEvents.getAPI().getInjector().updateUser(channel, user);
                        }
                    }

                    UserProfile profile = user.getProfile();
                    if (profile.getName() == null) {
                        profile.setName(p.getName());
                    }
                    if (profile.getUUID() == null) {
                        profile.setUUID(p.getUniqueId());
                    }
                    return user;
                }
            };

            private final ChannelInjector injector = new BungeePipelineInjector();
            private final NettyManager nettyManager = new NettyManagerImpl();
            private boolean loaded;
            private boolean initialized;
            private boolean terminated;

            @Override
            public void load() {
                if (!loaded) {
                    final String id = plugin.getDescription().getName();
                    //Resolve server version and cache
                    PacketEvents.IDENTIFIER = "pe-" + id;
                    PacketEvents.ENCODER_NAME = "pe-encoder-" + id;
                    PacketEvents.DECODER_NAME = "pe-decoder-" + id;
                    PacketEvents.CONNECTION_HANDLER_NAME = "pe-connection-handler-" + id;
                    PacketEvents.SERVER_CHANNEL_HANDLER_NAME = "pe-connection-initializer-" + id;
                    PacketEvents.TIMEOUT_HANDLER_NAME = "pe-timeout-handler-" + id;

                    super.load();

                    injector.inject();
                    loaded = true;

                    this.getLogManager().info("Loaded packetevents v" + PEVersions.RAW + ("packetevents".equals(id) ? "" : " for " + id));
                }
            }

            @Override
            public boolean isLoaded() {
                return loaded;
            }

            @Override
            public void init() {
                // Load if we haven't loaded already
                load();
                if (!initialized) {
                    ProxyServer.getInstance().getPluginManager().registerListener(plugin, new InternalBungeeProcessor());
                    if (settings.shouldCheckForUpdates()) {
                        getUpdateChecker().handleUpdateCheck();
                    }

                    Metrics metrics = new Metrics(plugin, 11327);
                    //Just to have an idea of which versions of packetevents people use
                    metrics.addCustomChart(new SimplePie("packetevents_version", () -> getVersion().toStringWithoutSnapshot()));

                    PacketType.Play.Client.load();
                    PacketType.Play.Server.load();
                    initialized = true;
                }
            }

            @Override
            public boolean isInitialized() {
                return initialized;
            }

            @Override
            public void terminate() {
                if (initialized) {
                    super.terminate();
                    initialized = false;
                    terminated = true;
                }
            }

            @Override
            public boolean isTerminated() {
                return terminated;
            }

            @Override
            public Plugin getPlugin() {
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
            public ChannelInjector getInjector() {
                return injector;
            }

            @Override
            public PacketEventsSettings getSettings() {
                return settings;
            }

            @Override
            public NettyManager getNettyManager() {
                return nettyManager;
            }
        };
    }
}
