package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootSubProvider {
    protected LootTableGenerator (FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate () {
        dropSelf(AABlocks.OVERNETHER_BLOCK);
        dropSelf(AABlocks.ENDERNETHER_BLOCK);
    }
}
