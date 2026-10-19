package com.provismet.AdditionalArmoury.enchantment.incantation;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;

public record LambdaIncantationEffect (Identifier function) implements SpellIncantationTickingEffect {
    public static final MapCodec<LambdaIncantationEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Identifier.CODEC.fieldOf("function").forGetter(LambdaIncantationEffect::function)).apply(instance, LambdaIncantationEffect::new));

    @Override
    public void apply (ServerLevel world, int level, EnchantedItemInUse context, LivingEntity user, int remainingTicks) {
        Optional<Lambda> lambda = AARegistries.INCANTATION_LAMBDA.getOptional(this.function);
        lambda.ifPresent(value -> value.execute(world, level, context, user, remainingTicks));
    }

    @Override
    public MapCodec<? extends SpellIncantationTickingEffect> getCodec () {
        return CODEC;
    }

    @FunctionalInterface
    public interface Lambda {
        void execute (ServerLevel world, int level, EnchantedItemInUse context, LivingEntity user, int remainingTicks);
    }
}
