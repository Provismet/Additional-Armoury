package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class AABlocks {
    public static final Block OVERNETHER_BLOCK = registerCopy("overnether_block", Blocks.NETHERITE_BLOCK, settings -> new Block(settings.mapColor(MapColor.CYAN)));
    public static final Block ENDERNETHER_BLOCK = registerCopy("endernether_block", Blocks.NETHERITE_BLOCK, settings -> new Block(settings.mapColor(MapColor.PURPLE)));

    private static <T extends Block> T registerCopy (String name, Block baseBlock, Function<AbstractBlock.Settings, T> settingsFunction) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, AdditionalArmouryMain.identifier(name));
        T block = settingsFunction.apply(AbstractBlock.Settings.copy(baseBlock).registryKey(key));
        Registry.register(Registries.BLOCK, key, block);
        return block;
    }

    public static void init () {}
}
