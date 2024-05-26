package com.provismet.AdditionalArmoury.items;

import java.util.List;

import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;

import com.provismet.AdditionalArmoury.particles.effects.InkSplatParticleEffect;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.CombatPlusCore.interfaces.DualWeapon;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class DaggerItem extends AbstractMeleeWeapon implements DualWeapon {
    public static final int USES_PER_POTION = 8;
    public static final int defaultTipColour = 0x00000000;

    private static final float POTION_DURATION_MOD = 0.125f;

    public DaggerItem (ToolMaterial material, Settings settings) {
        super(material, settings.component(DataComponentTypes.TOOL, DaggerItem.createToolComponent()));
    }

    public static AttributeModifiersComponent createDefaultDaggerAttributes (ToolMaterial toolMaterial) {
        return Util.createAttributes(toolMaterial, 1, -2f);
    }

    private static ToolComponent createToolComponent () {
        return new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0f), ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5f)), 1.0f, 2);
    }

    @Override
    public void postChargedHit (ItemStack stack, LivingEntity user, LivingEntity target) {
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

        double splatterLevel = EnchantmentHelper.getLevel(AAEnchantments.SPLATTER, stack);
        int damage = 1;
        if (splatterLevel > 0) {
            List<LivingEntity> targets = target.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0 + splatterLevel * 0.5 , 0.25, 1.0 + splatterLevel * 0.5));
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

        boolean hasAdhesive = EnchantmentHelper.getLevel(AAEnchantments.ADHESIVE, stack) > 0;
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        potionContentsComponent.buildTooltip(tooltip::add, 0.125f, context.getUpdateTickRate());

        if (EnchantmentHelper.getLevel(AAEnchantments.ADHESIVE, stack) == 0 && potionContentsComponent.hasEffects())
            tooltip.add(Text.translatable("tooltip.additional-armoury.dagger_uses", this.getCurrentPotionUses(stack)));
    }

    @Override
    public String getTranslationKey (ItemStack stack) {
        PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);

        if (!potionContentsComponent.hasEffects()) return super.getTranslationKey();
        return Potion.finishTranslationKey(potionContentsComponent.potion(), this.getTranslationKey() + ".effect.");
    }

    public int getCurrentPotionUses (ItemStack stack) {
        return stack.getOrDefault(AADataComponentTypes.USES, 0);
    }

    public void setCurrentPotionUses (ItemStack stack, int uses) {
        stack.set(AADataComponentTypes.USES, uses);
    }

    public int decrementCurrentPotionUses (ItemStack stack, int amount) {
        int currentUses = this.getCurrentPotionUses(stack) - amount;
        this.setCurrentPotionUses(stack, currentUses);
        return currentUses;
    }

    public int decrementCurrentPotionUses (ItemStack stack) {
        return this.decrementCurrentPotionUses(stack, 1);
    }

    public void spawnInkParticles (Entity entity, int count, ItemStack stack) {
        PotionContentsComponent potionContentsComponent = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
        if (!potionContentsComponent.hasEffects()) return;

        InkSplatParticleEffect splatEffect = new InkSplatParticleEffect(Vec3d.unpackRgb(potionContentsComponent.getColor()).toVector3f(), 0.2f);
        entity.getWorld().addParticle(splatEffect, entity.getX(), entity.getEyeY() - 0.1, entity.getZ(), 0, 0, 0);
        entity.getWorld().addParticle(splatEffect, entity.getX(), entity.getEyeY() - 0.1, entity.getZ(), 0, 0, 0);
        entity.getWorld().addParticle(splatEffect, entity.getX(), entity.getEyeY() - 0.1, entity.getZ(), 0, 0, 0);
    }
}
