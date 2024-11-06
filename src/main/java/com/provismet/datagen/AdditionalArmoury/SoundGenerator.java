package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AASounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SoundGenerator extends LilySoundProvider {
    protected SoundGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected void generateSoundFile (SoundWriter writer) {
        writer.add(
            AASounds.BOOMERANG_THROW,
            "subtitles.additional-armoury.boomerang.throw",
            AdditionalArmouryMain.identifier("boomerang_1"),
            AdditionalArmouryMain.identifier("boomerang_2"),
            AdditionalArmouryMain.identifier("boomerang_3")
        );

        writer.add(
            AASounds.STAFF_CAST,
            "subtitles.additional-armoury.staff.cast",
            AdditionalArmouryMain.identifier("staff_cast")
        );
    }
}
