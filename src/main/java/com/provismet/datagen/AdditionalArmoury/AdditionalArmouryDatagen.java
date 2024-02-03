package com.provismet.datagen.AdditionalArmoury;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AdditionalArmouryDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator (FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(TagGenerator::new);
        pack.addProvider(BlockTagGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(LanguageGenerator::new);
        pack.addProvider(LanguageGeneratorUK::new);
        pack.addProvider(RecipeGenerator::new);
        pack.addProvider(AdvancementGenerator::new);
        pack.addProvider(LootTableGenerator::new);
    }
}
