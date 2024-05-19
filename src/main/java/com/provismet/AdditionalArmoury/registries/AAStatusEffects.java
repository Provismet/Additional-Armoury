package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.effect.ShatteredStatusEffect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class AAStatusEffects {
    public static final RegistryEntry<StatusEffect> SHATTERED = register(new ShatteredStatusEffect(), "shattered");

    private static RegistryEntry<StatusEffect> register (StatusEffect effect, String name) {
        return Registry.registerReference(Registries.STATUS_EFFECT, AdditionalArmouryMain.identifier(name), effect);
    }

    public static void register () {
        // Do nothing
    }
}
