package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.MissileSpellEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class MissileEnchantment extends StaffEnchantment {
    public MissileEnchantment () {
        super(Rarity.RARE, 0x975DFF, 96, 20);
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
