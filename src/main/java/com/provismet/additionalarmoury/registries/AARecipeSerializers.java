package com.provismet.additionalarmoury.registries;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.crafting.TippedDaggerRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class AARecipeSerializers {
    public static void register () {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, AdditionalArmouryMain.identifier("tipped_dagger"), TippedDaggerRecipe.SERIALIZER);
    }
}
