package com.provismet.AdditionalArmoury.enchantments.boomerang;

import net.minecraft.enchantment.Enchantment;

public class DistanceEnchantment extends BoomerangEnchantment {
    public DistanceEnchantment () {
        super(10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2);
    }

    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof ThrowStrengthEnchantment);
    }
}
