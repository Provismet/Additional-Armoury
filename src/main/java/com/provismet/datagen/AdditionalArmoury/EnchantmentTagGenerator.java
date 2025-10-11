package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;

import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.CombatPlusCore.utility.tag.CPCEnchantmentTags;
import com.provismet.lilylib.datagen.tag.LilyTagProviders;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.EnchantmentTags;

public class EnchantmentTagGenerator extends LilyTagProviders.LilyEnchantmentTagProvider {
    public EnchantmentTagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void configure (WrapperLookup arg) {
        this.builder(CPCEnchantmentTags.WEAPON_UTILITY)
            .add(AAEnchantments.SPLATTER.getKey())
            .add(AAEnchantments.DISMANTLE.getKey())
            .add(AAEnchantments.SHREDDING.getKey());

        this.builder(AAEnchantmentTags.STAFF)
            .add(AAEnchantments.BOOST.getKey())
            .add(AAEnchantments.JUMP.getKey())
            .add(AAEnchantments.FIREBALL.getKey())
            .add(AAEnchantments.FROSTBALL.getKey())
            .add(AAEnchantments.ERUPTION.getKey())
            .add(AAEnchantments.GALE.getKey())
            .add(AAEnchantments.MAGIC_MISSILE.getKey())
            .add(AAEnchantments.GHOSTLY_ORB.getKey())
            .add(AAEnchantments.EXPLOSION.getKey());

        this.builder(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)
            .add(Enchantments.MENDING);

        this.builder(AAEnchantmentTags.THROW_EXCLUSIVE)
            .add(AAEnchantments.STRONG_THROW.getKey())
            .add(AAEnchantments.FAR_THROW.getKey());

        this.builder(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
            .add(AAEnchantments.RICOCHET.getKey())
            .add(AAEnchantments.MULTITHROW.getKey());

        this.builder(EnchantmentTags.IN_ENCHANTING_TABLE)
            .add(AAEnchantments.SPLATTER.getKey(), AAEnchantments.ADHESIVE.getKey())
            .add(AAEnchantments.DISMANTLE.getKey(), AAEnchantments.SHREDDING.getKey())
            .add(AAEnchantments.RICOCHET.getKey(), AAEnchantments.MULTITHROW.getKey(), AAEnchantments.STRONG_THROW.getKey(), AAEnchantments.FAR_THROW.getKey())
            .add(AAEnchantments.BOOST.getKey(), AAEnchantments.JUMP.getKey(), AAEnchantments.FIREBALL.getKey(), AAEnchantments.FROSTBALL.getKey(), AAEnchantments.ERUPTION.getKey(), AAEnchantments.GALE.getKey(), AAEnchantments.MAGIC_MISSILE.getKey(), AAEnchantments.GHOSTLY_ORB.getKey());

        this.builder(EnchantmentTags.TRADEABLE)
            .add(AAEnchantments.SPLATTER.getKey(), AAEnchantments.ADHESIVE.getKey())
            .add(AAEnchantments.DISMANTLE.getKey(), AAEnchantments.SHREDDING.getKey())
            .add(AAEnchantments.RICOCHET.getKey(), AAEnchantments.MULTITHROW.getKey(), AAEnchantments.STRONG_THROW.getKey(), AAEnchantments.FAR_THROW.getKey())
            .add(AAEnchantments.BOOST.getKey(), AAEnchantments.JUMP.getKey(), AAEnchantments.FIREBALL.getKey(), AAEnchantments.FROSTBALL.getKey(), AAEnchantments.ERUPTION.getKey(), AAEnchantments.GALE.getKey(), AAEnchantments.MAGIC_MISSILE.getKey(), AAEnchantments.GHOSTLY_ORB.getKey());

        this.builder(EnchantmentTags.NON_TREASURE)
            .add(AAEnchantments.SPLATTER.getKey(), AAEnchantments.ADHESIVE.getKey())
            .add(AAEnchantments.DISMANTLE.getKey(), AAEnchantments.SHREDDING.getKey())
            .add(AAEnchantments.RICOCHET.getKey(), AAEnchantments.MULTITHROW.getKey(), AAEnchantments.STRONG_THROW.getKey(), AAEnchantments.FAR_THROW.getKey())
            .add(AAEnchantments.BOOST.getKey(), AAEnchantments.JUMP.getKey(), AAEnchantments.FIREBALL.getKey(), AAEnchantments.FROSTBALL.getKey(), AAEnchantments.ERUPTION.getKey(), AAEnchantments.GALE.getKey(), AAEnchantments.MAGIC_MISSILE.getKey(), AAEnchantments.GHOSTLY_ORB.getKey());

        this.builder(EnchantmentTags.TREASURE)
            .add(AAEnchantments.EXPLOSION.getKey());
    }
    
}
