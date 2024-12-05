package com.provismet.AdditionalArmoury.items.key;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;

public abstract class AAEquipmentAssetKeys {
    public static final RegistryKey<EquipmentAsset> OVERNETHER = register("overnether");
    public static final RegistryKey<EquipmentAsset> ENDERNETHER = register("endernether");

    private static RegistryKey<EquipmentAsset> register (String name) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, AdditionalArmouryMain.identifier(name));
    }
}
