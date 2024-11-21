package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.crafting.TippedDaggerRecipe;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator (RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new AARecipeGenerator(wrapperLookup, recipeExporter);
    }
    
    public static class AARecipeGenerator extends RecipeGenerator {
        public AARecipeGenerator (RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
            super(wrapperLookup, exporter);
        }

        @Override
        public void generate () {
            offerOvernetherUpgradeRecipe(Items.DIAMOND_SWORD, RecipeCategory.COMBAT, AAItems.OVERNETHER_SWORD);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_SWORD, RecipeCategory.COMBAT, AAItems.ENDERNETHER_SWORD);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_AXE, RecipeCategory.COMBAT, AAItems.OVERNETHER_AXE);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_AXE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_AXE);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_PICKAXE, RecipeCategory.COMBAT, AAItems.OVERNETHER_PICKAXE);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_PICKAXE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_PICKAXE);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_SHOVEL, RecipeCategory.COMBAT, AAItems.OVERNETHER_SHOVEL);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_SHOVEL, RecipeCategory.COMBAT, AAItems.ENDERNETHER_SHOVEL);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_HOE, RecipeCategory.COMBAT, AAItems.OVERNETHER_HOE);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_HOE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_HOE);

            createDaggerRecipe(AAItems.WOODEN_DAGGER, ItemTags.PLANKS).offerTo(exporter);
            offerDaggerRecipe(AAItems.STONE_DAGGER, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, exporter);
            offerDaggerRecipe(AAItems.GOLDEN_DAGGER, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT, exporter);
            offerDaggerRecipe(AAItems.IRON_DAGGER, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT, exporter);
            offerDaggerRecipe(AAItems.DIAMOND_DAGGER, ConventionalItemTags.DIAMOND_GEMS, Items.DIAMOND, exporter);
            offerNetheriteUpgradeRecipe(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.NETHERITE_DAGGER);
            offerOvernetherUpgradeRecipe(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.OVERNETHER_DAGGER);
            offerEndernetherUpgradeRecipe(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.ENDERNETHER_DAGGER);

            createMaceRecipe(AAItems.WOODEN_MACE, ItemTags.PLANKS).offerTo(exporter);
            offerMaceRecipe(AAItems.STONE_MACE, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE);
            offerMaceRecipe(AAItems.GOLDEN_MACE, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT);
            offerMaceRecipe(AAItems.IRON_MACE, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT);
            offerMaceRecipe(AAItems.DIAMOND_MACE, ConventionalItemTags.DIAMOND_GEMS, Items.DIAMOND);
            offerNetheriteUpgradeRecipe(AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.NETHERITE_MACE);
            offerOvernetherUpgradeRecipe(AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.OVERNETHER_MACE);
            offerEndernetherUpgradeRecipe(AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_MACE);

            offerOvernetherUpgradeRecipe(Items.DIAMOND_HELMET, RecipeCategory.COMBAT, AAItems.OVERNETHER_HELMET);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, AAItems.OVERNETHER_CHESTPLATE);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, AAItems.OVERNETHER_LEGGINGS);
            offerOvernetherUpgradeRecipe(Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, AAItems.OVERNETHER_BOOTS);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_HELMET, RecipeCategory.COMBAT, AAItems.ENDERNETHER_HELMET);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, AAItems.ENDERNETHER_CHESTPLATE);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, AAItems.ENDERNETHER_LEGGINGS);
            offerEndernetherUpgradeRecipe(Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, AAItems.ENDERNETHER_BOOTS);

            ComplexRecipeJsonBuilder.create(TippedDaggerRecipe::new).offerTo(exporter, AdditionalArmouryMain.identifier("tipped_dagger").toString());

            this.createShapeless(RecipeCategory.MISC, AAItems.OVERNETHER_INGOT)
                .input(Items.NETHERITE_SCRAP, 4)
                .input(Items.ECHO_SHARD, 4)
                .criterion(RecipeGenerator.hasItem(Items.NETHERITE_SCRAP), this.conditionsFromItem(Items.NETHERITE_SCRAP))
                .criterion(RecipeGenerator.hasItem(Items.ECHO_SHARD), this.conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter);
            this.createShapeless(RecipeCategory.MISC, AAItems.ENDERNETHER_INGOT)
                .input(Items.NETHERITE_SCRAP, 4)
                .input(Items.POPPED_CHORUS_FRUIT, 4)
                .criterion(RecipeGenerator.hasItem(Items.NETHERITE_SCRAP), this.conditionsFromItem(Items.NETHERITE_SCRAP))
                .criterion(RecipeGenerator.hasItem(Items.POPPED_CHORUS_FRUIT), this.conditionsFromItem(Items.POPPED_CHORUS_FRUIT))
                .offerTo(exporter);

            this.offerSmithingTemplateCopyingRecipe(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.DEEPSLATE);
            this.offerSmithingTemplateCopyingRecipe(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);

            offerReversibleCompactingRecipe(AAItems.OVERNETHER_INGOT, AAItems.OVERNETHER_BLOCK);
            offerReversibleCompactingRecipe(AAItems.ENDERNETHER_INGOT, AAItems.ENDERNETHER_BLOCK);
        }

        protected ShapedRecipeJsonBuilder createDaggerRecipe (DaggerItem dagger, ItemConvertible material) {
            return this.createDaggerBase(dagger)
                .input('i', material)
                .criterion(RecipeGenerator.hasItem(material), this.conditionsFromItem(material))
                .criterion(RecipeGenerator.hasItem(Items.STICK), this.conditionsFromItem(Items.STICK));
        }

        protected ShapedRecipeJsonBuilder createDaggerRecipe (DaggerItem dagger, TagKey<Item> tag) {
            return this.createDaggerBase(dagger)
                .input('i', tag)
                .criterion(RecipeGenerator.hasItem(Items.STICK), this.conditionsFromItem(Items.STICK));
        }

        protected void offerDaggerRecipe (DaggerItem dagger, ItemConvertible material) {
            this.createDaggerRecipe(dagger, material).offerTo(exporter);
        }

        protected void offerDaggerRecipe (DaggerItem dagger, TagKey<Item> tag, ItemConvertible baseTagItem, RecipeExporter exporter) {
            this.createDaggerRecipe(dagger, tag)
                .criterion(RecipeGenerator.hasItem(baseTagItem), this.conditionsFromTag(tag))
                .offerTo(exporter);
        }

        protected ShapedRecipeJsonBuilder createMaceRecipe (MaceItem mace, ItemConvertible material) {
            return this.createMaceBase(mace)
                .input('i', material)
                .criterion(RecipeGenerator.hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                .criterion(RecipeGenerator.hasItem(material), this.conditionsFromItem(material));
        }

        protected ShapedRecipeJsonBuilder createMaceRecipe (MaceItem mace, TagKey<Item> tag) {
            return this.createMaceBase(mace)
                .input('i', tag)
                .criterion(RecipeGenerator.hasItem(Items.STICK), this.conditionsFromItem(Items.STICK));
        }

        protected void offerMaceRecipe (MaceItem mace, ItemConvertible material) {
            this.createMaceRecipe(mace, material).offerTo(exporter);
        }

        protected void offerMaceRecipe (MaceItem mace, TagKey<Item> tag, ItemConvertible baseTagItem) {
            this.createMaceRecipe(mace, tag)
                .criterion(RecipeGenerator.hasItem(baseTagItem), this.conditionsFromTag(tag))
                .offerTo(exporter);
        }

        protected void offerOvernetherUpgradeRecipe (Item input, RecipeCategory category, Item result) {
            SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(AAItems.OVERNETHER_INGOT), category, result)
                .criterion(RecipeGenerator.hasItem(AAItems.OVERNETHER_INGOT), this.conditionsFromItem(AAItems.OVERNETHER_INGOT))
                .offerTo(exporter, getItemPath(result) + "_smithing");
        }

        protected void offerEndernetherUpgradeRecipe(Item input, RecipeCategory category, Item result) {
            SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItems(input), Ingredient.ofItems(AAItems.ENDERNETHER_INGOT), category, result)
                .criterion(RecipeGenerator.hasItem(AAItems.ENDERNETHER_INGOT), this.conditionsFromItem(AAItems.ENDERNETHER_INGOT))
                .offerTo(exporter, getItemPath(result) + "_smithing");
        }

        private void offerReversibleCompactingRecipe (Item input, Item result) {
            this.createShapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .input(input, 9)
                .criterion(RecipeGenerator.hasItem(input), this.conditionsFromItem(input))
                .offerTo(exporter);

            this.createShapeless(RecipeCategory.MISC, input, 9)
                .input(result)
                .criterion(RecipeGenerator.hasItem(result), this.conditionsFromItem(result))
                .offerTo(exporter, Registries.ITEM.getId(input) + "_decompressed");
        }

        private ShapedRecipeJsonBuilder createDaggerBase (DaggerItem dagger) {
            return this.createShaped(RecipeCategory.COMBAT, dagger)
                .pattern("i")
                .pattern("s")
                .input('s', Items.STICK);
        }

        private ShapedRecipeJsonBuilder createMaceBase (MaceItem mace) {
            return this.createShaped(RecipeCategory.COMBAT, mace)
                .pattern(" i ")
                .pattern("isi")
                .pattern(" s ")
                .input('s', Items.STICK);
        }
    }

    @Override
    public String getName () {
        return "";
    }
}
