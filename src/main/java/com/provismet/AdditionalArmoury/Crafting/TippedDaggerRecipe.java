package com.provismet.AdditionalArmoury.crafting;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
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
        Potion potionInput = null;

        for (ItemStack input : recipeInputInventory.getInputStacks()) {
            if (input.getItem() instanceof DaggerItem) {
                if (hasOneDagger) return false;
                hasOneDagger = true;
            }
            else if (input.isOf(Items.LINGERING_POTION)) {
                if (potionInput == null) potionInput = PotionUtil.getPotion(input);
                else if (PotionUtil.getPotion(input) != potionInput) return false;
            }
            else if (!input.isEmpty()) return false;
        }

        return hasOneDagger;
    }

    @Override
    public ItemStack craft (RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack inputDagger = null;
        ItemStack potion = null;
        int potionCount = 0;

        for (ItemStack input : recipeInputInventory.getInputStacks()) {
            if (input.getItem() instanceof DaggerItem) inputDagger = input.copy();
            else if (input.isOf(Items.LINGERING_POTION)) {
                potion = input;
                ++potionCount;
            }
        }

        if (inputDagger == null || potion == null) return ItemStack.EMPTY;
        else {
            PotionUtil.setPotion(inputDagger, PotionUtil.getPotion(potion));
            PotionUtil.setCustomPotionEffects(inputDagger, PotionUtil.getCustomPotionEffects(potion));
            ((DaggerItem)inputDagger.getItem()).setCurrentPotionUses(inputDagger, potionCount * DaggerItem.USES_PER_POTION);
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
