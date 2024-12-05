package com.provismet.AdditionalArmoury.mixin;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.render.DaggerTintSource;
import com.provismet.AdditionalArmoury.items.render.SpellTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TintSourceTypes.class)
public abstract class TintSourceTypesMixin {
    @Shadow @Final public static Codecs.IdMapper<Identifier, MapCodec<? extends TintSource>> ID_MAPPER;

    @Inject(method="bootstrap", at=@At("TAIL"))
    private static void extraBootstrap (CallbackInfo info) {
        add("spell", SpellTintSource.CODEC);
        add("dagger", DaggerTintSource.CODEC);
    }

    @Unique
    private static void add (String name, MapCodec<? extends TintSource> codec) {
        ID_MAPPER.put(AdditionalArmouryMain.identifier(name), codec);
    }
}
