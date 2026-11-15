package com.provismet.additionalarmoury.datagen.generator;

import com.provismet.additionalarmoury.item.DaggerItem;
import com.provismet.additionalarmoury.item.MaceItem;
import com.provismet.additionalarmoury.references.AAItemIds;
import com.provismet.additionalarmoury.registries.AAItems;
import com.provismet.additionalarmoury.utility.tags.AAItemTags;
import com.provismet.combatplus.api.tag.CombatPlusItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.ItemTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    private static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "hidden_from_recipe_viewers"));

    public ItemTagGenerator(FabricPackOutput output, CompletableFuture<Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags (Provider arg) {
        this.builder(AAItemTags.DAGGERS)
            .add(AAItemIds.WOODEN_DAGGER)
            .add(AAItemIds.STONE_DAGGER)
            .add(AAItemIds.COPPER_DAGGER)
            .add(AAItemIds.GOLDEN_DAGGER)
            .add(AAItemIds.IRON_DAGGER)
            .add(AAItemIds.DIAMOND_DAGGER)
            .add(AAItemIds.NETHERITE_DAGGER)
            .add(AAItemIds.OVERNETHER_DAGGER)
            .add(AAItemIds.ENDERNETHER_DAGGER);

        this.builder(AAItemTags.MACES)
            .add(AAItemIds.WOODEN_MACE)
            .add(AAItemIds.STONE_MACE)
            .add(AAItemIds.COPPER_MACE)
            .add(AAItemIds.GOLDEN_MACE)
            .add(AAItemIds.IRON_MACE)
            .add(AAItemIds.DIAMOND_MACE)
            .add(AAItemIds.NETHERITE_MACE)
            .add(AAItemIds.OVERNETHER_MACE)
            .add(AAItemIds.ENDERNETHER_MACE);

        this.builder(AAItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS);

        this.builder(AAItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AAItemTags.MACES);

        this.builder(AAItemTags.BOOMERANG_ENCHANTABLE)
            .add(AAItemIds.BOOMERANG);

        this.builder(AAItemTags.STAFF_ENCHANTABLE)
            .add(AAItemIds.STAFF);

        this.builder(CombatPlusItemTags.MELEE_WEAPON)
            .addOptionalTag(AAItemTags.MACES);

        this.builder(CombatPlusItemTags.DUAL_WEAPON)
            .addOptionalTag(AAItemTags.DAGGERS);

        this.builder(CombatPlusItemTags.DAMAGE_PRIMARY_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES);

        this.builder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES);

        this.builder(HIDDEN_FROM_RECIPE_VIEWERS)
            .add(AAItemIds.FIREBALL_SPELL)
            .add(AAItemIds.FROSTBALL_SPELL)
            .add(AAItemIds.GHOSTLY_ORB_SPELL)
            .add(AAItemIds.WIND_TORNADO_SPELL)
            .add(AAItemIds.MISSILE_SPELL);

        this.builder(ItemTags.SWORDS)
            .add(AAItemIds.OVERNETHER_SWORD)
            .add(AAItemIds.ENDERNETHER_SWORD);

        this.builder(ItemTags.SPEARS)
            .add(AAItemIds.OVERNETHER_SPEAR)
            .add(AAItemIds.ENDERNETHER_SPEAR);

        this.builder(ItemTags.AXES)
            .add(AAItemIds.OVERNETHER_AXE)
            .add(AAItemIds.ENDERNETHER_AXE);

        this.builder(ItemTags.PICKAXES)
            .add(AAItemIds.OVERNETHER_PICKAXE)
            .add(AAItemIds.ENDERNETHER_PICKAXE);

        this.builder(ItemTags.SHOVELS)
            .add(AAItemIds.OVERNETHER_SHOVEL)
            .add(AAItemIds.ENDERNETHER_SHOVEL);

        this.builder(ItemTags.HOES)
            .add(AAItemIds.OVERNETHER_HOE)
            .add(AAItemIds.ENDERNETHER_HOE);

        this.builder(ItemTags.TRIMMABLE_ARMOR)
            .add(AAItemIds.OVERNETHER_HELMET)
            .add(AAItemIds.OVERNETHER_CHESTPLATE)
            .add(AAItemIds.OVERNETHER_LEGGINGS)
            .add(AAItemIds.OVERNETHER_BOOTS)
            .add(AAItemIds.ENDERNETHER_HELMET)
            .add(AAItemIds.ENDERNETHER_CHESTPLATE)
            .add(AAItemIds.ENDERNETHER_LEGGINGS)
            .add(AAItemIds.ENDERNETHER_BOOTS);

        this.builder(ItemTags.HEAD_ARMOR)
            .add(AAItemIds.OVERNETHER_HELMET)
            .add(AAItemIds.ENDERNETHER_HELMET);

        this.builder(ItemTags.CHEST_ARMOR)
            .add(AAItemIds.OVERNETHER_CHESTPLATE)
            .add(AAItemIds.ENDERNETHER_CHESTPLATE);

        this.builder(ItemTags.LEG_ARMOR)
            .add(AAItemIds.OVERNETHER_LEGGINGS)
            .add(AAItemIds.ENDERNETHER_LEGGINGS);

        this.builder(ItemTags.FOOT_ARMOR)
            .add(AAItemIds.OVERNETHER_BOOTS)
            .add(AAItemIds.ENDERNETHER_BOOTS);

        this.builder(ItemTags.BEACON_PAYMENT_ITEMS)
            .add(AAItemIds.OVERNETHER_INGOT)
            .add(AAItemIds.ENDERNETHER_INGOT);

        this.builder(ItemTags.VANISHING_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES)
            .add(AAItemIds.BOOMERANG);

        this.builder(ItemTags.DURABILITY_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES)
            .add(AAItemIds.BOOMERANG);

        this.builder(ConventionalItemTags.ENCHANTABLES)
            .addOptionalTag(AAItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AAItemTags.BOOMERANG_ENCHANTABLE);
            // STAFF_ENCHANTABLE is omitted just in case other mods do something with this tag.
    }
    
}
