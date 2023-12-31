package com.provismet.AdditionalArmoury.asm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.provismet.AdditionalArmoury.items.StaffItem;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class StaffEnchantmentTarget extends StaffEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem (Item item) {
        return item instanceof StaffItem;
    }
}

@Mixin(EnchantmentTarget.class)
abstract class StaffEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem (Item item);
}