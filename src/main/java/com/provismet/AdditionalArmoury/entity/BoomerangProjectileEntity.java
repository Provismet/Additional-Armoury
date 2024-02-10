package com.provismet.AdditionalArmoury.entity;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.lilylib.interfaces.entity.WorldItemEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BoomerangProjectileEntity extends ThrownItemEntity implements WorldItemEntity {
    private static final String RICOCHET_KEY = "ricochet_count";
    private static final String FLIGHT_TIME_KEY = "flight_time";
    private static final String POWER_KEY = "power";
    private static final String RESETS_COOLDOWN_KEY = "resets_cooldown";

    protected int maxTime = 15;
    protected int flightTime = 0;
    protected int ricochetCount = 1;
    protected float power = 4f;
    protected boolean isReturning = false;

    private int ricochetCounterCooldown = 0;
    private boolean resetsCooldown = true;
    private LivingEntity previousHit = null;

    public BoomerangProjectileEntity (EntityType<? extends BoomerangProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BoomerangProjectileEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.BOOMERANG, owner, world);
    }

    @Override
    public void writeCustomDataToNbt (NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(RICOCHET_KEY, this.ricochetCount);
        nbt.putInt(FLIGHT_TIME_KEY, this.flightTime);
        nbt.putFloat(POWER_KEY, this.power);
        nbt.putBoolean(RESETS_COOLDOWN_KEY, this.resetsCooldown);
    }

    @Override
    public void readCustomDataFromNbt (NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(RICOCHET_KEY)) this.ricochetCount = nbt.getInt(RICOCHET_KEY);
        if (nbt.contains(FLIGHT_TIME_KEY)) this.flightTime = nbt.getInt(FLIGHT_TIME_KEY);
        if (nbt.contains(POWER_KEY)) this.power = nbt.getFloat(POWER_KEY);
        if (nbt.contains(RESETS_COOLDOWN_KEY)) this.resetsCooldown = nbt.getBoolean(RESETS_COOLDOWN_KEY);
    }
    
    @Override
    public void tick () {
        if (!this.getWorld().isClient()) {
            if (this.ricochetCount > 0 && ++this.flightTime >= this.maxTime) {
                this.ricochet(false, true);
            }
            if (this.ricochetCounterCooldown > 0) --this.ricochetCounterCooldown;
        }
        super.tick();
    }

    @Override
    protected void onBlockHit (BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient()) {
            if (this.ricochetCount <= 0) this.discard();
            else this.ricochetBlock(blockHitResult.getSide());
        }
    }

    @Override
    protected void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getWorld().isClient()) {
            if (this.getOwner() instanceof PlayerEntity player && entityHitResult.getEntity() == player) {
                if (this.resetsCooldown) player.getItemCooldownManager().remove(AAItems.BOOMERANG);
                this.discard();
            }
            else if (!(entityHitResult.getEntity() instanceof ProjectileEntity)) {
                if (entityHitResult.getEntity() instanceof LivingEntity target) {
                    target.damage(AADamageSources.boomerang(this, this.getOwner()), this.power);
                    this.applyOnHitEffects(target);
                    if (target.isAlive()) this.previousHit = target;
                    else this.previousHit = null;
                }
                if (this.ricochetCount > 0) this.ricochet();
            }
        }
    }

    protected boolean ricochet (boolean doFallback, boolean returnToUser) {
        if (this.ricochetCounterCooldown <= 0) {
            this.ricochetCounterCooldown = 2;
            --this.ricochetCount;
        }
        this.flightTime = 0;
        if (this.ricochetCount < 0) this.ricochetCount = 0;

        if ((this.ricochetCount <= 0 || returnToUser) && this.getOwner() != null && !this.isReturning) {
            this.isReturning = true;
            Entity owner = this.getOwner();
            this.setVelocity(owner.getX() - this.getX(), owner.getEyeY() - this.getY(), owner.getZ() - this.getZ(), 1f, 0.5f);
            return true;
        }
        
        List<Entity> potentialTargets = this.getWorld().getOtherEntities(this, this.getBoundingBox().expand(5), entity -> {
            if (entity instanceof LivingEntity target) {
                if (target == this.getOwner() || target == this.previousHit || target.isDead()) return false;
                else if (this.getOwner() instanceof LivingEntity owner) return !Util.isFriendly(owner, target);
                else return target instanceof HostileEntity || target instanceof PlayerEntity;
            }
            return false;
        });
        
        if (!potentialTargets.isEmpty()) {
            Entity target = potentialTargets.get(this.random.nextBetween(0, potentialTargets.size() - 1));
            this.setVelocity(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), 1f, 0.5f);
            this.isReturning = false;
            return true;
        }

        if (doFallback) {
            this.setVelocity(-this.getVelocity().getX(), -this.getVelocity().getY(), -this.getVelocity().getZ(), 1f, 1f);
            this.isReturning = false;
            return true;
        }
        
        return false;
    }

    protected boolean ricochet () {
        return this.ricochet(true, false);
    }

    protected void ricochetBlock (Direction direction) {
        if (!this.ricochet(false, false)) {
            switch (direction) {
                case UP:
                case DOWN:
                    this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY() * -1, this.getVelocity().getZ(), 1f, 0f);
                    break;
                
                case NORTH:
                case SOUTH:
                    this.setVelocity(this.getVelocity().getX(), this.getVelocity().getY(), this.getVelocity().getZ() * -1, 1f, 0f);
                    break;

                case EAST:
                case WEST:
                    this.setVelocity(this.getVelocity().getX() * -1, this.getVelocity().getY(), this.getVelocity().getZ(), 1f, 0f);

                default:
                    break;
            }

            this.isReturning = false;
        }
    }

    protected void applyOnHitEffects (LivingEntity target) {

    }

    @Override
    public ItemStack getStack () {
        return AAItems.BOOMERANG.getDefaultStack();
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.BOOMERANG;
    }

    @Override
    protected float getGravity () {
        return 0.005f;
    }

    public void setRicochetCount (int value) {
        this.ricochetCount = value;
    }

    public void setCanResetCooldown (boolean value) {
        this.resetsCooldown = value;
    }

    public int getMaxFlightTime () {
        return this.maxTime;
    }

    public void setMaxFlightTime (int value) {
        this.maxTime = value;
    }

    public float getPower () {
        return this.power;
    }

    public void setPower (float value) {
        this.power = value;
    }

    @Override
    public float getXRotation (float tickDelta) {
        return 90f;
    }

    @Override
    public float getZRotation (float tickDelta) {
        return (this.age * 33f) % 360f;
    }

    @Override
    public float getYOffset (float tickDelta) {
        return this.getHeight() / 2f;
    }
}
