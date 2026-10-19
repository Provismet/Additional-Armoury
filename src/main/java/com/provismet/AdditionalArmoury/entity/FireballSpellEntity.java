package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class FireballSpellEntity extends AbstractSpellEntity {
    public FireballSpellEntity (EntityType<? extends FireballSpellEntity> entityType, Level world) {
        super(entityType, world);
    }

    public FireballSpellEntity (Level world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.FIREBALL, world, owner, AAItems.FIREBALL.getDefaultInstance(), true, false, 50, 0.85f);
    }

    @Override
    protected void onHitEntity (EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level() instanceof ServerLevel world) {

            Entity target = entityHitResult.getEntity();
            Entity owner = this.getOwner();

            if (target.hurtServer(world, AADamageTypes.FIREBALL.createDamageSource(this, owner), 6f)) {
                target.igniteForSeconds(5);
            }
        }
    }

    @Override
    protected void onHitBlock (BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (this.level().isClientSide()) return;

        BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
        if (this.level().isEmptyBlock(blockPos)) {
            this.level().setBlockAndUpdate(blockPos, BaseFireBlock.getState(this.level(), blockPos));
        }
    }

    @Override
    public void tick () {
        if (!this.level().isClientSide() && this.isInWater()) {
            this.discard();
            return;
        }
        super.tick();
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.FIREBALL;
    }

    @Override
    protected ParticleOptions getParticleType () {
        return ParticleTypes.SMALL_FLAME;
    }
}
