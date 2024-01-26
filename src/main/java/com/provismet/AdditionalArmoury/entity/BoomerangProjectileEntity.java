package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

public class BoomerangProjectileEntity extends ThrownItemEntity {
    private static final String RICOCHET_KEY = "ricochet_count";
    private static final String FLIGHT_TIME_KEY = "flight_time";
    private static final String RESETS_COOLDOWN_KEY = "resets_cooldown";

    protected final int maxTime;
    protected int flightTime;
    protected int ricochetCount;

    private int ricochetCounterCooldown = 0;
    private boolean resetsCooldown = true;

    public BoomerangProjectileEntity (EntityType<? extends BoomerangProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.maxTime = 40;
        this.flightTime = 0;
        this.ricochetCount = 1;
    }

    public BoomerangProjectileEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.BOOMERANG, owner, world);
        this.maxTime = 40;
        this.flightTime = 0;
        this.ricochetCount = 1;
    }

    @Override
    public void writeCustomDataToNbt (NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(RICOCHET_KEY, this.ricochetCount);
        nbt.putInt(FLIGHT_TIME_KEY, this.flightTime);
        nbt.putBoolean(RESETS_COOLDOWN_KEY, this.resetsCooldown);
    }

    @Override
    public void readCustomDataFromNbt (NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(RICOCHET_KEY)) this.ricochetCount = nbt.getInt(RICOCHET_KEY);
        if (nbt.contains(FLIGHT_TIME_KEY)) this.flightTime = nbt.getInt(FLIGHT_TIME_KEY);
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
                if (this.ricochetCount > 0) this.ricochet();
                if (entityHitResult.getEntity() instanceof LivingEntity target) {
                    target.damage(AADamageSources.boomerang(this, this.getOwner()), 4f);
                }
            }
        }
    }

    protected boolean ricochet (boolean doFallback, boolean returnToUser) {
        if (this.ricochetCounterCooldown <= 0) {
            this.ricochetCounterCooldown = 2;
            --this.ricochetCount;
        }
        this.flightTime = 0;

        if ((this.ricochetCount <= 0 || returnToUser) && this.getOwner() != null) {
            this.ricochetCount = 0;
            Entity owner = this.getOwner();
            this.setVelocity(owner.getX() - this.getX(), owner.getEyeY() - this.getY(), owner.getZ() - this.getZ(), 1f, 0.5f);
            AdditionalArmouryMain.LOGGER.info("Ricochet Count: " + this.ricochetCount + " (player ricochet)");
            return true;
        }
        else if (doFallback) {
            this.setVelocity(-this.getVelocity().getX(), -this.getVelocity().getY(), -this.getVelocity().getZ(), 1f, 1f);
            AdditionalArmouryMain.LOGGER.info("Ricochet Count: " + this.ricochetCount + " (used fallback)");
            return true;
        }
        
        AdditionalArmouryMain.LOGGER.info("Ricochet Count: " + this.ricochetCount);
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
        }
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
}
