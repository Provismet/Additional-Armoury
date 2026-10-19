package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;

import com.provismet.lilylib.datagen.provider.LilyLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class LanguageGeneratorUK extends LilyLanguageProvider {
    protected LanguageGeneratorUK(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_gb", registryLookup);
    }

    @Override
    public void generateTranslations (HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        LanguageGenerator.addAdvancement(translationBuilder, "overnether", "I Am Listening", "Get a full suit of Overnether armour");
        LanguageGenerator.addAdvancement(translationBuilder, "endernether", "Part Of The Chorus", "Get a full suit of Endernether armour");

        this.addEnchantment(translationBuilder, AAEnchantments.DISMANTLE, "Dismantling", "Increases damage dealt to armour from maces.");
    }
}
