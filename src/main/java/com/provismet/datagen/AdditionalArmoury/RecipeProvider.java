package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.crafting.TippedDaggerRecipe;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.CustomCraftingRecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    @NotNull
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider (HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new AARecipeGenerator(wrapperLookup, recipeExporter);
    }
    
    public static class AARecipeGenerator extends net.minecraft.data.recipes.RecipeProvider {
        private final HolderGetter<Item> lookup;

        public AARecipeGenerator (HolderLookup.Provider wrapperLookup, RecipeOutput exporter) {
            super(wrapperLookup, exporter);
            this.lookup = registries.lookupOrThrow(Registries.ITEM);
        }

        @Override
        public void buildRecipes () {
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

            createDaggerRecipe(AAItems.WOODEN_DAGGER, ItemTags.PLANKS).save(output);
            offerDaggerRecipe(AAItems.STONE_DAGGER, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE, output);
            offerDaggerRecipe(AAItems.COPPER_DAGGER, ItemTags.COPPER_TOOL_MATERIALS, Items.COPPER_INGOT, output);
            offerDaggerRecipe(AAItems.GOLDEN_DAGGER, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT, output);
            offerDaggerRecipe(AAItems.IRON_DAGGER, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT, output);
            offerDaggerRecipe(AAItems.DIAMOND_DAGGER, ConventionalItemTags.DIAMOND_GEMS, Items.DIAMOND, output);
            netheriteSmithing(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.NETHERITE_DAGGER);
            offerOvernetherUpgradeRecipe(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.OVERNETHER_DAGGER);
            offerEndernetherUpgradeRecipe(AAItems.DIAMOND_DAGGER, RecipeCategory.COMBAT, AAItems.ENDERNETHER_DAGGER);

            createMaceRecipe(AAItems.WOODEN_MACE, ItemTags.PLANKS).save(output);
            offerMaceRecipe(AAItems.STONE_MACE, ItemTags.STONE_TOOL_MATERIALS, Items.COBBLESTONE);
            offerMaceRecipe(AAItems.COPPER_MACE, ItemTags.COPPER_TOOL_MATERIALS, Items.COPPER_INGOT);
            offerMaceRecipe(AAItems.GOLDEN_MACE, ConventionalItemTags.GOLD_INGOTS, Items.GOLD_INGOT);
            offerMaceRecipe(AAItems.IRON_MACE, ConventionalItemTags.IRON_INGOTS, Items.IRON_INGOT);
            offerMaceRecipe(AAItems.DIAMOND_MACE, ConventionalItemTags.DIAMOND_GEMS, Items.DIAMOND);
            netheriteSmithing(AAItems.DIAMOND_MACE, RecipeCategory.COMBAT, AAItems.NETHERITE_MACE);
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

            CustomCraftingRecipeBuilder.customCrafting(
                RecipeCategory.COMBAT,
                (commonInfo, craftingBookInfo) -> new TippedDaggerRecipe(
                    commonInfo,
                    craftingBookInfo,
                    Ingredient.of(Items.LINGERING_POTION),
                    Ingredient.of(this.lookup.getOrThrow(AAItemTags.DAGGERS))
                ))
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.LINGERING_POTION), this.has(Items.LINGERING_POTION))
                .unlockedBy("has_any_dagger", this.has(AAItemTags.DAGGERS))
                .save(output, AdditionalArmouryMain.identifier("tipped_dagger").toString());

            this.shapeless(RecipeCategory.MISC, AAItems.OVERNETHER_INGOT)
                .requires(Items.NETHERITE_SCRAP, 4)
                .requires(Items.ECHO_SHARD, 4)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.NETHERITE_SCRAP), this.has(Items.NETHERITE_SCRAP))
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.ECHO_SHARD), this.has(Items.ECHO_SHARD))
                .save(output);
            this.shapeless(RecipeCategory.MISC, AAItems.ENDERNETHER_INGOT)
                .requires(Items.NETHERITE_SCRAP, 4)
                .requires(Items.POPPED_CHORUS_FRUIT, 4)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.NETHERITE_SCRAP), this.has(Items.NETHERITE_SCRAP))
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.POPPED_CHORUS_FRUIT), this.has(Items.POPPED_CHORUS_FRUIT))
                .save(output);

            this.copySmithingTemplate(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.DEEPSLATE);
            this.copySmithingTemplate(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE, Items.END_STONE);

            offerReversibleCompactingRecipe(AAItems.OVERNETHER_INGOT, AAItems.OVERNETHER_BLOCK);
            offerReversibleCompactingRecipe(AAItems.ENDERNETHER_INGOT, AAItems.ENDERNETHER_BLOCK);
        }

        protected ShapedRecipeBuilder createDaggerRecipe (DaggerItem dagger, ItemLike material) {
            return this.createDaggerBase(dagger)
                .define('i', material)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(material), this.has(material))
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.STICK), this.has(Items.STICK));
        }

        protected ShapedRecipeBuilder createDaggerRecipe (DaggerItem dagger, TagKey<Item> tag) {
            return this.createDaggerBase(dagger)
                .define('i', tag)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.STICK), this.has(Items.STICK));
        }

        protected void offerDaggerRecipe (DaggerItem dagger, ItemLike material) {
            this.createDaggerRecipe(dagger, material).save(output);
        }

        protected void offerDaggerRecipe (DaggerItem dagger, TagKey<Item> tag, ItemLike baseTagItem, RecipeOutput exporter) {
            this.createDaggerRecipe(dagger, tag)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(baseTagItem), this.has(tag))
                .save(exporter);
        }

        protected ShapedRecipeBuilder createMaceRecipe (MaceItem mace, ItemLike material) {
            return this.createMaceBase(mace)
                .define('i', material)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.STICK), this.has(Items.STICK))
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(material), this.has(material));
        }

        protected ShapedRecipeBuilder createMaceRecipe (MaceItem mace, TagKey<Item> tag) {
            return this.createMaceBase(mace)
                .define('i', tag)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(Items.STICK), this.has(Items.STICK));
        }

        protected void offerMaceRecipe (MaceItem mace, ItemLike material) {
            this.createMaceRecipe(mace, material).save(output);
        }

        protected void offerMaceRecipe (MaceItem mace, TagKey<Item> tag, ItemLike baseTagItem) {
            this.createMaceRecipe(mace, tag)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(baseTagItem), this.has(tag))
                .save(output);
        }

        protected void offerOvernetherUpgradeRecipe (Item input, RecipeCategory category, Item result) {
            SmithingTransformRecipeBuilder.smithing(Ingredient.of(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(AAItems.OVERNETHER_INGOT), category, result)
                .unlocks(net.minecraft.data.recipes.RecipeProvider.getHasName(AAItems.OVERNETHER_INGOT), this.has(AAItems.OVERNETHER_INGOT))
                .save(output, getItemName(result) + "_smithing");
        }

        protected void offerEndernetherUpgradeRecipe(Item input, RecipeCategory category, Item result) {
            SmithingTransformRecipeBuilder.smithing(Ingredient.of(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(AAItems.ENDERNETHER_INGOT), category, result)
                .unlocks(net.minecraft.data.recipes.RecipeProvider.getHasName(AAItems.ENDERNETHER_INGOT), this.has(AAItems.ENDERNETHER_INGOT))
                .save(output, getItemName(result) + "_smithing");
        }

        private void offerReversibleCompactingRecipe (Item input, Item result) {
            this.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(input, 9)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(input), this.has(input))
                .save(output);

            this.shapeless(RecipeCategory.MISC, input, 9)
                .requires(result)
                .unlockedBy(net.minecraft.data.recipes.RecipeProvider.getHasName(result), this.has(result))
                .save(output, BuiltInRegistries.ITEM.getKey(input) + "_decompressed");
        }

        private ShapedRecipeBuilder createDaggerBase (DaggerItem dagger) {
            return this.shaped(RecipeCategory.COMBAT, dagger)
                .pattern("i")
                .pattern("s")
                .define('s', Items.STICK);
        }

        private ShapedRecipeBuilder createMaceBase (MaceItem mace) {
            return this.shaped(RecipeCategory.COMBAT, mace)
                .pattern(" i ")
                .pattern("isi")
                .pattern(" s ")
                .define('s', Items.STICK);
        }
    }

    @Override
    public String getName () {
        return "";
    }
}
