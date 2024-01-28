package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.SpellRingParticleEffect;
import com.provismet.lilylib.particle.FlatParticle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;

public class SpellRingParticle extends FlatParticle {
    private static final float MOVING_TICKS = 15f;

    private final float rotation;
    private final float maxScale;

    protected SpellRingParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, SpellRingParticleEffect effect) {
        super(world, x, y, z, spriteProvider);

        this.scale = 0.1f;
        this.maxScale = effect.getScale();
        this.maxAge = effect.getDuration();
        this.rotation = this.random.nextBoolean() ? (this.maxScale / 1.5f) / MathHelper.DEGREES_PER_RADIAN : (this.maxScale / -1.5f) / MathHelper.DEGREES_PER_RADIAN;
    }

    @Override
    public void tick () {
        super.tick();
        this.setAngleY(this.angle + this.rotation);

        if (this.age <= MOVING_TICKS) this.scale = this.maxScale * ((float)this.age / MOVING_TICKS);
        else if (this.maxAge - this.age <= MOVING_TICKS) this.scale = this.maxScale * ((float)(this.maxAge - this.age) / MOVING_TICKS);
    }
    
    public static class Factory implements ParticleFactory<SpellRingParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory (SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle (SpellRingParticleEffect particleEffect, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
            return new SpellRingParticle(clientWorld, x, y, z, this.spriteProvider, particleEffect);
        }
    }
}
