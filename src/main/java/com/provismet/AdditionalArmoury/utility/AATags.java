package com.provismet.AdditionalArmoury.utility;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class AATags {
    public static class ItemTags {
        public static final TagKey<Item> DAGGERS = ItemTags.of("daggers");
        public static final TagKey<Item> MACES = ItemTags.of("maces");

        public static final TagKey<Item> DAGGER_ENCHANTABLE = ItemTags.of("enchantable/daggers");
        public static final TagKey<Item> MACE_ENCHANTABLE = ItemTags.of("enchantable/maces");
        public static final TagKey<Item> BOOMERANG_ENCHANTABLE = ItemTags.of("enchantable/boomerang");
        public static final TagKey<Item> STAFF_ENCHANTABLE = ItemTags.of("enchantable/staff");

        private static TagKey<Item> of (String path) {
            return TagKey.of(RegistryKeys.ITEM, AdditionalArmouryMain.identifier(path));
        }
    }
}
