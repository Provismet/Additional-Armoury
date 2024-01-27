package com.provismet.AdditionalArmoury.particles.effects;

import org.joml.Vector3f;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class InkSplatParticleEffect extends AbstractDustParticleEffect {
    public InkSplatParticleEffect(Vector3f color, float scale) {
        super(color, scale);
    }

    @SuppressWarnings("deprecation")
    public static final ParticleEffect.Factory<InkSplatParticleEffect> PARAMETERS_FACTORY = new ParticleEffect.Factory<InkSplatParticleEffect>() {
        @Override
        public InkSplatParticleEffect read (ParticleType<InkSplatParticleEffect> particleType, StringReader stringReader) throws CommandSyntaxException {
            Vector3f colour = AbstractDustParticleEffect.readColor(stringReader);
            stringReader.expect(' ');
            float scale = stringReader.readFloat();
            return new InkSplatParticleEffect(colour, scale);
        }

        @Override
        public InkSplatParticleEffect read (ParticleType<InkSplatParticleEffect> particleType, PacketByteBuf buffer) {
            return new InkSplatParticleEffect(buffer.readVector3f(), buffer.readFloat());
        }
    };

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.INK_SPLAT;
    }
}
