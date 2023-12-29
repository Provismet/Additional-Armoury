package com.provismet.datagen.AdditionalArmoury;

import java.util.function.Consumer;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate (Consumer<RecipeJsonProvider> exporter) {
        RecipeGenerator.createDaggerRecipe(AAItems.WOODEN_DAGGER, ItemTags.PLANKS).offerTo(exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.STONE_DAGGER, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.GOLDEN_DAGGER, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.IRON_DAGGER, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.DIAMOND_DAGGER, ConventionalItemTags.DIAMONDS, Items.DIAMOND, exporter);
        RecipeProvider.offerNetheriteUpgradeRecipe(exporter, AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.NETHERITE_DAGGER);

        ComplexRecipeJsonBuilder.create(AARecipeSerializers.TIPPED_DAGGER).offerTo(exporter, AdditionalArmouryMain.identifier("tipped_dagger").toString());
    }
    
    public static void offerDaggerRecipe (DaggerItem dagger, Item material, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, dagger)
            .pattern("i")
            .pattern("s")
            .input('i', material)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .offerTo(exporter);
    }

    public static ShapedRecipeJsonBuilder createDaggerRecipe (DaggerItem dagger, TagKey<Item> tag) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, dagger)
            .pattern("i")
            .pattern("s")
            .input('i', tag)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK));
    }

    public static void offerDaggerRecipe (DaggerItem dagger, TagKey<Item> tag, ItemConvertible baseTagItem, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, dagger)
            .pattern("i")
            .pattern("s")
            .input('i', tag)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .criterion(FabricRecipeProvider.hasItem(baseTagItem), FabricRecipeProvider.conditionsFromTag(tag))
            .offerTo(exporter);
    }
}
