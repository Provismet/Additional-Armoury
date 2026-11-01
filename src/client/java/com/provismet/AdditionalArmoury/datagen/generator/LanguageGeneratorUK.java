package com.provismet.AdditionalArmoury.datagen.generator;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;

import com.provismet.lilylib.datagen.provider.LilyLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class LanguageGeneratorUK extends LilyLanguageProvider {
    public LanguageGeneratorUK (FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_gb", registryLookup);
    }

    @Override
    public void generateTranslations (HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        LanguageGenerator.addAdvancement(translationBuilder, "overnether", "I Am Listening", "Get a full suit of Overnether armour");
        LanguageGenerator.addAdvancement(translationBuilder, "endernether", "Part Of The Chorus", "Get a full suit of Endernether armour");

        this.addEnchantment(translationBuilder, AAEnchantments.DISMANTLE, "Dismantling", "Increases damage dealt to armour from maces.");
    }
}
