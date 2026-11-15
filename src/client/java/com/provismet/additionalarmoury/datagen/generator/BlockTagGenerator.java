package com.provismet.additionalarmoury.datagen.generator;

import java.util.concurrent.CompletableFuture;

import com.provismet.additionalarmoury.references.AABlockItemIds;
import com.provismet.additionalarmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator (FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags (Provider arg) {
        this.builder(BlockTags.BEACON_BASE_BLOCKS)
            .add(AABlockItemIds.OVERNETHER_BLOCK)
            .add(AABlockItemIds.ENDERNETHER_BLOCK);

        this.builder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(AABlockItemIds.OVERNETHER_BLOCK)
            .add(AABlockItemIds.ENDERNETHER_BLOCK);

        this.builder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(AABlockItemIds.OVERNETHER_BLOCK)
            .add(AABlockItemIds.ENDERNETHER_BLOCK);
    }
    
}
