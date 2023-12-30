package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantments.staff.BoostEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.PushBackEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.StaffEnchantment;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAEnchantments {
    public static final StaffEnchantment FORCE = new PushBackEnchantment();
    public static final StaffEnchantment BOOST = new BoostEnchantment();

    public static void register () {
        //Registry.register(Registries.ENCHANTMENT, AdditionalArmouryMain.identifier("force"), FORCE);
        Registry.register(Registries.ENCHANTMENT, AdditionalArmouryMain.identifier("boost"), BOOST);
    }
}
