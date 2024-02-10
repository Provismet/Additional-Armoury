package com.provismet.datagen.AdditionalArmoury;

import java.util.function.Consumer;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate (Consumer<RecipeJsonProvider> exporter) {
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, AAItems.OVERNETHER_SWORD);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, AAItems.ENDERNETHER_SWORD);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_AXE, RecipeCategory.COMBAT, AAItems.OVERNETHER_AXE);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_AXE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_AXE);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_PICKAXE, RecipeCategory.COMBAT, AAItems.OVERNETHER_PICKAXE);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_PICKAXE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_PICKAXE);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_SHOVEL, RecipeCategory.COMBAT, AAItems.OVERNETHER_SHOVEL);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_SHOVEL, RecipeCategory.COMBAT, AAItems.ENDERNETHER_SHOVEL);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_HOE, RecipeCategory.COMBAT, AAItems.OVERNETHER_HOE);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_HOE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_HOE);

        RecipeGenerator.createDaggerRecipe(AAItems.WOODEN_DAGGER, ItemTags.PLANKS).offerTo(exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.STONE_DAGGER, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.GOLDEN_DAGGER, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.IRON_DAGGER, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT, exporter);
        RecipeGenerator.offerDaggerRecipe(AAItems.DIAMOND_DAGGER, ConventionalItemTags.DIAMONDS, Items.DIAMOND, exporter);
        RecipeProvider.offerNetheriteUpgradeRecipe(exporter, AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.NETHERITE_DAGGER);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.OVERNETHER_DAGGER);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.ENDERNETHER_DAGGER);

        RecipeGenerator.createMaceRecipe(AAItems.WOODEN_MACE, ItemTags.PLANKS).offerTo(exporter);
        RecipeGenerator.offerMaceRecipe(AAItems.STONE_MACE, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, exporter);
        RecipeGenerator.offerMaceRecipe(AAItems.GOLDEN_MACE, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT, exporter);
        RecipeGenerator.offerMaceRecipe(AAItems.IRON_MACE, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT, exporter);
        RecipeGenerator.offerMaceRecipe(AAItems.DIAMOND_MACE, ConventionalItemTags.DIAMONDS, Items.DIAMOND, exporter);
        RecipeProvider.offerNetheriteUpgradeRecipe(exporter, AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.NETHERITE_MACE);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.OVERNETHER_MACE);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_MACE);

        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, AAItems.OVERNETHER_HELMET);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, AAItems.OVERNETHER_CHESTPLATE);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, AAItems.OVERNETHER_LEGGINGS);
        RecipeGenerator.offerOvernetherUpgradeRecipe(exporter, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, AAItems.OVERNETHER_BOOTS);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, AAItems.ENDERNETHER_HELMET);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_CHESTPLATE);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, AAItems.ENDERNETHER_LEGGINGS);
        RecipeGenerator.offerEndernetherUpgradeRecipe(exporter, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, AAItems.ENDERNETHER_BOOTS);

        ComplexRecipeJsonBuilder.create(AARecipeSerializers.TIPPED_DAGGER).offerTo(exporter, AdditionalArmouryMain.identifier("tipped_dagger").toString());

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AAItems.OVERNETHER_INGOT)
            .input(Items.NETHERITE_SCRAP, 4)
            .input(Items.ECHO_SHARD, 4)
            .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_SCRAP), FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_SCRAP))
            .criterion(FabricRecipeProvider.hasItem(Items.ECHO_SHARD), FabricRecipeProvider.conditionsFromItem(Items.ECHO_SHARD))
            .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AAItems.ENDERNETHER_INGOT)
            .input(Items.NETHERITE_SCRAP, 4)
            .input(Items.POPPED_CHORUS_FRUIT, 4)
            .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_SCRAP), FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_SCRAP))
            .criterion(FabricRecipeProvider.hasItem(Items.POPPED_CHORUS_FRUIT), FabricRecipeProvider.conditionsFromItem(Items.POPPED_CHORUS_FRUIT))
            .offerTo(exporter);

        VanillaRecipeProvider.offerSmithingTemplateCopyingRecipe(exporter, AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.DEEPSLATE);
        VanillaRecipeProvider.offerSmithingTemplateCopyingRecipe(exporter, AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);

        RecipeGenerator.offerReversibleCompactingRecipe(exporter, AAItems.OVERNETHER_INGOT, AAItems.OVERNETHER_BLOCK);
        RecipeGenerator.offerReversibleCompactingRecipe(exporter, AAItems.ENDERNETHER_INGOT, AAItems.ENDERNETHER_BLOCK);
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

    public static void offerMaceRecipe (MaceItem mace, Item material, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, mace)
            .pattern(" i ")
            .pattern("isi")
            .pattern(" s ")
            .input('i', material)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .criterion(FabricRecipeProvider.hasItem(material), FabricRecipeProvider.conditionsFromItem(material))
            .offerTo(exporter);
    }

    public static void offerMaceRecipe (MaceItem mace, TagKey<Item> tag, ItemConvertible baseTagItem, Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, mace)
            .pattern(" i ")
            .pattern("isi")
            .pattern(" s ")
            .input('i', tag)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .criterion(FabricRecipeProvider.hasItem(baseTagItem), FabricRecipeProvider.conditionsFromTag(tag))
            .offerTo(exporter);
    }

    public static ShapedRecipeJsonBuilder createMaceRecipe (MaceItem mace, TagKey<Item> tag) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, mace)
            .pattern(" i ")
            .pattern("isi")
            .pattern(" s ")
            .input('i', tag)
            .input('s', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), FabricRecipeProvider.conditionsFromItem(Items.STICK));
    }

    public static void offerOvernetherUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(AAItems.OVERNETHER_INGOT), category, result)
            .criterion(FabricRecipeProvider.hasItem(AAItems.OVERNETHER_INGOT), FabricRecipeProvider.conditionsFromItem(AAItems.OVERNETHER_INGOT))
            .offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }

    public static void offerEndernetherUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(AAItems.ENDERNETHER_INGOT), category, result)
            .criterion(FabricRecipeProvider.hasItem(AAItems.ENDERNETHER_INGOT), FabricRecipeProvider.conditionsFromItem(AAItems.ENDERNETHER_INGOT))
            .offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }

    private static void offerReversibleCompactingRecipe (Consumer<RecipeJsonProvider> exporter, Item input, Item result) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, result)
            .input(input, 9)
            .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
            .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, input, 9)
            .input(result)
            .criterion(FabricRecipeProvider.hasItem(result), FabricRecipeProvider.conditionsFromItem(result))
            .offerTo(exporter, Registries.ITEM.getId(input) + "_decompressed");
    }
}
