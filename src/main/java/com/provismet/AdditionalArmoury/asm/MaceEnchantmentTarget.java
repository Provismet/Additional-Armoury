package com.provismet.AdditionalArmoury.asm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.provismet.AdditionalArmoury.items.MaceItem;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;

public class MaceEnchantmentTarget extends MaceEnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem (Item item) {
        return item instanceof MaceItem;
    }
}

@Mixin(EnchantmentTarget.class)
abstract class MaceEnchantmentTargetMixin {
    @Shadow
    abstract boolean isAcceptableItem (Item item);
}
