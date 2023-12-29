package com.provismet.AdditionalArmoury.registries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.potion.PotionUtil;

@Environment(EnvType.CLIENT)
public class ColourRegistry {
    public static void register () {
        AAItems.DAGGERS.forEach(dagger -> ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 1) {
                int colour = PotionUtil.getColor(stack);
                return colour == 0xF800F8 ? dagger.defaultTipColour : colour;
            }
            else return -1;
        }, dagger));
    }
}
