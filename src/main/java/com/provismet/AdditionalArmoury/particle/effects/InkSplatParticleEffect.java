package com.provismet.AdditionalArmoury.particle.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3fc;

public record InkSplatParticleEffect (Vector3fc colour, float scale) implements ParticleOptions {

    public static final MapCodec<InkSplatParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("colour").forGetter(effect -> effect.colour),
            ExtraCodecs.POSITIVE_FLOAT.fieldOf("scale").forGetter(effect -> effect.scale)
        ).apply(instance, InkSplatParticleEffect::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, InkSplatParticleEffect> PACKET_CODEC = StreamCodec.composite(
        ByteBufCodecs.VECTOR3F,
        effect -> effect.colour,
        ByteBufCodecs.FLOAT,
        effect -> effect.scale,
        InkSplatParticleEffect::new
    );

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.INK_SPLAT;
    }
}
