package com.provismet.datagen.AdditionalArmoury;

import java.util.concurrent.CompletableFuture;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.utility.AATags.ItemTags;
import com.provismet.CombatPlusCore.utility.CombatTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagGenerator extends ItemTagProvider {
    private static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "hidden_from_recipe_viewers"));

    public TagGenerator (FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.DAGGERS)
            .add(AAItems.DAGGERS.toArray(new DaggerItem[0]));

        getOrCreateTagBuilder(CombatTags.DUAL_WEAPON)
            .addOptionalTag(ItemTags.DAGGERS);
        
        getOrCreateTagBuilder(HIDDEN_FROM_RECIPE_VIEWERS)
            .add(AAItems.ITEM_PROJECTILES.toArray(new Item[0]));
    }
    
}
