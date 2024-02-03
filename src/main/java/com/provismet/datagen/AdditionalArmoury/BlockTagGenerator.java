package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;

public class BlockTagGenerator extends BlockTagProvider {
    public BlockTagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);
    }
    
}
