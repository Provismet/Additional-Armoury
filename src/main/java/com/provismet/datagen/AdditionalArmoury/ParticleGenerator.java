package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParticleGenerator extends LilyParticleTextureProvider {
    protected ParticleGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup registryLookup, ParticleWriter writer) {
        writer.add(
            AAParticleTypes.INK_SPLAT,
            count(AdditionalArmouryMain.identifier("ink_splat"), 1, 5)
        );

        writer.add(
            AAParticleTypes.SHATTER,
            count(AdditionalArmouryMain.identifier("shatter"), 1, 10)
        );

        writer.add(
            AAParticleTypes.SPELL_CHARGE,
            AdditionalArmouryMain.identifier("spell_charge")
        );

        writer.add(
            AAParticleTypes.SPELL_RING,
            AdditionalArmouryMain.identifier("spell_ring")
        );
    }

    private List<Identifier> count (Identifier base, int minInclusive, int maxExclusive) {
        List<Identifier> ids = new ArrayList<>();
        for (int i = minInclusive; i < maxExclusive; ++i) {
            ids.add(Identifier.of(base.getNamespace(), base.getPath() + "_" + i));
        }
        return ids;
    }
}
