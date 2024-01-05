package com.provismet.AdditionalArmoury.enchantments.dagger;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentTargets;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class AdhesiveEnchantment extends Enchantment {
    public AdhesiveEnchantment () {
        super(Rarity.RARE, AAEnchantmentTargets.DAGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof MendingEnchantment);
    }

    @Override
    public int getMinPower (int level) {
        return level * 25;
    }

    @Override
    public int getMaxPower (int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public boolean isTreasure () {
        return true;
    }
}
