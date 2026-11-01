package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.item.BoomerangItem;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.CombatPlusCore.registries.CombatPlusAttributes;
import com.provismet.lilylib.interfaces.entity.WorldItemEntity;
import com.provismet.lilylib.util.Relations;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class BoomerangProjectileEntity extends ThrowableItemProjectile implements WorldItemEntity {
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

    public BoomerangProjectileEntity (EntityType<? extends BoomerangProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    public BoomerangProjectileEntity (Level world, @NotNull LivingEntity owner, @NotNull ItemStack stack) {
        super(AAEntityTypes.BOOMERANG, owner, world, stack);
    }

    @Override
    protected void addAdditionalSaveData (ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt(RICOCHET_KEY, this.ricochetCount);
        view.putInt(FLIGHT_TIME_KEY, this.flightTime);
        view.putFloat(POWER_KEY, this.power);
        view.putBoolean(RESETS_COOLDOWN_KEY, this.resetsCooldown);
    }

    @Override
    protected void readAdditionalSaveData (ValueInput view) {
        super.readAdditionalSaveData(view);
        this.ricochetCount = view.getIntOr(RICOCHET_KEY, this.ricochetCount);
        this.flightTime = view.getIntOr(FLIGHT_TIME_KEY, this.flightTime);
        this.power = view.getFloatOr(POWER_KEY, this.power);
        this.resetsCooldown = view.getBooleanOr(RESETS_COOLDOWN_KEY, this.resetsCooldown);
    }
    
    @Override
    public void tick () {
        if (!this.level().isClientSide()) {
            if (this.ricochetCount > 0 && ++this.flightTime >= this.maxTime) {
                this.ricochet(false, true);
            }
            if (this.ricochetCounterCooldown > 0) --this.ricochetCounterCooldown;
        }
        super.tick();
        this.setYRot(0f);
        this.yRotO = 0f;
        this.setXRot(0f);
        this.xRotO = 0f;
    }

    @Override
    public void shoot (double x, double y, double z, float speed, float divergence) {
        super.shoot(x, y, z, speed, divergence);
        this.setYRot(0f);
        this.setXRot(0f);
    }

    @Override
    protected void onHitBlock (BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide()) {
            if (this.ricochetCount <= 0) this.discard();
            else this.ricochetBlock(blockHitResult.getDirection());
        }
    }

    @Override
    protected void onHitEntity (EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level() instanceof ServerLevel world) {
            if (this.getOwner() instanceof Player player && entityHitResult.getEntity() == player) {
                if (this.resetsCooldown) player.getCooldowns().removeCooldown(player.getCooldowns().getCooldownGroup(AAItems.BOOMERANG.getDefaultInstance()));
                this.discard();
            }
            else if (!(entityHitResult.getEntity() instanceof Projectile)) {
                if (entityHitResult.getEntity() instanceof LivingEntity target) {
                    DamageSource damageSource = AADamageTypes.BOOMERANG.createDamageSource(this, this.getOwner());
                    float damage = EnchantmentHelper.modifyDamage(world, this.getItem(), target, damageSource, this.getPower());
                    target.hurtServer(world, damageSource, damage);
                    this.applyOnHitEffects(target);
                    EnchantmentHelper.doPostAttackEffectsWithItemSource(world, target, damageSource, this.getItem());
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

        if ((this.ricochetCount == 0 || returnToUser) && this.getOwner() != null && !this.isReturning) {
            this.isReturning = true;
            Entity owner = this.getOwner();
            this.shoot(owner.getX() - this.getX(), owner.getEyeY() - this.getY(), owner.getZ() - this.getZ(), 1f, 0.5f);
            return true;
        }
        
        List<Entity> potentialTargets = this.level().getEntities(this, this.getBoundingBox().inflate(5), entity -> {
            if (entity instanceof LivingEntity target) {
                if (target == this.getOwner() || target == this.previousHit || target.isDeadOrDying()) return false;
                else if (this.getOwner() instanceof LivingEntity livingOwner) return !Relations.isFriendly(livingOwner, target);
                else return target instanceof Monster || target instanceof Player;
            }
            return false;
        });
        
        if (!potentialTargets.isEmpty()) {
            Entity target = potentialTargets.get(this.random.nextIntBetweenInclusive(0, potentialTargets.size() - 1));
            this.shoot(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), 1f, 0.5f);
            this.isReturning = false;
            return true;
        }

        if (doFallback) {
            this.shoot(-this.getDeltaMovement().x(), -this.getDeltaMovement().y(), -this.getDeltaMovement().z(), 1f, 1f);
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
                    this.shoot(this.getDeltaMovement().x(), this.getDeltaMovement().y() * -1, this.getDeltaMovement().z(), 1f, 0f);
                    break;
                
                case NORTH:
                case SOUTH:
                    this.shoot(this.getDeltaMovement().x(), this.getDeltaMovement().y(), this.getDeltaMovement().z() * -1, 1f, 0f);
                    break;

                case EAST:
                case WEST:
                    this.shoot(this.getDeltaMovement().x() * -1, this.getDeltaMovement().y(), this.getDeltaMovement().z(), 1f, 0f);

                default:
                    break;
            }

            this.isReturning = false;
        }
    }

    protected void applyOnHitEffects (LivingEntity target) {
        if (this.getItem().getItem() instanceof BoomerangItem boomerang && this.getOwner() instanceof LivingEntity livingOwner) {
            boomerang.applyOnHit(livingOwner, target);
        }
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.BOOMERANG;
    }

    @Override
    protected double getDefaultGravity () {
        return 0.005;
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
        if (this.getOwner() instanceof LivingEntity living) {
            return this.power + (float)living.getAttributeValue(CombatPlusAttributes.PROJECTILE_DAMAGE);
        }
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
        float currentRotation = this.tickCount * 33f;
        float nextRotation = (this.tickCount + 1) * 33f;
        return Mth.lerp(tickDelta, currentRotation, nextRotation) % 360f;
    }

    @Override
    public float getXOffset (float tickDelta) {
        return -Mth.cos((this.getZRotation(tickDelta) - 15f) / Mth.RAD_TO_DEG) * 0.25f;
    }

    @Override
    public float getYOffset (float tickDelta) {
        return this.getBbHeight() / 2f;
    }

    @Override
    public float getZOffset (float tickDelta) {
        return -Mth.sin((this.getZRotation(tickDelta) - 15f) / Mth.RAD_TO_DEG) * 0.25f;
    }
}
