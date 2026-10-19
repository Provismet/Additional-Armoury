package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;

import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

public class AAItemGroups {

    public static void register () {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(content -> content.addAfter(Items.NETHERITE_BLOCK, AAItems.OVERNETHER_BLOCK, AAItems.ENDERNETHER_BLOCK));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(Items.NETHERITE_SWORD, AAItems.OVERNETHER_SWORD, AAItems.ENDERNETHER_SWORD));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(Items.NETHERITE_AXE, AAItems.OVERNETHER_AXE, AAItems.ENDERNETHER_AXE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> content.addAfter(Items.NETHERITE_HOE, AAItems.OVERNETHER_SHOVEL, AAItems.OVERNETHER_PICKAXE, AAItems.OVERNETHER_AXE, AAItems.OVERNETHER_HOE, AAItems.ENDERNETHER_SHOVEL, AAItems.ENDERNETHER_PICKAXE, AAItems.ENDERNETHER_AXE, AAItems.ENDERNETHER_HOE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(AAItems.ENDERNETHER_SWORD, AAItems.DAGGERS.toArray(new DaggerItem[0])));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(AAItems.ENDERNETHER_AXE, AAItems.MACES.toArray(new MaceItem[0])));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(Items.TRIDENT, AAItems.STAFF));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(Items.CROSSBOW, AAItems.BOOMERANG));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> content.addAfter(Items.NETHERITE_INGOT, AAItems.OVERNETHER_INGOT, AAItems.ENDERNETHER_INGOT));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> content.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(Items.NETHERITE_BOOTS, AAItems.OVERNETHER_ARMOUR.toArray(new Item[0])));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> content.addAfter(AAItems.OVERNETHER_BOOTS, AAItems.ENDERNETHER_ARMOUR.toArray(new Item[0])));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
            content.getContext().holders().lookup(Registries.ENCHANTMENT).ifPresent(wrapper -> {
                wrapper.listElements()
                    .filter(entry -> entry.is(AAEnchantmentTags.STAFF)).filter(entry -> entry.unwrapKey().isPresent())
                    .map(entry -> {
                        ItemStack stack = AAItems.STAFF.getDefaultInstance();
                        stack.enchant(wrapper.getOrThrow(entry.key()), 1);
                        return stack;
                    })
                    .forEach(content::accept);
            });
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
            content.getContext().holders().lookup(Registries.POTION).ifPresent(wrapper -> {
                AAItems.DAGGERS.forEach(dagger -> addPotions(content, wrapper, dagger, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, content.getEnabledFeatures()));
            });
        });
    }

    private static void addPotions (CreativeModeTab.Output entries, HolderLookup<Potion> registryWrapper, Item item, CreativeModeTab.TabVisibility visibility, FeatureFlagSet enabledFeatures) {
        registryWrapper.listElements()
            .filter(entry -> entry.value().isEnabled(enabledFeatures))
            .map(entry -> PotionContents.createItemStack(item, entry))
            .peek(entry -> entry.set(AADataComponentTypes.USES, 64))
            .forEach(stack -> entries.accept(stack, visibility));
    }
}
