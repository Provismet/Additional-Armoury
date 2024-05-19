package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {
    protected LootTableGenerator (FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate () {
        addDrop(AABlocks.OVERNETHER_BLOCK);
        addDrop(AABlocks.ENDERNETHER_BLOCK);
    }
    
}
