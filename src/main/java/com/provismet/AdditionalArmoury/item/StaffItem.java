package com.provismet.AdditionalArmoury.item;

import com.mojang.datafixers.util.Pair;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.particle.effects.SpellChargeParticleEffect;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AASounds;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.CombatPlusCore.utility.CombatPlusEnchantmentHelper;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ARGB;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StaffItem extends Item {
    public StaffItem (Properties settings) {
        super(settings);
    }

    public static int getColour (ItemStack stack) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getHighestLevel(stack, AAEnchantmentComponentTypes.SPELL_COLOUR);
        if (pair != null) return pair.getFirst();
        return 0xFFC18920;
    }

    @Override
    public Component getName (ItemStack stack) {
        if (!EnchantmentHelper.hasAnyEnchantments(stack)) return super.getName(stack);
        else return Component.translatable(this.descriptionId + ".enchanted");
    }

    @Override
    public InteractionResult use (Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        
        if (!EnchantmentHelper.hasAnyEnchantments(itemStack)) {
            return InteractionResult.PASS;
        }
        else {
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void onUseTick (Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world instanceof ServerLevel serverWorld && EnchantmentHelper.hasAnyEnchantments(stack)) {
            CombatPlusEnchantmentHelper.forEachEnchantment((enchantment, level, context) -> {
                for (ConditionalEffect<SpellIncantationTickingEffect> effect : enchantment.value().getEffects(AAEnchantmentComponentTypes.TICK_INCANTATION)) {
                    effect.effect().apply(serverWorld, level, context, user, remainingUseTicks);
                }
            }, user, user.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

            if (!EnchantmentHelper.has(stack, AAEnchantmentComponentTypes.NO_SPELL_PARTICLES))
                serverWorld.sendParticles(new SpellChargeParticleEffect(ARGB.vector3fFromRGB24(StaffItem.getColour(stack)), 0.1f), user.getX(), user.getY(), user.getZ(), 1, 0, 0, 0, 0);
        }
    }

    @Override
    public ItemUseAnimation getUseAnimation (ItemStack stack) {
        if (!EnchantmentHelper.hasAnyEnchantments(stack)) return super.getUseAnimation(stack);
        else return ItemUseAnimation.BOW;
    }

    @Override
    public int getUseDuration (ItemStack stack, LivingEntity user) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getHighestLevel(stack, AAEnchantmentComponentTypes.SPELL_CAST_DURATION);
        if (pair != null) return pair.getFirst();
        return 0;
    }

    @Override
    public boolean isFoil (ItemStack stack) {
        return false;
    }

    @Override
    public boolean isBarVisible (ItemStack stack) {
        return this.getMaxUseCount(stack) > 0 && EnchantmentHelper.hasAnyEnchantments(stack);
    }

    @Override
    public int getBarWidth (ItemStack stack) {
        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) return 0;
        else return 13 - (int)(13f * (uses / maxUses));
    }

    @Override
    public int getBarColor (ItemStack stack) {
        float progress;

        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) progress = 0f;
        else progress = uses / maxUses;

        return ARGB.srgbLerp(progress, 0xFF3251FF, 0xFFB2BDFF);
    }

    @Override
    public ItemStack finishUsingItem (ItemStack stack, Level world, LivingEntity user) {
        if (EnchantmentHelper.hasAnyEnchantments(stack) && world instanceof ServerLevel serverWorld) {
            CombatPlusEnchantmentHelper.forEachEnchantment((enchantment, level, context) -> {
                for (ConditionalEffect<EnchantmentEntityEffect> effect : enchantment.value().getEffects(AAEnchantmentComponentTypes.ON_ACTIVATION)) {
                    effect.effect().apply(serverWorld, level, context, user, user.position());
                }
            }, user, user.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, stack);

            if (user instanceof Player player && !player.isCreative()) {
                int useCount = 0;
                Pair<Integer, Integer> pair = EnchantmentHelper.getHighestLevel(stack, AAEnchantmentComponentTypes.SPELL_USES);
                if (pair != null) useCount = pair.getFirst();
                this.setMaxUseCount(stack, useCount);
                this.incrementUseCount(stack);
            }

            serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), AASounds.STAFF_CAST, SoundSource.PLAYERS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);

            if (user instanceof Player player)
                player.getCooldowns().addCooldown(stack, 20);
        }

        return stack;
    }

    public void setMaxUseCount (ItemStack stack, int maxUses) {
        stack.set(AADataComponentTypes.MAX_USES, maxUses);
    }

    public int getMaxUseCount (ItemStack stack) {
        return stack.getOrDefault(AADataComponentTypes.MAX_USES, 0);
    }

    public void incrementUseCount (ItemStack stack) {
        this.setUseCount(stack, this.getUseCount(stack) + 1);
    }

    public int getUseCount (ItemStack stack) {
        return stack.getOrDefault(AADataComponentTypes.USES, 0);
    }

    public void setUseCount (ItemStack stack, int uses) {
        if (uses >= this.getMaxUseCount(stack)) {
            this.resetCounters(stack);
            EnchantmentHelper.updateEnchantments(stack, components -> components.removeIf(enchantment -> true));
        }
        else stack.set(AADataComponentTypes.USES, uses);
    }

    public void resetCounters (ItemStack stack) {
        stack.remove(AADataComponentTypes.MAX_USES);
        stack.remove(AADataComponentTypes.USES);
    }

    @Override
    public void inventoryTick (ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        if (this.getMaxUseCount(stack) > 0 && !EnchantmentHelper.hasAnyEnchantments(stack)) {
            this.resetCounters(stack);
        }
    }

    @Override
    public boolean canBeEnchantedWith (@NotNull ItemStack stack, @NotNull Holder<Enchantment> enchantment, @NotNull EnchantingContext context) {
        return !EnchantmentHelper.hasAnyEnchantments(stack) && enchantment.is(AAEnchantmentTags.STAFF);
    }
}
