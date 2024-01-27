package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.InkSplatParticleEffect;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class InkSplatParticle extends AnimatedParticle {
    protected InkSplatParticle (ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float upwardsAcceleration, InkSplatParticleEffect effect) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);

        this.red = effect.getColor().x;
        this.green = effect.getColor().y;
        this.blue = effect.getColor().z;
        this.scale = effect.getScale();

        this.velocityX = this.random.nextDouble() * 0.1 - 0.05;
        this.velocityY = this.random.nextDouble() * 0.1;
        this.velocityZ = this.random.nextDouble() * 0.1 - 0.05;

        this.maxAge += 20;
        this.setSpriteForAge(spriteProvider);
    }
    
    @Override
    public void tick () {
        if (this.onGround) {
            this.setVelocity(0.0, 0.0, 0.0);
            this.velocityMultiplier = 0;
            this.gravityStrength = 0;
        }
        super.tick();
    }

    public static class Factory implements ParticleFactory<InkSplatParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory (SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle (InkSplatParticleEffect particleEffect, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
            return new InkSplatParticle(clientWorld, x, y, z, this.spriteProvider, 0.3f, particleEffect);
        }
    }
}
