package com.provismet.AdditionalArmoury.utility.registry;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;

public class AARegistries {
    public static final Registry<MapCodec<? extends SpellIncantationTickingEffect>> TICKING_INCANTATION = FabricRegistryBuilder.createSimple(AARegistryKeys.TICKING_INCANTATION).buildAndRegister();
    public static final Registry<LambdaIncantationEffect.Lambda> INCANTATION_LAMBDA = FabricRegistryBuilder.createSimple(AARegistryKeys.INCANTATION_LAMBDA).buildAndRegister();

    public static void init () {}
}
