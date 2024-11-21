package com.provismet.datagen.AdditionalArmoury;

import java.util.Optional;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AABlocks;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentModel;
import net.minecraft.util.Identifier;

public class ModelGenerator extends FabricModelProvider {
    public static final Model HANDHELD_LAYERED = createModel("handheld", TextureKey.LAYER0, TextureKey.LAYER1);

    public ModelGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels (BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(AABlocks.OVERNETHER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(AABlocks.ENDERNETHER_BLOCK);
    }

    @Override
    public void generateItemModels (ItemModelGenerator itemModelGenerator) {
        registerMass(itemModelGenerator, Models.GENERATED,
            AAItems.OVERNETHER_INGOT,
            AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE,
            AAItems.ENDERNETHER_INGOT,
            AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE
        );

        registerMass(itemModelGenerator, Models.HANDHELD,
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
        HANDHELD_LAYERED.upload(ModelIds.getItemModelId(AAItems.STAFF), TextureMap.layered(AdditionalArmouryMain.identifier("item/staff_head"), AdditionalArmouryMain.identifier("item/staff_shaft")), itemModelGenerator.writer);

        AAItems.MACES.forEach(item -> itemModelGenerator.register(item, Models.HANDHELD));
        AAItems.ITEM_PROJECTILES.forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
        AAItems.OVERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
        AAItems.ENDERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
    }

    public static void registerDagger (ItemModelGenerator itemModelGenerator, DaggerItem dagger) {
        HANDHELD_LAYERED.upload(
            ModelIds.getItemModelId(dagger),
            TextureMap.layered(TextureMap.getId(dagger), AdditionalArmouryMain.identifier("item/dagger_tip")),
            itemModelGenerator.writer
        );
    }

    private static void registerMass (ItemModelGenerator itemModelGenerator, Model model, Item... items) {
        for (Item i : items) {
            itemModelGenerator.register(i, model);
        }
    }

    private static Model createModel (String parent, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private void registerArmour (ArmorItem item, ItemModelGenerator itemModelGenerator) {
        EquippableComponent equippableComponent = item.getComponents().get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent == null || equippableComponent.model().isEmpty()) {
            AdditionalArmouryMain.LOGGER.warn("No equippable component found for {}", item.getName().getString());
            return;
        }

        Identifier modelId = equippableComponent.model().get();
        EquipmentModel model = EquipmentModel.builder().addHumanoidLayers(modelId).build();

        itemModelGenerator.registerArmor(item, modelId, model, equippableComponent.slot());
    }
}
