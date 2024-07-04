package com.provismet.AdditionalArmoury.mixin;

import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.Registerable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageTypes.class)
public interface DamageTypesMixin {
    @Inject(method="bootstrap", at=@At("TAIL"))
    private static void bootstrapMod (Registerable<DamageType> registerable, CallbackInfo info) {
        AADamageTypes.bootstrap(registerable);
    }
}
