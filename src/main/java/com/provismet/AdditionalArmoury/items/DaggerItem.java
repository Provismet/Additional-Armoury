package com.provismet.AdditionalArmoury.items;

import java.util.List;
import java.util.function.Consumer;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;
import com.provismet.CombatPlusCore.items.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CPCDataComponentTypes;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.ToolComponent;

import com.provismet.AdditionalArmoury.particles.effects.InkSplatParticleEffect;
import com.provismet.CombatPlusCore.interfaces.DualWeapon;

import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class DaggerItem extends AbstractMeleeWeapon implements DualWeapon {
    public static final int USES_PER_POTION = 8;
    public static final int defaultTipColour = 0x00000000;

    private static final float POTION_DURATION_MOD = 0.125f;
    private static final float BASE_ATTACK_DAMAGE = 1f;
    private static final float BASE_ATTACK_SPEED = -2f;

    public DaggerItem (Settings settings) {
        super(settings);
    }

    public static DaggerItem withDefaultSettings (Settings settings) {
        return new DaggerItem(settings.component(DataComponentTypes.TOOL, DaggerItem.createToolComponent()));
    }

    public static AttributeModifiersComponent createDefaultDaggerAttributes (ToolMaterial material) {
        return Util.createAttributes(material, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static AttributeModifiersComponent createDefaultDaggerAttributes (AAToolMaterial material) {
        return material.createAttributeComponent(BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static Item.Settings createDefaultDaggerSettings (ToolMaterial material, Item.Settings settings) {
        return Util.applyToolSettings(material, settings)
            .attributeModifiers(createDefaultDaggerAttributes(material))
            .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    public static Item.Settings createDefaultDaggerSettings (AAToolMaterial material, Item.Settings settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .attributeModifiers(createDefaultDaggerAttributes(material))
            .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.baseMaterial().attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    private static ToolComponent createToolComponent () {
        RegistryEntryLookup<Block> blockLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(
            List.of(
                ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15f),
                ToolComponent.Rule.of(blockLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                ToolComponent.Rule.of(blockLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5f)
            ),
            1f,
            2,
            false
        );
    }

    @Override
    public void postChargedHit (ItemStack stack, LivingEntity user, LivingEntity target) {
        if (!(user.getWorld() instanceof ServerWorld world)) return;

        PotionContentsComponent potionContents = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        for (StatusEffectInstance instance : potionContents.getEffects()) {
            target.addStatusEffect(
                new StatusEffectInstance(
                    instance.getEffectType(), Math.max(instance.mapDuration(i -> (int)(i * POTION_DURATION_MOD)), 1),
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.shouldShowParticles()
                ),
                user
            );
        }
        this.spawnInkParticles(target, 3, stack);

        double splatterRadius = CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.EFFECT_RADIUS, world, stack, 0f);
        int damage = 1;
        if (splatterRadius > 0) {
            List<LivingEntity> targets = target.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(splatterRadius, 0.25, splatterRadius));
            for (LivingEntity newTarget : targets) {
                if (newTarget == user || newTarget == target) continue;
                for (StatusEffectInstance instance : potionContents.getEffects()) {
                    newTarget.addStatusEffect(
                        new StatusEffectInstance(
                            instance.getEffectType(), Math.max(instance.mapDuration(i -> (int)(i * POTION_DURATION_MOD)), 1),
                            instance.getAmplifier(),
                            instance.isAmbient(),
                            instance.shouldShowParticles()
                        ),
                        user
                    );
                }
                this.spawnInkParticles(newTarget, 3, stack);
                ++damage;
            }
        }

        boolean hasAdhesive = EnchantmentHelper.hasAnyEnchantmentsWith(stack, AAEnchantmentComponentTypes.INFINITE_POTION);
        if (hasAdhesive) {
            stack.damage(damage * 2, user, user.getStackInHand(Hand.MAIN_HAND) == stack ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        else if (user instanceof PlayerEntity player && !player.isCreative() && potionContents.hasEffects() && this.decrementCurrentPotionUses(stack, damage) <= 0) {
            stack.set(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        }
    }

    @Override
    public ItemStack getDefaultStack () {
        ItemStack itemStack = super.getDefaultStack();
        itemStack.set(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        return itemStack;
    }

    @Override
    public void appendTooltip (ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        if (!EnchantmentHelper.hasAnyEnchantmentsWith(stack, AAEnchantmentComponentTypes.INFINITE_POTION) && potionContentsComponent.hasEffects())
            textConsumer.accept(Text.translatable("tooltip.additional-armoury.dagger_uses", this.getCurrentPotionUses(stack)));
    }

    @Override
    public Text getName (ItemStack stack) {
        PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);

        if (!potionContentsComponent.hasEffects()) return super.getName(stack);
        return potionContentsComponent.getName(this.translationKey + ".effect.");
    }

    public int getCurrentPotionUses (ItemStack stack) {
        return stack.getOrDefault(AADataComponentTypes.USES, 0);
    }

    public void setCurrentPotionUses (ItemStack stack, int uses) {
        uses = Math.max(uses, 0);
        stack.set(AADataComponentTypes.USES, uses);
    }

    public int decrementCurrentPotionUses (ItemStack stack, int amount) {
        int currentUses = Math.max(this.getCurrentPotionUses(stack) - amount, 0);
        this.setCurrentPotionUses(stack, currentUses);
        return currentUses;
    }

    public int decrementCurrentPotionUses (ItemStack stack) {
        return this.decrementCurrentPotionUses(stack, 1);
    }

    public void spawnInkParticles (Entity entity, int count, ItemStack stack) {
        if (entity.getWorld() instanceof ServerWorld world) {
            PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
            if (!potionContentsComponent.hasEffects()) return;

            InkSplatParticleEffect splatEffect = new InkSplatParticleEffect(Vec3d.unpackRgb(potionContentsComponent.getColor()).toVector3f(), 0.2f);
            world.spawnParticles(splatEffect, entity.getX(), (entity.getY() + entity.getEyeY()) / 2.0, entity.getZ(), count, 0, 0, 0, 0);
        }

    }
}
