package com.provismet.AdditionalArmoury.entity;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class WindTornadoSpellEntity extends AbstractSpellEntity {
    private static final float SPEED = 0.75f;

    public WindTornadoSpellEntity (EntityType<? extends WindTornadoSpellEntity> entityType, Level world) {
        super(entityType, world);
    }

    public WindTornadoSpellEntity (Level world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.WIND_TORNADO, world, owner, AAItems.WIND_TORNADO.getDefaultInstance(), false, true, 75, SPEED);
    }

    public WindTornadoSpellEntity (Level world, @NotNull LivingEntity owner, float aimOffset) {
        this(world, owner);
        this.setDeltaMovement(-Mth.sin((owner.getYHeadRot() + aimOffset) / Mth.RAD_TO_DEG) * SPEED, 0f, Mth.cos((owner.getYHeadRot() + aimOffset) / Mth.RAD_TO_DEG) * SPEED);
    }

    @Override
    protected float getBasePower () {
        return 1f;
    }

    @Override
    public void onHitEntity (EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        if (this.level() instanceof ServerLevel world) {
            entityHitResult.getEntity().hurtServer(world, AADamageTypes.WIND_TORNADO.createDamageSource(this, this.getOwner()), this.getPower());
            world.sendParticles(ParticleTypes.GUST, this.getX(), this.getY() + this.getBbHeight() / 2f, this.getZ(), 1, 0, 0, 0, 0);
        }
        
        if (entityHitResult.getEntity() instanceof LivingEntity living && this.getOwner() != null) {
            double dx = this.getOwner().getX() - living.getX();
            double dz = this.getOwner().getZ() - living.getZ();
            living.knockback(2.0, dx, dz);
            living.push(0, 0.1, 0);
        }
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.WIND_TORNADO;
    }
}
