package com.provismet.AdditionalArmoury.particles;

import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;

public class SpellChargeParticle extends SpriteBillboardParticle {
    private final double destX;
    private final double destZ;
    private final float randomAngle;

    protected SpellChargeParticle (ClientWorld clientWorld, double x, double y, double z, SpellChargeParticleEffect effect) {
        super(clientWorld, x, y, z, 0, 0, 0);
        this.destX = x;
        this.destZ = z;
        this.scale = effect.getScale();
        this.red = effect.getColor().x;
        this.green = effect.getColor().y;
        this.blue = effect.getColor().z;
        this.maxAge = 20;

        this.velocityX = this.random.nextDouble() * 0.05 - 0.025;
        this.velocityY = 0;
        this.velocityZ = this.random.nextDouble() * 0.05 - 0.025;

        float randDeg = this.random.nextFloat() * 2f * MathHelper.PI;
        this.setPos(x - MathHelper.sin(randDeg) * 1.5, y + this.random.nextDouble() * 1.5, z + MathHelper.cos(randDeg) * 1.5);
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.randomAngle = this.random.nextBoolean() ? this.random.nextFloat() * 0.2f : -this.random.nextFloat() * 0.2f;
    }

    @Override
    public void tick () {
        super.tick();

        this.prevAngle = this.angle;
        this.angle += this.randomAngle;

        if (this.age == 2) {
            this.setVelocity((this.destX - this.x) * 0.05, this.velocityY, (this.destZ - this.z) * 0.05);
        }
        if (MathHelper.abs((float)this.x - (float)this.destX) < 0.1 && MathHelper.abs((float)this.z - (float)this.destZ) < 0.1) this.markDead();
    }

    @Override
    public ParticleTextureSheet getType () {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }
    
    public static class Factory implements ParticleFactory<SpellChargeParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory (SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle (SpellChargeParticleEffect particleEffect, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
            SpellChargeParticle particle = new SpellChargeParticle(clientWorld, x, y, z, particleEffect);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    
    }
}
