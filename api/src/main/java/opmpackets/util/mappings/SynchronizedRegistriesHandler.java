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

package opmpackets.util.mappings;

import opmpackets.PacketEvents;
import opmpackets.protocol.chat.ChatType;
import opmpackets.protocol.chat.ChatTypes;
import opmpackets.protocol.dialog.Dialog;
import opmpackets.protocol.dialog.Dialogs;
import opmpackets.protocol.entity.cat.CatSoundVariant;
import opmpackets.protocol.entity.cat.CatSoundVariants;
import opmpackets.protocol.entity.cat.CatVariant;
import opmpackets.protocol.entity.cat.CatVariants;
import opmpackets.protocol.entity.chicken.ChickenSoundVariant;
import opmpackets.protocol.entity.chicken.ChickenSoundVariants;
import opmpackets.protocol.entity.chicken.ChickenVariant;
import opmpackets.protocol.entity.chicken.ChickenVariants;
import opmpackets.protocol.entity.cow.CowSoundVariant;
import opmpackets.protocol.entity.cow.CowSoundVariants;
import opmpackets.protocol.entity.cow.CowVariant;
import opmpackets.protocol.entity.cow.CowVariants;
import opmpackets.protocol.entity.frog.FrogVariant;
import opmpackets.protocol.entity.frog.FrogVariants;
import opmpackets.protocol.entity.nautilus.ZombieNautilusVariant;
import opmpackets.protocol.entity.nautilus.ZombieNautilusVariants;
import opmpackets.protocol.entity.pig.PigSoundVariant;
import opmpackets.protocol.entity.pig.PigSoundVariants;
import opmpackets.protocol.entity.pig.PigVariant;
import opmpackets.protocol.entity.pig.PigVariants;
import opmpackets.protocol.entity.sulfurcube.SulfurCubeArchtype;
import opmpackets.protocol.entity.sulfurcube.SulfurCubeArchtypes;
import opmpackets.protocol.entity.wolfvariant.WolfSoundVariant;
import opmpackets.protocol.entity.wolfvariant.WolfSoundVariants;
import opmpackets.protocol.entity.wolfvariant.WolfVariant;
import opmpackets.protocol.entity.wolfvariant.WolfVariants;
import opmpackets.protocol.item.banner.BannerPattern;
import opmpackets.protocol.item.banner.BannerPatterns;
import opmpackets.protocol.item.enchantment.type.EnchantmentType;
import opmpackets.protocol.item.enchantment.type.EnchantmentTypes;
import opmpackets.protocol.item.instrument.Instrument;
import opmpackets.protocol.item.instrument.Instruments;
import opmpackets.protocol.item.jukebox.IJukeboxSong;
import opmpackets.protocol.item.jukebox.JukeboxSongs;
import opmpackets.protocol.item.trimmaterial.TrimMaterial;
import opmpackets.protocol.item.trimmaterial.TrimMaterials;
import opmpackets.protocol.item.trimpattern.TrimPattern;
import opmpackets.protocol.item.trimpattern.TrimPatterns;
import opmpackets.protocol.mapper.CopyableEntity;
import opmpackets.protocol.mapper.DeepComparableEntity;
import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.nbt.NBT;
import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTList;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.protocol.player.User;
import opmpackets.protocol.util.NbtDecoder;
import opmpackets.protocol.world.attributes.timelines.Timeline;
import opmpackets.protocol.world.attributes.timelines.Timelines;
import opmpackets.protocol.world.biome.Biome;
import opmpackets.protocol.world.biome.Biomes;
import opmpackets.protocol.world.clock.WorldClock;
import opmpackets.protocol.world.clock.WorldClocks;
import opmpackets.protocol.world.damagetype.DamageType;
import opmpackets.protocol.world.damagetype.DamageTypes;
import opmpackets.protocol.world.dimension.DimensionType;
import opmpackets.protocol.world.dimension.DimensionTypes;
import opmpackets.protocol.world.painting.PaintingVariant;
import opmpackets.protocol.world.painting.PaintingVariants;
import opmpackets.resources.ResourceLocation;
import opmpackets.wrapper.PacketWrapper;
import opmpackets.wrapper.configuration.server.WrapperConfigServerRegistryData.RegistryElement;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

