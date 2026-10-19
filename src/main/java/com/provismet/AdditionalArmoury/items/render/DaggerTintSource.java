package com.provismet.AdditionalArmoury.items.render;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record DaggerTintSource () implements ItemTintSource {
    public static final MapCodec<DaggerTintSource> CODEC = MapCodec.unit(DaggerTintSource::new);

    private static final int POTION_DEFAULT = -13083194;

    @Override
    public int calculate (ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity user) {
        PotionContents potionContentsComponent = stack.get(DataComponents.POTION_CONTENTS);
        return potionContentsComponent != null ? ARGB.opaque(potionContentsComponent.getColorOr(POTION_DEFAULT)) : 0;
    }

    @Override
    public MapCodec<? extends ItemTintSource> type () {
        return CODEC;
    }
}
