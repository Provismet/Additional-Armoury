package com.provismet.AdditionalArmoury.enchantments.mace;

import com.provismet.AdditionalArmoury.utility.AATags;
import com.provismet.CombatPlusCore.enchantments.WeaponUtilityEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class DismantleEnchantment extends WeaponUtilityEnchantment {
    public DismantleEnchantment () {
        super(Enchantment.properties(
                AATags.ItemTags.MACE_ENCHANTABLE,
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2,
                EquipmentSlot.MAINHAND
        ));
    }
}
