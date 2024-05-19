package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class FrostballEnchantment extends AbstractStaffEnchantment {
    public FrostballEnchantment () {
        super(8, Enchantment.constantCost(0), Enchantment.constantCost(50), 2, 0xFFBADCFF, 64, 20);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            FrostballSpellEntity frostball = new FrostballSpellEntity(serverWorld, user);
            serverWorld.spawnEntity(frostball);
        }
        return true;
    }
}
