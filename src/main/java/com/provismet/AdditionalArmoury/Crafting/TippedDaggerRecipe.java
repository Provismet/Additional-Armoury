package com.provismet.AdditionalArmoury.crafting;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TippedDaggerRecipe extends SpecialCraftingRecipe {

    public TippedDaggerRecipe (Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches (RecipeInputInventory recipeInputInventory, World world) {
        boolean hasOneDagger = false;
        boolean hasOnePotion = false;

        for (ItemStack input : recipeInputInventory.getInputStacks()) {
            if (input.getItem() instanceof DaggerItem) {
                if (hasOneDagger) return false;
                hasOneDagger = true;
            }
            else if (input.isOf(Items.LINGERING_POTION)) {
                if (hasOnePotion) return false;
                hasOnePotion = true;
            }
        }

        return hasOneDagger && hasOnePotion;
    }

    @Override
    public ItemStack craft (RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack inputDagger = null;
        ItemStack potion = null;

        for (ItemStack input : recipeInputInventory.getInputStacks()) {
            if (input.getItem() instanceof DaggerItem) inputDagger = input.copy();
            else if (input.isOf(Items.LINGERING_POTION)) potion = input;
        }

        if (inputDagger == null || potion == null) return ItemStack.EMPTY;
        else {
            PotionUtil.setPotion(inputDagger, PotionUtil.getPotion(potion));
            PotionUtil.setCustomPotionEffects(inputDagger, PotionUtil.getCustomPotionEffects(potion));
            return inputDagger;
        }
    }

    @Override
    public boolean fits (int width, int height) {
        return width >= 2 || height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer () {
        return AARecipeSerializers.TIPPED_DAGGER;
    }
    
}
