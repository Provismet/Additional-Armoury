package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class GhostlySpellEntity extends AbstractSpellEntity {

    public GhostlySpellEntity (EntityType<? extends GhostlySpellEntity> entityType, Level world) {
        super(entityType, world);
    }

    public GhostlySpellEntity (Level world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.GHOSTLY_ORB, world, owner, AAItems.GHOSTLY_ORB.getDefaultInstance(), true, false, 75, 0.8f);
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.GHOSTLY_ORB;
    }

    @Override
    public void tick () {
        Vec3 truePosition = this.position().add(this.getDeltaMovement());
        super.tick();
        this.setPos(truePosition);
    }

    @Override
    protected float getBasePower () {
        return 6f;
    }

    @Override
    public void onHit (HitResult hitResult) {
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)hitResult);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, hitResult.getLocation(), GameEvent.Context.of(this, null));
            this.discard();
        }
    }

    @Override
    public void onHitEntity (EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level() instanceof ServerLevel world) {
            entityHitResult.getEntity().hurtServer(world, AADamageTypes.GHOSTLY_ORB.createDamageSource(this, this.getOwner()), this.getPower());
        }
    }

    @Override
    public void onHitBlock (BlockHitResult blockHitResult) {

    }

    @Override
    protected boolean isAffectedByBlocks () {
        return false;
    }

    @Override
    public boolean isInWater () {
        return false;
    }
}
