package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.SpellRingParticleEffect;
import com.provismet.lilylib.particle.FlatParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class SpellRingParticle extends FlatParticle {
    private static final float MOVING_TICKS = 15f;

    private final float rotation;
    private final float maxScale;

    private float prevScale;

    protected SpellRingParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, SpellRingParticleEffect effect) {
        super(world, x, y, z, spriteProvider);

        this.scale = 0.1f;
        this.prevScale = this.scale;
        this.maxScale = effect.scale();
        this.maxAge = effect.duration();
        this.rotation = this.random.nextBoolean() ? (this.maxScale / 1.5f) / MathHelper.DEGREES_PER_RADIAN : (this.maxScale / -1.5f) / MathHelper.DEGREES_PER_RADIAN;
    }

    @Override
    public void tick () {
        super.tick();
        this.setAngleZ(this.angleZ + this.rotation);
        this.prevScale = this.scale;

        if (this.age <= MOVING_TICKS) this.scale = this.maxScale * ((float)this.age / MOVING_TICKS);
        else if (this.maxAge - this.age <= MOVING_TICKS) this.scale = this.maxScale * ((float)(this.maxAge - this.age) / MOVING_TICKS);
    }

    @Override
    public float getSize (float tickDelta) {
        return MathHelper.lerp(tickDelta, this.prevScale, this.scale);
    }
    
    public static class Factory implements ParticleFactory<SpellRingParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory (SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle (SpellRingParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new SpellRingParticle(world, x, y, z, this.spriteProvider, parameters);
        }
    }
}
