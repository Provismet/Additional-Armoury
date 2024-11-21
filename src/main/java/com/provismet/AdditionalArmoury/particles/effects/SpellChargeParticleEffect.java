package com.provismet.AdditionalArmoury.particles.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import org.joml.Vector3f;

import com.provismet.AdditionalArmoury.registries.AAParticleTypes;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public record SpellChargeParticleEffect (Vector3f colour, float scale) implements ParticleEffect {
    public static final MapCodec<SpellChargeParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Codecs.VECTOR_3F.fieldOf("colour").forGetter(effect -> effect.colour),
            Codecs.POSITIVE_FLOAT.fieldOf("scale").forGetter(effect -> effect.scale)
        ).apply(instance, SpellChargeParticleEffect::new)
    );

    public static final PacketCodec<RegistryByteBuf, SpellChargeParticleEffect> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.VECTOR_3F,
        effect -> effect.colour,
        PacketCodecs.FLOAT,
        effect -> effect.scale,
        SpellChargeParticleEffect::new
    );

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_CHARGE;
    }
}
