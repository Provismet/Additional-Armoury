package com.provismet.additionalarmoury.particle;

import com.provismet.additionalarmoury.particle.effects.SpellRingParticleEffect;
import com.provismet.lilylib.particle.FlatParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class SpellRingParticle extends FlatParticle {
    private static final float MOVING_TICKS = 15f;

    private final float rotation;
    private final float maxScale;

    private float prevScale;

    protected SpellRingParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, SpellRingParticleEffect effect) {
        super(world, x, y, z, spriteProvider);

        this.quadSize = 0.1f;
        this.prevScale = this.quadSize;
        this.maxScale = effect.scale();
        this.lifetime = effect.duration();
        this.rotation = this.random.nextBoolean() ? (this.maxScale / 1.5f) / Mth.RAD_TO_DEG : (this.maxScale / -1.5f) / Mth.RAD_TO_DEG;
    }

    @Override
    public void tick () {
        super.tick();
        this.setAngleZ(this.angleZ + this.rotation);
        this.prevScale = this.quadSize;

        if (this.age <= MOVING_TICKS) this.quadSize = this.maxScale * ((float)this.age / MOVING_TICKS);
        else if (this.lifetime - this.age <= MOVING_TICKS) this.quadSize = this.maxScale * ((float)(this.lifetime - this.age) / MOVING_TICKS);
    }

    @Override
    public float getQuadSize (float tickDelta) {
        return Mth.lerp(tickDelta, this.prevScale, this.quadSize);
    }
    
    public static class Factory implements ParticleProvider<SpellRingParticleEffect> {
        private final SpriteSet spriteProvider;

        public Factory (SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle (SpellRingParticleEffect parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new SpellRingParticle(world, x, y, z, this.spriteProvider, parameters);
        }
    }
}
