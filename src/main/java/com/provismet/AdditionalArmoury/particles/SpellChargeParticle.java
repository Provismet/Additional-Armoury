package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class SpellChargeParticle extends SingleQuadParticle {
    private final double destX;
    private final double destZ;
    private final float randomAngle;

    protected SpellChargeParticle (ClientLevel clientWorld, double x, double y, double z, SpellChargeParticleEffect effect, SpriteSet spriteProvider) {
        super(clientWorld, x, y, z, 0, 0, 0, spriteProvider.first());
        this.destX = x;
        this.destZ = z;
        this.quadSize = effect.scale();
        this.rCol = effect.colour().x();
        this.gCol = effect.colour().y();
        this.bCol = effect.colour().z();
        this.lifetime = 20;

        this.xd = this.random.nextDouble() * 0.05 - 0.025;
        this.yd = 0;
        this.zd = this.random.nextDouble() * 0.05 - 0.025;

        float randDeg = this.random.nextFloat() * 2f * Mth.PI;
        this.setPos(x - Mth.sin(randDeg) * 1.5, y + this.random.nextDouble() * 1.5, z + Mth.cos(randDeg) * 1.5);
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.randomAngle = this.random.nextBoolean() ? this.random.nextFloat() * 0.2f : -this.random.nextFloat() * 0.2f;
    }

    @Override
    public void tick () {
        super.tick();

        this.oRoll = this.roll;
        this.roll += this.randomAngle;

        if (this.age == 2) {
            this.setParticleSpeed((this.destX - this.x) * 0.05, this.yd, (this.destZ - this.z) * 0.05);
        }
        if (Mth.abs((float)this.x - (float)this.destX) < 0.1 && Mth.abs((float)this.z - (float)this.destZ) < 0.1) this.remove();
    }

    @Override
    protected int getLightColor (float tint) {
        return 0xF000F0;
    }

    @Override
    protected Layer getLayer () {
        return Layer.OPAQUE;
    }

    public static class Factory implements ParticleProvider<SpellChargeParticleEffect> {
        private final SpriteSet spriteProvider;

        public Factory (SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle (SpellChargeParticleEffect parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new SpellChargeParticle(world, x, y, z, parameters, this.spriteProvider);
        }
    }
}
