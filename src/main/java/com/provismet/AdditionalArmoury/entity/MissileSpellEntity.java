package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.lilylib.util.Relations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MissileSpellEntity extends AbstractSpellEntity {
    private static final float SPEED = 0.85f;

    public MissileSpellEntity (EntityType<? extends MissileSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public MissileSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.MAGIC_MISSILE, world, owner, AAItems.MAGIC_MISSILE.getDefaultStack(), true, false, 50, SPEED);
    }

    @Override
    public void tick () {
        if (this.getOwner() instanceof LivingEntity livingOwner) {
            Optional<Entity> optionalTarget = this.getEntityWorld().getOtherEntities(
                this,
                this.getBoundingBox().expand(3.0),
                entity -> entity instanceof LivingEntity livingTarget && livingTarget.canTakeDamage() && !Relations.isFriendly(livingOwner, livingTarget)
            ).stream().reduce((entity1, entity2) -> entity1.distanceTo(this) < entity2.distanceTo(this) ? entity1 : entity2);

            if (optionalTarget.isPresent()) {
                Entity target = optionalTarget.get();
                this.setVelocity(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), SPEED, 0f);
            }
        }
        super.tick();
    }
    
    @Override
    public void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getEntityWorld() instanceof ServerWorld world) {
            entityHitResult.getEntity().damage(world, AADamageTypes.WIND_TORNADO.createDamageSource(this, this.getOwner()), 6f);
        }
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.MAGIC_MISSILE;
    }
}
