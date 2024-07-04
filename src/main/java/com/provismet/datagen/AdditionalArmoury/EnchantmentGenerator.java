package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.lilylib.datagen.provider.LilyEnchantmentProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnchantmentGenerator extends LilyEnchantmentProvider {
    public EnchantmentGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, EnchantmentBuilder builder) {
        builder.add(AAEnchantments.BOOST);
        builder.add(AAEnchantments.JUMP);
        builder.add(AAEnchantments.FIREBALL);
        builder.add(AAEnchantments.FROSTBALL);
        builder.add(AAEnchantments.ERUPTION);
        builder.add(AAEnchantments.GALE);
        builder.add(AAEnchantments.MAGIC_MISSILE);
        builder.add(AAEnchantments.GHOSTLY_ORB);
        builder.add(AAEnchantments.EXPLOSION);

        builder.add(AAEnchantments.ADHESIVE);
        builder.add(AAEnchantments.SPLATTER);

        builder.add(AAEnchantments.SHREDDING);
        builder.add(AAEnchantments.DISMANTLE);

        builder.add(AAEnchantments.RICOCHET);
        builder.add(AAEnchantments.MULTITHROW);
        builder.add(AAEnchantments.STRONG_THROW);
        builder.add(AAEnchantments.FAR_THROW);
    }
}
