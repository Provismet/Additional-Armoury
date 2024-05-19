package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.MissileSpellEntity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class MissileEnchantment extends AbstractStaffEnchantment {
    public MissileEnchantment () {
        super(3, Enchantment.constantCost(20), Enchantment.constantCost(70), 5, 0xFF975DFF, 96, 20);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            MissileSpellEntity missile = new MissileSpellEntity(serverWorld, user);
            serverWorld.spawnEntity(missile);
        }
        return true;
    }
}
