package com.provismet.AdditionalArmoury.items;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class AAArmourMaterial {
    public static final RegistryEntry<ArmorMaterial> OVERNETHER = AAArmourMaterial.register(
        AdditionalArmouryMain.identifier("overnether").toString(),
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
            map.put(ArmorItem.Type.BODY, 11);
        }),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
        3.0f,
        0f,
        () -> Ingredient.ofItems(AAItems.OVERNETHER_INGOT)
    );

    public static final RegistryEntry<ArmorMaterial> ENDERNETHER = AAArmourMaterial.register(
        AdditionalArmouryMain.identifier("endernether").toString(),
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 3);
            map.put(ArmorItem.Type.LEGGINGS, 6);
            map.put(ArmorItem.Type.CHESTPLATE, 8);
            map.put(ArmorItem.Type.HELMET, 3);
            map.put(ArmorItem.Type.BODY, 11);
        }),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
        3.0f,
        0f,
        () -> Ingredient.ofItems(AAItems.ENDERNETHER_INGOT)
    );

    private final RegistryEntry<ArmorMaterial> internal;
    private final RegistryEntry<EntityAttribute> attribute;
    private final Map<ArmorItem.Type, Double> attributeValues;
    private final EntityAttributeModifier.Operation operation;

    public AAArmourMaterial (RegistryEntry<ArmorMaterial> baseMaterial, RegistryEntry<EntityAttribute> customAttribute, EntityAttributeModifier.Operation operation, Map<ArmorItem.Type, Double> attributeValues) {
        this.internal = baseMaterial;
        this.attribute = customAttribute;
        this.operation = operation;
        this.attributeValues = attributeValues;
    }

    public RegistryEntry<ArmorMaterial> getBaseMaterial () {
        return this.internal;
    }

    public int getProtection (ArmorItem.Type type) {
        return this.internal.value().getProtection(type);
    }

    public float getToughness () {
        return this.internal.value().toughness();
    }

    public double getCustomAttributeValue (ArmorItem.Type type) {
        return this.attributeValues.get(type);
    }

    public RegistryEntry<EntityAttribute> getAttribute () {
        return this.attribute;
    }

    public EntityAttributeModifier.Operation getOperation () {
        return this.operation;
    }

    public static void register () {}

    // Yoinked this code from vanilla.
    private static RegistryEntry<ArmorMaterial> register (String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.ofVanilla(id)));
        return AAArmourMaterial.register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static RegistryEntry<ArmorMaterial> register (String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.ofVanilla(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}
