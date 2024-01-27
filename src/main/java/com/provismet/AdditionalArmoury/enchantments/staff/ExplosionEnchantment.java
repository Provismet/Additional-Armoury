package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ExplosionEnchantment extends StaffEnchantment {
    public ExplosionEnchantment () {
        super(Rarity.VERY_RARE, 0xCE0000, 16, 200);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        // TODO: become the archwizard (also add an advancement for this)
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.createExplosion(user, user.getX(), user.getEyeY(), user.getZ(), 10f, true, World.ExplosionSourceType.MOB);
        }
        return true;
    }

    @Override
    public void onChargeTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.onChargeTick(world, user, stack, remainingUseTicks);
        // TODO: spawn some particles
    }
    
    @Override
    public boolean isTreasure () {
        return true;
    }
}
