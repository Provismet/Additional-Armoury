package com.provismet.AdditionalArmoury.asm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.provismet.AdditionalArmoury.items.BoomerangItem;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class BoomerangEnchantmentTarget extends BoomerangEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem (Item item) {
        return item instanceof BoomerangItem;
    }
}


@Mixin(EnchantmentTarget.class)
abstract class BoomerangEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem (Item item);
}