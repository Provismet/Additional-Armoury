package com.provismet.AdditionalArmoury.effect;

import com.provismet.CombatPlusCore.CPCMain;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ShatteredStatusEffect extends StatusEffect {
    public ShatteredStatusEffect () {
        super(StatusEffectCategory.HARMFUL, 0x726760);
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ARMOR,
                CPCMain.identifier("shattered"),
                -0.25,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}
