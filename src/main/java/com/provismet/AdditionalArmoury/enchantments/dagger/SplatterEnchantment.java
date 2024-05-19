package com.provismet.AdditionalArmoury.enchantments.dagger;

import com.provismet.AdditionalArmoury.utility.AATags;
import com.provismet.CombatPlusCore.enchantments.WeaponUtilityEnchantment;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class SplatterEnchantment extends WeaponUtilityEnchantment {
    public SplatterEnchantment () {
        super(Enchantment.properties(
                AATags.ItemTags.DAGGER_ENCHANTABLE,
                10,
                2,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2,
                EquipmentSlot.MAINHAND
        ));
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (CPCEnchantmentHelper.isOffhand(other)) return true;
        return super.canAccept(other);
    }
}
