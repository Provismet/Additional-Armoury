package com.provismet.AdditionalArmoury.item;

import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AASounds;
import com.provismet.CombatPlusCore.utility.CombatPlusEnchantmentHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class BoomerangItem extends Item {
    public BoomerangItem (Properties settings) {
        super(settings);
    }

    @Override
    public ItemUseAnimation getUseAnimation (ItemStack stack) {
        return ItemUseAnimation.TRIDENT;
    }

    @Override
    public int getUseDuration (ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public InteractionResult use (Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            return InteractionResult.FAIL;
        }
        user.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean releaseUsing (ItemStack itemStack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player player)) return false;
        if (this.getUseDuration(itemStack, user) - remainingUseTicks < 10) return false;

        if (world instanceof ServerLevel serverWorld) {
            BoomerangProjectileEntity boomerang = new BoomerangProjectileEntity(world, player, itemStack);
            boomerang.shootFromRotation(player, player.getXRot(), player.getYRot(), 0f, 1f, 1f);

            int ricochetLevel = (int) CombatPlusEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.RICOCHET, serverWorld, itemStack, 1);
            boomerang.setRicochetCount(ricochetLevel);
            boomerang.setPower(this.getBasePower());
            boomerang.setItem(itemStack);
            boomerang.setMaxFlightTime(boomerang.getMaxFlightTime() + (int)CombatPlusEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.THROW_DISTANCE, serverWorld, itemStack, 0f));
            world.addFreshEntity(boomerang);

            int count = EnchantmentHelper.processProjectileCount(serverWorld, itemStack, user, 1);
            if (count > 1) {
                for (int i = 1; i < count; ++i) {
                    BoomerangProjectileEntity newBoomerang = new BoomerangProjectileEntity(world, player, itemStack);
                    float spread = EnchantmentHelper.processProjectileSpread(serverWorld, itemStack, user, 0f);
                    spread *= Math.ceilDiv(i, 2) * (i % 2 == 0 ? 1 : -1);
                    newBoomerang.shootFromRotation(player, player.getXRot(), player.getYRot() + spread, 0f, 1f, 1f);
                    newBoomerang.setCanResetCooldown(false);
                    newBoomerang.setPower(this.getBasePower());
                    newBoomerang.setRicochetCount(ricochetLevel);
                    newBoomerang.setItem(itemStack);
                    newBoomerang.setMaxFlightTime(boomerang.getMaxFlightTime());
                    world.addFreshEntity(newBoomerang);
                }
            }

            itemStack.hurtAndBreak(count, player, player.getUsedItemHand().asEquipmentSlot());
            player.getCooldowns().addCooldown(itemStack, 160);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), AASounds.BOOMERANG_THROW, SoundSource.PLAYERS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);
        player.awardStat(Stats.ITEM_USED.get(this));
        return true;
    }

    public void applyOnHit (LivingEntity owner, LivingEntity target) {

    }

    protected float getBasePower () {
        return 4f;
    }
}
