package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class FrostballEnchantment extends StaffEnchantment {
    public FrostballEnchantment () {
        super(Rarity.COMMON, 0xBADCFF, 64, 30);
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
