package com.provismet.AdditionalArmoury.utility;

import com.provismet.lilylib.container.DamageTypeContainer;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.registry.Registerable;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.entity.damage.DamageType;

public class AADamageTypes {
    public static final DamageTypeContainer FIREBALL = createDamageType("fireball_spell", DamageEffects.BURNING);
    public static final DamageTypeContainer FROSTBALL = createDamageType("frostball_spell", DamageEffects.FREEZING);
    public static final DamageTypeContainer ERUPTION = createDamageType("eruption_spell", DamageEffects.BURNING);
    public static final DamageTypeContainer GHOSTLY_ORB = createDamageType("ghostly_orb_spell");
    public static final DamageTypeContainer WIND_TORNADO = createDamageType("wind_tornado_spell");
    public static final DamageTypeContainer MAGIC_MISSILE = createDamageType("missile_spell");
    public static final DamageTypeContainer BOOMERANG = createDamageType("boomerang");

    private static DamageTypeContainer createDamageType (String name, DamageEffects effects) {
        return new DamageTypeContainer(
            AdditionalArmouryMain.identifier(name),
            new DamageType(name, 0.1f, effects)
        );
    }

    private static DamageTypeContainer createDamageType (String name) {
        return new DamageTypeContainer(
            AdditionalArmouryMain.identifier(name),
            new DamageType(name, 0.1f)
        );
    }

    private static void register (Registerable<DamageType> registry, DamageTypeContainer container) {
        registry.register(container.getKey(), container.getDamageType());
    }

    public static void bootstrap (Registerable<DamageType> registerable) {
       register(registerable, FIREBALL);
       register(registerable, FROSTBALL);
       register(registerable, ERUPTION);
       register(registerable, GHOSTLY_ORB);
       register(registerable, WIND_TORNADO);
       register(registerable, MAGIC_MISSILE);
       register(registerable, BOOMERANG);
    }
}
