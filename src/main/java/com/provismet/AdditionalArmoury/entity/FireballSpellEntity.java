package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class FireballSpellEntity extends AbstractSpellEntity {
    public FireballSpellEntity (EntityType<? extends FireballSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireballSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.FIREBALL, world, owner, AAItems.FIREBALL.getDefaultStack(), true, false, 50, 0.85f);
    }

    @Override
    protected void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getEntityWorld() instanceof ServerWorld world) {

            Entity target = entityHitResult.getEntity();
            Entity owner = this.getOwner();

            if (target.damage(world, AADamageTypes.FIREBALL.createDamageSource(this, owner), 6f)) {
                target.setOnFireFor(5);
            }
        }
    }

    @Override
    protected void onBlockHit (BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (this.getEntityWorld().isClient()) return;

        BlockPos blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
        if (this.getEntityWorld().isAir(blockPos)) {
            this.getEntityWorld().setBlockState(blockPos, AbstractFireBlock.getState(this.getEntityWorld(), blockPos));
        }
    }

    @Override
    public void tick () {
        if (!this.getEntityWorld().isClient() && this.isTouchingWater()) {
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
    protected ParticleEffect getParticleType () {
        return ParticleTypes.SMALL_FLAME;
    }
}
