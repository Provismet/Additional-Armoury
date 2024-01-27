package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;
import com.provismet.AdditionalArmoury.utility.Util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MissileSpellEntity extends AbstractSpellEntity {
    private static final float SPEED = 0.85f;

    public MissileSpellEntity (EntityType<? extends MissileSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public MissileSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.MAGIC_MISSILE, world, owner, true, false, 50, SPEED);
    }

    @Override
    public void tick () {
        if (this.getOwner() instanceof LivingEntity owner) {
            LivingEntity target = this.getWorld().getClosestEntity(
                LivingEntity.class,
                TargetPredicate.createAttackable().ignoreVisibility().setPredicate(entity -> !Util.isFriendly(entity, owner)),
                owner,
                this.getX(), this.getY(), this.getZ(),
                this.getBoundingBox().expand(3.0)
            );

            if (target != null) 
                this.setVelocity(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), SPEED, 0f);
        }
        super.tick();
    }
    
    @Override
    public void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(AADamageSources.magicMissile(this, this.getOwner()), 6f);
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.MAGIC_MISSILE;
    }
}
