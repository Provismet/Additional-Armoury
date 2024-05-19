package com.provismet.AdditionalArmoury.enchantments.mace;

import com.provismet.AdditionalArmoury.utility.AATags;
import com.provismet.CombatPlusCore.enchantments.WeaponUtilityEnchantment;

import net.minecraft.enchantment.Enchantment;

public class ShreddingEnchantment extends WeaponUtilityEnchantment {
    public ShreddingEnchantment () {
        super(Enchantment.properties(
                AATags.ItemTags.MACE_ENCHANTABLE,
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2
        ));
    }
}
