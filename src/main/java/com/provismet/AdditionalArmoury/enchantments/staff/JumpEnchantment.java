package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class JumpEnchantment extends AbstractStaffEnchantment {
    public JumpEnchantment () {
        super(Rarity.COMMON, 0xA2C663, 32, 10);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40));
        user.fallDistance = 0f;
        
        Vec3d velocity = user.getVelocity();
        user.setVelocity(velocity.x, 0.75, velocity.z);
        return true;
    }
}
