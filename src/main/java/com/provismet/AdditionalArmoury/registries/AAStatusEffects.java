package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.effect.ShatteredStatusEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public class AAStatusEffects {
    public static final Holder<MobEffect> SHATTERED = register(new ShatteredStatusEffect(), "shattered");

    private static Holder<MobEffect> register (MobEffect effect, String name) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, AdditionalArmouryMain.identifier(name), effect);
    }

    public static void register () {
        // Do nothing
    }
}
