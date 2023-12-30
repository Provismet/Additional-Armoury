package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class BoostEnchantment extends StaffEnchantment {
    public BoostEnchantment () {
        super(Rarity.COMMON, 0x7AFFE6, 64, 10);
    }

    @Override
    public void castSpell (ItemStack stack, LivingEntity user) {
        double dx = -MathHelper.sin(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
        double dz = MathHelper.cos(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);

        user.addVelocity(dx, 0.25, dz);
    }
    
}
