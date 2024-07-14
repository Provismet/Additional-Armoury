package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.enchantment.effect.doubleEntity.ApplyToTargetEntityEffect;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.CodeExecutionSingleEntityEffect;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.DamageEquipmentEffect;
import com.provismet.CombatPlusCore.registries.CPCEnchantmentComponentTypes;
import com.provismet.CombatPlusCore.utility.tag.CPCEnchantmentTags;
import com.provismet.lilylib.container.EnchantmentContainer;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;

import java.util.List;

public class AAEnchantments {
    public static final EnchantmentContainer BOOST = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("boost"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "boost", 0, 50, 10, 64, 0xFF7AFFE6, 10, 2)
    );
    public static final EnchantmentContainer JUMP = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("jump"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "jump", 0, 50, 10, 32, 0xFFA2C663, 10, 2)
    );
    public static final EnchantmentContainer FIREBALL = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("fireball"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "fireball", 0, 50, 20, 64, 0xFFFF331F, 8, 2)
    );
    public static final EnchantmentContainer FROSTBALL = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("frostball"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "frostball", 0, 50, 20, 64, 0xFFBADCFF, 8, 2)
    );
    public static final EnchantmentContainer ERUPTION = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("eruption"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "eruption", "eruption_tick", 10, 75, 30, 64, 0xFF7F3C18, 5, 4)
    );
    public static final EnchantmentContainer GALE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("gale"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "gale", 10, 60, 30, 32, 0xFFFFFFFF, 5, 5)
    );
    public static final EnchantmentContainer MAGIC_MISSILE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("missile"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "missile", 20, 70, 20, 96, 0xFF975DFF, 3, 5)
    );
    public static final EnchantmentContainer GHOSTLY_ORB = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("ghostly_orb"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "ghostly_orb", 20, 70, 20, 64, 0xFF6B6B6B, 3, 5)
    );
    public static final EnchantmentContainer EXPLOSION = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("explosion"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "explosion", "explosion_tick", 50, 100, 160, 16, 0xFFCE0000, 1, 8)
            .addEffect(AAEnchantmentComponentTypes.NO_SPELL_PARTICLES)
    );

    public static final EnchantmentContainer ADHESIVE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("adhesive"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE),
                2,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                6,
                AttributeModifierSlot.MAINHAND,
                AttributeModifierSlot.OFFHAND)
        ).addEffect(
            AAEnchantmentComponentTypes.INFINITE_POTION
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer SPLATTER = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("splatter"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE),
                10,
                2,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2,
                AttributeModifierSlot.MAINHAND,
                AttributeModifierSlot.OFFHAND)
        ).addEffect(
            AAEnchantmentComponentTypes.EFFECT_RADIUS,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(1.5f, 0.5f)
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_OFFHAND_EXCLUSIVE)
        )
    );

    public static final EnchantmentContainer SHREDDING = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("shredding"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE),
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2,
                AttributeModifierSlot.MAINHAND
            )
        ).addEffect(
            AAEnchantmentComponentTypes.EFFECT_DURATION,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(20)
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer DISMANTLE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("dismantle"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE),
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(50, 10),
                2,
                AttributeModifierSlot.MAINHAND
            )
        ).addEffect(
            CPCEnchantmentComponentTypes.POST_CRITICAL_ATTACK,
            new ApplyToTargetEntityEffect(
                new DamageEquipmentEffect(
                    List.of(EquipmentSlot.HEAD, EquipmentSlot.BODY, EquipmentSlot.LEGS, EquipmentSlot.FEET),
                    EnchantmentLevelBasedValue.linear(2)
                )
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)
        )
    );

    public static final EnchantmentContainer RICOCHET = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("ricochet"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                4,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(30, 10),
                2)
        ).addEffect(
            AAEnchantmentComponentTypes.RICOCHET,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(1)
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer MULTITHROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("multithrow"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                5,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(75),
                4)
        ).addEffect(
            EnchantmentEffectComponentTypes.PROJECTILE_COUNT,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.constant(2)
            )
        ).addEffect(
            EnchantmentEffectComponentTypes.PROJECTILE_SPREAD,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.constant(10)
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer FAR_THROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("throw_distance"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(30, 10),
                2
            )
        ).addEffect(
            AAEnchantmentComponentTypes.THROW_DISTANCE,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(5)
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer STRONG_THROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("throw_strength"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                3,
                Enchantment.leveledCost(0, 10),
                Enchantment.leveledCost(30, 10),
                2)
        ).addEffect(
            EnchantmentEffectComponentTypes.DAMAGE,
            new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(1)
            ),
            EntityPropertiesLootCondition.builder(
                LootContext.EntityTarget.DIRECT_ATTACKER,
                EntityPredicate.Builder.create().type(AAEntityTypes.BOOMERANG).build()
            )
        ).exclusiveSet(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)
        )
    );

    // This only executes as part of data generation. It does NOT create files, it only pre-loads the registry.
    public static void bootstrap (Registerable<Enchantment> registerable) {
        register(registerable, BOOST);
        register(registerable, JUMP);
        register(registerable, FIREBALL);
        register(registerable, FROSTBALL);
        register(registerable, ERUPTION);
        register(registerable, GALE);
        register(registerable, MAGIC_MISSILE);
        register(registerable, GHOSTLY_ORB);
        register(registerable, EXPLOSION);

        register(registerable, ADHESIVE);
        register(registerable, SPLATTER);

        register(registerable, SHREDDING);
        register(registerable, DISMANTLE);

        register(registerable, RICOCHET);
        register(registerable, MULTITHROW);
        register(registerable, STRONG_THROW);
        register(registerable, FAR_THROW);
    }

    public static Enchantment.Builder buildStaff (RegistryEntryLookup<Item> itemLookup, RegistryEntryLookup<Enchantment> enchantmentLookup, String activeEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.STAFF_ENCHANTABLE), weight, 1, Enchantment.constantCost(minCost), Enchantment.constantCost(maxCost), anvilCost, AttributeModifierSlot.MAINHAND))
            .addEffect(AAEnchantmentComponentTypes.ON_ACTIVATION, new CodeExecutionSingleEntityEffect(AdditionalArmouryMain.identifier(activeEffect)))
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_CAST_DURATION, duration)
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_USES, maxUses)
            .addNonListEffect(AAEnchantmentComponentTypes.SPELL_COLOUR, colour)
            .exclusiveSet(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF));
    }

    public static Enchantment.Builder buildStaff (RegistryEntryLookup<Item> itemLookup, RegistryEntryLookup<Enchantment> enchantmentLookup, String activeEffect, String incantationEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return buildStaff(itemLookup, enchantmentLookup, activeEffect, minCost, maxCost, duration, maxUses, colour, weight, anvilCost)
            .addEffect(AAEnchantmentComponentTypes.TICK_INCANTATION, new LambdaIncantationEffect(AdditionalArmouryMain.identifier(incantationEffect)));
    }

    public static void register (Registerable<Enchantment> registry, EnchantmentContainer enchantment) {
        registry.register(enchantment.getKey(), enchantment.getBuilder(registry).build(enchantment.getKey().getValue()));
    }
}
