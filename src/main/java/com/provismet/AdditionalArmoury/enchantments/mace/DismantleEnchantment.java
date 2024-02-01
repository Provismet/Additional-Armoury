package com.provismet.AdditionalArmoury.enchantments.mace;

import net.minecraft.enchantment.Enchantment;

public class DismantleEnchantment extends MaceEnchantment {
    public DismantleEnchantment () {
        super(Rarity.UNCOMMON);
    }
    
    @Override
    public int getMinPower (int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }
    
    @Override
    public int getMaxLevel () {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && !(other instanceof ShreddingEnchantment);
    }
}
