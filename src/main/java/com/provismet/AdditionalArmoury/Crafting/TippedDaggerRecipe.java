package com.provismet.AdditionalArmoury.crafting;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class TippedDaggerRecipe extends SpecialCraftingRecipe {
    public TippedDaggerRecipe (CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches (RecipeInputInventory recipeInputInventory, World world) {
        boolean hasOneDagger = false;
        RegistryEntry<Potion> potionInput = null;

        for (ItemStack input : recipeInputInventory.getHeldStacks()) {
            if (input.getItem() instanceof DaggerItem) {
                if (hasOneDagger) return false;
                hasOneDagger = true;
            }
            else if (input.isOf(Items.LINGERING_POTION)) {
                PotionContentsComponent potionComponent = input.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
                if (!potionComponent.hasEffects() || potionComponent.potion().isEmpty()) return false;

                if (potionInput == null) potionInput = potionComponent.potion().get();
                else if (potionComponent.potion().get() != potionInput) return false;
            }
            else if (!input.isEmpty()) return false;
        }

        return hasOneDagger;
    }

    @Override
    public ItemStack craft (RecipeInputInventory recipeInputInventory, RegistryWrapper.WrapperLookup lookup) {
        ItemStack inputDagger = null;
        ItemStack potion = null;
        int potionCount = 0;

        for (ItemStack input : recipeInputInventory.getHeldStacks()) {
            if (input.getItem() instanceof DaggerItem) inputDagger = input.copy();
            else if (input.isOf(Items.LINGERING_POTION)) {
                potion = input;
                ++potionCount;
            }
        }

        if (inputDagger == null || potion == null) return ItemStack.EMPTY;
        else {
            inputDagger.set(DataComponentTypes.POTION_CONTENTS, potion.get(DataComponentTypes.POTION_CONTENTS));
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
