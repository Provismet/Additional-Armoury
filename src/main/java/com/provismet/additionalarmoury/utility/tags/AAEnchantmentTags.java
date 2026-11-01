package com.provismet.additionalarmoury.utility.tags;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class AAEnchantmentTags {
    public static final TagKey<Enchantment> STAFF = AAEnchantmentTags.of("staff");

    public static final TagKey<Enchantment> ADHESIVE_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/adhesive");
    public static final TagKey<Enchantment> THROW_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/boomerang_throw");
    public static final TagKey<Enchantment> BOOMERANG_EXCLUSIVE = AAEnchantmentTags.of("exclusive_set/boomerang_effect");

    private static TagKey<Enchantment> of (String path) {
        return TagKey.create(Registries.ENCHANTMENT, AdditionalArmouryMain.identifier(path));
    }
}
