package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAItems {
    public static final DaggerItem WOODEN_DAGGER = new DaggerItem(ToolMaterials.WOOD, new FabricItemSettings());
    public static final DaggerItem STONE_DAGGER = new DaggerItem(ToolMaterials.STONE, new FabricItemSettings());
    public static final DaggerItem GOLD_DAGGER = new DaggerItem(ToolMaterials.GOLD, new FabricItemSettings());
    public static final DaggerItem IRON_DAGGER = new DaggerItem(ToolMaterials.IRON, new FabricItemSettings());
    public static final DaggerItem DIAMOND_DAGGER = new DaggerItem(ToolMaterials.DIAMOND, new FabricItemSettings());
    public static final DaggerItem NETHERITE_DAGGER = new DaggerItem(ToolMaterials.NETHERITE, new FabricItemSettings().fireproof());

    private static void register (Item item, String name) {
        Registry.register(Registries.ITEM, AdditionalArmouryMain.identifier(name), item);
    }

    public static void register () {
        register(WOODEN_DAGGER, "wooden_dagger");
        register(STONE_DAGGER, "stone_dagger");
        register(GOLD_DAGGER, "gold_dagger");
        register(IRON_DAGGER, "iron_dagger");
        register(DIAMOND_DAGGER, "diamond_dagger");
        register(NETHERITE_DAGGER, "netherite_dagger");
    }    
}
