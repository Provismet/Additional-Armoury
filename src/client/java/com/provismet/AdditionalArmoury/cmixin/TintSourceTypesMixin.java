package com.provismet.AdditionalArmoury.cmixin;

import com.mojang.serialization.MapCodec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.item.render.DaggerTintSource;
import com.provismet.AdditionalArmoury.item.render.SpellTintSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemTintSources.class)
public abstract class TintSourceTypesMixin {
    @Shadow @Final public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemTintSource>> ID_MAPPER;

    @Inject(method="bootstrap", at=@At("TAIL"))
    private static void extraBootstrap (CallbackInfo info) {
        add("spell", SpellTintSource.CODEC);
        add("dagger", DaggerTintSource.CODEC);
    }

    @Unique
    private static void add (String name, MapCodec<? extends ItemTintSource> codec) {
        ID_MAPPER.put(AdditionalArmouryMain.identifier(name), codec);
    }
}
