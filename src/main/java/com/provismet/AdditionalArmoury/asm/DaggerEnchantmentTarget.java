package com.provismet.AdditionalArmoury.asm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.provismet.AdditionalArmoury.items.DaggerItem;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class DaggerEnchantmentTarget extends DaggerEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem (Item item) {
        return item instanceof DaggerItem;
    }
}

@Mixin(EnchantmentTarget.class)
abstract class DaggerEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem (Item item);
}
