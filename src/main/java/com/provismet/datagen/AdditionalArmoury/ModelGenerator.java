package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.StaffItem;
import com.provismet.AdditionalArmoury.items.key.AAEquipmentAssetKeys;
import com.provismet.AdditionalArmoury.items.render.DaggerTintSource;
import com.provismet.AdditionalArmoury.items.render.SpellTintSource;
import com.provismet.AdditionalArmoury.registries.AABlocks;
import com.provismet.AdditionalArmoury.registries.AAItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CommonColors;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {
    public static final ModelTemplate HANDHELD_LAYERED = createModel("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    public ModelGenerator (FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels (BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(AABlocks.OVERNETHER_BLOCK);
        blockStateModelGenerator.createTrivialCube(AABlocks.ENDERNETHER_BLOCK);
    }

    @Override
    public void generateItemModels (ItemModelGenerators itemModelGenerator) {
        registerMass(itemModelGenerator, ModelTemplates.FLAT_ITEM,
            AAItems.OVERNETHER_INGOT,
            AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE,
            AAItems.ENDERNETHER_INGOT,
            AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE
        );

        registerMass(itemModelGenerator, ModelTemplates.FLAT_HANDHELD_ITEM,
            AAItems.OVERNETHER_SWORD,
            AAItems.OVERNETHER_AXE,
            AAItems.OVERNETHER_PICKAXE,
            AAItems.OVERNETHER_SHOVEL,
            AAItems.OVERNETHER_HOE,
            AAItems.ENDERNETHER_SWORD,
            AAItems.ENDERNETHER_AXE,
            AAItems.ENDERNETHER_PICKAXE,
            AAItems.ENDERNETHER_SHOVEL,
            AAItems.ENDERNETHER_HOE,
            AAItems.BOOMERANG
        );

        registerDagger(itemModelGenerator, AAItems.WOODEN_DAGGER, 0x643C64);
        registerDagger(itemModelGenerator, AAItems.STONE_DAGGER, 0x494949);
        registerDagger(itemModelGenerator, AAItems.COPPER_DAGGER, 0x5F2C1C);
        registerDagger(itemModelGenerator, AAItems.GOLDEN_DAGGER, 0x643C64);
        registerDagger(itemModelGenerator, AAItems.IRON_DAGGER, 0x444444);
        registerDagger(itemModelGenerator, AAItems.DIAMOND_DAGGER, 0x0E3F36);
        registerDagger(itemModelGenerator, AAItems.NETHERITE_DAGGER, 0x643C64);
        registerDagger(itemModelGenerator, AAItems.OVERNETHER_DAGGER, 0x082832);
        registerDagger(itemModelGenerator, AAItems.ENDERNETHER_DAGGER, 0x643C64);
        ModelGenerator.registerStaff(itemModelGenerator, AAItems.STAFF);

        AAItems.MACES.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM));
        AAItems.ITEM_PROJECTILES.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        this.registerArmour(AAItems.OVERNETHER_HELMET, AAItems.OVERNETHER_CHESTPLATE, AAItems.OVERNETHER_LEGGINGS, AAItems.OVERNETHER_BOOTS, null, AAEquipmentAssetKeys.OVERNETHER, itemModelGenerator);
        this.registerArmour(AAItems.ENDERNETHER_HELMET, AAItems.ENDERNETHER_CHESTPLATE, AAItems.ENDERNETHER_LEGGINGS, AAItems.ENDERNETHER_BOOTS, null, AAEquipmentAssetKeys.ENDERNETHER, itemModelGenerator);

        itemModelGenerator.generateSpear(AAItems.OVERNETHER_SPEAR);
        itemModelGenerator.generateSpear(AAItems.ENDERNETHER_SPEAR);
    }

    public static void registerDagger (ItemModelGenerators itemModelGenerator, DaggerItem dagger, int defaultColour) {
        Identifier model = HANDHELD_LAYERED.create(
            ModelLocationUtils.getModelLocation(dagger),
            TextureMapping.layered(
                TextureMapping.getItemTexture(dagger),
                new Material(AdditionalArmouryMain.identifier("item/dagger_tip"))
            ),
            itemModelGenerator.modelOutput
        );
        itemModelGenerator.itemModelOutput.accept(dagger, ItemModelUtils.tintedModel(model, new Constant(CommonColors.WHITE), new DaggerTintSource(defaultColour)));
    }

    public static void registerStaff (ItemModelGenerators itemModelGenerator, StaffItem staff) {
        Identifier model = HANDHELD_LAYERED.create(
            ModelLocationUtils.getModelLocation(staff),
            TextureMapping.layered(
                new Material(AdditionalArmouryMain.identifier("item/staff_head")),
                new Material(AdditionalArmouryMain.identifier("item/staff_shaft"))),
            itemModelGenerator.modelOutput
        );
        itemModelGenerator.itemModelOutput.accept(staff, ItemModelUtils.tintedModel(model, new SpellTintSource(0xFFC18920)));
    }

    private static void registerMass (ItemModelGenerators itemModelGenerator, ModelTemplate model, Item... items) {
        for (Item i : items) {
            itemModelGenerator.generateFlatItem(i, model);
        }
    }

    private static ModelTemplate createModel (String parent, TextureSlot ... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private void registerArmour (@Nullable Item head, @Nullable Item chest, @Nullable Item legs, @Nullable Item feet, @Nullable Item body, ResourceKey<EquipmentAsset> equipableAsset, ItemModelGenerators itemModelGenerator) {
        if (head != null) {
            itemModelGenerator.generateTrimmableItem(head, equipableAsset, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        }
        if (chest != null) {
            itemModelGenerator.generateTrimmableItem(chest, equipableAsset, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        }
        if (legs != null) {
            itemModelGenerator.generateTrimmableItem(legs, equipableAsset, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        }
        if (feet != null) {
            itemModelGenerator.generateTrimmableItem(feet, equipableAsset, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        }
        if (body != null) {
            itemModelGenerator.generateTrimmableItem(body, equipableAsset, ItemModelGenerators.prefixForSlotTrim("body"), false);
        }
    }
}
