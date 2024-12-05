package com.provismet.AdditionalArmoury.items.render;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SpellTintSource (int defaultColour) implements TintSource {
    public static final MapCodec<SpellTintSource> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(Codecs.RGB.fieldOf("default").forGetter(SpellTintSource::defaultColour)).apply(instance, SpellTintSource::new)
    );

    @Override
    public int getTint (ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getHighestLevelEffect(stack, AAEnchantmentComponentTypes.SPELL_COLOUR);
        if (pair != null) return ColorHelper.fullAlpha(pair.getFirst());
        return this.defaultColour;
    }

    @Override
    public MapCodec<? extends TintSource> getCodec () {
        return CODEC;
    }
}
