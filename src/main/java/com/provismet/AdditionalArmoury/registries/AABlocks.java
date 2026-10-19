package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class AABlocks {
    public static final Block OVERNETHER_BLOCK = registerCopy("overnether_block", Blocks.NETHERITE_BLOCK, settings -> new Block(settings.mapColor(MapColor.COLOR_CYAN)));
    public static final Block ENDERNETHER_BLOCK = registerCopy("endernether_block", Blocks.NETHERITE_BLOCK, settings -> new Block(settings.mapColor(MapColor.COLOR_PURPLE)));

    private static <T extends Block> T registerCopy (String name, Block baseBlock, Function<BlockBehaviour.Properties, T> settingsFunction) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, AdditionalArmouryMain.identifier(name));
        T block = settingsFunction.apply(BlockBehaviour.Properties.ofFullCopy(baseBlock).setId(key));
        Registry.register(BuiltInRegistries.BLOCK, key, block);
        return block;
    }

    public static void init () {}
}
