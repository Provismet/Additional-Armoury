package com.provismet.AdditionalArmoury.registries;

import com.mojang.serialization.Codec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.List;
import java.util.function.UnaryOperator;

public class AAEnchantmentComponentTypes {
    // Staff
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffect>>> ON_ACTIVATION = register("on_activation", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));
    public static final ComponentType<Integer> SPELL_COLOUR = register("spell_colour", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Integer> SPELL_USES = register("spell_uses", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Integer> SPELL_CAST_DURATION = register("spell_cast_duration", builder -> builder.codec(Codec.INT));
    public static final ComponentType<List<EnchantmentEffectEntry<SpellIncantationTickingEffect>>> TICK_INCANTATION = register("spell_incantation", builder -> builder.codec(EnchantmentEffectEntry.createCodec(SpellIncantationTickingEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));

    // Melee Weapons
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> EFFECT_RADIUS = register("effect_radius", createValueCodec());
    public static final ComponentType<Unit> INFINITE_POTION = register("infinite_potions", builder -> builder.codec(Unit.CODEC));
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> EFFECT_DURATION = register("effect_duration", createValueCodec());

    // Boomerangs
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> RICOCHET = register("boomerang_ricochet", createValueCodec());
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> THROW_DISTANCE = register("throw_distance", createValueCodec());

    public static void init () {}

    private static <T> ComponentType<T> register (String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, AdditionalArmouryMain.identifier(name), (builderOperator.apply(ComponentType.builder())).build());
    }

    private static UnaryOperator<ComponentType.Builder<List<EnchantmentEffectEntry<EnchantmentValueEffect>>>> createValueCodec () {
        return builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentValueEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf());
    }


}