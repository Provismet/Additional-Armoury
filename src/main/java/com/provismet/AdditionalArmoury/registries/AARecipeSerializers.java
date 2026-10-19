package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.crafting.TippedDaggerRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class AARecipeSerializers {
    public static final RecipeSerializer<TippedDaggerRecipe> TIPPED_DAGGER = new CustomRecipe.Serializer<>(TippedDaggerRecipe::new);

    public static void register () {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, AdditionalArmouryMain.identifier("tipped_dagger"), TIPPED_DAGGER);
    }
}