@NullMarked
@ApiStatus.Internal
public final class SynchronizedRegistriesHandler {

    private static final boolean FORCE_PER_USER_REGISTRIES = Boolean.getBoolean("packetevents.force-per-user-registries");
    private static final Map<ResourceLocation, RegistryEntry<?>> REGISTRY_KEYS = new HashMap<>();

    static {
        // packetevents ignores a few unimportant registries which aren't used anywhere else in the protocol:
        // test_environment, test_instance
        Stream.of(
                new RegistryEntry<>(Biomes.getRegistry(), Biome.CODEC),
                new RegistryEntry<>(ChatTypes.getRegistry(), ChatType::decode),
                new RegistryEntry<>(TrimPatterns.getRegistry(), (NbtEntryDecoder<TrimPattern>) TrimPattern::decode),
                new RegistryEntry<>(TrimMaterials.getRegistry(), (NbtEntryDecoder<TrimMaterial>) TrimMaterial::decode),
                new RegistryEntry<>(WolfVariants.getRegistry(), WolfVariant.CODEC),
                new RegistryEntry<>(WolfSoundVariants.getRegistry(), WolfSoundVariant.CODEC),
                new RegistryEntry<>(PigVariants.getRegistry(), PigVariant::decode),
                new RegistryEntry<>(FrogVariants.getRegistry(), FrogVariant::decode),
                new RegistryEntry<>(CatVariants.getRegistry(), CatVariant.CODEC),
                new RegistryEntry<>(CowVariants.getRegistry(), CowVariant.CODEC),
                new RegistryEntry<>(ChickenVariants.getRegistry(), ChickenVariant.CODEC),
                new RegistryEntry<>(ZombieNautilusVariants.getRegistry(), ZombieNautilusVariant::decode),
                new RegistryEntry<>(PaintingVariants.getRegistry(), PaintingVariant.CODEC),
                new RegistryEntry<>(DimensionTypes.getRegistry(), DimensionType.CODEC),
                new RegistryEntry<>(DamageTypes.getRegistry(), DamageType.DIRECT_CODEC),
                new RegistryEntry<>(BannerPatterns.getRegistry(), BannerPattern::decode),
                new RegistryEntry<>(EnchantmentTypes.getRegistry(), (NbtEntryDecoder<EnchantmentType>) EnchantmentType::decode),
                new RegistryEntry<>(JukeboxSongs.getRegistry(), (NbtEntryDecoder<IJukeboxSong>) IJukeboxSong::decode),
                new RegistryEntry<>(Instruments.getRegistry(), (NbtEntryDecoder<Instrument>) Instrument::decode),
                new RegistryEntry<>(Dialogs.getRegistry(), Dialog::decodeDirect),
                new RegistryEntry<>(Timelines.getRegistry(), Timeline.CODEC),
                new RegistryEntry<>(PigSoundVariants.getRegistry(), PigSoundVariant.CODEC),
                new RegistryEntry<>(CatSoundVariants.getRegistry(), CatSoundVariant.CODEC),
                new RegistryEntry<>(CowSoundVariants.getRegistry(), CowSoundVariant.CODEC),
                new RegistryEntry<>(ChickenSoundVariants.getRegistry(), ChickenSoundVariant.CODEC),
                new RegistryEntry<>(WorldClocks.getRegistry(), WorldClock.DIRECT_CODEC),
                new RegistryEntry<>(SulfurCubeArchtypes.getRegistry(), SulfurCubeArchtype.DIRECT_CODEC)
        ).forEach(entry -> REGISTRY_KEYS.put(entry.getRegistryKey(), entry));
    }

