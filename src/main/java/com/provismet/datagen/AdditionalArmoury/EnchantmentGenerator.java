package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.utility.tags.AAEnchantmentTags;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.enchantment.effect.doubleEntity.ApplyToTargetEntityEnchantmentEffect;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.DamageEquipmentEffect;
import com.provismet.CombatPlusCore.registries.CPCEnchantmentComponentTypes;
import com.provismet.CombatPlusCore.utility.tag.CPCEnchantmentTags;
import com.provismet.datagen.CombatPlusCore.provider.CPCEnchantmentProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EnchantmentGenerator extends CPCEnchantmentProvider {
    public EnchantmentGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup, Entries entries, EnchantmentBuilder builder) {
        builder.add(AAEnchantments.BOOST.getValue(),AAEnchantments.buildStaff(builder.itemLookup, "boost", 0, 50, 10, 64, 0xFF7AFFE6, 10, 2).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.JUMP.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "jump", 0, 50, 10, 32, 0xFFA2C663, 10, 2).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.FIREBALL.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "fireball", 0, 50, 20, 64, 0xFFFF331F, 8, 2).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.FROSTBALL.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "frostball", 0, 50, 20, 64, 0xFFBADCFF, 8, 2).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.ERUPTION.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "eruption", "eruption_tick", 10, 75, 30, 64, 0xFF7F3C18, 5, 4).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.GALE.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "gale", 10, 60, 30, 32, 0xFFFFFFFF, 5, 5).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.MAGIC_MISSILE.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "missile", 20, 70, 20, 96, 0xFF975DFF, 3, 5).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.GHOSTLY_ORB.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "ghostly_orb", 20, 70, 20, 64, 0xFF6B6B6B, 3, 5).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));
        builder.add(AAEnchantments.EXPLOSION.getValue(), AAEnchantments.buildStaff(builder.itemLookup, "explosion", "explosion_tick", 50, 100, 160, 16, 0xFFCE0000, 1, 8).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.STAFF)));

        builder.add(AAEnchantments.ADHESIVE.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.DAGGER_ENCHANTABLE), 2, 1, Enchantment.constantCost(20), Enchantment.constantCost(50), 6, AttributeModifierSlot.MAINHAND, AttributeModifierSlot.OFFHAND)).addEffect(AAEnchantmentComponentTypes.INFINITE_POTION).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.ADHESIVE_EXCLUSIVE)));
        builder.add(AAEnchantments.SPLATTER.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.DAGGER_ENCHANTABLE), 10, 2, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND, AttributeModifierSlot.OFFHAND)).addEffect(AAEnchantmentComponentTypes.EFFECT_RADIUS, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.5f, 0.5f))).exclusiveSet(builder.getEnchantmentEntryList(CPCEnchantmentTags.WEAPON_UTILITY_OFFHAND_EXCLUSIVE)));

        builder.add(AAEnchantments.SHREDDING.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.MACE_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND)).addEffect(AAEnchantmentComponentTypes.EFFECT_DURATION, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(20))).exclusiveSet(builder.getEnchantmentEntryList(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)));
        builder.add(AAEnchantments.DISMANTLE.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.MACE_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(50, 10), 2, AttributeModifierSlot.MAINHAND)).addEffect(CPCEnchantmentComponentTypes.POST_CRITICAL_ATTACK, new ApplyToTargetEntityEnchantmentEffect(new DamageEquipmentEffect(List.of(EquipmentSlot.HEAD, EquipmentSlot.BODY, EquipmentSlot.LEGS, EquipmentSlot.FEET), EnchantmentLevelBasedValue.linear(2)))).exclusiveSet(builder.getEnchantmentEntryList(CPCEnchantmentTags.WEAPON_UTILITY_EXCLUSIVE)));

        builder.add(AAEnchantments.RICOCHET.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 4, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(AAEnchantmentComponentTypes.RICOCHET, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1))).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)));
        builder.add(AAEnchantments.MULTITHROW.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.BOOMERANG_ENCHANTABLE), 5, 1, Enchantment.constantCost(20), Enchantment.constantCost(75), 4)).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_COUNT, new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(2))).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_SPREAD, new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(10))).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.BOOMERANG_EXCLUSIVE)));
        builder.add(AAEnchantments.STRONG_THROW.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(1)), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().type(AAEntityTypes.BOOMERANG).build())).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.THROW_EXCLUSIVE)));
        builder.add(AAEnchantments.FAR_THROW.getValue(), Enchantment.builder(Enchantment.definition(builder.getItemEntryList(AAItemTags.BOOMERANG_ENCHANTABLE), 10, 3, Enchantment.leveledCost(0, 10), Enchantment.leveledCost(30, 10), 2)).addEffect(AAEnchantmentComponentTypes.THROW_DISTANCE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(5))).exclusiveSet(builder.getEnchantmentEntryList(AAEnchantmentTags.THROW_EXCLUSIVE)));
    }
}
