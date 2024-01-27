package com.provismet.AdditionalArmoury.particles.effects;

import org.joml.Vector3f;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.AbstractDustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class SpellChargeParticleEffect extends AbstractDustParticleEffect {
    public SpellChargeParticleEffect(Vector3f colour, float scale) {
        super(colour, scale);
    }

    @SuppressWarnings("deprecation")
    public static final ParticleEffect.Factory<SpellChargeParticleEffect> PARAMETERS_FACTORY = new ParticleEffect.Factory<SpellChargeParticleEffect>() {
        @Override
        public SpellChargeParticleEffect read (ParticleType<SpellChargeParticleEffect> particleType, StringReader stringReader) throws CommandSyntaxException {
            Vector3f colour = AbstractDustParticleEffect.readColor(stringReader);
            stringReader.expect(' ');
            float scale = stringReader.readFloat();
            return new SpellChargeParticleEffect(colour, scale);
        }

        @Override
        public SpellChargeParticleEffect read (ParticleType<SpellChargeParticleEffect> particleType, PacketByteBuf buffer) {
            return new SpellChargeParticleEffect(buffer.readVector3f(), buffer.readFloat());
        }
    };

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_CHARGE;
    }
}
