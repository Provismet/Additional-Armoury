package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.particles.effects.InkSplatParticleEffect;
import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;
import com.provismet.AdditionalArmoury.particles.effects.SpellRingParticleEffect;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAParticleTypes {
    public static final ParticleType<SpellChargeParticleEffect> SPELL_CHARGE = FabricParticleTypes.complex(SpellChargeParticleEffect.CODEC, SpellChargeParticleEffect.PACKET_CODEC);
    public static final ParticleType<InkSplatParticleEffect> INK_SPLAT = FabricParticleTypes.complex(InkSplatParticleEffect.CODEC, InkSplatParticleEffect.PACKET_CODEC);
    public static final ParticleType<SpellRingParticleEffect> SPELL_RING = FabricParticleTypes.complex(SpellRingParticleEffect.CODEC, SpellRingParticleEffect.PACKET_CODEC);

    public static final SimpleParticleType SHATTER = FabricParticleTypes.simple(true);

    private static <T extends ParticleEffect> void register (ParticleType<T> particle, String name) {
        Registry.register(Registries.PARTICLE_TYPE, AdditionalArmouryMain.identifier(name), particle);
    }

    public static void register () {
        register(SPELL_CHARGE, "spell_charge");
        register(INK_SPLAT, "ink_splat");
        register(SPELL_RING, "spell_ring");

        register(SHATTER, "shatter");
    }
}
