package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantments.staff.BoostEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.EruptionEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.FireballEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.FrostballEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.JumpEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.StaffEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAEnchantments {
    public static final StaffEnchantment BOOST = new BoostEnchantment();
    public static final StaffEnchantment ERUPTION = new EruptionEnchantment();
    public static final StaffEnchantment JUMP = new JumpEnchantment();
    public static final StaffEnchantment FIREBALL = new FireballEnchantment();
    public static final StaffEnchantment FROSTBALL = new FrostballEnchantment();

    private static void register (String name, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, AdditionalArmouryMain.identifier(name), enchantment);
    }

    public static void register () {
        register("boost", BOOST);
        register("eruption", ERUPTION);
        register("jump", JUMP);
        register("fireball", FIREBALL);
        register("frostball", FROSTBALL);
    }
}
