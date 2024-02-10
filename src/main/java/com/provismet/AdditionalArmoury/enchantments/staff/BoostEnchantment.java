package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BoostEnchantment extends StaffEnchantment {
    public BoostEnchantment () {
        super(Rarity.COMMON, 0x7AFFE6, 64, 10);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        double dx = -MathHelper.sin(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
        double dz = MathHelper.cos(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
        Vec3d velocity = new Vec3d(dx, 0.0, dz).multiply(1.25).add(0.0, 0.2, 0.0);

        if (user.isOnGround()) user.move(MovementType.SELF, new Vec3d(0.0, 0.2, 0.0));
        user.addVelocity(velocity);
        return true;
    }
}
