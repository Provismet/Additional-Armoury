package com.provismet.AdditionalArmoury.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin (EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @ModifyVariable(method="attack", at=@At("STORE"), ordinal=0)
    private int applyKnockback (int value) {
        return value + (int)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
    }

    @Inject(method="tick", at=@At("HEAD"))
    public void resetMaxHealth (CallbackInfo info) {
        if (this.getHealth() > this.getMaxHealth()) this.setHealth(this.getMaxHealth());
    }

    @Inject(method="createPlayerAttributes", at=@At("RETURN"), allow=1, require=1)
    private static void addKnockback (CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
    }
}
