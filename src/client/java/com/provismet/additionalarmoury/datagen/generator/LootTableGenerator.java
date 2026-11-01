package com.provismet.additionalarmoury.datagen.generator;

import com.provismet.additionalarmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootSubProvider {
    public LootTableGenerator (FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate () {
        dropSelf(AABlocks.OVERNETHER_BLOCK);
        dropSelf(AABlocks.ENDERNETHER_BLOCK);
    }
}
