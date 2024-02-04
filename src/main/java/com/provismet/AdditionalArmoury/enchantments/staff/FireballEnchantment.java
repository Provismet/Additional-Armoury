package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class FireballEnchantment extends StaffEnchantment {
    public FireballEnchantment () {
        super(Rarity.COMMON, 0xFF331F, 64, 20);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            FireballSpellEntity fireballSpell = new FireballSpellEntity(serverWorld, user);
            serverWorld.spawnEntity(fireballSpell);
        }
        return true;
    }
    
}
