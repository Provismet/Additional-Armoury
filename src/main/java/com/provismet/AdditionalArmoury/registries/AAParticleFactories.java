package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.particles.InkSplatParticle;
import com.provismet.AdditionalArmoury.particles.ShatterParticle;
import com.provismet.AdditionalArmoury.particles.SpellChargeParticle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.PendingParticleFactory;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

@Environment(EnvType.CLIENT)
public class AAParticleFactories {

    private static <T extends ParticleEffect> void register (ParticleType<T> particle, PendingParticleFactory<T> constructor) {
        ParticleFactoryRegistry.getInstance().register(particle, constructor);
    }

    public static void register () {
        register(AAParticleTypes.SPELL_CHARGE, SpellChargeParticle.Factory::new);
        register(AAParticleTypes.INK_SPLAT, InkSplatParticle.Factory::new);
        register(AAParticleTypes.SHATTER, ShatterParticle.Factory::new);
    }
}
