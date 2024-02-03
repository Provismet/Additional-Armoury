package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AABlocks {
    public static final Block OVERNETHER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.CYAN));
    public static final Block ENDERNETHER_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK).mapColor(MapColor.PURPLE));

    private static void register (Block block, String name) {
        Registry.register(Registries.BLOCK, AdditionalArmouryMain.identifier(name), block);
    }

    public static void register () {
        register(OVERNETHER_BLOCK, "overnether_block");
        register(ENDERNETHER_BLOCK, "endernether_block");
    }
}
