package com.provismet.AdditionalArmoury.enchantments.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class PushBackEnchantment extends StaffEnchantment {
    public PushBackEnchantment () {
        super(Rarity.COMMON, 0xFFADD4, 128, 15);
    }

    @Override
    public void castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            
        }
    }
}
