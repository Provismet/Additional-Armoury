package com.provismet.AdditionalArmoury.items;

import java.util.EnumMap;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

public enum AAArmourMaterials implements ArmorMaterial {
    OVERNETHER("overnether",
        37,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        }),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
        3.0f,
        0f,
        EntityAttributes.GENERIC_MAX_HEALTH,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 1);
            map.put(ArmorItem.Type.LEGGINGS, 2);
            map.put(ArmorItem.Type.CHESTPLATE, 2);
            map.put(ArmorItem.Type.HELMET, 1);
        }),
        () -> Ingredient.ofItems(AAItems.OVERNETHER_INGOT)
    ),

    ENDERNETHER("endernether",
        37,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
        }),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
        3.0f,
        0f,
        EntityAttributes.GENERIC_MOVEMENT_SPEED,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 1);
            map.put(ArmorItem.Type.LEGGINGS, 2);
            map.put(ArmorItem.Type.CHESTPLATE, 2);
            map.put(ArmorItem.Type.HELMET, 1);
        }),
        () -> Ingredient.ofItems(AAItems.ENDERNETHER_INGOT)
    );

    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY;
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;
    private final EntityAttribute customAttribute;
    private final EnumMap<ArmorItem.Type, Integer> attributeValues;

    private AAArmourMaterials (String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, EntityAttribute attribute, EnumMap<ArmorItem.Type, Integer> attributeValues, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.customAttribute = attribute;
        this.attributeValues = attributeValues;
        this.repairIngredientSupplier = Suppliers.memoize(repairIngredientSupplier::get);
    }

    @Override
    public int getDurability (ArmorItem.Type armour) {
        return BASE_DURABILITY.get(armour) * this.durabilityMultiplier;
    }

    @Override
    public int getProtection (ArmorItem.Type armour) {
        return this.protectionAmounts.get(armour);
    }

    @Override
    public int getEnchantability () {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound () {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient () {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName () {
        return this.name;
    }

    @Override
    public float getToughness () {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance () {
        return this.knockbackResistance;
    }

    public EntityAttribute getCustomAttribute () {
        return this.customAttribute;
    }

    public int getCustomAttributeValue (ArmorItem.Type armour) {
        return this.attributeValues.get(armour);
    }
    
    static {
        BASE_DURABILITY = Util.make(new EnumMap<>(Type.class), map -> {
            map.put(Type.BOOTS, 13);
            map.put(Type.LEGGINGS, 15);
            map.put(Type.CHESTPLATE, 16);
            map.put(Type.HELMET, 11);
        });
    }
}
