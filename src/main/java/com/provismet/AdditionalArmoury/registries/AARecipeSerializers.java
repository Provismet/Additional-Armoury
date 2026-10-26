package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.crafting.TippedDaggerRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class AARecipeSerializers {
    public static void register () {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, AdditionalArmouryMain.identifier("tipped_dagger"), TippedDaggerRecipe.SERIALIZER);
    }
}
