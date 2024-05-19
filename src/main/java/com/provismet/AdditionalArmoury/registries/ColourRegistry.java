package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.StaffItem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;

@Environment(EnvType.CLIENT)
public class ColourRegistry {
    public static void register () {
        AAItems.DAGGERS.forEach(dagger -> ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 1) {
                int colour = stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).getColor();
                return colour == -13083194 ? DaggerItem.defaultTipColour : colour; // -13083194 is the vanilla default colour for empty potion components.
            }
            else return -1;
        }, dagger));

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                return StaffItem.getColour(stack);
            }
            else return -1;
        }, AAItems.STAFF);
    }
}
