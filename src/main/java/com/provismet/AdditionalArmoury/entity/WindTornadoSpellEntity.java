package com.provismet.AdditionalArmoury.entity;

import org.jetbrains.annotations.NotNull;

import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AADamageSources;

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
        super(AAEntityTypes.WIND_TORNADO, world, owner, false, true, 75, SPEED);
    }

    public WindTornadoSpellEntity (World world, @NotNull LivingEntity owner, float aimOffset) {
        this(world, owner);
        this.setVelocity(-MathHelper.sin((owner.getHeadYaw() + aimOffset) / MathHelper.DEGREES_PER_RADIAN) * SPEED, 0f, MathHelper.cos((owner.getHeadYaw() + aimOffset) / MathHelper.DEGREES_PER_RADIAN) * SPEED);
    }
    
    @Override
    public void onEntityHit (EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        entityHitResult.getEntity().damage(AADamageSources.windTornado(this, this.getOwner()), 1f);
        
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
