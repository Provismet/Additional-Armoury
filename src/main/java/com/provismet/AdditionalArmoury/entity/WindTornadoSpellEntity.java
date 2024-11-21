package com.provismet.AdditionalArmoury.entity;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WindTornadoSpellEntity extends AbstractSpellEntity {
    private static final float SPEED = 0.75f;

    public WindTornadoSpellEntity (EntityType<? extends WindTornadoSpellEntity> entityType, World world) {
        super(entityType, world);
    }

    public WindTornadoSpellEntity (World world, @NotNull LivingEntity owner) {
        super(AAEntityTypes.WIND_TORNADO, world, owner, AAItems.WIND_TORNADO.getDefaultStack(), false, true, 75, SPEED);
    }

    public WindTornadoSpellEntity (World world, @NotNull LivingEntity owner, float aimOffset) {
        this(world, owner);
        this.setVelocity(-MathHelper.sin((owner.getHeadYaw() + aimOffset) / MathHelper.DEGREES_PER_RADIAN) * SPEED, 0f, MathHelper.cos((owner.getHeadYaw() + aimOffset) / MathHelper.DEGREES_PER_RADIAN) * SPEED);
    }
    
    @Override
    public void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        if (this.getWorld() instanceof ServerWorld world) {
            entityHitResult.getEntity().damage(world, AADamageTypes.WIND_TORNADO.createDamageSource(this, this.getOwner()), 1f);
            world.spawnParticles(ParticleTypes.GUST, this.getX(), this.getY() + this.getHeight() / 2f, this.getZ(), 1, 0, 0, 0, 0);
        }
        
        if (entityHitResult.getEntity() instanceof LivingEntity living && this.getOwner() != null) {
            double dx = this.getOwner().getX() - living.getX();
            double dz = this.getOwner().getZ() - living.getZ();
            living.takeKnockback(2.0, dx, dz);
            living.addVelocity(0, 0.1, 0);
        }
    }

    @Override
    protected Item getDefaultItem () {
        return AAItems.WIND_TORNADO;
    }
}
