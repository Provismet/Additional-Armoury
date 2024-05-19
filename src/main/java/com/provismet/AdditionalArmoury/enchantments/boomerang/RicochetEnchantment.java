package com.provismet.AdditionalArmoury.enchantments.boomerang;

import net.minecraft.enchantment.Enchantment;

public class RicochetEnchantment extends BoomerangEnchantment {
    public RicochetEnchantment () {
        super(10, 4, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2);
    }
}
