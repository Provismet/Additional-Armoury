package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AASounds;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BoomerangItem extends Item {
    public BoomerangItem (Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction (ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime (ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public ActionResult use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return ActionResult.FAIL;
        }
        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    @Override
    public boolean onStoppedUsing (ItemStack itemStack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return false;
        if (this.getMaxUseTime(itemStack, user) - remainingUseTicks < 10) return false;

        if (world instanceof ServerWorld serverWorld) {
            BoomerangProjectileEntity boomerang = new BoomerangProjectileEntity(world, player, itemStack);
            boomerang.setVelocity(player, player.getPitch(), player.getYaw(), 0f, 1f, 1f);

            int ricochetLevel = (int)CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.RICOCHET, serverWorld, itemStack, 1);
            boomerang.setRicochetCount(ricochetLevel);
            boomerang.setPower(this.getBasePower());
            boomerang.setItem(itemStack);
            boomerang.setMaxFlightTime(boomerang.getMaxFlightTime() + (int)CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.THROW_DISTANCE, serverWorld, itemStack, 0f));
            world.spawnEntity(boomerang);

            int count = EnchantmentHelper.getProjectileCount(serverWorld, itemStack, user, 1);
            if (count > 1) {
                for (int i = 1; i < count; ++i) {
                    BoomerangProjectileEntity newBoomerang = new BoomerangProjectileEntity(world, player, itemStack);
                    float spread = EnchantmentHelper.getProjectileSpread(serverWorld, itemStack, user, 0f);
                    spread *= Math.ceilDiv(i, 2) * (i % 2 == 0 ? 1 : -1);
                    newBoomerang.setVelocity(player, player.getPitch(), player.getYaw() + spread, 0f, 1f, 1f);
                    newBoomerang.setCanResetCooldown(false);
                    newBoomerang.setPower(this.getBasePower());
                    newBoomerang.setRicochetCount(ricochetLevel);
                    newBoomerang.setItem(itemStack);
                    newBoomerang.setMaxFlightTime(boomerang.getMaxFlightTime());
                    world.spawnEntity(newBoomerang);
                }
            }

            itemStack.damage(count, player, player.getActiveHand().getEquipmentSlot());
            player.getItemCooldownManager().set(itemStack, 160);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), AASounds.BOOMERANG_THROW, SoundCategory.PLAYERS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return true;
    }

    public void applyOnHit (LivingEntity owner, LivingEntity target) {

    }

    protected float getBasePower () {
        return 4f;
    }
}
