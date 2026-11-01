package com.provismet.additionalarmoury.enchantment.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.provismet.additionalarmoury.utility.registry.AARegistries;
import java.util.function.Function;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;

public interface SpellIncantationTickingEffect {
    Codec<SpellIncantationTickingEffect> CODEC = AARegistries.TICKING_INCANTATION.byNameCodec().dispatch(SpellIncantationTickingEffect::getCodec, Function.identity());

    void apply (ServerLevel world, int level, EnchantedItemInUse context, LivingEntity user, int remainingTicks);

    MapCodec<? extends SpellIncantationTickingEffect> getCodec ();
}
