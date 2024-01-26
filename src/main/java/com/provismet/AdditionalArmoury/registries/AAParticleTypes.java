package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAParticleTypes {
    public static final ParticleType<SpellChargeParticleEffect> SPELL_CHARGE = FabricParticleTypes.complex(SpellChargeParticleEffect.PARAMETERS_FACTORY);
    public static final DefaultParticleType SHATTER = FabricParticleTypes.simple(true);

    private static <T extends ParticleEffect> void register (ParticleType<T> particle, String name) {
        Registry.register(Registries.PARTICLE_TYPE, AdditionalArmouryMain.identifier(name), particle);
    }

    public static void register () {
        register(SPELL_CHARGE, "spell_charge");
        register(SHATTER, "shatter");
    }
}
