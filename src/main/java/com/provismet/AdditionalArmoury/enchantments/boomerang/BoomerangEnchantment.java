package com.provismet.AdditionalArmoury.enchantments.boomerang;

import com.provismet.AdditionalArmoury.utility.AATags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public abstract class BoomerangEnchantment extends Enchantment {
    protected BoomerangEnchantment (int weight, int maxLevel, Cost minCost, Cost maxCost, int anvilCost) {
        super(Enchantment.properties(
                AATags.ItemTags.BOOMERANG_ENCHANTABLE,
                weight,
                maxLevel,
                minCost,
                maxCost,
                anvilCost,
                EquipmentSlot.MAINHAND
        ));
    }
}
