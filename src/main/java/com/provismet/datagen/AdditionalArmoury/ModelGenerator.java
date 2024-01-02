package com.provismet.datagen.AdditionalArmoury;

import java.util.Optional;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public class ModelGenerator extends FabricModelProvider {
    public static final Model HANDHELD_LAYERED = createModel("handheld", TextureKey.LAYER0, TextureKey.LAYER1);

    public ModelGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels (BlockStateModelGenerator blockStateModelGenerator) {
        
    }

    @Override
    public void generateItemModels (ItemModelGenerator itemModelGenerator) {
        AAItems.DAGGERS.forEach(dagger -> ModelGenerator.registerDagger(itemModelGenerator, dagger));
        HANDHELD_LAYERED.upload(ModelIds.getItemModelId(AAItems.STAFF), TextureMap.layered(AdditionalArmouryMain.identifier("item/staff_head"), AdditionalArmouryMain.identifier("item/staff_shaft")), itemModelGenerator.writer);

        AAItems.ITEM_PROJECTILES.forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }

    public static void registerDagger (ItemModelGenerator itemModelGenerator, DaggerItem dagger) {
        HANDHELD_LAYERED.upload(
            ModelIds.getItemModelId(dagger),
            TextureMap.layered(TextureMap.getId(dagger), AdditionalArmouryMain.identifier("item/dagger_tip")),
            itemModelGenerator.writer
        );
    }

    private static Model createModel (String parent, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("minecraft", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
