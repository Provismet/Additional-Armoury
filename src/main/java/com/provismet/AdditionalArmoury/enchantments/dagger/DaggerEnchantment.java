package com.provismet.AdditionalArmoury.enchantments.dagger;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentTargets;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public abstract class DaggerEnchantment extends Enchantment {
    protected DaggerEnchantment (Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, AAEnchantmentTargets.DAGGER, slotTypes);
    }
}
