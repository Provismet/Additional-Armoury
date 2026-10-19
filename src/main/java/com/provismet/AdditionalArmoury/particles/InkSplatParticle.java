package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.InkSplatParticleEffect;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class InkSplatParticle extends SimpleAnimatedParticle {
    protected InkSplatParticle (ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float upwardsAcceleration, InkSplatParticleEffect effect) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);

        this.rCol = effect.colour().x();
        this.gCol = effect.colour().y();
        this.bCol = effect.colour().z();
        this.quadSize = effect.scale();

        this.xd = this.random.nextDouble() * 0.1 - 0.05;
        this.yd = this.random.nextDouble() * 0.1;
        this.zd = this.random.nextDouble() * 0.1 - 0.05;

        this.lifetime += 20;
        this.setSpriteFromAge(spriteProvider);
    }
    
    @Override
    public void tick () {
        if (this.onGround) {
            this.setParticleSpeed(0.0, 0.0, 0.0);
            this.friction = 0;
            this.gravity = 0;
        }
        super.tick();
    }

    public static class Factory implements ParticleProvider<InkSplatParticleEffect> {
        private final SpriteSet spriteProvider;

        public Factory (SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle (InkSplatParticleEffect parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new InkSplatParticle(world, x, y, z, this.spriteProvider, 0.3f, parameters);
        }
    }
}
