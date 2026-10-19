package com.provismet.AdditionalArmoury.items.render;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SpellTintSource (int defaultColour) implements ItemTintSource {
    public static final MapCodec<SpellTintSource> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(ExtraCodecs.RGB_COLOR_CODEC.fieldOf("default").forGetter(SpellTintSource::defaultColour)).apply(instance, SpellTintSource::new)
    );

    @Override
    public int calculate (ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity user) {
        Pair<Integer, Integer> pair = EnchantmentHelper.getHighestLevel(stack, AAEnchantmentComponentTypes.SPELL_COLOUR);
        if (pair != null) return ARGB.opaque(pair.getFirst());
        return this.defaultColour;
    }

    @Override
    public MapCodec<? extends ItemTintSource> type () {
        return CODEC;
    }
}
