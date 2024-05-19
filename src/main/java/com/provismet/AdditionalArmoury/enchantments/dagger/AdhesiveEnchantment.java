package com.provismet.AdditionalArmoury.enchantments.dagger;

import com.provismet.AdditionalArmoury.utility.AATags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class AdhesiveEnchantment extends Enchantment {
    public AdhesiveEnchantment () {
        super(Enchantment.properties(
                AATags.ItemTags.DAGGER_ENCHANTABLE,
                2,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                6,
                EquipmentSlot.MAINHAND
        ));
    }

    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && !(other instanceof MendingEnchantment);
    }

    @Override
    public boolean isTreasure () {
        return true;
    }
}
