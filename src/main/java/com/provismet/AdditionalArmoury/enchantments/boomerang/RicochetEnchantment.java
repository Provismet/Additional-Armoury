package com.provismet.AdditionalArmoury.enchantments.boomerang;

public class RicochetEnchantment extends BoomerangEnchantment {
    public RicochetEnchantment () {
        super(Rarity.COMMON);
    }
    
    @Override
    public int getMaxLevel () {
        return 4;
    }
}
