package com.provismet.AdditionalArmoury.enchantments.mace;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentTargets;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public abstract class MaceEnchantment extends Enchantment {
    protected MaceEnchantment (Rarity weight) {
        super(weight, AAEnchantmentTargets.MACE, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
}
