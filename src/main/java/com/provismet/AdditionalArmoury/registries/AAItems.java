package com.provismet.AdditionalArmoury.registries;

import java.util.Arrays;
import java.util.List;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.DaggerItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAItems {
    public static final DaggerItem WOODEN_DAGGER = new DaggerItem(ToolMaterials.WOOD, new FabricItemSettings(), 0x372910);
    public static final DaggerItem STONE_DAGGER = new DaggerItem(ToolMaterials.STONE, new FabricItemSettings(), 0x494949);
    public static final DaggerItem GOLDEN_DAGGER = new DaggerItem(ToolMaterials.GOLD, new FabricItemSettings(), 0x825D16);
    public static final DaggerItem IRON_DAGGER = new DaggerItem(ToolMaterials.IRON, new FabricItemSettings(), 0x444444);
    public static final DaggerItem DIAMOND_DAGGER = new DaggerItem(ToolMaterials.DIAMOND, new FabricItemSettings(), 0x0E3F36);
    public static final DaggerItem NETHERITE_DAGGER = new DaggerItem(ToolMaterials.NETHERITE, new FabricItemSettings().fireproof(), 0x4A2940);

    // List representations so I don't have to update the generators and other registries.
    public static final List<DaggerItem> DAGGERS = Arrays.asList(WOODEN_DAGGER, STONE_DAGGER, GOLDEN_DAGGER, IRON_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER);

    private static void register (Item item, String name) {
        Registry.register(Registries.ITEM, AdditionalArmouryMain.identifier(name), item);
    }

    public static void register () {
        register(WOODEN_DAGGER, "wooden_dagger");
        register(STONE_DAGGER, "stone_dagger");
        register(GOLDEN_DAGGER, "golden_dagger");
        register(IRON_DAGGER, "iron_dagger");
        register(DIAMOND_DAGGER, "diamond_dagger");
        register(NETHERITE_DAGGER, "netherite_dagger");
    }
}
