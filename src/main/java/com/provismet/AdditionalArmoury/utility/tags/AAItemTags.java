package com.provismet.AdditionalArmoury.utility.tags;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AAItems;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class AAItemTags {
    public static final TagKey<Item> DAGGERS = AAItemTags.of("daggers");
    public static final TagKey<Item> MACES = AAItemTags.of("maces");

    public static final TagKey<Item> DAGGER_ENCHANTABLE = AAItemTags.of("enchantable/daggers");
    public static final TagKey<Item> MACE_ENCHANTABLE = AAItemTags.of("enchantable/maces");
    public static final TagKey<Item> BOOMERANG_ENCHANTABLE = AAItemTags.of("enchantable/boomerang");
    public static final TagKey<Item> STAFF_ENCHANTABLE = AAItemTags.of("enchantable/staff");

    public static final TagKey<Item> OVERNETHER_TOOL_MATERIALS = AAItemTags.of("overnether_tool_materials");
    public static final TagKey<Item> ENDERNETHER_TOOL_MATERIALS = AAItemTags.of("endernether_tool_materials");

    public static final TagKey<Item> REPAIRS_OVERNETHER_ARMOUR = AAItemTags.of("repairs_overnether_armour");
    public static final TagKey<Item> REPAIRS_ENDERNETHER_ARMOUR = AAItemTags.of("repairs_endernether_armour");

    private static TagKey<Item> of (String path) {
        return TagKey.of(RegistryKeys.ITEM, AdditionalArmouryMain.identifier(path));
    }
}
