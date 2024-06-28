package com.provismet.AdditionalArmoury.registries;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import net.minecraft.registry.Registry;

public class AAIncantationEffects {
    public static void register () {
        register("code_execution_incantation", LambdaIncantationEffect.CODEC);
    }

    private static void register (String name, MapCodec<? extends SpellIncantationTickingEffect> codec) {
        Registry.register(AARegistries.TICKING_INCANTATION, AdditionalArmouryMain.identifier(name), codec);
    }
}
