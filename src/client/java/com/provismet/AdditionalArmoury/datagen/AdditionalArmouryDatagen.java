package com.provismet.AdditionalArmoury.datagen;

import com.provismet.AdditionalArmoury.datagen.generator.AdvancementGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.BlockTagGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.DamageTypeGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.DamageTypeTagGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.EnchantmentGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.EnchantmentTagGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.EquipmentModelGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.ItemTagGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.LanguageGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.LanguageGeneratorUK;
import com.provismet.AdditionalArmoury.datagen.generator.LootTableGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.ModelGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.ParticleGenerator;
import com.provismet.AdditionalArmoury.datagen.generator.RecipeProvider;
import com.provismet.AdditionalArmoury.datagen.generator.SoundGenerator;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
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
