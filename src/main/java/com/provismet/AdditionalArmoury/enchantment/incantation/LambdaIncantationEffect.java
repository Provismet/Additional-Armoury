package com.provismet.AdditionalArmoury.enchantment.incantation;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Optional;

public record LambdaIncantationEffect (Identifier function) implements SpellIncantationTickingEffect {
    public static final MapCodec<LambdaIncantationEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Identifier.CODEC.fieldOf("function").forGetter(LambdaIncantationEffect::function)).apply(instance, LambdaIncantationEffect::new));

    @Override
    public void apply (ServerWorld world, int level, EnchantmentEffectContext context, LivingEntity user, int remainingTicks) {
        Optional<Lambda> lambda = AARegistries.INCANTATION_LAMBDA.getOrEmpty(this.function);
        lambda.ifPresent(value -> value.execute(world, level, context, user, remainingTicks));
    }

    @Override
    public MapCodec<? extends SpellIncantationTickingEffect> getCodec () {
        return CODEC;
    }

    @FunctionalInterface
    public interface Lambda {
        void execute (ServerWorld world, int level, EnchantmentEffectContext context, LivingEntity user, int remainingTicks);
    }
}
