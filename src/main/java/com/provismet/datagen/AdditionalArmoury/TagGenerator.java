package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AATags;
import com.provismet.CombatPlusCore.utility.CPCItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagGenerator extends ItemTagProvider {
    private static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "hidden_from_recipe_viewers"));

    public TagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
        getOrCreateTagBuilder(AATags.ItemTags.DAGGERS)
            .add(AAItems.DAGGERS.toArray(new DaggerItem[0]));

        getOrCreateTagBuilder(AATags.ItemTags.MACES)
            .add(AAItems.MACES.toArray(new MaceItem[0]));

        getOrCreateTagBuilder(AATags.ItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGERS);

        getOrCreateTagBuilder(AATags.ItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.MACES);

        getOrCreateTagBuilder(AATags.ItemTags.BOOMERANG_ENCHANTABLE)
            .add(AAItems.BOOMERANG);

        getOrCreateTagBuilder(AATags.ItemTags.STAFF_ENCHANTABLE)
            .add(AAItems.STAFF);

        getOrCreateTagBuilder(CPCItemTags.MELEE_WEAPON)
            .addOptionalTag(AATags.ItemTags.MACES);

        getOrCreateTagBuilder(CPCItemTags.DUAL_WEAPON)
            .addOptionalTag(AATags.ItemTags.DAGGERS);

        getOrCreateTagBuilder(CPCItemTags.DAMAGE_PRIMARY_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGERS)
            .addOptionalTag(AATags.ItemTags.MACES);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGERS)
            .addOptionalTag(AATags.ItemTags.MACES);
        
        getOrCreateTagBuilder(HIDDEN_FROM_RECIPE_VIEWERS)
            .add(AAItems.ITEM_PROJECTILES.toArray(new Item[0]));

        getOrCreateTagBuilder(ItemTags.SWORDS)
            .add(AAItems.OVERNETHER_SWORD)
            .add(AAItems.ENDERNETHER_SWORD);

        getOrCreateTagBuilder(ItemTags.AXES)
            .add(AAItems.OVERNETHER_AXE)
            .add(AAItems.ENDERNETHER_AXE);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
            .add(AAItems.OVERNETHER_PICKAXE)
            .add(AAItems.ENDERNETHER_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
            .add(AAItems.OVERNETHER_SHOVEL)
            .add(AAItems.ENDERNETHER_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
            .add(AAItems.OVERNETHER_HOE)
            .add(AAItems.ENDERNETHER_HOE);
        
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(AAItems.OVERNETHER_ARMOUR.toArray(new ArmorItem[0]))
            .add(AAItems.ENDERNETHER_ARMOUR.toArray(new ArmorItem[0]));

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
            .add(AAItems.OVERNETHER_HELMET)
            .add(AAItems.ENDERNETHER_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
            .add(AAItems.OVERNETHER_CHESTPLATE)
            .add(AAItems.ENDERNETHER_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
            .add(AAItems.OVERNETHER_LEGGINGS)
            .add(AAItems.ENDERNETHER_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
            .add(AAItems.OVERNETHER_BOOTS)
            .add(AAItems.ENDERNETHER_BOOTS);

        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
            .add(AAItems.OVERNETHER_INGOT)
            .add(AAItems.ENDERNETHER_INGOT);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGERS)
            .addOptionalTag(AATags.ItemTags.MACES)
            .add(AAItems.BOOMERANG);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGERS)
            .addOptionalTag(AATags.ItemTags.MACES)
            .add(AAItems.BOOMERANG);

        getOrCreateTagBuilder(ConventionalItemTags.ENCHANTABLES)
            .addOptionalTag(AATags.ItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AATags.ItemTags.BOOMERANG_ENCHANTABLE);
            // AATags.ItemTags.STAFF_ENCHANTABLE is omitted just in case other mods do something with this tag.
    }
    
}
