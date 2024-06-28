package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.enchantment.effect.doubleEntity.ApplyToTargetEntityEnchantmentEffect;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.CodeExecutionSingleEntityEffect;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.DamageEquipmentEffect;
import com.provismet.CombatPlusCore.registries.CPCEnchantmentComponentTypes;
import com.provismet.CombatPlusCore.utility.tag.CPCEnchantmentTags;
import net.minecraft.block.Block;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.List;

public class AAEnchantments {
    public static final RegistryKey<Enchantment> BOOST = AAEnchantments.of("boost");
    public static final RegistryKey<Enchantment> ERUPTION = AAEnchantments.of("eruption");
    public static final RegistryKey<Enchantment> JUMP = AAEnchantments.of("jump");
    public static final RegistryKey<Enchantment> FIREBALL = AAEnchantments.of("fireball");
    public static final RegistryKey<Enchantment> FROSTBALL = AAEnchantments.of("frostball");
    public static final RegistryKey<Enchantment> GHOSTLY_ORB = AAEnchantments.of("ghostly_orb");
    public static final RegistryKey<Enchantment> GALE = AAEnchantments.of("gale");
    public static final RegistryKey<Enchantment> MAGIC_MISSILE = AAEnchantments.of("missile");
    public static final RegistryKey<Enchantment> EXPLOSION = AAEnchantments.of("explosion");

    public static final RegistryKey<Enchantment> ADHESIVE = AAEnchantments.of("adhesive");
    public static final RegistryKey<Enchantment> SPLATTER = AAEnchantments.of("splatter");

    public static final RegistryKey<Enchantment> SHREDDING = AAEnchantments.of("shredding");
    public static final RegistryKey<Enchantment> DISMANTLE = AAEnchantments.of("dismantle");

    public static final RegistryKey<Enchantment> RICOCHET = AAEnchantments.of("ricochet");
    public static final RegistryKey<Enchantment> MULTITHROW = AAEnchantments.of("multithrow");
    public static final RegistryKey<Enchantment> FAR_THROW = AAEnchantments.of("throw_distance");
    public static final RegistryKey<Enchantment> STRONG_THROW = AAEnchantments.of("throw_strength");

    private static RegistryKey<Enchantment> of (String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, AdditionalArmouryMain.identifier(name));
    }

    // This only executes as part of data generation. It does NOT create files, it only pre-loads the registry.
    public static void bootstrap (Registerable<Enchantment> registerable) {
        RegistryEntryLookup<DamageType> damageLookup = registerable.getRegistryLookup(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> enchantmentLookup = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> blockLookup = registerable.getRegistryLookup(RegistryKeys.BLOCK);

        register(registerable, BOOST, buildStaff(itemLookup, "boost", 0, 50, 10, 64, 0xFF7AFFE6, 10, 2).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, JUMP, buildStaff(itemLookup, "jump", 0, 50, 10, 32, 0xFFA2C663, 10, 2).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, FIREBALL, buildStaff(itemLookup, "fireball", 0, 50, 20, 64, 0xFFFF331F, 8, 2).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, FROSTBALL, buildStaff(itemLookup, "frostball", 0, 50, 20, 64, 0xFFBADCFF, 8, 2).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, ERUPTION, buildStaff(itemLookup, "eruption", "eruption_tick", 10, 75, 30, 64, 0xFF7F3C18, 5, 4).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, GALE, buildStaff(itemLookup, "gale", 10, 60, 30, 32, 0xFFFFFFFF, 5, 5).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, MAGIC_MISSILE, buildStaff(itemLookup, "missile", 20, 70, 20, 96, 0xFF975DFF, 3, 5).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, GHOSTLY_ORB, buildStaff(itemLookup, "ghostly_orb", 20, 70, 20, 64, 0xFF6B6B6B, 3, 5).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));
        register(registerable, EXPLOSION, buildStaff(itemLookup, "explosion", "explosion_tick", 50, 100, 160, 16, 0xFFCE0000, 1, 8).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF)));

        register(registerable, ADHESIVE, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE), 2, 1, Enchantment.constantCost(20), Enchantment.constantCost(50), 6, AttributeModifierSlot.MAINHAND, AttributeModifierSlot.OFFHAND)).addEffect(AAEnchantmentComponentTypes.INFINITE_POTION).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)));
        register(registerable, SPLATTER, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE), 10, 2, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND, AttributeModifierSlot.OFFHAND)).addEffect(AAEnchantmentComponentTypes.EFFECT_RADIUS, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.5f, 0.5f))).exclusiveSet(enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_OFFHAND_EXCLUSIVE)));

        register(registerable, SHREDDING, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND)).addEffect(AAEnchantmentComponentTypes.EFFECT_DURATION, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(20))).exclusiveSet(enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)));
        register(registerable, DISMANTLE, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND)).addEffect(CPCEnchantmentComponentTypes.POST_CRITICAL_ATTACK, new ApplyToTargetEntityEnchantmentEffect(new DamageEquipmentEffect(List.of(EquipmentSlot.HEAD, EquipmentSlot.BODY, EquipmentSlot.LEGS, EquipmentSlot.FEET), EnchantmentLevelBasedValue.linear(2)))).exclusiveSet(enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)));

        register(registerable, RICOCHET, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 4, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(AAEnchantmentComponentTypes.RICOCHET, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1))).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)));
        register(registerable, MULTITHROW, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE), 5, 1, Enchantment.constantCost(20), Enchantment.constantCost(75), 4)).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_COUNT, new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(2))).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_SPREAD, new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(10))).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)));
        register(registerable, STRONG_THROW, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1)), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().type(AAEntityTypes.BOOMERANG).build())).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)));
        register(registerable, FAR_THROW, Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(AAEnchantmentComponentTypes.THROW_DISTANCE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(5))).exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)));
    }

    public static Enchantment.Builder buildStaff (RegistryEntryLookup<Item> itemLookup, String activeEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.STAFF_ENCHANTABLE), weight, 1, Enchantment.constantCost(minCost), Enchantment.constantCost(maxCost), anvilCost, AttributeModifierSlot.MAINHAND))
            .addEffect(AAEnchantmentComponentTypes.ON_ACTIVATION, new CodeExecutionSingleEntityEffect(AdditionalArmouryMain.identifier(activeEffect)))
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_CAST_DURATION, duration)
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_USES, maxUses)
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_COLOUR, colour);
    }

    public static Enchantment.Builder buildStaff (RegistryEntryLookup<Item> itemLookup, String activeEffect, String incantationEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return buildStaff(itemLookup, activeEffect, minCost, maxCost, duration, maxUses, colour, weight, anvilCost)
            .addEffect(AAEnchantmentComponentTypes.TICK_INCANTATION, new LambdaIncantationEffect(AdditionalArmouryMain.identifier(incantationEffect)));
    }

    public static void register (Registerable<Enchantment> registry, RegistryKey<Enchantment> enchantment, Enchantment.Builder builder) {
        registry.register(enchantment, builder.build(enchantment.getValue()));
    }
}
