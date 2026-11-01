package com.provismet.AdditionalArmoury.effect;

import com.provismet.CombatPlusCore.CombatPlusCoreMain;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ShatteredStatusEffect extends MobEffect {
    public ShatteredStatusEffect () {
        super(MobEffectCategory.HARMFUL, 0x726760);
        this.addAttributeModifier(
                Attributes.ARMOR,
                CombatPlusCoreMain.identifier("shattered"),
                -0.25,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}
