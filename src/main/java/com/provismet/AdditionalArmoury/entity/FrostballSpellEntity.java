package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class FrostballSpellEntity extends AbstractSpellEntity {
    public FrostballSpellEntity (EntityType<? extends AbstractSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public FrostballSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.FROSTBALL, world, owner, AAItems.FROSTBALL.getDefaultStack(), true, false, 50, 0.85f);
    }

    @Override
    public ItemStack getStack () {
        return AAItems.FROSTBALL.getDefaultStack();
    }

    @Override
    public void tick () {
        super.tick();
        if (!this.getEntityWorld().isClient()) {
            FluidState fluidState = this.getEntityWorld().getFluidState(this.getBlockPos());
            if (fluidState.isIn(FluidTags.WATER) && fluidState.isStill()) {
                this.getEntityWorld().setBlockState(this.getBlockPos(), Blocks.ICE.getDefaultState());
                for (Direction direction : Direction.values()) {
                    BlockPos newPosition = this.getBlockPos().offset(direction);
                    FluidState adjacentState = this.getEntityWorld().getFluidState(newPosition);
                    if (adjacentState.isIn(FluidTags.WATER) && adjacentState.isStill()) this.getEntityWorld().setBlockState(newPosition, Blocks.ICE.getDefaultState());
                }
                this.discard();
            }
        }
    }

    @Override
    protected void onEntityHit (EntityHitResult hitResult) {
        super.onEntityHit(hitResult);
        if (this.getEntityWorld() instanceof ServerWorld world) {
            Entity target = hitResult.getEntity();
            Entity owner = this.getOwner();

            if (target.damage(world, AADamageTypes.FIREBALL.createDamageSource(this, owner), 6f)) {
                target.setFrozenTicks(Math.min(target.getFrozenTicks() + 120, target.getMinFreezeDamageTicks() + 300));
            }
        }
    }

    @Override
    protected void onBlockHit (BlockHitResult hitResult) {
        super.onBlockHit(hitResult);
        if (!this.getEntityWorld().isClient() && hitResult.getSide() == Direction.UP) {
            BlockPos position = hitResult.getBlockPos().offset(Direction.UP);
            if (this.getEntityWorld().isAir(position)) this.getEntityWorld().setBlockState(position, Blocks.SNOW.getDefaultState());
        }
    }

    @Override
    protected ParticleEffect getParticleType () {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.FIREBALL;
    }
}
