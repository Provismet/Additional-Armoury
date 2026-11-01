package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.lilylib.util.Relations;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class MissileSpellEntity extends AbstractSpellEntity {
    private static final float SPEED = 0.85f;

    public MissileSpellEntity (EntityType<? extends MissileSpellEntity> entityType, Level world) {
        super(entityType, world);
    }

    public MissileSpellEntity (Level world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.MAGIC_MISSILE, world, owner, AAItems.MAGIC_MISSILE.getDefaultInstance(), true, false, 50, SPEED);
    }

    @Override
    public void tick () {
        if (this.getOwner() instanceof LivingEntity livingOwner) {
            Optional<Entity> optionalTarget = this.level().getEntities(
                this,
                this.getBoundingBox().inflate(3.0),
                entity -> entity instanceof LivingEntity livingTarget && livingTarget.canBeSeenAsEnemy() && !Relations.isFriendly(livingOwner, livingTarget)
            ).stream().reduce((entity1, entity2) -> entity1.distanceTo(this) < entity2.distanceTo(this) ? entity1 : entity2);

            if (optionalTarget.isPresent()) {
                Entity target = optionalTarget.get();
                this.shoot(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), SPEED, 0f);
            }
        }
        super.tick();
    }

    @Override
    protected float getBasePower () {
        return 6f;
    }

    @Override
    public void onHitEntity (EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level() instanceof ServerLevel world) {
            entityHitResult.getEntity().hurtServer(world, AADamageTypes.WIND_TORNADO.createDamageSource(this, this.getOwner()), this.getPower());
        }
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.MAGIC_MISSILE;
    }
}
