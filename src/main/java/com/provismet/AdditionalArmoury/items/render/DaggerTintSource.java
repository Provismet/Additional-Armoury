package com.provismet.AdditionalArmoury.items.render;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record DaggerTintSource () implements TintSource {
    public static final MapCodec<DaggerTintSource> CODEC = MapCodec.unit(DaggerTintSource::new);

    private static final int POTION_DEFAULT = -13083194;

    @Override
    public int getTint (ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        PotionContentsComponent potionContentsComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
        return potionContentsComponent != null ? ColorHelper.fullAlpha(potionContentsComponent.getColor(POTION_DEFAULT)) : 0;
    }

    @Override
    public MapCodec<? extends TintSource> getCodec () {
        return CODEC;
    }
}
