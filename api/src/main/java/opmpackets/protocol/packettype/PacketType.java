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

package opmpackets.protocol.packettype;

import opmpackets.protocol.ConnectionState;
import opmpackets.protocol.PacketSide;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_12;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_12_1;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_13;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_14;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_14_4;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_15;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_15_2;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_16;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_16_2;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_17;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_18;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_19;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_19_1;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_19_3;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_19_4;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_20_2;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_20_3;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_20_5;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_21;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_21_2;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_21_5;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_21_6;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_21_9;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_7_10;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_8;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_9;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_1_9_3;
import opmpackets.protocol.packettype.clientbound.ClientboundPacketType_26_1;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_20_2;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_20_3;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_20_5;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_21;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_21_6;
import opmpackets.protocol.packettype.config.clientbound.ClientboundConfigPacketType_1_21_9;
import opmpackets.protocol.packettype.config.serverbound.ServerboundConfigPacketType_1_20_2;
import opmpackets.protocol.packettype.config.serverbound.ServerboundConfigPacketType_1_20_5;
import opmpackets.protocol.packettype.config.serverbound.ServerboundConfigPacketType_1_21_6;
import opmpackets.protocol.packettype.config.serverbound.ServerboundConfigPacketType_1_21_9;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_12;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_12_1;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_13;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_14;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_15_2;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_16;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_16_2;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_17;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_19;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_19_1;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_19_3;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_19_4;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_20_2;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_20_3;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_20_5;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_21_2;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_21_4;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_21_5;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_21_6;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_21_9;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_7_10;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_8;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_1_9;
import opmpackets.protocol.packettype.serverbound.ServerboundPacketType_26_1;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.util.VersionMapper;
import opmpackets.wrapper.PacketWrapper;
import opmpackets.wrapper.configuration.client.WrapperConfigClientAcceptCodeOfConduct;
import opmpackets.wrapper.configuration.client.WrapperConfigClientConfigurationEndAck;
import opmpackets.wrapper.configuration.client.WrapperConfigClientCookieResponse;
import opmpackets.wrapper.configuration.client.WrapperConfigClientCustomClickAction;
import opmpackets.wrapper.configuration.client.WrapperConfigClientKeepAlive;
import opmpackets.wrapper.configuration.client.WrapperConfigClientPluginMessage;
import opmpackets.wrapper.configuration.client.WrapperConfigClientPong;
import opmpackets.wrapper.configuration.client.WrapperConfigClientResourcePackStatus;
import opmpackets.wrapper.configuration.client.WrapperConfigClientSelectKnownPacks;
import opmpackets.wrapper.configuration.client.WrapperConfigClientSettings;
import opmpackets.wrapper.configuration.server.WrapperConfigServerClearDialog;
import opmpackets.wrapper.configuration.server.WrapperConfigServerCodeOfConduct;
import opmpackets.wrapper.configuration.server.WrapperConfigServerConfigurationEnd;
import opmpackets.wrapper.configuration.server.WrapperConfigServerCookieRequest;
import opmpackets.wrapper.configuration.server.WrapperConfigServerCustomReportDetails;
import opmpackets.wrapper.configuration.server.WrapperConfigServerDisconnect;
import opmpackets.wrapper.configuration.server.WrapperConfigServerKeepAlive;
import opmpackets.wrapper.configuration.server.WrapperConfigServerPing;
import opmpackets.wrapper.configuration.server.WrapperConfigServerPluginMessage;
import opmpackets.wrapper.configuration.server.WrapperConfigServerRegistryData;
import opmpackets.wrapper.configuration.server.WrapperConfigServerResetChat;
import opmpackets.wrapper.configuration.server.WrapperConfigServerResourcePackRemove;
import opmpackets.wrapper.configuration.server.WrapperConfigServerResourcePackSend;
import opmpackets.wrapper.configuration.server.WrapperConfigServerSelectKnownPacks;
import opmpackets.wrapper.configuration.server.WrapperConfigServerServerLinks;
import opmpackets.wrapper.configuration.server.WrapperConfigServerShowDialog;
import opmpackets.wrapper.configuration.server.WrapperConfigServerStoreCookie;
import opmpackets.wrapper.configuration.server.WrapperConfigServerTransfer;
import opmpackets.wrapper.configuration.server.WrapperConfigServerUpdateEnabledFeatures;
import opmpackets.wrapper.handshaking.client.WrapperHandshakingClientHandshake;
import opmpackets.wrapper.login.client.WrapperLoginClientCookieResponse;
import opmpackets.wrapper.login.client.WrapperLoginClientEncryptionResponse;
import opmpackets.wrapper.login.client.WrapperLoginClientLoginStart;
import opmpackets.wrapper.login.client.WrapperLoginClientLoginSuccessAck;
import opmpackets.wrapper.login.client.WrapperLoginClientPluginResponse;
import opmpackets.wrapper.login.server.WrapperLoginServerCookieRequest;
import opmpackets.wrapper.login.server.WrapperLoginServerDisconnect;
import opmpackets.wrapper.login.server.WrapperLoginServerEncryptionRequest;
import opmpackets.wrapper.login.server.WrapperLoginServerLoginSuccess;
import opmpackets.wrapper.login.server.WrapperLoginServerPluginRequest;
import opmpackets.wrapper.login.server.WrapperLoginServerSetCompression;
import opmpackets.wrapper.play.client.WrapperPlayClientAdvancementTab;
import opmpackets.wrapper.play.client.WrapperPlayClientAnimation;
import opmpackets.wrapper.play.client.WrapperPlayClientAttack;
import opmpackets.wrapper.play.client.WrapperPlayClientChangeGameMode;
import opmpackets.wrapper.play.client.WrapperPlayClientChatAck;
import opmpackets.wrapper.play.client.WrapperPlayClientChatCommand;
import opmpackets.wrapper.play.client.WrapperPlayClientChatCommandUnsigned;
import opmpackets.wrapper.play.client.WrapperPlayClientChatMessage;
import opmpackets.wrapper.play.client.WrapperPlayClientChatPreview;
import opmpackets.wrapper.play.client.WrapperPlayClientChatSessionUpdate;
import opmpackets.wrapper.play.client.WrapperPlayClientChunkBatchAck;
import opmpackets.wrapper.play.client.WrapperPlayClientClickWindow;
import opmpackets.wrapper.play.client.WrapperPlayClientClickWindowButton;
import opmpackets.wrapper.play.client.WrapperPlayClientClientStatus;
import opmpackets.wrapper.play.client.WrapperPlayClientClientTickEnd;
import opmpackets.wrapper.play.client.WrapperPlayClientCloseWindow;
import opmpackets.wrapper.play.client.WrapperPlayClientConfigurationAck;
import opmpackets.wrapper.play.client.WrapperPlayClientCookieResponse;
import opmpackets.wrapper.play.client.WrapperPlayClientCraftRecipeRequest;
import opmpackets.wrapper.play.client.WrapperPlayClientCreativeInventoryAction;
import opmpackets.wrapper.play.client.WrapperPlayClientCustomClickAction;
import opmpackets.wrapper.play.client.WrapperPlayClientDebugPing;
import opmpackets.wrapper.play.client.WrapperPlayClientDebugSampleSubscription;
import opmpackets.wrapper.play.client.WrapperPlayClientDebugSubscriptionRequest;
import opmpackets.wrapper.play.client.WrapperPlayClientEditBook;
import opmpackets.wrapper.play.client.WrapperPlayClientEntityAction;
import opmpackets.wrapper.play.client.WrapperPlayClientGenerateStructure;
import opmpackets.wrapper.play.client.WrapperPlayClientHeldItemChange;
import opmpackets.wrapper.play.client.WrapperPlayClientInteractEntity;
import opmpackets.wrapper.play.client.WrapperPlayClientKeepAlive;
import opmpackets.wrapper.play.client.WrapperPlayClientLockDifficulty;
import opmpackets.wrapper.play.client.WrapperPlayClientNameItem;
import opmpackets.wrapper.play.client.WrapperPlayClientPickItem;
import opmpackets.wrapper.play.client.WrapperPlayClientPickItemFromBlock;
import opmpackets.wrapper.play.client.WrapperPlayClientPickItemFromEntity;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerAbilities;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerDigging;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerFlying;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerInput;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerLoaded;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerPosition;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import opmpackets.wrapper.play.client.WrapperPlayClientPlayerRotation;
import opmpackets.wrapper.play.client.WrapperPlayClientPluginMessage;
import opmpackets.wrapper.play.client.WrapperPlayClientPong;
import opmpackets.wrapper.play.client.WrapperPlayClientQueryBlockNBT;
import opmpackets.wrapper.play.client.WrapperPlayClientQueryEntityNBT;
import opmpackets.wrapper.play.client.WrapperPlayClientResourcePackStatus;
import opmpackets.wrapper.play.client.WrapperPlayClientSelectBundleItem;
import opmpackets.wrapper.play.client.WrapperPlayClientSelectTrade;
import opmpackets.wrapper.play.client.WrapperPlayClientSetBeaconEffect;
import opmpackets.wrapper.play.client.WrapperPlayClientSetDifficulty;
import opmpackets.wrapper.play.client.WrapperPlayClientSetDisplayedRecipe;
import opmpackets.wrapper.play.client.WrapperPlayClientSetGameRule;
import opmpackets.wrapper.play.client.WrapperPlayClientSetRecipeBookState;
import opmpackets.wrapper.play.client.WrapperPlayClientSetStructureBlock;
import opmpackets.wrapper.play.client.WrapperPlayClientSetTestBlock;
import opmpackets.wrapper.play.client.WrapperPlayClientSettings;
import opmpackets.wrapper.play.client.WrapperPlayClientSlotStateChange;
import opmpackets.wrapper.play.client.WrapperPlayClientSpectate;
import opmpackets.wrapper.play.client.WrapperPlayClientSpectateEntity;
import opmpackets.wrapper.play.client.WrapperPlayClientSteerBoat;
import opmpackets.wrapper.play.client.WrapperPlayClientSteerVehicle;
import opmpackets.wrapper.play.client.WrapperPlayClientTabComplete;
import opmpackets.wrapper.play.client.WrapperPlayClientTeleportConfirm;
import opmpackets.wrapper.play.client.WrapperPlayClientTestInstanceBlockAction;
import opmpackets.wrapper.play.client.WrapperPlayClientUpdateCommandBlock;
import opmpackets.wrapper.play.client.WrapperPlayClientUpdateCommandBlockMinecart;
import opmpackets.wrapper.play.client.WrapperPlayClientUpdateJigsawBlock;
import opmpackets.wrapper.play.client.WrapperPlayClientUpdateSign;
import opmpackets.wrapper.play.client.WrapperPlayClientUseItem;
import opmpackets.wrapper.play.client.WrapperPlayClientVehicleMove;
import opmpackets.wrapper.play.client.WrapperPlayClientWindowConfirmation;
import opmpackets.wrapper.play.server.WrapperPlayServerAcknowledgeBlockChanges;
import opmpackets.wrapper.play.server.WrapperPlayServerAcknowledgePlayerDigging;
import opmpackets.wrapper.play.server.WrapperPlayServerActionBar;
import opmpackets.wrapper.play.server.WrapperPlayServerAttachEntity;
import opmpackets.wrapper.play.server.WrapperPlayServerBlockAction;
import opmpackets.wrapper.play.server.WrapperPlayServerBlockBreakAnimation;
import opmpackets.wrapper.play.server.WrapperPlayServerBlockChange;
import opmpackets.wrapper.play.server.WrapperPlayServerBlockEntityData;
import opmpackets.wrapper.play.server.WrapperPlayServerBossBar;
import opmpackets.wrapper.play.server.WrapperPlayServerBundle;
import opmpackets.wrapper.play.server.WrapperPlayServerCamera;
import opmpackets.wrapper.play.server.WrapperPlayServerChangeGameState;
import opmpackets.wrapper.play.server.WrapperPlayServerChatMessage;
import opmpackets.wrapper.play.server.WrapperPlayServerChatPreview;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkBatchBegin;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkBatchEnd;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkData;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkBiomes;
import opmpackets.wrapper.play.server.WrapperPlayServerChunkDataBulk;
import opmpackets.wrapper.play.server.WrapperPlayServerClearDialog;
import opmpackets.wrapper.play.server.WrapperPlayServerClearTitles;
import opmpackets.wrapper.play.server.WrapperPlayServerCloseWindow;
import opmpackets.wrapper.play.server.WrapperPlayServerCollectItem;
import opmpackets.wrapper.play.server.WrapperPlayServerCombatEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerConfigurationStart;
import opmpackets.wrapper.play.server.WrapperPlayServerCookieRequest;
import opmpackets.wrapper.play.server.WrapperPlayServerCraftRecipeResponse;
import opmpackets.wrapper.play.server.WrapperPlayServerCustomChatCompletions;
import opmpackets.wrapper.play.server.WrapperPlayServerCustomReportDetails;
import opmpackets.wrapper.play.server.WrapperPlayServerDamageEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerDeathCombatEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugBlockValue;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugChunkValue;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugEntityValue;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugPong;
import opmpackets.wrapper.play.server.WrapperPlayServerDebugSample;
import opmpackets.wrapper.play.server.WrapperPlayServerDeclareCommands;
import opmpackets.wrapper.play.server.WrapperPlayServerDeclareRecipes;
import opmpackets.wrapper.play.server.WrapperPlayServerDeleteChat;
import opmpackets.wrapper.play.server.WrapperPlayServerDestroyEntities;
import opmpackets.wrapper.play.server.WrapperPlayServerDifficulty;
import opmpackets.wrapper.play.server.WrapperPlayServerDisconnect;
import opmpackets.wrapper.play.server.WrapperPlayServerDisguisedChat;
import opmpackets.wrapper.play.server.WrapperPlayServerDisplayScoreboard;
import opmpackets.wrapper.play.server.WrapperPlayServerEffect;
import opmpackets.wrapper.play.server.WrapperPlayServerEndCombatEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerEnterCombatEvent;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityAnimation;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityEffect;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityEquipment;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityHeadLook;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityMetadata;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityMovement;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityPositionSync;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityRelativeMove;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityRelativeMoveAndRotation;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityRotation;
import opmpackets.wrapper.play.server.WrapperPlayServerEntitySoundEffect;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityStatus;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityTeleport;
import opmpackets.wrapper.play.server.WrapperPlayServerEntityVelocity;
import opmpackets.wrapper.play.server.WrapperPlayServerExplosion;
import opmpackets.wrapper.play.server.WrapperPlayServerFacePlayer;
import opmpackets.wrapper.play.server.WrapperPlayServerGameRuleValues;
import opmpackets.wrapper.play.server.WrapperPlayServerGameTestHighlightPos;
import opmpackets.wrapper.play.server.WrapperPlayServerHeldItemChange;
import opmpackets.wrapper.play.server.WrapperPlayServerHurtAnimation;
import opmpackets.wrapper.play.server.WrapperPlayServerInitializeWorldBorder;
import opmpackets.wrapper.play.server.WrapperPlayServerJoinGame;
import opmpackets.wrapper.play.server.WrapperPlayServerKeepAlive;
import opmpackets.wrapper.play.server.WrapperPlayServerLowDiskSpaceWarning;
import opmpackets.wrapper.play.server.WrapperPlayServerMapData;
import opmpackets.wrapper.play.server.WrapperPlayServerMerchantOffers;
import opmpackets.wrapper.play.server.WrapperPlayServerMoveMinecart;
import opmpackets.wrapper.play.server.WrapperPlayServerMultiBlockChange;
import opmpackets.wrapper.play.server.WrapperPlayServerNBTQueryResponse;
import opmpackets.wrapper.play.server.WrapperPlayServerOpenBook;
import opmpackets.wrapper.play.server.WrapperPlayServerOpenHorseWindow;
import opmpackets.wrapper.play.server.WrapperPlayServerOpenSignEditor;
import opmpackets.wrapper.play.server.WrapperPlayServerOpenWindow;
import opmpackets.wrapper.play.server.WrapperPlayServerParticle;
import opmpackets.wrapper.play.server.WrapperPlayServerPing;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerAbilities;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerChatHeader;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerInfo;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerInfoRemove;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerInfoUpdate;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerListHeaderAndFooter;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import opmpackets.wrapper.play.server.WrapperPlayServerPlayerRotation;
import opmpackets.wrapper.play.server.WrapperPlayServerPluginMessage;
import opmpackets.wrapper.play.server.WrapperPlayServerProjectilePower;
import opmpackets.wrapper.play.server.WrapperPlayServerRecipeBookAdd;
import opmpackets.wrapper.play.server.WrapperPlayServerRecipeBookRemove;
import opmpackets.wrapper.play.server.WrapperPlayServerRecipeBookSettings;
import opmpackets.wrapper.play.server.WrapperPlayServerRemoveEntityEffect;
import opmpackets.wrapper.play.server.WrapperPlayServerResetScore;
import opmpackets.wrapper.play.server.WrapperPlayServerResourcePackRemove;
import opmpackets.wrapper.play.server.WrapperPlayServerResourcePackSend;
import opmpackets.wrapper.play.server.WrapperPlayServerRespawn;
import opmpackets.wrapper.play.server.WrapperPlayServerScoreboardObjective;
import opmpackets.wrapper.play.server.WrapperPlayServerSelectAdvancementsTab;
import opmpackets.wrapper.play.server.WrapperPlayServerServerData;
import opmpackets.wrapper.play.server.WrapperPlayServerServerLinks;
import opmpackets.wrapper.play.server.WrapperPlayServerSetCompression;
import opmpackets.wrapper.play.server.WrapperPlayServerSetCooldown;
import opmpackets.wrapper.play.server.WrapperPlayServerSetCursorItem;
import opmpackets.wrapper.play.server.WrapperPlayServerSetDisplayChatPreview;
import opmpackets.wrapper.play.server.WrapperPlayServerSetExperience;
import opmpackets.wrapper.play.server.WrapperPlayServerSetPassengers;
import opmpackets.wrapper.play.server.WrapperPlayServerSetPlayerInventory;
import opmpackets.wrapper.play.server.WrapperPlayServerSetSlot;
import opmpackets.wrapper.play.server.WrapperPlayServerSetTitleSubtitle;
import opmpackets.wrapper.play.server.WrapperPlayServerSetTitleText;
import opmpackets.wrapper.play.server.WrapperPlayServerSetTitleTimes;
import opmpackets.wrapper.play.server.WrapperPlayServerShowDialog;
import opmpackets.wrapper.play.server.WrapperPlayServerSoundEffect;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnEntity;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnExperienceOrb;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnLivingEntity;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnPainting;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnPlayer;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnPosition;
import opmpackets.wrapper.play.server.WrapperPlayServerSpawnWeatherEntity;
import opmpackets.wrapper.play.server.WrapperPlayServerStatistics;
import opmpackets.wrapper.play.server.WrapperPlayServerStoreCookie;
import opmpackets.wrapper.play.server.WrapperPlayServerSystemChatMessage;
import opmpackets.wrapper.play.server.WrapperPlayServerTabComplete;
import opmpackets.wrapper.play.server.WrapperPlayServerTags;
import opmpackets.wrapper.play.server.WrapperPlayServerTeams;
import opmpackets.wrapper.play.server.WrapperPlayServerTestInstanceBlockStatus;
import opmpackets.wrapper.play.server.WrapperPlayServerTickingState;
import opmpackets.wrapper.play.server.WrapperPlayServerTickingStep;
import opmpackets.wrapper.play.server.WrapperPlayServerTimeUpdate;
import opmpackets.wrapper.play.server.WrapperPlayServerTransfer;
import opmpackets.wrapper.play.server.WrapperPlayServerUnloadChunk;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateAdvancements;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateAttributes;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateEnabledFeatures;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateEntityNBT;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateHealth;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateLight;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateScore;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateSimulationDistance;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateViewDistance;
import opmpackets.wrapper.play.server.WrapperPlayServerUpdateViewPosition;
import opmpackets.wrapper.play.server.WrapperPlayServerUseBed;
import opmpackets.wrapper.play.server.WrapperPlayServerVehicleMove;
import opmpackets.wrapper.play.server.WrapperPlayServerWaypoint;
import opmpackets.wrapper.play.server.WrapperPlayServerWindowConfirmation;
import opmpackets.wrapper.play.server.WrapperPlayServerWindowItems;
import opmpackets.wrapper.play.server.WrapperPlayServerWindowProperty;
import opmpackets.wrapper.play.server.WrapperPlayServerWorldBorder;
import opmpackets.wrapper.play.server.WrapperPlayServerWorldBorderCenter;
import opmpackets.wrapper.play.server.WrapperPlayServerWorldBorderSize;
import opmpackets.wrapper.play.server.WrapperPlayServerWorldBorderWarningReach;
import opmpackets.wrapper.play.server.WrapperPlayWorldBorderLerpSize;
import opmpackets.wrapper.play.server.WrapperPlayWorldBorderWarningDelay;
import opmpackets.wrapper.status.client.WrapperStatusClientPing;
import opmpackets.wrapper.status.client.WrapperStatusClientRequest;
import opmpackets.wrapper.status.server.WrapperStatusServerPong;
import opmpackets.wrapper.status.server.WrapperStatusServerResponse;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class PacketType {

    private static volatile boolean PREPARED = false;
    private static final Object PREPARE_LOCK = new Object();

    //TODO UPDATE Update packet type mappings (clientbound pt. 1)
    private static final VersionMapper CLIENTBOUND_PLAY_VERSION_MAPPER = new VersionMapper(
            ClientVersion.V_1_7_2,
            ClientVersion.V_1_8,
            ClientVersion.V_1_9,
            ClientVersion.V_1_10,
            ClientVersion.V_1_12,
            ClientVersion.V_1_12_1,
            ClientVersion.V_1_13,
            ClientVersion.V_1_14,
            ClientVersion.V_1_14_4,
            ClientVersion.V_1_15,
            ClientVersion.V_1_15_2,
            ClientVersion.V_1_16,
            ClientVersion.V_1_16_2,
            ClientVersion.V_1_17,
            ClientVersion.V_1_18,
            ClientVersion.V_1_19,
            ClientVersion.V_1_19_1,
            ClientVersion.V_1_19_3,
            ClientVersion.V_1_19_4,
            ClientVersion.V_1_20_2,
            ClientVersion.V_1_20_3,
            ClientVersion.V_1_20_5,
            ClientVersion.V_1_21,
            ClientVersion.V_1_21_2,
            ClientVersion.V_1_21_5,
            ClientVersion.V_1_21_6,
            ClientVersion.V_1_21_9,
            ClientVersion.V_26_1);

    //TODO UPDATE Update packet type mappings (serverbound pt. 1)
    private static final VersionMapper SERVERBOUND_PLAY_VERSION_MAPPER = new VersionMapper(
            ClientVersion.V_1_7_2,
            ClientVersion.V_1_8,
            ClientVersion.V_1_9,
            ClientVersion.V_1_12,
            ClientVersion.V_1_12_1,
            ClientVersion.V_1_13,
            ClientVersion.V_1_14,
            ClientVersion.V_1_15_2,
            ClientVersion.V_1_16,
            ClientVersion.V_1_16_2,
            ClientVersion.V_1_17,
            ClientVersion.V_1_19,
            ClientVersion.V_1_19_1,
            ClientVersion.V_1_19_3,
            ClientVersion.V_1_19_4,
            ClientVersion.V_1_20_2,
            ClientVersion.V_1_20_3,
            ClientVersion.V_1_20_5,
            ClientVersion.V_1_21_2,
            ClientVersion.V_1_21_4,
            ClientVersion.V_1_21_5,
            ClientVersion.V_1_21_6,
            ClientVersion.V_1_21_9,
            ClientVersion.V_26_1);

    // TODO UPDATE Update packet type mappings (config clientbound pt. 1)
    private static final VersionMapper CLIENTBOUND_CONFIG_VERSION_MAPPER = new VersionMapper(
            ClientVersion.V_1_20_2,
            ClientVersion.V_1_20_3,
            ClientVersion.V_1_20_5,
            ClientVersion.V_1_21,
            ClientVersion.V_1_21_6,
            ClientVersion.V_1_21_9);
    // TODO UPDATE Update packet type mappings (config serverbound pt. 1)
    private static final VersionMapper SERVERBOUND_CONFIG_VERSION_MAPPER = new VersionMapper(
            ClientVersion.V_1_20_2,
            ClientVersion.V_1_20_5,
            ClientVersion.V_1_21_6,
            ClientVersion.V_1_21_9);

    private PacketType() {
    }

    @ApiStatus.Internal
    public static void prepare() {
        if (PREPARED) {
            return;
        }

        synchronized (PREPARE_LOCK) {
            if (PREPARED) {
                return;
            }

            PacketType.Play.Client.load();
            PacketType.Play.Server.load();
            PacketType.Configuration.Client.load();
            PacketType.Configuration.Server.load();

            PREPARED = true;
        }
    }

    @ApiStatus.Internal
    public static boolean isPrepared() {
        return PREPARED;
    }

    public static PacketTypeCommon getById(PacketSide side, ConnectionState state, ClientVersion version, int packetID) {
        switch (state) {
            case HANDSHAKING:
                if (side == PacketSide.CLIENT) {
                    return PacketType.Handshaking.Client.getById(packetID);
                } else {
                    return PacketType.Handshaking.Server.getById(packetID);
                }
            case STATUS:
                if (side == PacketSide.CLIENT) {
                    return Status.Client.getById(packetID);
                } else {
                    return Status.Server.getById(packetID);
                }
            case LOGIN:
                if (side == PacketSide.CLIENT) {
                    return Login.Client.getById(packetID);
                } else {
                    return Login.Server.getById(packetID);
                }
            case PLAY:
                if (side == PacketSide.CLIENT) {
                    return Play.Client.getById(version, packetID);
                } else {
                    return Play.Server.getById(version, packetID);
                }
            case CONFIGURATION:
                if (side == PacketSide.CLIENT) {
                    return Configuration.Client.getById(version, packetID);
                } else {
                    return Configuration.Server.getById(version, packetID);
                }
            default:
                return null;
        }
    }

    public static class Handshaking {

        public enum Client implements PacketTypeConstant, ServerBoundPacket {

            HANDSHAKE(0, WrapperHandshakingClientHandshake.class),
            /**
             * Technically not part of the current protocol, but clients older than 1.7 will send this to initiate Server List Ping.
             * 1.8 and newer servers will handle it correctly though.
             */
            LEGACY_SERVER_LIST_PING(0xFE, null),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Client(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetID) {
                if (packetID == 0) {
                    return HANDSHAKE;
                } else if (packetID == 0xFE) {
                    return LEGACY_SERVER_LIST_PING;
                } else {
                    return null;
                }
            }

            public int getId() {
                return id;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.CLIENT;
            }
        }

        public enum Server implements PacketTypeConstant, ClientBoundPacket {

            LEGACY_SERVER_LIST_RESPONSE(0xFE, null),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Server(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetID) {
                return packetID == 0xFE ? LEGACY_SERVER_LIST_RESPONSE : null;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            public int getId() {
                return id;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.SERVER;
            }
        }
    }

    public static class Status {

        public enum Client implements PacketTypeConstant, ServerBoundPacket {

            REQUEST(0x00, WrapperStatusClientRequest.class),
            PING(0x01, WrapperStatusClientPing.class),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Client(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetId) {
                if (packetId == 0) {
                    return REQUEST;
                } else if (packetId == 1) {
                    return PING;
                } else {
                    return null;
                }
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            public int getId() {
                return id;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.CLIENT;
            }
        }

        public enum Server implements PacketTypeConstant, ClientBoundPacket {

            RESPONSE(0x00, WrapperStatusServerResponse.class),
            PONG(0x01, WrapperStatusServerPong.class),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Server(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetID) {
                if (packetID == 0) {
                    return RESPONSE;
                } else if (packetID == 1) {
                    return PONG;
                } else {
                    return null;
                }
            }

            public int getId() {
                return id;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.SERVER;
            }
        }
    }

    public static class Login {

        public enum Client implements PacketTypeConstant, ServerBoundPacket {

            LOGIN_START(0x00, WrapperLoginClientLoginStart.class),
            ENCRYPTION_RESPONSE(0x01, WrapperLoginClientEncryptionResponse.class),

            // Added in 1.13
            LOGIN_PLUGIN_RESPONSE(0x02, WrapperLoginClientPluginResponse.class),

            // Added in 1.20.2
            LOGIN_SUCCESS_ACK(0x03, WrapperLoginClientLoginSuccessAck.class),

            // Added in 1.20.5
            COOKIE_RESPONSE(0x04, WrapperLoginClientCookieResponse.class),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Client(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetID) {
                switch (packetID) {
                    case 0x00:
                        return LOGIN_START;
                    case 0x01:
                        return ENCRYPTION_RESPONSE;
                    case 0x02:
                        return LOGIN_PLUGIN_RESPONSE;
                    case 0x03:
                        return LOGIN_SUCCESS_ACK;
                    case 0x04:
                        return COOKIE_RESPONSE;
                    default:
                        return null;
                }
            }

            public int getId() {
                return id;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.CLIENT;
            }
        }

        public enum Server implements PacketTypeConstant, ClientBoundPacket {

            DISCONNECT(0x00, WrapperLoginServerDisconnect.class),
            ENCRYPTION_REQUEST(0x01, WrapperLoginServerEncryptionRequest.class),
            LOGIN_SUCCESS(0x02, WrapperLoginServerLoginSuccess.class),

            // Added in 1.8
            SET_COMPRESSION(0x03, WrapperLoginServerSetCompression.class),

            // Added in 1.13
            LOGIN_PLUGIN_REQUEST(0x04, WrapperLoginServerPluginRequest.class),

            // Added in 1.20.5
            COOKIE_REQUEST(0x05, WrapperLoginServerCookieRequest.class),
            ;

            private final int id;
            private final Class<? extends PacketWrapper<?>> wrapperClass;

            Server(int id, @Nullable Class<? extends PacketWrapper<?>> wrapperClass) {
                this.id = id;
                this.wrapperClass = wrapperClass;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapperClass;
            }

            @Nullable
            public static PacketTypeCommon getById(int packetID) {
                switch (packetID) {
                    case 0x00:
                        return DISCONNECT;
                    case 0x01:
                        return ENCRYPTION_REQUEST;
                    case 0x02:
                        return LOGIN_SUCCESS;
                    case 0x03:
                        return SET_COMPRESSION;
                    case 0x04:
                        return LOGIN_PLUGIN_REQUEST;
                    case 0x05:
                        return COOKIE_REQUEST;
                    default:
                        return null;
                }
            }

            public int getId() {
                return id;
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.SERVER;
            }
        }
    }

    /**
     * Added with 1.20.2
     */
    public static class Configuration {

        public enum Client implements PacketTypeCommon, ServerBoundPacket {

            CLIENT_SETTINGS(WrapperConfigClientSettings.class),
            PLUGIN_MESSAGE(WrapperConfigClientPluginMessage.class),
            CONFIGURATION_END_ACK(WrapperConfigClientConfigurationEndAck.class),
            KEEP_ALIVE(WrapperConfigClientKeepAlive.class),
            PONG(WrapperConfigClientPong.class),
            RESOURCE_PACK_STATUS(WrapperConfigClientResourcePackStatus.class),

            /**
             * Added with 1.20.5
             */
            COOKIE_RESPONSE(WrapperConfigClientCookieResponse.class),
            /**
             * Added with 1.20.5
             */
            SELECT_KNOWN_PACKS(WrapperConfigClientSelectKnownPacks.class),

            /**
             * Added with 1.21.6
             */
            CUSTOM_CLICK_ACTION(WrapperConfigClientCustomClickAction.class),
            /**
             * @versions 1.21.9+
             */
            ACCEPT_CODE_OF_CONDUCT(WrapperConfigClientAcceptCodeOfConduct.class),
            ;

            private static int INDEX = 0;
            private static final Map<Byte, Map<Integer, PacketTypeCommon>> PACKET_TYPE_ID_MAP = new HashMap<>();
            private final int[] ids;
            private final Class<? extends PacketWrapper<?>> wrapper;

            Client(@Nullable Class<? extends PacketWrapper<?>> wrapper) {
                this.ids = new int[SERVERBOUND_CONFIG_VERSION_MAPPER.getVersions().length];
                Arrays.fill(this.ids, -1);
                this.wrapper = wrapper;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapper;
            }

            public static void load() {
                INDEX = 0;
                loadPacketIds(ServerboundConfigPacketType_1_20_2.values());
                loadPacketIds(ServerboundConfigPacketType_1_20_5.values());
                loadPacketIds(ServerboundConfigPacketType_1_21_6.values());
                loadPacketIds(ServerboundConfigPacketType_1_21_9.values());
                // TODO UPDATE Update packet type mappings (config serverbound pt. 2)
            }

            private static void loadPacketIds(Enum<?>[] enumConstants) {
                int index = INDEX;
                for (Enum<?> constant : enumConstants) {
                    int id = constant.ordinal();
                    Configuration.Client value = Configuration.Client.valueOf(constant.name());
                    value.ids[index] = id;
                    Map<Integer, PacketTypeCommon> packetIdMap = PACKET_TYPE_ID_MAP.computeIfAbsent((byte) index, k -> new HashMap<>());
                    packetIdMap.put(id, value);
                }
                INDEX++;
            }

            public static @Nullable PacketTypeCommon getById(int packetId) {
                return getById(ClientVersion.getLatest(), packetId);
            }

            public static @Nullable PacketTypeCommon getById(ClientVersion version, int packetId) {
                PacketType.prepare();

                int index = SERVERBOUND_CONFIG_VERSION_MAPPER.getIndex(version);
                Map<Integer, PacketTypeCommon> map = PACKET_TYPE_ID_MAP.get((byte) index);
                return map.get(packetId);
            }

            @Deprecated
            public int getId() {
                return this.getId(ClientVersion.getLatest());
            }

            @Override
            public int getId(ClientVersion version) {
                PacketType.prepare();

                int index = SERVERBOUND_CONFIG_VERSION_MAPPER.getIndex(version);
                return this.ids[index];
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.CLIENT;
            }
        }

        public enum Server implements PacketTypeCommon, ClientBoundPacket {

            PLUGIN_MESSAGE(WrapperConfigServerPluginMessage.class),
            DISCONNECT(WrapperConfigServerDisconnect.class),
            CONFIGURATION_END(WrapperConfigServerConfigurationEnd.class),
            KEEP_ALIVE(WrapperConfigServerKeepAlive.class),
            PING(WrapperConfigServerPing.class),
            REGISTRY_DATA(WrapperConfigServerRegistryData.class),
            RESOURCE_PACK_SEND(WrapperConfigServerResourcePackSend.class),
            UPDATE_ENABLED_FEATURES(WrapperConfigServerUpdateEnabledFeatures.class),
            UPDATE_TAGS(null),

            /**
             * Added with 1.20.3
             */
            RESOURCE_PACK_REMOVE(WrapperConfigServerResourcePackRemove.class),

            /**
             * Added with 1.20.5
             */
            COOKIE_REQUEST(WrapperConfigServerCookieRequest.class),
            /**
             * Added with 1.20.5
             */
            RESET_CHAT(WrapperConfigServerResetChat.class),
            /**
             * Added with 1.20.5
             */
            STORE_COOKIE(WrapperConfigServerStoreCookie.class),
            /**
             * Added with 1.20.5
             */
            TRANSFER(WrapperConfigServerTransfer.class),
            /**
             * Added with 1.20.5
             */
            SELECT_KNOWN_PACKS(WrapperConfigServerSelectKnownPacks.class),

            /**
             * Added with 1.21
             */
            CUSTOM_REPORT_DETAILS(WrapperConfigServerCustomReportDetails.class),
            /**
             * Added with 1.21
             */
            SERVER_LINKS(WrapperConfigServerServerLinks.class),

            /**
             * Added with 1.21.6
             */
            CLEAR_DIALOG(WrapperConfigServerClearDialog.class),
            /**
             * Added with 1.21.6
             */
            SHOW_DIALOG(WrapperConfigServerShowDialog.class),

            /**
             * @versions 1.21.9+
             */
            CODE_OF_CONDUCT(WrapperConfigServerCodeOfConduct.class),
            ;

            private static int INDEX = 0;
            private static final Map<Byte, Map<Integer, PacketTypeCommon>> PACKET_TYPE_ID_MAP = new HashMap<>();
            private final int[] ids;
            private final Class<? extends PacketWrapper<?>> wrapper;

            Server(@Nullable Class<? extends PacketWrapper<?>> wrapper) {
                this.ids = new int[CLIENTBOUND_CONFIG_VERSION_MAPPER.getVersions().length];
                Arrays.fill(this.ids, -1);
                this.wrapper = wrapper;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapper;
            }

            public static void load() {
                INDEX = 0;
                loadPacketIds(ClientboundConfigPacketType_1_20_2.values());
                loadPacketIds(ClientboundConfigPacketType_1_20_3.values());
                loadPacketIds(ClientboundConfigPacketType_1_20_5.values());
                loadPacketIds(ClientboundConfigPacketType_1_21.values());
                loadPacketIds(ClientboundConfigPacketType_1_21_6.values());
                loadPacketIds(ClientboundConfigPacketType_1_21_9.values());
                // TODO UPDATE Update packet type mappings (config clientbound pt. 2)
            }

            private static void loadPacketIds(Enum<?>[] enumConstants) {
                int index = INDEX;
                for (Enum<?> constant : enumConstants) {
                    int id = constant.ordinal();
                    Configuration.Server value = Configuration.Server.valueOf(constant.name());
                    value.ids[index] = id;
                    Map<Integer, PacketTypeCommon> packetIdMap = PACKET_TYPE_ID_MAP.computeIfAbsent((byte) index, k -> new HashMap<>());
                    packetIdMap.put(id, value);
                }
                INDEX++;
            }

            public static @Nullable PacketTypeCommon getById(int packetId) {
                return getById(ClientVersion.getLatest(), packetId);
            }

            public static @Nullable PacketTypeCommon getById(ClientVersion version, int packetId) {
                PacketType.prepare();

                int index = CLIENTBOUND_CONFIG_VERSION_MAPPER.getIndex(version);
                Map<Integer, PacketTypeCommon> map = PACKET_TYPE_ID_MAP.get((byte) index);
                return map.get(packetId);
            }

            @Deprecated
            public int getId() {
                return this.getId(ClientVersion.getLatest());
            }

            @Override
            public int getId(ClientVersion version) {
                PacketType.prepare();

                int index = CLIENTBOUND_CONFIG_VERSION_MAPPER.getIndex(version);
                return this.ids[index];
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.SERVER;
            }
        }
    }

    public static class Play {

        public enum Client implements PacketTypeCommon, ServerBoundPacket {

            // Packets which no longer exist on the latest version
            CHAT_PREVIEW(WrapperPlayClientChatPreview.class),

            TELEPORT_CONFIRM(WrapperPlayClientTeleportConfirm.class),
            QUERY_BLOCK_NBT(WrapperPlayClientQueryBlockNBT.class),
            SET_DIFFICULTY(WrapperPlayClientSetDifficulty.class),
            CHAT_MESSAGE(WrapperPlayClientChatMessage.class),
            CLIENT_STATUS(WrapperPlayClientClientStatus.class),
            CLIENT_SETTINGS(WrapperPlayClientSettings.class),
            TAB_COMPLETE(WrapperPlayClientTabComplete.class),
            WINDOW_CONFIRMATION(WrapperPlayClientWindowConfirmation.class),
            CLICK_WINDOW_BUTTON(WrapperPlayClientClickWindowButton.class),
            CLICK_WINDOW(WrapperPlayClientClickWindow.class),
            CLOSE_WINDOW(WrapperPlayClientCloseWindow.class),
            PLUGIN_MESSAGE(WrapperPlayClientPluginMessage.class),
            EDIT_BOOK(WrapperPlayClientEditBook.class),
            QUERY_ENTITY_NBT(WrapperPlayClientQueryEntityNBT.class),
            INTERACT_ENTITY(WrapperPlayClientInteractEntity.class),
            GENERATE_STRUCTURE(WrapperPlayClientGenerateStructure.class),
            KEEP_ALIVE(WrapperPlayClientKeepAlive.class),
            LOCK_DIFFICULTY(WrapperPlayClientLockDifficulty.class),
            PLAYER_POSITION(WrapperPlayClientPlayerPosition.class),
            PLAYER_POSITION_AND_ROTATION(WrapperPlayClientPlayerPositionAndRotation.class),
            PLAYER_ROTATION(WrapperPlayClientPlayerRotation.class),
            PLAYER_FLYING(WrapperPlayClientPlayerFlying.class),
            VEHICLE_MOVE(WrapperPlayClientVehicleMove.class),
            STEER_BOAT(WrapperPlayClientSteerBoat.class),
            /**
             * Removed with 1.21.4
             */
            @ApiStatus.Obsolete
            PICK_ITEM(WrapperPlayClientPickItem.class),
            CRAFT_RECIPE_REQUEST(WrapperPlayClientCraftRecipeRequest.class),
            PLAYER_ABILITIES(WrapperPlayClientPlayerAbilities.class),
            PLAYER_DIGGING(WrapperPlayClientPlayerDigging.class),
            ENTITY_ACTION(WrapperPlayClientEntityAction.class),
            /**
             * Removed with 1.21.2
             */
            @ApiStatus.Obsolete
            STEER_VEHICLE(WrapperPlayClientSteerVehicle.class),
            PONG(WrapperPlayClientPong.class),
            RECIPE_BOOK_DATA(null),
            SET_DISPLAYED_RECIPE(WrapperPlayClientSetDisplayedRecipe.class),
            SET_RECIPE_BOOK_STATE(WrapperPlayClientSetRecipeBookState.class),
            NAME_ITEM(WrapperPlayClientNameItem.class),
            RESOURCE_PACK_STATUS(WrapperPlayClientResourcePackStatus.class),
            ADVANCEMENT_TAB(WrapperPlayClientAdvancementTab.class),
            SELECT_TRADE(WrapperPlayClientSelectTrade.class),
            SET_BEACON_EFFECT(WrapperPlayClientSetBeaconEffect.class),
            HELD_ITEM_CHANGE(WrapperPlayClientHeldItemChange.class),
            UPDATE_COMMAND_BLOCK(WrapperPlayClientUpdateCommandBlock.class),
            UPDATE_COMMAND_BLOCK_MINECART(WrapperPlayClientUpdateCommandBlockMinecart.class),
            CREATIVE_INVENTORY_ACTION(WrapperPlayClientCreativeInventoryAction.class),
            UPDATE_JIGSAW_BLOCK(WrapperPlayClientUpdateJigsawBlock.class),
            UPDATE_STRUCTURE_BLOCK(WrapperPlayClientSetStructureBlock.class),
            UPDATE_SIGN(WrapperPlayClientUpdateSign.class),
            ANIMATION(WrapperPlayClientAnimation.class),
            SPECTATE(WrapperPlayClientSpectate.class),
            PLAYER_BLOCK_PLACEMENT(WrapperPlayClientPlayerBlockPlacement.class),
            USE_ITEM(WrapperPlayClientUseItem.class),

            /**
             * Added with 1.19
             */
            CHAT_COMMAND(WrapperPlayClientChatCommand.class),

            /**
             * Added with 1.19.1
             */
            CHAT_ACK(WrapperPlayClientChatAck.class),

            /**
             * Added with 1.19.3
             */
            CHAT_SESSION_UPDATE(WrapperPlayClientChatSessionUpdate.class),

            /**
             * Added with 1.20.2
             */
            CHUNK_BATCH_ACK(WrapperPlayClientChunkBatchAck.class),
            /**
             * Added with 1.20.2
             */
            CONFIGURATION_ACK(WrapperPlayClientConfigurationAck.class),
            /**
             * Added with 1.20.2
             */
            DEBUG_PING(WrapperPlayClientDebugPing.class),

            /**
             * Added with 1.20.3
             */
            SLOT_STATE_CHANGE(WrapperPlayClientSlotStateChange.class),

            /**
             * Added with 1.20.5
             */
            CHAT_COMMAND_UNSIGNED(WrapperPlayClientChatCommandUnsigned.class),
            /**
             * Added with 1.20.5
             */
            COOKIE_RESPONSE(WrapperPlayClientCookieResponse.class),
            /**
             * @versions 1.20.5-1.21.8
             */
            @ApiStatus.Obsolete
            DEBUG_SAMPLE_SUBSCRIPTION(WrapperPlayClientDebugSampleSubscription.class),

            /**
             * Added with 1.21.2
             */
            CLIENT_TICK_END(WrapperPlayClientClientTickEnd.class),
            /**
             * Added with 1.21.2
             */
            SELECT_BUNDLE_ITEM(WrapperPlayClientSelectBundleItem.class),
            /**
             * Added with 1.21.2, based on {@link #STEER_VEHICLE}
             */
            PLAYER_INPUT(WrapperPlayClientPlayerInput.class),

            /**
             * Added with 1.21.4
             */
            PICK_ITEM_FROM_BLOCK(WrapperPlayClientPickItemFromBlock.class),
            /**
             * Added with 1.21.4
             */
            PICK_ITEM_FROM_ENTITY(WrapperPlayClientPickItemFromEntity.class),
            /**
             * Added with 1.21.4
             */
            PLAYER_LOADED(WrapperPlayClientPlayerLoaded.class),

            /**
             * Added with 1.21.5
             */
            SET_TEST_BLOCK(WrapperPlayClientSetTestBlock.class),
            /**
             * Added with 1.21.5
             */
            TEST_INSTANCE_BLOCK_ACTION(WrapperPlayClientTestInstanceBlockAction.class),

            /**
             * Added with 1.21.6
             */
            CHANGE_GAME_MODE(WrapperPlayClientChangeGameMode.class),
            /**
             * Added with 1.21.6
             */
            CUSTOM_CLICK_ACTION(WrapperPlayClientCustomClickAction.class),

            /**
             * @versions 1.21.9+
             */
            DEBUG_SUBSCRIPTION_REQUEST(WrapperPlayClientDebugSubscriptionRequest.class),

            /**
             * @versions 26.1+
             */
            ATTACK(WrapperPlayClientAttack.class),
            /**
             * @versions 26.1+
             */
            SET_GAME_RULE(WrapperPlayClientSetGameRule.class),
            /**
             * @versions 26.1+
             */
            SPECTATE_ENTITY(WrapperPlayClientSpectateEntity.class),
            ;

            private static int INDEX = 0;
            private static final Map<Byte, Map<Integer, PacketTypeCommon>> PACKET_TYPE_ID_MAP = new HashMap<>();
            private final int[] ids;
            private final Class<? extends PacketWrapper<?>> wrapper;

            Client(@Nullable Class<? extends PacketWrapper<?>> wrapper) {
                ids = new int[SERVERBOUND_PLAY_VERSION_MAPPER.getVersions().length];
                Arrays.fill(ids, -1);
                this.wrapper = wrapper;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapper;
            }

            @Nullable
            public static PacketTypeCommon getById(ClientVersion version, int packetId) {
                PacketType.prepare();

                int index = SERVERBOUND_PLAY_VERSION_MAPPER.getIndex(version);
                Map<Integer, PacketTypeCommon> packetIdMap = PACKET_TYPE_ID_MAP.computeIfAbsent((byte) index, k -> new HashMap<>());
                return packetIdMap.get(packetId);
            }

            private static void loadPacketIds(Enum<?>[] enumConstants) {
                int index = INDEX;
                for (Enum<?> constant : enumConstants) {
                    int id = constant.ordinal();
                    Client value = Client.valueOf(constant.name());
                    value.ids[index] = id;
                    Map<Integer, PacketTypeCommon> packetIdMap = PACKET_TYPE_ID_MAP.computeIfAbsent((byte) index,
                            k -> new HashMap<>());
                    packetIdMap.put(id, value);
                }
                INDEX++;
            }

            public static void load() {
                INDEX = 0;
                loadPacketIds(ServerboundPacketType_1_7_10.values());
                loadPacketIds(ServerboundPacketType_1_8.values());
                loadPacketIds(ServerboundPacketType_1_9.values());
                loadPacketIds(ServerboundPacketType_1_12.values());
                loadPacketIds(ServerboundPacketType_1_12_1.values());
                loadPacketIds(ServerboundPacketType_1_13.values());
                loadPacketIds(ServerboundPacketType_1_14.values());
                loadPacketIds(ServerboundPacketType_1_15_2.values());
                loadPacketIds(ServerboundPacketType_1_16.values());
                loadPacketIds(ServerboundPacketType_1_16_2.values());
                loadPacketIds(ServerboundPacketType_1_17.values());
                loadPacketIds(ServerboundPacketType_1_19.values());
                loadPacketIds(ServerboundPacketType_1_19_1.values());
                loadPacketIds(ServerboundPacketType_1_19_3.values());
                loadPacketIds(ServerboundPacketType_1_19_4.values());
                loadPacketIds(ServerboundPacketType_1_20_2.values());
                loadPacketIds(ServerboundPacketType_1_20_3.values());
                loadPacketIds(ServerboundPacketType_1_20_5.values());
                loadPacketIds(ServerboundPacketType_1_21_2.values());
                loadPacketIds(ServerboundPacketType_1_21_4.values());
                loadPacketIds(ServerboundPacketType_1_21_5.values());
                loadPacketIds(ServerboundPacketType_1_21_6.values());
                loadPacketIds(ServerboundPacketType_1_21_9.values());
                loadPacketIds(ServerboundPacketType_26_1.values());
                //TODO UPDATE Update packet type mappings (serverbound pt. 2)
            }

            public int getId(ClientVersion version) {
                PacketType.prepare();

                int index = SERVERBOUND_PLAY_VERSION_MAPPER.getIndex(version);
                return ids[index];
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.CLIENT;
            }
        }

        public enum Server implements PacketTypeCommon, ClientBoundPacket {

            // Packets which are no longer exist on the latest version
            SET_COMPRESSION(WrapperPlayServerSetCompression.class),
            MAP_CHUNK_BULK(WrapperPlayServerChunkDataBulk.class),
            UPDATE_ENTITY_NBT(WrapperPlayServerUpdateEntityNBT.class),
            UPDATE_SIGN(null),
            USE_BED(WrapperPlayServerUseBed.class),
            SPAWN_WEATHER_ENTITY(WrapperPlayServerSpawnWeatherEntity.class),
            TITLE(WrapperPlayServerSetTitleSubtitle.class),
            WORLD_BORDER(WrapperPlayServerWorldBorder.class),
            COMBAT_EVENT(WrapperPlayServerCombatEvent.class),
            ENTITY_MOVEMENT(WrapperPlayServerEntityMovement.class),
            SPAWN_LIVING_ENTITY(WrapperPlayServerSpawnLivingEntity.class),
            SPAWN_PAINTING(WrapperPlayServerSpawnPainting.class),
            SCULK_VIBRATION_SIGNAL(null),
            ACKNOWLEDGE_PLAYER_DIGGING(WrapperPlayServerAcknowledgePlayerDigging.class),
            CHAT_PREVIEW_PACKET(WrapperPlayServerChatPreview.class),
            NAMED_SOUND_EFFECT(null),
            PLAYER_CHAT_HEADER(WrapperPlayServerPlayerChatHeader.class),
            PLAYER_INFO(WrapperPlayServerPlayerInfo.class),
            DISPLAY_CHAT_PREVIEW(WrapperPlayServerSetDisplayChatPreview.class),
            UPDATE_ENABLED_FEATURES(WrapperPlayServerUpdateEnabledFeatures.class),
            SPAWN_PLAYER(WrapperPlayServerSpawnPlayer.class),

            // Still existing packets
            WINDOW_CONFIRMATION(WrapperPlayServerWindowConfirmation.class),
            SPAWN_ENTITY(WrapperPlayServerSpawnEntity.class),
            /**
             * Removed with 1.21.5
             */
            @ApiStatus.Obsolete
            SPAWN_EXPERIENCE_ORB(WrapperPlayServerSpawnExperienceOrb.class),
            ENTITY_ANIMATION(WrapperPlayServerEntityAnimation.class),
            STATISTICS(WrapperPlayServerStatistics.class),
            BLOCK_BREAK_ANIMATION(WrapperPlayServerBlockBreakAnimation.class),
            BLOCK_ENTITY_DATA(WrapperPlayServerBlockEntityData.class),
            BLOCK_ACTION(WrapperPlayServerBlockAction.class),
            BLOCK_CHANGE(WrapperPlayServerBlockChange.class),
            BOSS_BAR(WrapperPlayServerBossBar.class),
            SERVER_DIFFICULTY(WrapperPlayServerDifficulty.class),
            CLEAR_TITLES(WrapperPlayServerClearTitles.class),
            TAB_COMPLETE(WrapperPlayServerTabComplete.class),
            MULTI_BLOCK_CHANGE(WrapperPlayServerMultiBlockChange.class),
            DECLARE_COMMANDS(WrapperPlayServerDeclareCommands.class),
            CLOSE_WINDOW(WrapperPlayServerCloseWindow.class),
            WINDOW_ITEMS(WrapperPlayServerWindowItems.class),
            WINDOW_PROPERTY(WrapperPlayServerWindowProperty.class),
            SET_SLOT(WrapperPlayServerSetSlot.class),
            SET_COOLDOWN(WrapperPlayServerSetCooldown.class),
            PLUGIN_MESSAGE(WrapperPlayServerPluginMessage.class),
            DISCONNECT(WrapperPlayServerDisconnect.class),
            ENTITY_STATUS(WrapperPlayServerEntityStatus.class),
            EXPLOSION(WrapperPlayServerExplosion.class),
            UNLOAD_CHUNK(WrapperPlayServerUnloadChunk.class),
            CHANGE_GAME_STATE(WrapperPlayServerChangeGameState.class),
            OPEN_HORSE_WINDOW(WrapperPlayServerOpenHorseWindow.class),
            INITIALIZE_WORLD_BORDER(WrapperPlayServerInitializeWorldBorder.class),
            KEEP_ALIVE(WrapperPlayServerKeepAlive.class),
            CHUNK_DATA(WrapperPlayServerChunkData.class),
            EFFECT(WrapperPlayServerEffect.class),
            PARTICLE(WrapperPlayServerParticle.class),
            UPDATE_LIGHT(WrapperPlayServerUpdateLight.class),
            JOIN_GAME(WrapperPlayServerJoinGame.class),
            MAP_DATA(WrapperPlayServerMapData.class),
            MERCHANT_OFFERS(WrapperPlayServerMerchantOffers.class),
            ENTITY_RELATIVE_MOVE(WrapperPlayServerEntityRelativeMove.class),
            ENTITY_RELATIVE_MOVE_AND_ROTATION(WrapperPlayServerEntityRelativeMoveAndRotation.class),
            ENTITY_ROTATION(WrapperPlayServerEntityRotation.class),
            VEHICLE_MOVE(WrapperPlayServerVehicleMove.class),
            OPEN_BOOK(WrapperPlayServerOpenBook.class),
            OPEN_WINDOW(WrapperPlayServerOpenWindow.class),
            OPEN_SIGN_EDITOR(WrapperPlayServerOpenSignEditor.class),
            PING(WrapperPlayServerPing.class),
            CRAFT_RECIPE_RESPONSE(WrapperPlayServerCraftRecipeResponse.class),
            PLAYER_ABILITIES(WrapperPlayServerPlayerAbilities.class),
            END_COMBAT_EVENT(WrapperPlayServerEndCombatEvent.class),
            ENTER_COMBAT_EVENT(WrapperPlayServerEnterCombatEvent.class),
            DEATH_COMBAT_EVENT(WrapperPlayServerDeathCombatEvent.class),
            FACE_PLAYER(WrapperPlayServerFacePlayer.class),
            PLAYER_POSITION_AND_LOOK(WrapperPlayServerPlayerPositionAndLook.class),
            /**
             * Removed with 1.21.2
             */
            @ApiStatus.Obsolete
            UNLOCK_RECIPES(null),
            DESTROY_ENTITIES(WrapperPlayServerDestroyEntities.class),
            REMOVE_ENTITY_EFFECT(WrapperPlayServerRemoveEntityEffect.class),
            RESOURCE_PACK_SEND(WrapperPlayServerResourcePackSend.class),
            RESPAWN(WrapperPlayServerRespawn.class),
            ENTITY_HEAD_LOOK(WrapperPlayServerEntityHeadLook.class),
            SELECT_ADVANCEMENTS_TAB(WrapperPlayServerSelectAdvancementsTab.class),
            ACTION_BAR(WrapperPlayServerActionBar.class),
            WORLD_BORDER_CENTER(WrapperPlayServerWorldBorderCenter.class),
            WORLD_BORDER_LERP_SIZE(WrapperPlayWorldBorderLerpSize.class),
            WORLD_BORDER_SIZE(WrapperPlayServerWorldBorderSize.class),
            WORLD_BORDER_WARNING_DELAY(WrapperPlayWorldBorderWarningDelay.class),
            WORLD_BORDER_WARNING_REACH(WrapperPlayServerWorldBorderWarningReach.class),
            CAMERA(WrapperPlayServerCamera.class),
            HELD_ITEM_CHANGE(WrapperPlayServerHeldItemChange.class),
            UPDATE_VIEW_POSITION(WrapperPlayServerUpdateViewPosition.class),
            UPDATE_VIEW_DISTANCE(WrapperPlayServerUpdateViewDistance.class),
            SPAWN_POSITION(WrapperPlayServerSpawnPosition.class),
            DISPLAY_SCOREBOARD(WrapperPlayServerDisplayScoreboard.class),
            ENTITY_METADATA(WrapperPlayServerEntityMetadata.class),
            ATTACH_ENTITY(WrapperPlayServerAttachEntity.class),
            ENTITY_VELOCITY(WrapperPlayServerEntityVelocity.class),
            ENTITY_EQUIPMENT(WrapperPlayServerEntityEquipment.class),
            SET_EXPERIENCE(WrapperPlayServerSetExperience.class),
            UPDATE_HEALTH(WrapperPlayServerUpdateHealth.class),
            SCOREBOARD_OBJECTIVE(WrapperPlayServerScoreboardObjective.class),
            SET_PASSENGERS(WrapperPlayServerSetPassengers.class),
            TEAMS(WrapperPlayServerTeams.class),
            UPDATE_SCORE(WrapperPlayServerUpdateScore.class),
            UPDATE_SIMULATION_DISTANCE(WrapperPlayServerUpdateSimulationDistance.class),
            SET_TITLE_SUBTITLE(WrapperPlayServerSetTitleSubtitle.class),
            TIME_UPDATE(WrapperPlayServerTimeUpdate.class),
            SET_TITLE_TEXT(WrapperPlayServerSetTitleText.class),
            SET_TITLE_TIMES(WrapperPlayServerSetTitleTimes.class),
            ENTITY_SOUND_EFFECT(WrapperPlayServerEntitySoundEffect.class),
            SOUND_EFFECT(WrapperPlayServerSoundEffect.class),
            STOP_SOUND(null),
            PLAYER_LIST_HEADER_AND_FOOTER(WrapperPlayServerPlayerListHeaderAndFooter.class),
            NBT_QUERY_RESPONSE(WrapperPlayServerNBTQueryResponse.class),
            COLLECT_ITEM(WrapperPlayServerCollectItem.class),
            ENTITY_TELEPORT(WrapperPlayServerEntityTeleport.class),
            UPDATE_ADVANCEMENTS(WrapperPlayServerUpdateAdvancements.class),
            UPDATE_ATTRIBUTES(WrapperPlayServerUpdateAttributes.class),
            ENTITY_EFFECT(WrapperPlayServerEntityEffect.class),
            DECLARE_RECIPES(WrapperPlayServerDeclareRecipes.class),
            TAGS(WrapperPlayServerTags.class),
            CHAT_MESSAGE(WrapperPlayServerChatMessage.class),

            /**
             * Added with 1.19
             */
            ACKNOWLEDGE_BLOCK_CHANGES(WrapperPlayServerAcknowledgeBlockChanges.class),
            /**
             * Added with 1.19
             */
            SERVER_DATA(WrapperPlayServerServerData.class),
            /**
             * Added with 1.19
             */
            SYSTEM_CHAT_MESSAGE(WrapperPlayServerSystemChatMessage.class),

            /**
             * Added with 1.19.1
             */
            DELETE_CHAT(WrapperPlayServerDeleteChat.class),
            /**
             * Added with 1.19.1
             */
            CUSTOM_CHAT_COMPLETIONS(WrapperPlayServerCustomChatCompletions.class),

            /**
             * Added with 1.19.3
             */
            DISGUISED_CHAT(WrapperPlayServerDisguisedChat.class),
            /**
             * Added with 1.19.3
             */
            PLAYER_INFO_REMOVE(WrapperPlayServerPlayerInfoRemove.class),
            /**
             * Added with 1.19.3
             */
            PLAYER_INFO_UPDATE(WrapperPlayServerPlayerInfoUpdate.class),

            /**
             * Added with 1.19.4
             */
            DAMAGE_EVENT(WrapperPlayServerDamageEvent.class),
            /**
             * Added with 1.19.4
             */
            HURT_ANIMATION(WrapperPlayServerHurtAnimation.class),
            /**
             * Added with 1.19.4
             */
            BUNDLE(WrapperPlayServerBundle.class),
            /**
             * Added with 1.19.4
             */
            CHUNK_BIOMES(WrapperPlayServerChunkBiomes.class),

            /**
             * Added with 1.20.2
             */
            CHUNK_BATCH_END(WrapperPlayServerChunkBatchEnd.class),
            /**
             * Added with 1.20.2
             */
            CHUNK_BATCH_BEGIN(WrapperPlayServerChunkBatchBegin.class),
            /**
             * Added with 1.20.2
             */
            DEBUG_PONG(WrapperPlayServerDebugPong.class),
            /**
             * Added with 1.20.2
             */
            CONFIGURATION_START(WrapperPlayServerConfigurationStart.class),

            /**
             * Added with 1.20.3
             */
            RESET_SCORE(WrapperPlayServerResetScore.class),
            /**
             * Added with 1.20.3
             */
            RESOURCE_PACK_REMOVE(WrapperPlayServerResourcePackRemove.class),
            /**
             * Added with 1.20.3
             */
            TICKING_STATE(WrapperPlayServerTickingState.class),
            /**
             * Added with 1.20.3
             */
            TICKING_STEP(WrapperPlayServerTickingStep.class),

            /**
             * Added with 1.20.5
             */
            COOKIE_REQUEST(WrapperPlayServerCookieRequest.class),
            /**
             * Added with 1.20.5
             */
            DEBUG_SAMPLE(WrapperPlayServerDebugSample.class),
            /**
             * Added with 1.20.5
             */
            STORE_COOKIE(WrapperPlayServerStoreCookie.class),
            /**
             * Added with 1.20.5
             */
            TRANSFER(WrapperPlayServerTransfer.class),
            /**
             * Added with 1.20.5
             */
            PROJECTILE_POWER(WrapperPlayServerProjectilePower.class),

            /**
             * Added with 1.21
             */
            CUSTOM_REPORT_DETAILS(WrapperPlayServerCustomReportDetails.class),
            /**
             * Added with 1.21
             */
            SERVER_LINKS(WrapperPlayServerServerLinks.class),

            /**
             * Added with 1.21.2
             */
            MOVE_MINECART(WrapperPlayServerMoveMinecart.class),
            /**
             * Added with 1.21.2
             */
            SET_CURSOR_ITEM(WrapperPlayServerSetCursorItem.class),
            /**
             * Added with 1.21.2
             */
            SET_PLAYER_INVENTORY(WrapperPlayServerSetPlayerInventory.class),
            /**
             * Added with 1.21.2
             */
            ENTITY_POSITION_SYNC(WrapperPlayServerEntityPositionSync.class),
            /**
             * Added with 1.21.2
             */
            PLAYER_ROTATION(WrapperPlayServerPlayerRotation.class),
            /**
             * Added with 1.21.2
             */
            RECIPE_BOOK_ADD(WrapperPlayServerRecipeBookAdd.class),
            /**
             * Added with 1.21.2
             */
            RECIPE_BOOK_REMOVE(WrapperPlayServerRecipeBookRemove.class),
            /**
             * Added with 1.21.2
             */
            RECIPE_BOOK_SETTINGS(WrapperPlayServerRecipeBookSettings.class),

            /**
             * Added with 1.21.5
             */
            TEST_INSTANCE_BLOCK_STATUS(WrapperPlayServerTestInstanceBlockStatus.class),

            /**
             * Added with 1.21.6
             */
            WAYPOINT(WrapperPlayServerWaypoint.class),
            /**
             * Added with 1.21.6
             */
            CLEAR_DIALOG(WrapperPlayServerClearDialog.class),
            /**
             * Added with 1.21.6
             */
            SHOW_DIALOG(WrapperPlayServerShowDialog.class),

            /**
             * @versions 1.21.9+
             */
            DEBUG_BLOCK_VALUE(WrapperPlayServerDebugBlockValue.class),
            /**
             * @versions 1.21.9+
             */
            DEBUG_CHUNK_VALUE(WrapperPlayServerDebugChunkValue.class),
            /**
             * @versions 1.21.9+
             */
            DEBUG_ENTITY_VALUE(WrapperPlayServerDebugEntityValue.class),
            /**
             * @versions 1.21.9+
             */
            DEBUG_EVENT(WrapperPlayServerDebugEvent.class),
            /**
             * @versions 1.21.9+
             */
            GAME_TEST_HIGHLIGHT_POS(WrapperPlayServerGameTestHighlightPos.class),

            /**
             * @versions 26.1+
             */
            GAME_RULE_VALUES(WrapperPlayServerGameRuleValues.class),
            /**
             * @versions 26.1+
             */
            LOW_DISK_SPACE_WARNING(WrapperPlayServerLowDiskSpaceWarning.class),
            ;

            private static int INDEX = 0;
            private static final Map<Byte, Map<Integer, PacketTypeCommon>> PACKET_TYPE_ID_MAP = new HashMap<>();
            private final int[] ids;
            private final Class<? extends PacketWrapper<?>> wrapper;

            Server(@Nullable Class<? extends PacketWrapper<?>> wrapper) {
                ids = new int[CLIENTBOUND_PLAY_VERSION_MAPPER.getVersions().length];
                Arrays.fill(ids, -1);
                this.wrapper = wrapper;
            }

            @Override
            public Class<? extends PacketWrapper<?>> getWrapperClass() {
                return wrapper;
            }

            public int getId(ClientVersion version) {
                PacketType.prepare();

                int index = CLIENTBOUND_PLAY_VERSION_MAPPER.getIndex(version);
                return ids[index];
            }

            @Nullable
            public static PacketTypeCommon getById(ClientVersion version, int packetId) {
                PacketType.prepare();

                int index = CLIENTBOUND_PLAY_VERSION_MAPPER.getIndex(version);
                Map<Integer, PacketTypeCommon> map = PACKET_TYPE_ID_MAP.get((byte) index);
                return map.get(packetId);
            }

            @Override
            public PacketSide getSide() {
                return PacketSide.SERVER;
            }

            private static void loadPacketIds(Enum<?>[] enumConstants) {
                int index = INDEX;
                for (Enum<?> constant : enumConstants) {
                    int id = constant.ordinal();
                    Server value = Server.valueOf(constant.name());
                    value.ids[index] = id;
                    Map<Integer, PacketTypeCommon> packetIdMap = PACKET_TYPE_ID_MAP.computeIfAbsent((byte) index, k -> new HashMap<>());
                    packetIdMap.put(id, value);
                }
                INDEX++;
            }

            public static void load() {
                INDEX = 0;
                loadPacketIds(ClientboundPacketType_1_7_10.values());
                loadPacketIds(ClientboundPacketType_1_8.values());
                loadPacketIds(ClientboundPacketType_1_9.values());
                loadPacketIds(ClientboundPacketType_1_9_3.values());
                loadPacketIds(ClientboundPacketType_1_12.values());
                loadPacketIds(ClientboundPacketType_1_12_1.values());
                loadPacketIds(ClientboundPacketType_1_13.values());
                loadPacketIds(ClientboundPacketType_1_14.values());
                loadPacketIds(ClientboundPacketType_1_14_4.values());
                loadPacketIds(ClientboundPacketType_1_15.values());
                loadPacketIds(ClientboundPacketType_1_15_2.values());
                loadPacketIds(ClientboundPacketType_1_16.values());
                loadPacketIds(ClientboundPacketType_1_16_2.values());
                loadPacketIds(ClientboundPacketType_1_17.values());
                loadPacketIds(ClientboundPacketType_1_18.values());
                loadPacketIds(ClientboundPacketType_1_19.values());
                loadPacketIds(ClientboundPacketType_1_19_1.values());
                loadPacketIds(ClientboundPacketType_1_19_3.values());
                loadPacketIds(ClientboundPacketType_1_19_4.values());
                loadPacketIds(ClientboundPacketType_1_20_2.values());
                loadPacketIds(ClientboundPacketType_1_20_3.values());
                loadPacketIds(ClientboundPacketType_1_20_5.values());
                loadPacketIds(ClientboundPacketType_1_21.values());
                loadPacketIds(ClientboundPacketType_1_21_2.values());
                loadPacketIds(ClientboundPacketType_1_21_5.values());
                loadPacketIds(ClientboundPacketType_1_21_6.values());
                loadPacketIds(ClientboundPacketType_1_21_9.values());
                loadPacketIds(ClientboundPacketType_26_1.values());
                //TODO UPDATE Update packet type mappings (clientbound pt. 2)
            }
        }
    }
}
