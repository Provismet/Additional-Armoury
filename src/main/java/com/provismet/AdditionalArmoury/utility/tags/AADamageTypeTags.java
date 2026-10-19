package com.provismet.AdditionalArmoury.utility.tags;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public abstract class AADamageTypeTags {
    public static final TagKey<DamageType> STAFF_SPELLS = AADamageTypeTags.of("staff_spells");

    private static TagKey<DamageType> of (String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, AdditionalArmouryMain.identifier(path));
    }
}
