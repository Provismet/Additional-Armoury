package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireballSpellEntity extends AbstractSpellEntity {
    public FireballSpellEntity (EntityType<? extends FireballSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireballSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.FIREBALL, world, owner, true, false, 50, 0.75f);
    }

    @Override
    protected void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient()) return;

        Entity target = entityHitResult.getEntity();
        Entity owner = this.getOwner();
        
        if (target.damage(AADamageSources.fireball(this, owner), 5f)) {
            target.setOnFireFor(5);
        }
    }

    @Override
    protected void onBlockHit (BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (this.getWorld().isClient()) return;

        BlockPos blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
        if (this.getWorld().isAir(blockPos)) {
            this.getWorld().setBlockState(blockPos, AbstractFireBlock.getState(this.getWorld(), blockPos));
        }
    }

    @Override
    public void tick () {
        if (!this.getWorld().isClient() && this.isTouchingWater()) {
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
