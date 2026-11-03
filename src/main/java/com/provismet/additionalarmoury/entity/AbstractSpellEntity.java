package com.provismet.additionalarmoury.entity;

import com.provismet.combatplus.registries.CombatPlusAttributes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSpellEntity extends ThrowableItemProjectile {
    private final int maxAge;

    protected AbstractSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, Level world) {
        super(entityType, world);
        this.maxAge = 10;
        this.setNoGravity(true);
    }

    public AbstractSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, Level world, @NotNull LivingEntity owner, @NotNull ItemStack stack, boolean pitchAim, boolean startOnFloor, int maxAge, float speed) {
        super(entityType, owner, world, stack);

        if (pitchAim) {
            this.shootFromRotation(owner, owner.getXRot(), owner.getYRot(), 0f, speed, 0f);
        }
        else {
            this.setDeltaMovement(-Mth.sin(owner.getYRot() / Mth.RAD_TO_DEG) * speed, 0f, Mth.cos(owner.getYRot() / Mth.RAD_TO_DEG) * speed);
        }
        this.setPos(owner.getX(), startOnFloor ? owner.getY() + 0.05f : owner.getEyeY() - this.getBbHeight() / 2f, owner.getZ());
        this.setRot(owner.getYRot(), pitchAim ? owner.getXRot() : 0f);
        this.maxAge = maxAge;
        this.setNoGravity(true);
    }

    @Override
    public void tick () {
        if (!this.level().isClientSide() && this.tickCount >= this.maxAge) {
            this.discard();
            return;
        }
        if (this.getParticleType() != null) {
            this.level().addParticle(this.getParticleType(), this.getX(), this.getY() + this.getBbHeight() / 2f, this.getZ(), 0, 0, 0);
        }
        super.tick();
    }

    protected float getBasePower () {
        return 0f;
    }

    protected final float getPower () {
        if (this.getOwner() instanceof LivingEntity living) {
            return this.getBasePower() + (float)living.getAttributeValue(CombatPlusAttributes.MAGIC_DAMAGE);
        }
        return this.getBasePower();
    }

    @Override
    protected void onHit (HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) this.discard();
    }

    @Override
    public boolean isAttackable () {
        return false;
    }

    @Override
    public boolean hurtServer (ServerLevel world, DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isPickable () {
        return false;
    }

    @Nullable
    protected ParticleOptions getParticleType () {
        return null;
    }
}
