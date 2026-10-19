package com.provismet.AdditionalArmoury.crafting;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TippedDaggerRecipe extends CustomRecipe {
    public TippedDaggerRecipe (CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches (CraftingInput input, Level world) {
        boolean hasOneDagger = false;
        Holder<Potion> potionInput = null;

        for (ItemStack inputItem : input.items()) {
            if (inputItem.getItem() instanceof DaggerItem) {
                if (hasOneDagger) return false;
                hasOneDagger = true;
            }
            else if (inputItem.is(Items.LINGERING_POTION)) {
                PotionContents potionComponent = inputItem.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                if (!potionComponent.hasEffects() || potionComponent.potion().isEmpty()) return false;

                if (potionInput == null) potionInput = potionComponent.potion().get();
                else if (potionComponent.potion().get() != potionInput) return false;
            }
            else if (!inputItem.isEmpty()) return false;
        }

        return hasOneDagger;
    }

    @Override
    @NotNull
    public ItemStack assemble (CraftingInput input, HolderLookup.Provider lookup) {
        ItemStack inputDagger = null;
        ItemStack potion = null;
        int potionCount = 0;

        for (ItemStack inputItem : input.items()) {
            if (inputItem.getItem() instanceof DaggerItem) inputDagger = inputItem.copy();
            else if (inputItem.is(Items.LINGERING_POTION)) {
                potion = inputItem;
                ++potionCount;
            }
        }

        if (inputDagger == null || potion == null) return ItemStack.EMPTY;
        else {
            inputDagger.set(DataComponents.POTION_CONTENTS, potion.get(DataComponents.POTION_CONTENTS));
            ((DaggerItem)inputDagger.getItem()).setCurrentPotionUses(inputDagger, potionCount * DaggerItem.USES_PER_POTION);
            return inputDagger;
        }
    }

    @Override
    @NotNull
    public RecipeSerializer<? extends CustomRecipe> getSerializer () {
        return AARecipeSerializers.TIPPED_DAGGER;
    }
}
