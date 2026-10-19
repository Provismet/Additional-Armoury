package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class FrostballSpellEntity extends AbstractSpellEntity {
    public FrostballSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, Level world) {
        super(entityType, world);
    }

    public FrostballSpellEntity (Level world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.FROSTBALL, world, owner, AAItems.FROSTBALL.getDefaultInstance(), true, false, 50, 0.85f);
    }

    @Override
    public ItemStack getItem () {
        return AAItems.FROSTBALL.getDefaultInstance();
    }

    @Override
    public void tick () {
        super.tick();
        if (!this.level().isClientSide()) {
            FluidState fluidState = this.level().getFluidState(this.blockPosition());
            if (fluidState.is(FluidTags.WATER) && fluidState.isSource()) {
                this.level().setBlockAndUpdate(this.blockPosition(), Blocks.ICE.defaultBlockState());
                for (Direction direction : Direction.values()) {
                    BlockPos newPosition = this.blockPosition().relative(direction);
                    FluidState adjacentState = this.level().getFluidState(newPosition);
                    if (adjacentState.is(FluidTags.WATER) && adjacentState.isSource()) this.level().setBlockAndUpdate(newPosition, Blocks.ICE.defaultBlockState());
                }
                this.discard();
            }
        }
    }

    @Override
    protected void onHitEntity (EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (this.level() instanceof ServerLevel world) {
            Entity target = hitResult.getEntity();
            Entity owner = this.getOwner();

            if (target.hurtServer(world, AADamageTypes.FIREBALL.createDamageSource(this, owner), 6f)) {
                target.setTicksFrozen(Math.min(target.getTicksFrozen() + 120, target.getTicksRequiredToFreeze() + 300));
            }
        }
    }

    @Override
    protected void onHitBlock (BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        if (!this.level().isClientSide() && hitResult.getDirection() == Direction.UP) {
            BlockPos position = hitResult.getBlockPos().relative(Direction.UP);
            if (this.level().isEmptyBlock(position)) this.level().setBlockAndUpdate(position, Blocks.SNOW.defaultBlockState());
        }
    }

    @Override
    protected ParticleOptions getParticleType () {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.FIREBALL;
    }
}
