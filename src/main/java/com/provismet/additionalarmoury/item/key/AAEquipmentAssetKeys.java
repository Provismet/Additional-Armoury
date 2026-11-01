package com.provismet.additionalarmoury.item.key;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public abstract class AAEquipmentAssetKeys {
    public static final ResourceKey<EquipmentAsset> OVERNETHER = register("overnether");
    public static final ResourceKey<EquipmentAsset> ENDERNETHER = register("endernether");

    private static ResourceKey<EquipmentAsset> register (String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, AdditionalArmouryMain.identifier(name));
    }
}
