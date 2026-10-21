package opmpackets.protocol.entity.sulfurcube;

import opmpackets.protocol.attribute.Attribute;
import opmpackets.protocol.attribute.AttributeEntry;
import opmpackets.protocol.attribute.AttributeModifier;
import opmpackets.protocol.item.type.ItemType;
import opmpackets.protocol.mapper.MappedEntityBuilder;
import opmpackets.protocol.mapper.MappedEntitySet;
import opmpackets.protocol.sound.Sounds;
import opmpackets.protocol.world.states.defaulttags.ItemTags;
import opmpackets.resources.ResourceLocation;
import opmpackets.resources.TagKey;
import opmpackets.util.mappings.TypesBuilderData;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @versions 26.2+
 */
@NullMarked
public final class SulfurCubeArchtypeBuilder implements MappedEntityBuilder<SulfurCubeArchtype> {

    private static final MappedEntitySet<ItemType> REGULAR_ITEMS = new MappedEntitySet<>(
            new ResourceLocation(ItemTags.SULFUR_CUBE_ARCHETYPE_REGULAR.getName()));
    private static final SulfurCubeKnockbackModifiers REGULAR_KNOCKBACK_MODIFIERS =
            new SulfurCubeKnockbackModifiers(0.4125f, 0.09f);
    private static final SulfurCubeSoundSettings REGULAR_SOUND_SETTINGS = new SulfurCubeSoundSettings(
            Sounds.ENTITY_SULFUR_CUBE_REGULAR_HIT, Sounds.ENTITY_SULFUR_CUBE_REGULAR_PUSH,
            0.2f, 0.5f);

    private MappedEntitySet<ItemType> items = REGULAR_ITEMS;
    private List<AttributeEntry> attributeModifiers = new ArrayList<>();
    private boolean buoyant = false;
    private @Nullable SulfurCubeExplosionData explosion = null;
    private @Nullable SulfurCubeContactDamage contactDamage = null;
    private SulfurCubeKnockbackModifiers knockbackModifiers = REGULAR_KNOCKBACK_MODIFIERS;
    private SulfurCubeSoundSettings soundSettings = REGULAR_SOUND_SETTINGS;

    private SulfurCubeArchtypeBuilder() {
    }

    public static SulfurCubeArchtypeBuilder sulfurCubeArchtypeBuilder() {
        return new SulfurCubeArchtypeBuilder();
    }

    @ApiStatus.Internal
    @Override
    public SulfurCubeArchtype build(@Nullable TypesBuilderData data) {
        return new StaticSulfurCubeArchtype(
                data, this.items, this.attributeModifiers, this.buoyant, this.explosion,
                this.contactDamage, this.knockbackModifiers, this.soundSettings
        );
    }

    public MappedEntitySet<ItemType> getItems() {
        return this.items;
    }

    public SulfurCubeArchtypeBuilder setItems(ItemTags itemsTag) {
        return this.setItems(new MappedEntitySet<>(new ResourceLocation(itemsTag.getName())));
    }

    public SulfurCubeArchtypeBuilder setItems(TagKey itemsTag) {
        return this.setItems(new MappedEntitySet<>(itemsTag.getId()));
    }

    public SulfurCubeArchtypeBuilder setItems(MappedEntitySet<ItemType> items) {
        this.items = items;
        return this;
    }

    public SulfurCubeArchtypeBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        return this.addAttributeModifier(new AttributeEntry(attribute, modifier));
    }

    public SulfurCubeArchtypeBuilder addAttributeModifier(AttributeEntry entry) {
        this.attributeModifiers.add(entry);
        return this;
    }

    public List<AttributeEntry> getAttributeModifiers() {
        return this.attributeModifiers;
    }

    public SulfurCubeArchtypeBuilder setAttributeModifiers(List<AttributeEntry> attributeModifiers) {
        this.attributeModifiers = attributeModifiers;
        return this;
    }

    public boolean isBuoyant() {
        return this.buoyant;
    }

    public SulfurCubeArchtypeBuilder setBuoyant(boolean buoyant) {
        this.buoyant = buoyant;
        return this;
    }

    public @Nullable SulfurCubeExplosionData getExplosion() {
        return this.explosion;
    }

    public SulfurCubeArchtypeBuilder setExplosion(@Nullable SulfurCubeExplosionData explosion) {
        this.explosion = explosion;
        return this;
    }

    public @Nullable SulfurCubeContactDamage getContactDamage() {
        return this.contactDamage;
    }

    public SulfurCubeArchtypeBuilder setContactDamage(@Nullable SulfurCubeContactDamage contactDamage) {
        this.contactDamage = contactDamage;
        return this;
    }

    public SulfurCubeKnockbackModifiers getKnockbackModifiers() {
        return this.knockbackModifiers;
    }

    public SulfurCubeArchtypeBuilder setKnockbackModifiers(SulfurCubeKnockbackModifiers knockbackModifiers) {
        this.knockbackModifiers = knockbackModifiers;
        return this;
    }

    public SulfurCubeSoundSettings getSoundSettings() {
        return this.soundSettings;
    }

    public SulfurCubeArchtypeBuilder setSoundSettings(SulfurCubeSoundSettings soundSettings) {
        this.soundSettings = soundSettings;
        return this;
    }
}
