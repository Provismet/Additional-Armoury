package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;

import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class AAItemGroups {

    public static void register () {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> content.addAfter(Items.NETHERITE_BLOCK, AAItems.OVERNETHER_BLOCK, AAItems.ENDERNETHER_BLOCK));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_SWORD, AAItems.OVERNETHER_SWORD, AAItems.ENDERNETHER_SWORD));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_AXE, AAItems.OVERNETHER_AXE, AAItems.ENDERNETHER_AXE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.addAfter(Items.NETHERITE_HOE, AAItems.OVERNETHER_SHOVEL, AAItems.OVERNETHER_PICKAXE, AAItems.OVERNETHER_AXE, AAItems.OVERNETHER_HOE, AAItems.ENDERNETHER_SHOVEL, AAItems.ENDERNETHER_PICKAXE, AAItems.ENDERNETHER_AXE, AAItems.ENDERNETHER_HOE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(AAItems.ENDERNETHER_SWORD, AAItems.DAGGERS.toArray(new DaggerItem[0])));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(AAItems.ENDERNETHER_AXE, AAItems.MACES.toArray(new MaceItem[0])));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.TRIDENT, AAItems.STAFF));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.CROSSBOW, AAItems.BOOMERANG));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.NETHERITE_INGOT, AAItems.OVERNETHER_INGOT, AAItems.ENDERNETHER_INGOT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_BOOTS, AAItems.OVERNETHER_ARMOUR.toArray(new ArmorItem[0])));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(AAItems.OVERNETHER_BOOTS, AAItems.ENDERNETHER_ARMOUR.toArray(new ArmorItem[0])));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.getContext().lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent(wrapper -> {
                wrapper.streamEntries()
                    .filter(entry -> entry.isIn(AAEnchantmentTags.STAFF)).filter(entry -> entry.getKey().isPresent())
                    .map(entry -> {
                        ItemStack stack = AAItems.STAFF.getDefaultStack();
                        stack.addEnchantment(wrapper.getOrThrow(entry.registryKey()), 1);
                        return stack;
                    })
                    .forEach(content::add);
            });
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.getContext().lookup().getOptional(RegistryKeys.POTION).ifPresent(wrapper -> {
                AAItems.DAGGERS.forEach(dagger -> addPotions(content, wrapper, dagger, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS, content.getEnabledFeatures()));
            });
        });
    }

    private static void addPotions (ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, ItemGroup.StackVisibility visibility, FeatureSet enabledFeatures) {
        registryWrapper.streamEntries()
            .filter(entry -> entry.value().isEnabled(enabledFeatures))
            .map(entry -> PotionContentsComponent.createStack(item, entry))
            .peek(entry -> entry.set(AADataComponentTypes.USES, 64))
            .forEach(stack -> entries.add(stack, visibility));
    }
}
