package com.provismet.AdditionalArmoury.enchantments.boomerang;

import net.minecraft.enchantment.Enchantment;

public class MultiThrowEnchantment extends BoomerangEnchantment {
    public MultiThrowEnchantment () {
        super(Rarity.UNCOMMON);
    }

    @Override
    public int getMinPower (int level) {
        return 20;
    }

    @Override
    public int getMaxPower (int level) {
        return 50;
    }
    
    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof RicochetEnchantment);
    }
}
