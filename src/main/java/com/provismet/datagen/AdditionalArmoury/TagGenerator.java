package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AATags.ItemTags;
import com.provismet.CombatPlusCore.utility.CombatTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class TagGenerator extends ItemTagProvider {

    public TagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.DAGGERS)
            .add(AAItems.WOODEN_DAGGER)
            .add(AAItems.STONE_DAGGER)
            .add(AAItems.GOLD_DAGGER)
            .add(AAItems.IRON_DAGGER)
            .add(AAItems.DIAMOND_DAGGER)
            .add(AAItems.NETHERITE_DAGGER);

        getOrCreateTagBuilder(CombatTags.DUAL_WEAPON)
            .addOptionalTag(ItemTags.DAGGERS);
    }
    
}
