package com.provismet.AdditionalArmoury.utility.tags;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class AAEnchantmentTags {
    public static final TagKey<Enchantment> STAFF = AAEnchantmentTags.of("staff");

    public static final TagKey<Enchantment> ADHESIVE_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/adhesive");
    public static final TagKey<Enchantment> THROW_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/boomerang_throw");
    public static final TagKey<Enchantment> BOOMERANG_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/boomerang_effect");

    private static TagKey<Enchantment> of (String path) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, AdditionalArmouryMain.identifier(path));
    }
}
