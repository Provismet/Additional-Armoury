package com.provismet.AdditionalArmoury.utility.registry;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class AARegistryKeys {
    public static final RegistryKey<Registry<MapCodec<? extends SpellIncantationTickingEffect>>> TICKING_INCANTATION = RegistryKey.ofRegistry(AdditionalArmouryMain.identifier("ticking_incantation"));
    public static final RegistryKey<Registry<LambdaIncantationEffect.Lambda>> INCANTATION_LAMBDA = RegistryKey.ofRegistry(AdditionalArmouryMain.identifier("incantation_lambda"));
}
