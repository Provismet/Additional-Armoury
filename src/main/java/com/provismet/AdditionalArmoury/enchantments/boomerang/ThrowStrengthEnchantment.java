package com.provismet.AdditionalArmoury.enchantments.boomerang;

import net.minecraft.enchantment.Enchantment;

public class ThrowStrengthEnchantment extends BoomerangEnchantment {
    public ThrowStrengthEnchantment () {
        super(Rarity.COMMON);
    }
    
    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof DistanceEnchantment);
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
