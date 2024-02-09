package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EnchantmentTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class EnchantmentTagGenerator extends EnchantmentTagProvider {
    public EnchantmentTagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
        getOrCreateTagBuilder(CPCEnchantmentTags.WEAPON_UTILITY)
            .add(AAEnchantments.SPLATTER)
            .add(AAEnchantments.DISMANTLE)
            .add(AAEnchantments.SHREDDING);
    }
    
}
