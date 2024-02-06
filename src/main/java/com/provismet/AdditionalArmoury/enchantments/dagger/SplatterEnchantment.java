package com.provismet.AdditionalArmoury.enchantments.dagger;

import com.provismet.CombatPlusCore.enchantments.WeaponUtilityEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class SplatterEnchantment extends DaggerEnchantment {
    public SplatterEnchantment () {
        super(Rarity.COMMON, EquipmentSlot.MAINHAND);
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
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && !(other instanceof WeaponUtilityEnchantment);
    }
}