    private SynchronizedRegistriesHandler() {
    }

    public static void init() {
        // check this class has been fully loaded
        if (REGISTRY_KEYS.isEmpty()) {
            throw new AssertionError();
        }
    }

    public static @Nullable RegistryEntry<?> getRegistryEntry(ResourceLocation registryKey) {
        return REGISTRY_KEYS.get(registryKey);
    }

    public static void handleRegistry(
            User user, PacketWrapper<?> wrapper,
            ResourceLocation registryName,
            List<RegistryElement> elements
    ) {
        Object cacheKey = PacketEvents.getAPI().getServerManager().getRegistryCacheKey(
                user, wrapper.getServerVersion().toClientVersion());
        handleRegistry(user, wrapper, registryName, elements, cacheKey);
    }

    public static void handleRegistry(
            User user, PacketWrapper<?> wrapper,
            ResourceLocation registryName,
            List<RegistryElement> elements,
            @Nullable Object cacheKey
    ) {
        RegistryEntry<?> registryData = REGISTRY_KEYS.get(registryName);
        if (registryData == null) {
            return;
        }
        try {
            SimpleRegistry<?> syncedRegistry;
            if (FORCE_PER_USER_REGISTRIES || cacheKey == null) {
                syncedRegistry = registryData.createFromElements(elements, wrapper); // no caching
            } else {
                syncedRegistry = registryData.computeSyncedRegistry(cacheKey, () ->
                        registryData.createFromElements(elements, wrapper));
            }
            user.putRegistry(syncedRegistry);
        } catch (Exception exception) {
            throw new IllegalStateException("Error while reading registry " + registryName + " for " + user, exception);
        }
    }

    public static void handleLegacyRegistries(
            User user, PacketWrapper<?> wrapper,
            NBTCompound registryData
    ) {
        Object cacheKey = PacketEvents.getAPI().getServerManager().getRegistryCacheKey(
                user, wrapper.getServerVersion().toClientVersion());
        for (NBT tag : registryData.getTags().values()) {
            //On 1.16 they send an NBTList for dimension.
            if (tag instanceof NBTList) {
                @SuppressWarnings("unchecked")
                NBTList<NBTCompound> list = (NBTList<NBTCompound>) tag;
                handleRegistry(user, wrapper, DimensionTypes.getRegistry().getRegistryKey(),
                        RegistryElement.convertNbt(list), cacheKey);

            }
            //Newer versions
            else {
                NBTCompound compound = (NBTCompound) tag;
                // extract registry name
                ResourceLocation registryName = new ResourceLocation(compound.getStringTagValueOrThrow("type"));
                // extract registry entries
                NBTList<NBTCompound> nbtElements = compound.getCompoundListTagOrNull("value");
                if (nbtElements != null) {
                    // store registry elements
                    handleRegistry(user, wrapper, registryName,
                            RegistryElement.convertNbt(nbtElements), cacheKey);
                }
            }
        }
    }

    @ApiStatus.Internal
    @FunctionalInterface
    public interface LegacyNbtEntryDecoder<T> {

        T decode(NBT nbt, ClientVersion version, @Nullable TypesBuilderData data);

        default NbtEntryDecoder<T> upgrade() {
            return (nbt, wrapper, data) ->
                    this.decode(nbt, wrapper.getServerVersion().toClientVersion(), data);
        }
    }

    @ApiStatus.Internal
    @FunctionalInterface
    public interface NbtEntryDecoder<T> {

        static <T extends MappedEntity & CopyableEntity<T>> NbtEntryDecoder<T> fromDecoder(NbtDecoder<T> decoder) {
            return (tag, wrapper, data) -> decoder.decode(tag, wrapper).copy(data);
        }

        T decode(NBT tag, PacketWrapper<?> wrapper, @Nullable TypesBuilderData data);
    }

