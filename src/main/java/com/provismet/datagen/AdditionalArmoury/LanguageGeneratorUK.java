package com.provismet.datagen.AdditionalArmoury;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LanguageGeneratorUK extends FabricLanguageProvider {
    protected LanguageGeneratorUK (FabricDataOutput dataOutput) {
        super(dataOutput, "en_gb");
    }

    @Override
    public void generateTranslations (TranslationBuilder translationBuilder) {
        LanguageGenerator.addAdvancement(translationBuilder, "overnether", "I Am Listening", "Get a full suit of Overnether armour");
        LanguageGenerator.addAdvancement(translationBuilder, "endernether", "Part Of The Chorus", "Get a full suit of Endernether armour");
    }
}
