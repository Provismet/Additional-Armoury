package com.provismet.AdditionalArmoury.enchantments.boomerang;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentTargets;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public abstract class BoomerangEnchantment extends Enchantment {
    protected BoomerangEnchantment (Rarity weight) {
        super(weight, AAEnchantmentTargets.BOOMERANG, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
}
