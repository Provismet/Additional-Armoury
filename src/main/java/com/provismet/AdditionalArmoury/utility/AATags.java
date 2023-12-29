package com.provismet.AdditionalArmoury.utility;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class AATags {
    public static class ItemTags {
        public static final TagKey<Item> DAGGERS = ItemTags.of("daggers");

        private static TagKey<Item> of (String path) {
            return TagKey.of(RegistryKeys.ITEM, AdditionalArmouryMain.identifier(path));
        }
    }
}
