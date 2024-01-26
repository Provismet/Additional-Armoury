package com.provismet.AdditionalArmoury.utility;

import org.jetbrains.annotations.Nullable;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AADamageSources {
    private static final RegistryKey<DamageType> FIREBALL = createDamageType("fireball_spell");
    private static final RegistryKey<DamageType> FROSTBALL = createDamageType("frostball_spell");
    private static final RegistryKey<DamageType> ERUPTION = createDamageType("eruption_spell");
    private static final RegistryKey<DamageType> BOOMERANG = createDamageType("boomerang");

    public static DamageSource fireball (Entity source, @Nullable Entity attacker) {
        return AADamageSources.create(source.getDamageSources(), FIREBALL, source, attacker);
    }

    public static DamageSource frostball (Entity source, @Nullable Entity attacker) {
        return AADamageSources.create(source.getDamageSources(), FROSTBALL, source, attacker);
    }

    public static DamageSource eruption (Entity attacker) {
        return AADamageSources.create(attacker.getDamageSources(), ERUPTION, attacker);
    }

    public static DamageSource boomerang (Entity source, @Nullable Entity attacker) {
        return AADamageSources.create(source.getDamageSources(), BOOMERANG, source, attacker);
    }

    @SuppressWarnings("unused")
    private static DamageSource create (DamageSources sources, RegistryKey<DamageType> damageType) {
        return sources.create(damageType);
    }

    private static DamageSource create (DamageSources sources, RegistryKey<DamageType> damageType, Entity attacker) {
        return sources.create(damageType, attacker);
    }

    private static DamageSource create (DamageSources sources, RegistryKey<DamageType> damageType, Entity source, @Nullable Entity attacker) {
        return sources.create(damageType, source, attacker);
    }

    private static final RegistryKey<DamageType> createDamageType (String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, AdditionalArmouryMain.identifier(name));
    }
}
