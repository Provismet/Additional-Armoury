package com.provismet.additionalarmoury.datagen;

import com.provismet.additionalarmoury.datagen.generator.AdvancementGenerator;
import com.provismet.additionalarmoury.datagen.generator.BlockTagGenerator;
import com.provismet.additionalarmoury.datagen.generator.DamageTypeGenerator;
import com.provismet.additionalarmoury.datagen.generator.DamageTypeTagGenerator;
import com.provismet.additionalarmoury.datagen.generator.EnchantmentGenerator;
import com.provismet.additionalarmoury.datagen.generator.EnchantmentTagGenerator;
import com.provismet.additionalarmoury.datagen.generator.EquipmentModelGenerator;
import com.provismet.additionalarmoury.datagen.generator.ItemTagGenerator;
import com.provismet.additionalarmoury.datagen.generator.LanguageGenerator;
import com.provismet.additionalarmoury.datagen.generator.LanguageGeneratorUK;
import com.provismet.additionalarmoury.datagen.generator.LootTableGenerator;
import com.provismet.additionalarmoury.datagen.generator.ModelGenerator;
import com.provismet.additionalarmoury.datagen.generator.ParticleGenerator;
import com.provismet.additionalarmoury.datagen.generator.RecipeProvider;
import com.provismet.additionalarmoury.datagen.generator.SoundGenerator;
import com.provismet.additionalarmoury.registries.AAEnchantments;
import com.provismet.additionalarmoury.utility.AADamageTypes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

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
    public void buildRegistry (RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.DAMAGE_TYPE, AADamageTypes::bootstrap);
        registryBuilder.add(Registries.ENCHANTMENT, AAEnchantments::bootstrap);
    }
}
