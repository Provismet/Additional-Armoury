package com.provismet.AdditionalArmoury.particles.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.dynamic.Codecs;

public record SpellRingParticleEffect (float scale, int duration) implements ParticleEffect {
    public static final MapCodec<SpellRingParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Codecs.POSITIVE_FLOAT.fieldOf("scale").forGetter(effect -> effect.scale),
            Codecs.POSITIVE_INT.fieldOf("duration").forGetter(effect -> effect.duration)
        ).apply(instance, SpellRingParticleEffect::new)
    );

    public static final PacketCodec<RegistryByteBuf, SpellRingParticleEffect> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.FLOAT,
        effect -> effect.scale,
        PacketCodecs.INTEGER,
        effect -> effect.duration,
        SpellRingParticleEffect::new
    );

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_RING;
    }
}
