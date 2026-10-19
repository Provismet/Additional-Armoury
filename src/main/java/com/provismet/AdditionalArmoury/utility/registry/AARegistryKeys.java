package com.provismet.AdditionalArmoury.utility.registry;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class AARegistryKeys {
    public static final ResourceKey<Registry<MapCodec<? extends SpellIncantationTickingEffect>>> TICKING_INCANTATION = ResourceKey.createRegistryKey(AdditionalArmouryMain.identifier("ticking_incantation"));
    public static final ResourceKey<Registry<LambdaIncantationEffect.Lambda>> INCANTATION_LAMBDA = ResourceKey.createRegistryKey(AdditionalArmouryMain.identifier("incantation_lambda"));
}
