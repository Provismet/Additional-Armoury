package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.GhostlySpellEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class GhostOrbEnchantment extends StaffEnchantment {
    public GhostOrbEnchantment () {
        super(Rarity.UNCOMMON, 0x6B6B6B, 64, 20);
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
