package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class GhostlySpellEntity extends AbstractSpellEntity {

    public GhostlySpellEntity (EntityType<? extends GhostlySpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public GhostlySpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.GHOSTLY_ORB, world, owner, true, false, 75, 0.8f);
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.GHOSTLY_ORB;
    }

    @Override
    public void onCollision (HitResult hitResult) {
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)hitResult);
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));
            this.discard();
        }
    }

    @Override
    public void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient()) return;

        entityHitResult.getEntity().damage(AADamageSources.ghostlyOrb(this, this.getOwner()), 5f);
    }

    @Override
    public void onBlockHit (BlockHitResult blockHitResult) {

    }
}
