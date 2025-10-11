package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;

import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.utility.tag.CPCItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ItemTagGenerator extends ItemTagProvider {
    private static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "hidden_from_recipe_viewers"));

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
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

        this.valueLookupBuilder(CPCItemTags.MELEE_WEAPON)
            .addOptionalTag(AAItemTags.MACES);

        this.valueLookupBuilder(CPCItemTags.DUAL_WEAPON)
            .addOptionalTag(AAItemTags.DAGGERS);

        this.valueLookupBuilder(CPCItemTags.DAMAGE_PRIMARY_ENCHANTABLE)
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
