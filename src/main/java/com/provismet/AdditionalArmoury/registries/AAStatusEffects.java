package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.effect.ShatteredStatusEffect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAStatusEffects {
    public static final StatusEffect SHATTERED = new ShatteredStatusEffect();

    private static void register (StatusEffect effect, String name) {
        Registry.register(Registries.STATUS_EFFECT, AdditionalArmouryMain.identifier(name), effect);
    }

    public static void register () {
        register(SHATTERED, "shattered");
    }
}
