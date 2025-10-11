package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class SpellChargeParticle extends BillboardParticle {
    private final double destX;
    private final double destZ;
    private final float randomAngle;

    protected SpellChargeParticle (ClientWorld clientWorld, double x, double y, double z, SpellChargeParticleEffect effect, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, 0, 0, 0, spriteProvider.getFirst());
        this.destX = x;
        this.destZ = z;
        this.scale = effect.scale();
        this.red = effect.colour().x;
        this.green = effect.colour().y;
        this.blue = effect.colour().z;
        this.maxAge = 20;

        this.velocityX = this.random.nextDouble() * 0.05 - 0.025;
        this.velocityY = 0;
        this.velocityZ = this.random.nextDouble() * 0.05 - 0.025;

        float randDeg = this.random.nextFloat() * 2f * MathHelper.PI;
        this.setPos(x - MathHelper.sin(randDeg) * 1.5, y + this.random.nextDouble() * 1.5, z + MathHelper.cos(randDeg) * 1.5);
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;
        this.randomAngle = this.random.nextBoolean() ? this.random.nextFloat() * 0.2f : -this.random.nextFloat() * 0.2f;
    }

    @Override
    public void tick () {
        super.tick();

        this.lastZRotation = this.zRotation;
        this.zRotation += this.randomAngle;

        if (this.age == 2) {
            this.setVelocity((this.destX - this.x) * 0.05, this.velocityY, (this.destZ - this.z) * 0.05);
        }
        if (MathHelper.abs((float)this.x - (float)this.destX) < 0.1 && MathHelper.abs((float)this.z - (float)this.destZ) < 0.1) this.markDead();
    }

    @Override
    protected int getBrightness (float tint) {
        return 0xF000F0;
    }

    @Override
    protected RenderType getRenderType () {
        return RenderType.PARTICLE_ATLAS_OPAQUE;
    }

    public static class Factory implements ParticleFactory<SpellChargeParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory (SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle (SpellChargeParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new SpellChargeParticle(world, x, y, z, parameters, this.spriteProvider);
        }
    }
}
