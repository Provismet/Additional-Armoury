package com.provismet.datagen.AdditionalArmoury;

import java.util.Map;
import java.util.Optional;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.StaffItem;
import com.provismet.AdditionalArmoury.items.render.DaggerTintSource;
import com.provismet.AdditionalArmoury.items.render.SpellTintSource;
import com.provismet.AdditionalArmoury.registries.AABlocks;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CommonColors;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

public class ModelGenerator extends FabricModelProvider {
    public static final ModelTemplate HANDHELD_LAYERED = createModel("handheld", TextureSlot.LAYER0, TextureSlot.LAYER1);

    public ModelGenerator (FabricDataOutput output) {
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

        AAItems.DAGGERS.forEach(dagger -> ModelGenerator.registerDagger(itemModelGenerator, dagger));
        ModelGenerator.registerStaff(itemModelGenerator, AAItems.STAFF);

        AAItems.MACES.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM));
        AAItems.ITEM_PROJECTILES.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        AAItems.OVERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
        AAItems.ENDERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
    }

    public static void registerDagger (ItemModelGenerators itemModelGenerator, DaggerItem dagger) {
        Identifier model = HANDHELD_LAYERED.create(
            ModelLocationUtils.getModelLocation(dagger),
            TextureMapping.layered(
                TextureMapping.getItemTexture(dagger),
                AdditionalArmouryMain.identifier("item/dagger_tip")
            ),
            itemModelGenerator.modelOutput
        );
        itemModelGenerator.itemModelOutput.accept(dagger, ItemModelUtils.tintedModel(model, new Constant(CommonColors.WHITE), new DaggerTintSource()));
    }

    public static void registerStaff (ItemModelGenerators itemModelGenerator, StaffItem staff) {
        Identifier model = HANDHELD_LAYERED.create(
            ModelLocationUtils.getModelLocation(staff),
            TextureMapping.layered(
                AdditionalArmouryMain.identifier("item/staff_head"),
                AdditionalArmouryMain.identifier("item/staff_shaft")),
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

    private void registerArmour (Item item, ItemModelGenerators itemModelGenerator) {
        final Map<EquipmentSlot, Identifier> slotToTrim = Map.of(
            EquipmentSlot.HEAD, ItemModelGenerators.TRIM_PREFIX_HELMET,
            EquipmentSlot.CHEST, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,
            EquipmentSlot.LEGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS,
            EquipmentSlot.FEET, ItemModelGenerators.TRIM_PREFIX_BOOTS,
            EquipmentSlot.BODY, ItemModelGenerators.prefixForSlotTrim("body")
        );

        Equippable equippableComponent = item.components().get(DataComponents.EQUIPPABLE);
        if (equippableComponent == null || equippableComponent.assetId().isEmpty()) {
            AdditionalArmouryMain.LOGGER.warn("No equippable component found for {}", item.getName().getString());
            return;
        }
        ResourceKey<EquipmentAsset> modelId = equippableComponent.assetId().get();
        itemModelGenerator.generateTrimmableItem(item, modelId, slotToTrim.get(equippableComponent.slot()), false);
    }
}
