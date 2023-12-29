package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.crafting.TippedDaggerRecipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AARecipeSerializers {
    public static final RecipeSerializer<TippedDaggerRecipe> TIPPED_DAGGER = new SpecialRecipeSerializer<>(TippedDaggerRecipe::new);

    public static void register () {
        Registry.register(Registries.RECIPE_SERIALIZER, AdditionalArmouryMain.identifier("tipped_dagger"), TIPPED_DAGGER);
    }
}
