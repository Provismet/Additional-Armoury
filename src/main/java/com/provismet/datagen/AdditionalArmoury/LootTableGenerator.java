package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AABlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class LootTableGenerator extends FabricBlockLootTableProvider {
    protected LootTableGenerator (FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate () {
        addDrop(AABlocks.OVERNETHER_BLOCK);
        addDrop(AABlocks.ENDERNETHER_BLOCK);
    }
    
}
