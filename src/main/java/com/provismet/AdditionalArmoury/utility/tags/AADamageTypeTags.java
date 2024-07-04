package com.provismet.AdditionalArmoury.utility.tags;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public abstract class AADamageTypeTags {
    public static final TagKey<DamageType> STAFF_SPELLS = AADamageTypeTags.of("staff_spells");

    private static TagKey<DamageType> of (String path) {
        return TagKey.of(RegistryKeys.DAMAGE_TYPE, AdditionalArmouryMain.identifier(path));
    }
}
