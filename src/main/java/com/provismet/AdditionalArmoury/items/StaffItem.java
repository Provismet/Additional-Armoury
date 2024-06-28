package com.provismet.AdditionalArmoury.items;

import com.mojang.datafixers.util.Pair;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;

import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;
import com.provismet.AdditionalArmoury.registries.AASounds;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicBoolean;

public class StaffItem extends Item {
    public StaffItem (Settings settings) {
        super(settings);
    }

    public static int getColour (ItemStack stack) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getEffectListAndLevel(stack, AAEnchantmentComponentTypes.SPELL_COLOUR);
        if (pair != null) return pair.getFirst();
        return 0xFFC18920;
    }

    @Override
    public String getTranslationKey (ItemStack stack) {
        if (!EnchantmentHelper.hasEnchantments(stack)) return this.getTranslationKey();
        else return this.getTranslationKey() + ".enchanted";
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        
        if (!EnchantmentHelper.hasEnchantments(itemStack)) {
            return TypedActionResult.pass(itemStack);
        }
        else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public void usageTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world instanceof ServerWorld serverWorld && EnchantmentHelper.hasEnchantments(stack)) {
            CPCEnchantmentHelper.forEachEnchantment((enchantment, level, context) -> {
                for (EnchantmentEffectEntry<SpellIncantationTickingEffect> effect : enchantment.value().getEffect(AAEnchantmentComponentTypes.TICK_INCANTATION)) {
                    effect.effect().apply(serverWorld, level, context, user, remainingUseTicks);
                }
            }, user, user.getActiveHand() == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            serverWorld.spawnParticles(new SpellChargeParticleEffect(Vec3d.unpackRgb(StaffItem.getColour(stack)).toVector3f(), 0.1f), user.getX(), user.getY(), user.getZ(), 1, 0, 0, 0, 0);
        }
    }

    @Override
    public UseAction getUseAction (ItemStack stack) {
        if (!EnchantmentHelper.hasEnchantments(stack)) return super.getUseAction(stack);
        else return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime (ItemStack stack, LivingEntity user) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getEffectListAndLevel(stack, AAEnchantmentComponentTypes.SPELL_CAST_DURATION);
        if (pair != null) return pair.getFirst();
        return 0;
    }

    @Override
    public boolean hasGlint (ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantability () {
        return 1;
    }

    @Override
    public boolean isEnchantable (ItemStack stack) {
        return true;
    }

    @Override
    public boolean isItemBarVisible (ItemStack stack) {
        return this.getMaxUseCount(stack) > 0 && EnchantmentHelper.hasEnchantments(stack);
    }

    @Override
    public int getItemBarStep (ItemStack stack) {
        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) return 0;
        else return 13 - (int)(13f * (uses / maxUses));
    }

    @Override
    public int getItemBarColor (ItemStack stack) {
        float progress;

        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) progress = 0f;
        else progress = uses / maxUses;

        return ColorHelper.Argb.lerp(progress, 0xFF3251FF, 0xFFB2BDFF);
    }

    @Override
    public ItemStack finishUsing (ItemStack stack, World world, LivingEntity user) {
        if (EnchantmentHelper.hasEnchantments(stack) && world instanceof ServerWorld serverWorld) {
            CPCEnchantmentHelper.forEachEnchantment((enchantment, level, context) -> {
                for (EnchantmentEffectEntry<EnchantmentEntityEffect> effect : enchantment.value().getEffect(AAEnchantmentComponentTypes.ON_ACTIVATION)) {
                    effect.effect().apply(serverWorld, level, context, user, user.getPos());
                }
            }, user, user.getActiveHand() == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, stack);

            if (user instanceof PlayerEntity player && !player.isCreative()) {
                int useCount = 0;
                Pair<Integer, Integer> pair = EnchantmentHelper.getEffectListAndLevel(stack, AAEnchantmentComponentTypes.SPELL_USES);
                if (pair != null) useCount = pair.getFirst();
                this.setMaxUseCount(stack, useCount);
                this.incrementUseCount(stack);
            }

            serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), AASounds.STAFF_CAST, SoundCategory.PLAYERS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);

            if (user instanceof PlayerEntity player)
                player.getItemCooldownManager().set(this, 20);
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
            EnchantmentHelper.apply(stack, components -> components.remove(enchantment -> true));
        }
        else stack.set(AADataComponentTypes.USES, uses);
    }

    public void resetCounters (ItemStack stack) {
        stack.remove(AADataComponentTypes.MAX_USES);
        stack.remove(AADataComponentTypes.USES);
    }

    @Override
    public void inventoryTick (ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (this.getMaxUseCount(stack) > 0 && !EnchantmentHelper.hasEnchantments(stack)) {
            this.resetCounters(stack);
        }
    }

    @Override
    public boolean canBeEnchantedWith (ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return !EnchantmentHelper.hasEnchantments(stack) && enchantment.isIn(AAEnchantmentTags.STAFF);
    }
}
