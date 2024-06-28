package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;

import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.CombatPlusCore.utility.tag.CPCEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EnchantmentTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.EnchantmentTags;

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

        getOrCreateTagBuilder(AAEnchantmentTags.STAFF)
            .add(AAEnchantments.BOOST)
            .add(AAEnchantments.JUMP)
            .add(AAEnchantments.FIREBALL)
            .add(AAEnchantments.FROSTBALL)
            .add(AAEnchantments.ERUPTION)
            .add(AAEnchantments.GALE)
            .add(AAEnchantments.MAGIC_MISSILE)
            .add(AAEnchantments.GHOSTLY_ORB)
            .add(AAEnchantments.EXPLOSION);

        getOrCreateTagBuilder(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)
            .add(Enchantments.MENDING);

        getOrCreateTagBuilder(AAEnchantmentTags.THROW_EXCLUSIVE)
            .add(AAEnchantments.STRONG_THROW)
            .add(AAEnchantments.FAR_THROW);

        getOrCreateTagBuilder(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
            .add(AAEnchantments.RICOCHET)
            .add(AAEnchantments.MULTITHROW);

        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
            .add(AAEnchantments.SPLATTER, AAEnchantments.ADHESIVE)
            .add(AAEnchantments.DISMANTLE, AAEnchantments.SHREDDING)
            .add(AAEnchantments.RICOCHET, AAEnchantments.MULTITHROW, AAEnchantments.STRONG_THROW, AAEnchantments.FAR_THROW)
            .add(AAEnchantments.BOOST, AAEnchantments.JUMP, AAEnchantments.FIREBALL, AAEnchantments.FROSTBALL, AAEnchantments.ERUPTION, AAEnchantments.GALE, AAEnchantments.MAGIC_MISSILE, AAEnchantments.GHOSTLY_ORB);

        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE)
            .add(AAEnchantments.SPLATTER, AAEnchantments.ADHESIVE)
            .add(AAEnchantments.DISMANTLE, AAEnchantments.SHREDDING)
            .add(AAEnchantments.RICOCHET, AAEnchantments.MULTITHROW, AAEnchantments.STRONG_THROW, AAEnchantments.FAR_THROW)
            .add(AAEnchantments.BOOST, AAEnchantments.JUMP, AAEnchantments.FIREBALL, AAEnchantments.FROSTBALL, AAEnchantments.ERUPTION, AAEnchantments.GALE, AAEnchantments.MAGIC_MISSILE, AAEnchantments.GHOSTLY_ORB);

        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
            .add(AAEnchantments.SPLATTER, AAEnchantments.ADHESIVE)
            .add(AAEnchantments.DISMANTLE, AAEnchantments.SHREDDING)
            .add(AAEnchantments.RICOCHET, AAEnchantments.MULTITHROW, AAEnchantments.STRONG_THROW, AAEnchantments.FAR_THROW)
            .add(AAEnchantments.BOOST, AAEnchantments.JUMP, AAEnchantments.FIREBALL, AAEnchantments.FROSTBALL, AAEnchantments.ERUPTION, AAEnchantments.GALE, AAEnchantments.MAGIC_MISSILE, AAEnchantments.GHOSTLY_ORB);

        getOrCreateTagBuilder(EnchantmentTags.TREASURE)
            .add(AAEnchantments.EXPLOSION);
    }
    
}
