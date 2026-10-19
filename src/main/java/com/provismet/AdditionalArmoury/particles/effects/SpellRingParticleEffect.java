package com.provismet.AdditionalArmoury.particles.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

public record SpellRingParticleEffect (float scale, int duration) implements ParticleOptions {
    public static final MapCodec<SpellRingParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            ExtraCodecs.POSITIVE_FLOAT.fieldOf("scale").forGetter(effect -> effect.scale),
            ExtraCodecs.POSITIVE_INT.fieldOf("duration").forGetter(effect -> effect.duration)
        ).apply(instance, SpellRingParticleEffect::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SpellRingParticleEffect> PACKET_CODEC = StreamCodec.composite(
        ByteBufCodecs.FLOAT,
        effect -> effect.scale,
        ByteBufCodecs.INT,
        effect -> effect.duration,
        SpellRingParticleEffect::new
    );

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_RING;
    }
}
