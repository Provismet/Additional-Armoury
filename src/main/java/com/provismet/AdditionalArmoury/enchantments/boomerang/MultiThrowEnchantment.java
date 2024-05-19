package com.provismet.AdditionalArmoury.enchantments.boomerang;

import net.minecraft.enchantment.Enchantment;

public class MultiThrowEnchantment extends BoomerangEnchantment {
    public MultiThrowEnchantment () {
        super(5, 1, Enchantment.constantCost(20), Enchantment.constantCost(75), 4);
    }
    
    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof RicochetEnchantment);
    }
}
