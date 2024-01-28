package com.provismet.AdditionalArmoury.particles.effects;

import java.util.Locale;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;

public class SpellRingParticleEffect implements ParticleEffect {
    private final float scale;
    private final int duration;

    public SpellRingParticleEffect (float scale, int duration) {
        this.scale = scale;
        this.duration = duration;
    }

    @SuppressWarnings("deprecation")
    public static final ParticleEffect.Factory<SpellRingParticleEffect> PARAMETERS_FACTORY = new ParticleEffect.Factory<SpellRingParticleEffect>() {
        @Override
        public SpellRingParticleEffect read (ParticleType<SpellRingParticleEffect> particleType, StringReader stringReader) throws CommandSyntaxException {
            float scale = stringReader.readFloat();
            stringReader.expect(' ');
            int duration = stringReader.readInt();
            return new SpellRingParticleEffect(scale, duration);
        }

        @Override
        public SpellRingParticleEffect read (ParticleType<SpellRingParticleEffect> particleType, PacketByteBuf buffer) {
            return new SpellRingParticleEffect(buffer.readFloat(), buffer.readInt());
        }
    };

    @Override
    public ParticleType<?> getType () {
        return AAParticleTypes.SPELL_RING;
    }

    @Override
    public void write (PacketByteBuf buf) {
        buf.writeFloat(this.scale);
        buf.writeInt(this.duration);
    }

    @Override
    public String asString () {
        return String.format(Locale.ROOT, "%s %.2f %d", Registries.PARTICLE_TYPE.getId(this.getType()), Float.valueOf(this.scale), Integer.valueOf(this.duration));
    }

    public float getScale () {
        return this.scale;
    }

    public int getDuration () {
        return this.duration;
    }
}
