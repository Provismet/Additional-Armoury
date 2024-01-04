package com.provismet.AdditionalArmoury.effect;

import java.util.UUID;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ShatteredStatusEffect extends StatusEffect {
    public ShatteredStatusEffect () {
        super(StatusEffectCategory.HARMFUL, 0x726760);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, UUID.nameUUIDFromBytes("Additional Armoury: Shattered Armour".getBytes()).toString(), 0.75, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
