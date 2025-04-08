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
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.data.Model;
import net.minecraft.client.data.ModelIds;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.render.item.tint.ConstantTintSource;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Colors;
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
        ModelGenerator.registerStaff(itemModelGenerator, AAItems.STAFF);

        AAItems.MACES.forEach(item -> itemModelGenerator.register(item, Models.HANDHELD));
        AAItems.ITEM_PROJECTILES.forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
        AAItems.OVERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
        AAItems.ENDERNETHER_ARMOUR.forEach(item -> registerArmour(item, itemModelGenerator));
    }

    public static void registerDagger (ItemModelGenerator itemModelGenerator, DaggerItem dagger) {
        Identifier model = HANDHELD_LAYERED.upload(
            ModelIds.getItemModelId(dagger),
            TextureMap.layered(
                TextureMap.getId(dagger),
                AdditionalArmouryMain.identifier("item/dagger_tip")
            ),
            itemModelGenerator.modelCollector
        );
        itemModelGenerator.output.accept(dagger, ItemModels.tinted(model, new ConstantTintSource(Colors.WHITE), new DaggerTintSource()));
    }

    public static void registerStaff (ItemModelGenerator itemModelGenerator, StaffItem staff) {
        Identifier model = HANDHELD_LAYERED.upload(
            ModelIds.getItemModelId(staff),
            TextureMap.layered(
                AdditionalArmouryMain.identifier("item/staff_head"),
                AdditionalArmouryMain.identifier("item/staff_shaft")),
            itemModelGenerator.modelCollector
        );
        itemModelGenerator.output.accept(staff, ItemModels.tinted(model, new SpellTintSource(0xFFC18920)));
    }

    private static void registerMass (ItemModelGenerator itemModelGenerator, Model model, Item... items) {
        for (Item i : items) {
            itemModelGenerator.register(i, model);
        }
    }

    private static Model createModel (String parent, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.ofVanilla("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private void registerArmour (Item item, ItemModelGenerator itemModelGenerator) {
        final Map<EquipmentSlot, Identifier> slotToTrim = Map.of(
            EquipmentSlot.HEAD, ItemModelGenerator.HELMET_TRIM_ID_PREFIX,
            EquipmentSlot.CHEST, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX,
            EquipmentSlot.LEGS, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX,
            EquipmentSlot.FEET, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX,
            EquipmentSlot.BODY, ItemModelGenerator.getTrimAssetIdPrefix("body")
        );

        EquippableComponent equippableComponent = item.getComponents().get(DataComponentTypes.EQUIPPABLE);
        if (equippableComponent == null || equippableComponent.assetId().isEmpty()) {
            AdditionalArmouryMain.LOGGER.warn("No equippable component found for {}", item.getName().getString());
            return;
        }
        RegistryKey<EquipmentAsset> modelId = equippableComponent.assetId().get();
        itemModelGenerator.registerArmor(item, modelId, slotToTrim.get(equippableComponent.slot()), false);
    }
}
