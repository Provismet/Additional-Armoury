package com.provismet.additionalarmoury.utility.registry;

import com.mojang.serialization.MapCodec;
import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.additionalarmoury.enchantment.incantation.LambdaIncantationEffect;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class AARegistryKeys {
    public static final ResourceKey<Registry<MapCodec<? extends SpellIncantationTickingEffect>>> TICKING_INCANTATION = ResourceKey.createRegistryKey(AdditionalArmouryMain.identifier("ticking_incantation"));
    public static final ResourceKey<Registry<LambdaIncantationEffect.Lambda>> INCANTATION_LAMBDA = ResourceKey.createRegistryKey(AdditionalArmouryMain.identifier("incantation_lambda"));
}
