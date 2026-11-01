package com.provismet.additionalarmoury.registries;

import com.mojang.serialization.MapCodec;
import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.additionalarmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.additionalarmoury.utility.registry.AARegistries;
import net.minecraft.core.Registry;

public class AAIncantationEffects {
    public static void register () {
        register("code_execution_incantation", LambdaIncantationEffect.CODEC);
    }

    private static void register (String name, MapCodec<? extends SpellIncantationTickingEffect> codec) {
        Registry.register(AARegistries.TICKING_INCANTATION, AdditionalArmouryMain.identifier(name), codec);
    }
}
