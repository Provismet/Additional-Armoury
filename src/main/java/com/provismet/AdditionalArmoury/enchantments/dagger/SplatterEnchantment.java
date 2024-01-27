package com.provismet.AdditionalArmoury.enchantments.dagger;

import net.minecraft.entity.EquipmentSlot;

public class SplatterEnchantment extends DaggerEnchantment {
    public SplatterEnchantment () {
        super(Rarity.COMMON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
}
