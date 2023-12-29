package com.provismet.AdditionalArmoury;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAItemGroups;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class AdditionalArmouryMain implements ModInitializer {
    public static final String MODID = "additional-armoury";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier identifier (String path) {
        return Identifier.of(MODID, path);
    }

    @Override
    public void onInitialize () {
        AAItems.register();
        AAEnchantments.register();
        AAItemGroups.register();
        AARecipeSerializers.register();
    }
}
