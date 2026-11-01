package com.provismet.AdditionalArmoury.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.item.DaggerItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.NormalCraftingRecipe;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TippedDaggerRecipe extends NormalCraftingRecipe {
    private final Ingredient potionInput;
    private final Ingredient weapon;

    public static final MapCodec<TippedDaggerRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
        CraftingBookInfo.MAP_CODEC.forGetter(recipe -> recipe.bookInfo),
        Ingredient.CODEC.fieldOf("potion_input").forGetter(recipe -> recipe.potionInput),
        Ingredient.CODEC.fieldOf("weapon").forGetter(recipe -> recipe.weapon)
    ).apply(instance, TippedDaggerRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, TippedDaggerRecipe> STREAM_CODEC = StreamCodec.composite(
        CommonInfo.STREAM_CODEC,
        recipe -> recipe.commonInfo,
        CraftingBookInfo.STREAM_CODEC,
        recipe -> recipe.bookInfo,
        Ingredient.CONTENTS_STREAM_CODEC,
        recipe -> recipe.potionInput,
        Ingredient.CONTENTS_STREAM_CODEC,
        recipe -> recipe.weapon,
        TippedDaggerRecipe::new
    );
    public static final RecipeSerializer<TippedDaggerRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public TippedDaggerRecipe (Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, Ingredient potionInput, Ingredient weapon) {
        super(commonInfo, bookInfo);
        this.potionInput = potionInput;
        this.weapon = weapon;
    }

    @Override
    public boolean matches (CraftingInput input, Level world) {
        boolean hasWeapon = false;
        Holder<Potion> potionInput = null;

        for (ItemStack inputItem : input.items()) {
            if (this.weapon.test(inputItem)) {
                if (hasWeapon) return false; // Only allow one weapon.
                hasWeapon = true;
            }
            else if (this.potionInput.test(inputItem)) {
                // Only allow one type of potion
                PotionContents potionComponent = inputItem.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                if (!potionComponent.hasEffects() || potionComponent.potion().isEmpty()) return false;

                if (potionInput == null) potionInput = potionComponent.potion().get();
                else if (potionComponent.potion().get() != potionInput) return false;
            }
            else if (!inputItem.isEmpty()) return false;
        }

        return hasWeapon;
    }

    @Override
    @NotNull
    public ItemStack assemble (CraftingInput input) {
        ItemStack inputWeapon = null;
        ItemStack potion = null;
        int potionCount = 0;

        for (ItemStack inputItem : input.items()) {
            if (this.weapon.test(inputItem)) inputWeapon = inputItem.copy();
            else if (this.potionInput.test(inputItem)) {
                potion = inputItem;
                ++potionCount;
            }
        }

        if (inputWeapon == null || potion == null) return ItemStack.EMPTY;
        else {
            inputWeapon.set(DataComponents.POTION_CONTENTS, potion.get(DataComponents.POTION_CONTENTS));
            if (inputWeapon.getItem() instanceof DaggerItem dagger) {
                dagger.setCurrentPotionUses(inputWeapon, potionCount * DaggerItem.USES_PER_POTION);
            }
            return inputWeapon;
        }
    }

    @Override
    @NotNull
    public RecipeSerializer<? extends TippedDaggerRecipe> getSerializer () {
        return SERIALIZER;
    }

    @Override
    @NotNull
    protected PlacementInfo createPlacementInfo () {
        return PlacementInfo.create(
            List.of(
                this.potionInput, this.potionInput, this.potionInput,
                this.potionInput, this.weapon, this.potionInput,
                this.potionInput, this.potionInput, this.potionInput
            )
        );
    }
}
