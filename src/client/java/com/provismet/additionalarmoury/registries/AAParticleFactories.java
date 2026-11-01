package com.provismet.additionalarmoury.registries;

import com.provismet.additionalarmoury.particle.InkSplatParticle;
import com.provismet.additionalarmoury.particle.ShatterParticle;
import com.provismet.additionalarmoury.particle.SpellChargeParticle;
import com.provismet.additionalarmoury.particle.SpellRingParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

public class AAParticleFactories {

    private static <T extends ParticleOptions> void register (ParticleType<T> particle, ParticleProviderRegistry.PendingParticleProvider<T> constructor) {
        ParticleProviderRegistry.getInstance().register(particle, constructor);
    }

    public static void register () {
        register(AAParticleTypes.SPELL_CHARGE, SpellChargeParticle.Factory::new);
        register(AAParticleTypes.INK_SPLAT, InkSplatParticle.Factory::new);
        register(AAParticleTypes.SPELL_RING, SpellRingParticle.Factory::new);

        register(AAParticleTypes.SHATTER, ShatterParticle.Factory::new);
    }
}