    @ApiStatus.Internal
    public static final class RegistryEntry<T extends MappedEntity & CopyableEntity<T> & DeepComparableEntity> {

        private final IRegistry<T> baseRegistry;
        private final NbtEntryDecoder<T> decoder;

        // each registry may have a synchronized registry cache, for convenience and enhanced performance
        //
        // the key to this cache depends on the platform - it may be a constant value for bukkit servers
        // or some backend server related value for proxy servers
        private final Map<Object, SimpleRegistry<T>> syncedRegistries = new ConcurrentHashMap<>(2);

        public RegistryEntry(
                IRegistry<T> baseRegistry,
                LegacyNbtEntryDecoder<T> decoder
        ) {
            this(baseRegistry, decoder.upgrade());
        }

        public RegistryEntry(
                IRegistry<T> baseRegistry,
                NbtDecoder<T> decoder
        ) {
            this(baseRegistry, NbtEntryDecoder.fromDecoder(decoder));
        }

        public RegistryEntry(
                IRegistry<T> baseRegistry,
                NbtEntryDecoder<T> decoder
        ) {
            this.baseRegistry = baseRegistry;
            this.decoder = decoder;
        }

        public @Nullable SimpleRegistry<T> getSyncedRegistry(Object key) {
            return this.syncedRegistries.get(key);
        }

        @SuppressWarnings("unchecked")
        public SimpleRegistry<T> computeSyncedRegistry(Object key, Supplier<SimpleRegistry<?>> registry) {
            return this.syncedRegistries.computeIfAbsent(key,
                    $ -> (SimpleRegistry<T>) registry.get());
        }

        private void handleElement(
                SimpleRegistry<T> registry,
                RegistryElement element,
                int id, PacketWrapper<?> wrapper
        ) {
            ResourceLocation elementName = element.getId();
            ClientVersion version = wrapper.getServerVersion().toClientVersion();
            T baseEntry = this.baseRegistry.getByName(version, elementName);

            // save new element definition in base entry for comparison - and fallback
            TypesBuilderData data = new SimpleTypesBuilderData(elementName, id);
            T copiedBaseEntry = baseEntry == null ? null : baseEntry.copy(data);

            if (element.getData() != null) {
                // data was provided, use registry element sent over network
                T value = this.decoder.decode(element.getData(), wrapper, data);
                if (!value.deepEquals(copiedBaseEntry)) {
                    // only define decoded value if it doesn't match the base
                    // registry value; this ensures we don't save everything twice,
                    // if it has been already stored in memory
                    registry.define(elementName, id, value);
                    return;
                }
            }

            // fallback to looking up in vanilla registry;
            // this isn't a 100% valid solution, but a full solution to this
            // is not possible with Mojang's concept of known packs
            //
            // if packetevents isn't running on a proxy and two backend servers
            // share the same custom datapack, the entries wouldn't be sent as
            // the player would tell the server it already knows about them
            //
            // this will cause issues, especially when some datapack overrides world height
            // of a vanilla dimension - and this can't be fixed (unless I missed something)

            if (copiedBaseEntry != null) {
                registry.define(elementName, id, copiedBaseEntry);
                return;
            }

            // can't find this element anywhere
            // TODO dummy values to make at least simple stuff work?
            PacketEvents.getAPI().getLogManager().warn("Unknown registry entry "
                    + elementName + " for " + this.getRegistryKey());
        }

        public SimpleRegistry<T> createFromElements(List<RegistryElement> elements, PacketWrapper<?> wrapper) {
            SimpleRegistry<T> registry = new SimpleRegistry<>(this.getRegistryKey());
            for (int id = 0; id < elements.size(); id++) {
                RegistryElement element = elements.get(id);
                this.handleElement(registry, element, id, wrapper);
            }
            return registry;
        }

        public ResourceLocation getRegistryKey() {
            return this.baseRegistry.getRegistryKey();
        }
    }
}
