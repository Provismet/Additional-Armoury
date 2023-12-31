package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class JumpEnchantment extends StaffEnchantment {
    public JumpEnchantment () {
        super(Rarity.COMMON, 0x87868C, 64, 30);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40));
        
        Vec3d velocity = user.getVelocity();
        user.setVelocity(velocity.x, 1, velocity.z);
        return true;
    }
    
}
