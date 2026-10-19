package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BlockTagGenerator extends BlockTagProvider {
    public BlockTagGenerator (FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags (Provider arg) {
        this.valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);

        this.valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);

        this.valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(AABlocks.OVERNETHER_BLOCK)
            .add(AABlocks.ENDERNETHER_BLOCK);
    }
    
}
