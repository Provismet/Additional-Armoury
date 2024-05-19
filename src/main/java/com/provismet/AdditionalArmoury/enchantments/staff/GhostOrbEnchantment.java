package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.GhostlySpellEntity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class GhostOrbEnchantment extends AbstractStaffEnchantment {
    public GhostOrbEnchantment () {
        super(3, Enchantment.constantCost(20), Enchantment.constantCost(70), 5, 0xFF6B6B6B, 64, 20);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            GhostlySpellEntity ghostSpell = new GhostlySpellEntity(serverWorld, user);
            serverWorld.spawnEntity(ghostSpell);
        }
        return true;
    }
}
