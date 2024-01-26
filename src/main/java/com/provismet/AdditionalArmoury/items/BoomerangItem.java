package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BoomerangItem extends Item implements Vanishable {
    public BoomerangItem (Settings settings) {
        super(settings);
    }
    
    @Override
    public UseAction getUseAction (ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime (ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void onStoppedUsing (ItemStack itemStack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) return;
        if (this.getMaxUseTime(itemStack) - remainingUseTicks < 10) return;

        PlayerEntity player = (PlayerEntity)user;

        if (!world.isClient()) {
            BoomerangProjectileEntity boomerang = new BoomerangProjectileEntity(world, player);
            boomerang.setVelocity(player, player.getPitch(), player.getYaw(), 0f, 1f, 1f);

            int ricochetLevel = 1 + EnchantmentHelper.getLevel(AAEnchantments.RICOCHET, itemStack);
            boomerang.setRicochetCount(ricochetLevel);
            boomerang.setItem(itemStack);
            boomerang.setPower(boomerang.getPower() + EnchantmentHelper.getLevel(AAEnchantments.STRONG_THROW, itemStack));
            boomerang.setMaxFlightTime(boomerang.getMaxFlightTime() + EnchantmentHelper.getLevel(AAEnchantments.FAR_THROW, itemStack) * 5);
            world.spawnEntity(boomerang);

            int damage = 1 + (EnchantmentHelper.getLevel(AAEnchantments.MULTITHROW, itemStack) > 0 ? 2 : 0);
            if (damage > 1) {
                for (int i = 0; i < 2; ++i) {
                    BoomerangProjectileEntity newBoomerang = new BoomerangProjectileEntity(world, player);
                    newBoomerang.setVelocity(player, player.getPitch(), player.getYaw() + (i == 0 ? 10f : -10f), 0f, 1f, 1f);
                    newBoomerang.setCanResetCooldown(false);
                    newBoomerang.setItem(itemStack);
                    newBoomerang.setPower(newBoomerang.getPower() + EnchantmentHelper.getLevel(AAEnchantments.STRONG_THROW, itemStack) / 2f);
                    newBoomerang.setMaxFlightTime(newBoomerang.getMaxFlightTime() + EnchantmentHelper.getLevel(AAEnchantments.FAR_THROW, itemStack) * 5);
                    world.spawnEntity(newBoomerang);
                }
            }

            itemStack.damage(damage, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            player.getItemCooldownManager().set(AAItems.BOOMERANG, 160);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + 0.5f);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    @Override
    public int getEnchantability () {
        return 1;
    }
}
