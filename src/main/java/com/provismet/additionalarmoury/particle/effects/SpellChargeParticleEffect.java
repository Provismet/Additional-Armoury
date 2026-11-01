package com.provismet.additionalarmoury.particle.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.additionalarmoury.registries.AAParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import org.joml.Vector3fc;

public record SpellChargeParticleEffect (Vector3fc colour, float scale) implements ParticleOptions {
    public static final MapCodec<SpellChargeParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            ExtraCodecs.VECTOR3F.fieldOf("colour").forGetter(effect -> effect.colour),
            ExtraCodecs.POSITIVE_FLOAT.fieldOf("scale").forGetter(effect -> effect.scale)
        ).apply(instance, SpellChargeParticleEffect::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SpellChargeParticleEffect> PACKET_CODEC = StreamCodec.composite(
        ByteBufCodecs.VECTOR3F,
        effect -> effect.colour,
        ByteBufCodecs.FLOAT,
        effect -> effect.scale,
        SpellChargeParticleEffect::new
    );

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_CHARGE;
    }
}
