package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class FireballEnchantment extends AbstractStaffEnchantment {
    public FireballEnchantment () {
        super(8, Enchantment.constantCost(0), Enchantment.constantCost(50), 2, 0xFFFF331F, 64, 20);
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
