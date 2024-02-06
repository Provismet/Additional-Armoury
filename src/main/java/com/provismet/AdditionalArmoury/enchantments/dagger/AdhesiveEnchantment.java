package com.provismet.AdditionalArmoury.enchantments.dagger;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class AdhesiveEnchantment extends DaggerEnchantment {
    public AdhesiveEnchantment () {
        super(Rarity.RARE, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof MendingEnchantment);
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
    public boolean isTreasure () {
        return true;
    }
}
