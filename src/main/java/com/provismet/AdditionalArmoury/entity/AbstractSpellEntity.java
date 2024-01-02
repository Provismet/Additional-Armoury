package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractSpellEntity extends ThrownItemEntity {
    private final int maxAge;

    protected AbstractSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, World world) {
        super(entityType, world);
        this.maxAge = 10;
        this.setNoGravity(true);
    }

    public AbstractSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, World world, @NotNull LivingEntity owner, boolean pitchAim, boolean startOnFloor, int maxAge, float speed) {
        super(entityType, owner, world);
        this.setVelocity(-MathHelper.sin(owner.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN) * speed, pitchAim ? -MathHelper.sin(owner.getPitch() / MathHelper.DEGREES_PER_RADIAN) * speed : 0f, MathHelper.cos(owner.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN) * speed);
        this.setPosition(owner.getX(), startOnFloor ? owner.getY() + this.getHeight() : owner.getEyeY() - this.getHeight() / 2f, owner.getZ());
        this.setRotation(owner.getYaw(), pitchAim ? owner.getPitch() : 0f);
        this.maxAge = maxAge;
        this.setNoGravity(true);
    }

    @Override
    public void tick () {
        if (!this.getWorld().isClient() && this.age >= this.maxAge) {
            this.discard();
            return;
        }
        if (this.getParticleType() != null) {
            this.getWorld().addParticle(this.getParticleType(), this.getX(), this.getY() + this.getHeight() / 2f, this.getZ(), 0, 0, 0);
        }
        super.tick();
    }

    @Override
    protected void onCollision (HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient()) this.discard();
    }

    @Override
    public boolean isAttackable () {
        return false;
    }

    @Override
    public boolean damage (DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean canHit () {
        return false;
    }

    @Nullable
    protected ParticleEffect getParticleType () {
        return null;
    }
}
