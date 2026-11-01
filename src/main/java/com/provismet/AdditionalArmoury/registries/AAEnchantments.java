package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.api.enchantment.effect.doubleEntity.ApplyToTargetEntityEffect;
import com.provismet.CombatPlusCore.api.enchantment.effect.singleEntity.CodeExecutionSingleEntityEffect;
import com.provismet.CombatPlusCore.api.enchantment.effect.singleEntity.DamageEquipmentEffect;
import com.provismet.CombatPlusCore.api.tag.CombatPlusEnchantmentTags;
import com.provismet.CombatPlusCore.registries.CombatPlusEnchantmentComponentTypes;
import com.provismet.lilylib.container.EnchantmentContainer;
import java.util.List;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityTypePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class AAEnchantments {
    public static final EnchantmentContainer BOOST = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("boost"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "boost", 0, 50, 10, 64, 0xFF7AFFE6, 10, 2)
    );
    public static final EnchantmentContainer JUMP = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("jump"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "jump", 0, 50, 10, 32, 0xFFA2C663, 10, 2)
    );
    public static final EnchantmentContainer FIREBALL = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("fireball"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "fireball", 0, 50, 20, 64, 0xFFFF331F, 8, 2)
    );
    public static final EnchantmentContainer FROSTBALL = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("frostball"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "frostball", 0, 50, 20, 64, 0xFFBADCFF, 8, 2)
    );
    public static final EnchantmentContainer ERUPTION = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("eruption"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "eruption", "eruption_tick", 10, 75, 30, 64, 0xFF7F3C18, 5, 4)
    );
    public static final EnchantmentContainer GALE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("gale"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "gale", 10, 60, 30, 32, 0xFFFFFFFF, 5, 5)
    );
    public static final EnchantmentContainer MAGIC_MISSILE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("missile"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "missile", 20, 70, 20, 96, 0xFF975DFF, 3, 5)
    );
    public static final EnchantmentContainer GHOSTLY_ORB = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("ghostly_orb"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "ghostly_orb", 20, 70, 20, 64, 0xFF6B6B6B, 3, 5)
    );
    public static final EnchantmentContainer EXPLOSION = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("explosion"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) ->
            buildStaff(itemLookup, enchantmentLookup, "explosion", "explosion_tick", 50, 100, 160, 16, 0xFFCE0000, 1, 8)
            .withEffect(AAEnchantmentComponentTypes.NO_SPELL_PARTICLES)
    );

    public static final EnchantmentContainer ADHESIVE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("adhesive"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE),
                2,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                6,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlotGroup.OFFHAND)
        ).withEffect(
            AAEnchantmentComponentTypes.INFINITE_POTION
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer SPLATTER = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("splatter"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.DAGGER_ENCHANTABLE),
                10,
                2,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(50, 10),
                2,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlotGroup.OFFHAND)
        ).withEffect(
            AAEnchantmentComponentTypes.EFFECT_RADIUS,
            new AddValue(
                LevelBasedValue.perLevel(1.5f, 0.5f)
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(CombatPlusEnchantmentTags.WEAPON_UTILITY_OFFHAND_EXCLUSIVE)
        )
    );

    public static final EnchantmentContainer SHREDDING = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("shredding"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE),
                10,
                3,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(50, 10),
                2,
                EquipmentSlotGroup.MAINHAND
            )
        ).withEffect(
            AAEnchantmentComponentTypes.EFFECT_DURATION,
            new AddValue(
                LevelBasedValue.perLevel(20)
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(CombatPlusEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer DISMANTLE = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("dismantle"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.MACE_ENCHANTABLE),
                10,
                3,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(50, 10),
                2,
                EquipmentSlotGroup.MAINHAND
            )
        ).withEffect(
            CombatPlusEnchantmentComponentTypes.POST_CRITICAL_ATTACK,
            new ApplyToTargetEntityEffect(
                new DamageEquipmentEffect(
                    List.of(EquipmentSlot.HEAD, EquipmentSlot.BODY, EquipmentSlot.LEGS, EquipmentSlot.FEET),
                    LevelBasedValue.perLevel(2)
                )
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(CombatPlusEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)
        )
    );

    public static final EnchantmentContainer RICOCHET = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("ricochet"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                4,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(30, 10),
                2)
        ).withEffect(
            AAEnchantmentComponentTypes.RICOCHET,
            new AddValue(
                LevelBasedValue.perLevel(1)
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer MULTITHROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("multithrow"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                5,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(75),
                4)
        ).withEffect(
            EnchantmentEffectComponents.PROJECTILE_COUNT,
            new AddValue(
                LevelBasedValue.constant(2)
            )
        ).withEffect(
            EnchantmentEffectComponents.PROJECTILE_SPREAD,
            new AddValue(
                LevelBasedValue.constant(10)
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer FAR_THROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("throw_distance"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                3,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(30, 10),
                2
            )
        ).withEffect(
            AAEnchantmentComponentTypes.THROW_DISTANCE,
            new AddValue(
                LevelBasedValue.perLevel(5)
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)
        )
    );
    public static final EnchantmentContainer STRONG_THROW = new EnchantmentContainer(
        AdditionalArmouryMain.identifier("throw_strength"),
        (itemLookup, enchantmentLookup, damageLookup, blockLookup, entityLookup) -> Enchantment.enchantment(
            Enchantment.definition(
                itemLookup.getOrThrow(AAItemTags.BOOMERANG_ENCHANTABLE),
                10,
                3,
                Enchantment.dynamicCost(0, 10),
                Enchantment.dynamicCost(30, 10),
                2)
        ).withEffect(
            EnchantmentEffectComponents.DAMAGE,
            new AddValue(
                LevelBasedValue.perLevel(1)
            ),
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.DIRECT_ATTACKER,
                EntityPredicate.Builder.entity().entityType(
                    EntityTypePredicate.of(entityLookup, AAEntityTypes.BOOMERANG)
                ).build()
            )
        ).exclusiveWith(
            enchantmentLookup.getOrThrow(AAEnchantmentTags.THROW_EXCLUSIVE)
        )
    );

    // This only executes as part of data generation. It does NOT create files, it only pre-loads the registry.
    public static void bootstrap (BootstrapContext<Enchantment> registerable) {
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

    public static Enchantment.Builder buildStaff (HolderGetter<Item> itemLookup, HolderGetter<Enchantment> enchantmentLookup, String activeEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return Enchantment.enchantment(Enchantment.definition(itemLookup.getOrThrow(AAItemTags.STAFF_ENCHANTABLE), weight, 1, Enchantment.constantCost(minCost), Enchantment.constantCost(maxCost), anvilCost, EquipmentSlotGroup.HAND))
            .withEffect(AAEnchantmentComponentTypes.ON_ACTIVATION, new CodeExecutionSingleEntityEffect(AdditionalArmouryMain.identifier(activeEffect)))
            .withSpecialEffect(AAEnchantmentComponentTypes.SPELL_CAST_DURATION, duration)
            .withSpecialEffect(AAEnchantmentComponentTypes.SPELL_USES, maxUses)
            .withSpecialEffect(AAEnchantmentComponentTypes.SPELL_COLOUR, colour)
            .exclusiveWith(enchantmentLookup.getOrThrow(AAEnchantmentTags.STAFF));
    }

    public static Enchantment.Builder buildStaff (HolderGetter<Item> itemLookup, HolderGetter<Enchantment> enchantmentLookup, String activeEffect, String incantationEffect, int minCost, int maxCost, int duration, int maxUses, int colour, int weight, int anvilCost) {
        return buildStaff(itemLookup, enchantmentLookup, activeEffect, minCost, maxCost, duration, maxUses, colour, weight, anvilCost)
            .withEffect(AAEnchantmentComponentTypes.TICK_INCANTATION, new LambdaIncantationEffect(AdditionalArmouryMain.identifier(incantationEffect)));
    }

    public static void register (BootstrapContext<Enchantment> registry, EnchantmentContainer enchantment) {
        registry.register(enchantment.getKey(), enchantment.getBuilder(registry).build(enchantment.getKey().identifier()));
    }
}
