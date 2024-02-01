package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantments.boomerang.BoomerangEnchantment;
import com.provismet.AdditionalArmoury.enchantments.boomerang.DistanceEnchantment;
import com.provismet.AdditionalArmoury.enchantments.boomerang.MultiThrowEnchantment;
import com.provismet.AdditionalArmoury.enchantments.boomerang.RicochetEnchantment;
import com.provismet.AdditionalArmoury.enchantments.boomerang.ThrowStrengthEnchantment;
import com.provismet.AdditionalArmoury.enchantments.dagger.AdhesiveEnchantment;
import com.provismet.AdditionalArmoury.enchantments.dagger.DaggerEnchantment;
import com.provismet.AdditionalArmoury.enchantments.dagger.SplatterEnchantment;
import com.provismet.AdditionalArmoury.enchantments.mace.ShreddingEnchantment;
import com.provismet.AdditionalArmoury.enchantments.mace.DismantleEnchantment;
import com.provismet.AdditionalArmoury.enchantments.mace.MaceEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.BoostEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.EruptionEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.ExplosionEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.FireballEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.FrostballEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.GaleEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.GhostOrbEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.JumpEnchantment;
import com.provismet.AdditionalArmoury.enchantments.staff.MissileEnchantment;
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
    public static final StaffEnchantment GHOSTLY_ORB = new GhostOrbEnchantment();
    public static final StaffEnchantment GALE = new GaleEnchantment();
    public static final StaffEnchantment MAGIC_MISSILE = new MissileEnchantment();
    public static final StaffEnchantment EXPLOSION = new ExplosionEnchantment();

    public static final DaggerEnchantment ADHESIVE = new AdhesiveEnchantment();
    public static final DaggerEnchantment SPLATTER = new SplatterEnchantment();

    public static final MaceEnchantment SHREDDING = new ShreddingEnchantment();
    public static final MaceEnchantment DISMANTLE = new DismantleEnchantment();

    public static final BoomerangEnchantment RICOCHET = new RicochetEnchantment();
    public static final BoomerangEnchantment MULTITHROW = new MultiThrowEnchantment();
    public static final BoomerangEnchantment FAR_THROW = new DistanceEnchantment();
    public static final BoomerangEnchantment STRONG_THROW = new ThrowStrengthEnchantment();

    private static void register (String name, Enchantment enchantment) {
        Registry.register(Registries.ENCHANTMENT, AdditionalArmouryMain.identifier(name), enchantment);
    }

    public static void register () {
        register("boost", BOOST);
        register("eruption", ERUPTION);
        register("jump", JUMP);
        register("fireball", FIREBALL);
        register("frostball", FROSTBALL);
        register("ghostly_orb", GHOSTLY_ORB);
        register("gale", GALE);
        register("missile", MAGIC_MISSILE);
        register("explosion", EXPLOSION);

        register("adhesive", ADHESIVE);
        register("splatter", SPLATTER);

        register("shredding", SHREDDING);
        register("dismantle", DISMANTLE);

        register("ricochet", RICOCHET);
        register("multithrow", MULTITHROW);
        register("throw_distance", FAR_THROW);
        register("throw_strength", STRONG_THROW);
    }
}
