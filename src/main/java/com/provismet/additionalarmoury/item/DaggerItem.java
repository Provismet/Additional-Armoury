package com.provismet.additionalarmoury.item;

import com.provismet.additionalarmoury.particle.effects.InkSplatParticleEffect;
import com.provismet.additionalarmoury.registries.AADataComponentTypes;
import com.provismet.additionalarmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.additionalarmoury.utility.Util;
import com.provismet.CombatPlusCore.api.item.AbstractMeleeWeapon;
import com.provismet.CombatPlusCore.api.item.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.api.item.interfaces.DualWeapon;
import com.provismet.CombatPlusCore.registries.CombatPlusDataComponentTypes;
import com.provismet.CombatPlusCore.utility.CombatPlusEnchantmentHelper;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ARGB;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Consumer;

public class DaggerItem extends AbstractMeleeWeapon implements DualWeapon {
    public static final int USES_PER_POTION = 8;
    public static final int defaultTipColour = 0x00000000;

    private static final float POTION_DURATION_MOD = 0.125f;
    private static final float BASE_ATTACK_DAMAGE = 1f;
    private static final float BASE_ATTACK_SPEED = -2f;

    public DaggerItem (Properties settings) {
        super(settings);
    }

    public static DaggerItem withDefaultSettings (Properties settings) {
        return new DaggerItem(settings.component(DataComponents.TOOL, DaggerItem.createToolComponent()));
    }

    public static ItemAttributeModifiers createDefaultDaggerAttributes (ToolMaterial material) {
        return Util.createAttributes(material, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static ItemAttributeModifiers createDefaultDaggerAttributes (AAToolMaterial material) {
        return material.createAttributeComponent(BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static Item.Properties createDefaultDaggerSettings (ToolMaterial material, Item.Properties settings) {
        return Util.applyToolSettings(material, settings)
            .attributes(createDefaultDaggerAttributes(material))
            .component(DataComponents.WEAPON, new Weapon(1))
            .component(CombatPlusDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    public static Item.Properties createDefaultDaggerSettings (AAToolMaterial material, Item.Properties settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .attributes(createDefaultDaggerAttributes(material))
            .component(DataComponents.WEAPON, new Weapon(1))
            .component(CombatPlusDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.baseMaterial().attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    private static Tool createToolComponent () {
        HolderGetter<Block> blockLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(
            List.of(
                Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15f),
                Tool.Rule.overrideSpeed(blockLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                Tool.Rule.overrideSpeed(blockLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5f)
            ),
            1f,
            2,
            false
        );
    }

    @Override
    public void postChargedHit (ItemStack stack, LivingEntity user, LivingEntity target) {
        if (!(user.level() instanceof ServerLevel world)) return;

        PotionContents potionContents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        for (MobEffectInstance instance : potionContents.getAllEffects()) {
            target.addEffect(
                new MobEffectInstance(
                    instance.getEffect(), Math.max(instance.mapDuration(i -> (int)(i * POTION_DURATION_MOD)), 1),
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.isVisible()
                ),
                user
            );
        }
        this.spawnInkParticles(target, 3, stack);

        double splatterRadius = CombatPlusEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.EFFECT_RADIUS, world, stack, 0f);
        int damage = 1;
        if (splatterRadius > 0) {
            List<LivingEntity> targets = target.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(splatterRadius, 0.25, splatterRadius));
            for (LivingEntity newTarget : targets) {
                if (newTarget == user || newTarget == target) continue;
                for (MobEffectInstance instance : potionContents.getAllEffects()) {
                    newTarget.addEffect(
                        new MobEffectInstance(
                            instance.getEffect(), Math.max(instance.mapDuration(i -> (int)(i * POTION_DURATION_MOD)), 1),
                            instance.getAmplifier(),
                            instance.isAmbient(),
                            instance.isVisible()
                        ),
                        user
                    );
                }
                this.spawnInkParticles(newTarget, 3, stack);
                ++damage;
            }
        }

        boolean hasAdhesive = EnchantmentHelper.has(stack, AAEnchantmentComponentTypes.INFINITE_POTION);
        if (hasAdhesive) {
            stack.hurtAndBreak(damage * 2, user, user.getItemInHand(InteractionHand.MAIN_HAND) == stack ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        else if (user instanceof Player player && !player.isCreative() && potionContents.hasEffects() && this.decrementCurrentPotionUses(stack, damage) <= 0) {
            stack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        }
    }

    @Override
    public ItemStack getDefaultInstance () {
        ItemStack itemStack = super.getDefaultInstance();
        itemStack.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        return itemStack;
    }

    @Override
    public void appendHoverText (ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        PotionContents potionContentsComponent = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (!EnchantmentHelper.has(stack, AAEnchantmentComponentTypes.INFINITE_POTION) && potionContentsComponent.hasEffects())
            textConsumer.accept(Component.translatable("tooltip.additional-armoury.dagger_uses", this.getCurrentPotionUses(stack)));
    }

    @Override
    public Component getName (ItemStack stack) {
        PotionContents potionContentsComponent = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

        if (!potionContentsComponent.hasEffects()) return super.getName(stack);
        return potionContentsComponent.getName(this.descriptionId + ".effect.");
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
        if (entity.level() instanceof ServerLevel world) {
            PotionContents potionContentsComponent = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            if (!potionContentsComponent.hasEffects()) return;

            InkSplatParticleEffect splatEffect = new InkSplatParticleEffect(ARGB.vector3fFromRGB24(potionContentsComponent.getColor()), 0.2f);
            world.sendParticles(splatEffect, entity.getX(), (entity.getY() + entity.getEyeY()) / 2.0, entity.getZ(), count, 0, 0, 0, 0);
        }

    }
}
