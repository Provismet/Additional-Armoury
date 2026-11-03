package com.provismet.additionalarmoury.datagen.generator;

import com.provismet.additionalarmoury.item.DaggerItem;
import com.provismet.additionalarmoury.item.MaceItem;
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
        this.valueLookupBuilder(AAItemTags.DAGGERS)
            .add(AAItems.DAGGERS.toArray(new DaggerItem[0]));

        this.valueLookupBuilder(AAItemTags.MACES)
            .add(AAItems.MACES.toArray(new MaceItem[0]));

        this.valueLookupBuilder(AAItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS);

        this.valueLookupBuilder(AAItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AAItemTags.MACES);

        this.valueLookupBuilder(AAItemTags.BOOMERANG_ENCHANTABLE)
            .add(AAItems.BOOMERANG);

        this.valueLookupBuilder(AAItemTags.STAFF_ENCHANTABLE)
            .add(AAItems.STAFF);

        this.valueLookupBuilder(CombatPlusItemTags.MELEE_WEAPON)
            .addOptionalTag(AAItemTags.MACES);

        this.valueLookupBuilder(CombatPlusItemTags.DUAL_WEAPON)
            .addOptionalTag(AAItemTags.DAGGERS);

        this.valueLookupBuilder(CombatPlusItemTags.DAMAGE_PRIMARY_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES);

        this.valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES);

        this.valueLookupBuilder(HIDDEN_FROM_RECIPE_VIEWERS)
            .add(AAItems.ITEM_PROJECTILES.toArray(new Item[0]));

        this.valueLookupBuilder(ItemTags.SWORDS)
            .add(AAItems.OVERNETHER_SWORD)
            .add(AAItems.ENDERNETHER_SWORD);

        this.valueLookupBuilder(ItemTags.SPEARS)
            .add(AAItems.OVERNETHER_SPEAR)
            .add(AAItems.ENDERNETHER_SPEAR);

        this.valueLookupBuilder(ItemTags.AXES)
            .add(AAItems.OVERNETHER_AXE)
            .add(AAItems.ENDERNETHER_AXE);

        this.valueLookupBuilder(ItemTags.PICKAXES)
            .add(AAItems.OVERNETHER_PICKAXE)
            .add(AAItems.ENDERNETHER_PICKAXE);

        this.valueLookupBuilder(ItemTags.SHOVELS)
            .add(AAItems.OVERNETHER_SHOVEL)
            .add(AAItems.ENDERNETHER_SHOVEL);

        this.valueLookupBuilder(ItemTags.HOES)
            .add(AAItems.OVERNETHER_HOE)
            .add(AAItems.ENDERNETHER_HOE);

        this.valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(AAItems.OVERNETHER_ARMOUR.toArray(new Item[0]))
            .add(AAItems.ENDERNETHER_ARMOUR.toArray(new Item[0]));

        this.valueLookupBuilder(ItemTags.HEAD_ARMOR)
            .add(AAItems.OVERNETHER_HELMET)
            .add(AAItems.ENDERNETHER_HELMET);

        this.valueLookupBuilder(ItemTags.CHEST_ARMOR)
            .add(AAItems.OVERNETHER_CHESTPLATE)
            .add(AAItems.ENDERNETHER_CHESTPLATE);

        this.valueLookupBuilder(ItemTags.LEG_ARMOR)
            .add(AAItems.OVERNETHER_LEGGINGS)
            .add(AAItems.ENDERNETHER_LEGGINGS);

        this.valueLookupBuilder(ItemTags.FOOT_ARMOR)
            .add(AAItems.OVERNETHER_BOOTS)
            .add(AAItems.ENDERNETHER_BOOTS);

        this.valueLookupBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
            .add(AAItems.OVERNETHER_INGOT)
            .add(AAItems.ENDERNETHER_INGOT);

        this.valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES)
            .add(AAItems.BOOMERANG);

        this.valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGERS)
            .addOptionalTag(AAItemTags.MACES)
            .add(AAItems.BOOMERANG);

        this.valueLookupBuilder(ConventionalItemTags.ENCHANTABLES)
            .addOptionalTag(AAItemTags.MACE_ENCHANTABLE)
            .addOptionalTag(AAItemTags.DAGGER_ENCHANTABLE)
            .addOptionalTag(AAItemTags.BOOMERANG_ENCHANTABLE);
            // STAFF_ENCHANTABLE is omitted just in case other mods do something with this tag.
    }
    
}
