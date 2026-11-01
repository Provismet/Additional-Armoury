package com.provismet.AdditionalArmoury.datagen.generator;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AASounds;
import com.provismet.lilylib.datagen.provider.LilySoundProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class SoundGenerator extends LilySoundProvider {
    public SoundGenerator (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected void generateSoundFile (HolderLookup.Provider wrapperLookup, SoundWriter soundWriter) {
        soundWriter.add(
            AASounds.BOOMERANG_THROW,
            "subtitles.additional-armoury.boomerang.throw",
            AdditionalArmouryMain.identifier("boomerang_1"),
            AdditionalArmouryMain.identifier("boomerang_2"),
            AdditionalArmouryMain.identifier("boomerang_3")
        );

        soundWriter.add(
            AASounds.STAFF_CAST,
            "subtitles.additional-armoury.staff.cast",
            AdditionalArmouryMain.identifier("staff_cast")
        );
    }
}
