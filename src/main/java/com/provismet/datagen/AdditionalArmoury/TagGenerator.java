package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AATags;
import com.provismet.CombatPlusCore.utility.CombatTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
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

        getOrCreateTagBuilder(CombatTags.MELEE_WEAPON)
            .addOptionalTag(AATags.ItemTags.MACES);

        getOrCreateTagBuilder(CombatTags.DUAL_WEAPON)
            .addOptionalTag(AATags.ItemTags.DAGGERS);
        
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
    }
    
}
