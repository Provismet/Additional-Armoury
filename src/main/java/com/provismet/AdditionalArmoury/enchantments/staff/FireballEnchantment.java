package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

public class FireballEnchantment extends StaffEnchantment {
    public FireballEnchantment () {
        super(Rarity.COMMON, 0xB71F26, 64, 30);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            double dx = -MathHelper.sin(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
            double dy = -MathHelper.sin(user.getPitch() / MathHelper.DEGREES_PER_RADIAN);
            double dz = MathHelper.cos(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);

            SmallFireballEntity fireball = new SmallFireballEntity(serverWorld, user, dx, dy, dz);
            fireball.setPosition(fireball.getX(), user.getEyeY(), fireball.getZ());
            serverWorld.spawnEntity(fireball);
        }
        return true;
    }
    
}
