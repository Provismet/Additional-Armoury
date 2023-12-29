package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.items.DaggerItem;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

public class AAItemGroups {

    public static void register () {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_SWORD, AAItems.DAGGERS.toArray(new DaggerItem[0])));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.getContext().lookup().getOptionalWrapper(RegistryKeys.POTION).ifPresent(wrapper -> {
                AAItems.DAGGERS.forEach(dagger -> addPotions(content, wrapper, dagger, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
            });
        });
    }

    private static void addPotions (ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, ItemGroup.StackVisibility visibility) {
        registryWrapper.streamEntries().filter(entry -> !entry.matchesKey(Potions.EMPTY_KEY)).map(entry -> PotionUtil.setPotion(new ItemStack(item), (Potion)entry.value())).forEach(stack -> entries.add((ItemStack)stack, visibility));
    }
}
