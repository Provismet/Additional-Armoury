package com.provismet.AdditionalArmoury.registries;

import com.mojang.serialization.Codec;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.component.SpellIncantationTickingEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import java.util.List;
import java.util.function.UnaryOperator;

public class AAEnchantmentComponentTypes {
    // Staff
    public static final DataComponentType<List<ConditionalEffect<EnchantmentEntityEffect>>> ON_ACTIVATION = register("on_activation", builder -> builder.persistent(conditionalCodec(EnchantmentEntityEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY)));
    public static final DataComponentType<Integer> SPELL_COLOUR = register("spell_colour", builder -> builder.persistent(Codec.INT));
    public static final DataComponentType<Unit> NO_SPELL_PARTICLES = register("no_spell_particles", builder -> builder.persistent(Unit.CODEC));
    public static final DataComponentType<Integer> SPELL_USES = register("spell_uses", builder -> builder.persistent(Codec.INT));
    public static final DataComponentType<Integer> SPELL_CAST_DURATION = register("spell_cast_duration", builder -> builder.persistent(Codec.INT));
    public static final DataComponentType<List<ConditionalEffect<SpellIncantationTickingEffect>>> TICK_INCANTATION = register("spell_incantation", builder -> builder.persistent(conditionalCodec(SpellIncantationTickingEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY)));

    // Melee Weapons
    public static final DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> EFFECT_RADIUS = register("effect_radius", createValueCodec());
    public static final DataComponentType<Unit> INFINITE_POTION = register("infinite_potions", builder -> builder.persistent(Unit.CODEC));
    public static final DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> EFFECT_DURATION = register("effect_duration", createValueCodec());

    // Boomerangs
    public static final DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> RICOCHET = register("boomerang_ricochet", createValueCodec());
    public static final DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>> THROW_DISTANCE = register("throw_distance", createValueCodec());

    public static void init () {}

    private static <T> DataComponentType<T> register (String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, AdditionalArmouryMain.identifier(name), (builderOperator.apply(DataComponentType.builder())).build());
    }

    private static UnaryOperator<DataComponentType.Builder<List<ConditionalEffect<EnchantmentValueEffect>>>> createValueCodec () {
        return builder -> builder.persistent(conditionalCodec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ENTITY));
    }

    private static <T extends Validatable> Codec<List<T>> validatedListCodec (Codec<T> elementCodec, ContextKeySet paramSet) {
        return elementCodec.listOf().validate(Validatable.listValidatorForContext(paramSet));
    }

    private static <T> Codec<List<ConditionalEffect<T>>> conditionalCodec (Codec<T> codec, ContextKeySet params) {
        return validatedListCodec(ConditionalEffect.codec(codec), params);
    }
}