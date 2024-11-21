package com.provismet.AdditionalArmoury.enchantment.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.function.Function;

public interface SpellIncantationTickingEffect {
    Codec<SpellIncantationTickingEffect> CODEC = AARegistries.TICKING_INCANTATION.getCodec().dispatch(SpellIncantationTickingEffect::getCodec, Function.identity());

    void apply (ServerWorld world, int level, EnchantmentEffectContext context, LivingEntity user, int remainingTicks);

    MapCodec<? extends SpellIncantationTickingEffect> getCodec ();
}
