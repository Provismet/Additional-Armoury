package com.provismet.AdditionalArmoury.enchantments.mace;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentTargets;
import com.provismet.CombatPlusCore.enchantments.WeaponUtilityEnchantment;

import net.minecraft.entity.EquipmentSlot;

public class ShreddingEnchantment extends WeaponUtilityEnchantment {
    public ShreddingEnchantment () {
        super(Rarity.COMMON, AAEnchantmentTargets.MACE, EquipmentSlot.MAINHAND);
    }
    
    @Override
    public int getMinPower (int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }
    
    @Override
    public int getMaxLevel () {
        return 3;
    }
}
