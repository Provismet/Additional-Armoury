package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class AdditionalArmouryDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator (FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(EnchantmentGenerator::new);
        pack.addProvider(DamageTypeGenerator::new);
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(BlockTagGenerator::new);
        pack.addProvider(EnchantmentTagGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(LanguageGenerator::new);
        pack.addProvider(LanguageGeneratorUK::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(AdvancementGenerator::new);
        pack.addProvider(LootTableGenerator::new);
        pack.addProvider(DamageTypeTagGenerator::new);
        pack.addProvider(SoundGenerator::new);
        pack.addProvider(ParticleGenerator::new);
        pack.addProvider(EquipmentModelGenerator::new);
    }

    @Override
    public void buildRegistry (RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, AADamageTypes::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, AAEnchantments::bootstrap);
    }
}
