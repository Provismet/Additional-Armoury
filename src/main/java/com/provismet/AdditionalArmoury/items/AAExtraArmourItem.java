package com.provismet.AdditionalArmoury.items;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.block.DispenserBlock;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Util;

public class AAExtraArmourItem extends ArmorItem {
    private static final EnumMap<Type, UUID> MODIFIERS = Util.make(new EnumMap<>(Type.class), uuidMap -> {
        uuidMap.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    private final Supplier<AttributeModifiersComponent> trueAttributeModifiers;

    public AAExtraArmourItem (AAArmourMaterial material, Type type, Settings settings) {
        super(material.getBaseMaterial(), type, settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);

        this.trueAttributeModifiers = Suppliers.memoize(() -> {
            int protection = material.getProtection(type);
            float toughness = material.getToughness();
            AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
            AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot());
            UUID uUID = MODIFIERS.get(type);
            builder.add(
                EntityAttributes.GENERIC_ARMOR,
                new EntityAttributeModifier(uUID, "Armor modifier", protection, EntityAttributeModifier.Operation.ADD_VALUE),
                attributeModifierSlot
            );
            builder.add(
                EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                new EntityAttributeModifier(uUID, "Armor toughness", toughness, EntityAttributeModifier.Operation.ADD_VALUE),
                attributeModifierSlot
            );

            // Include this just in case dependent mods try to add it.
            float knockbackResistance = material.getBaseMaterial().value().knockbackResistance();
            if (knockbackResistance > 0f) {
                builder.add(
                    EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                    new EntityAttributeModifier(uUID, "Armor knockback resistance", knockbackResistance, EntityAttributeModifier.Operation.ADD_VALUE),
                    attributeModifierSlot
                );
            }

            if (material.getAttribute() != null && material.getCustomAttributeValue(type) > 0f) {
                builder.add(
                    material.getAttribute(),
                    new EntityAttributeModifier(uUID, "Additional Armoury: Custom attribute", material.getCustomAttributeValue(type), material.getOperation()),
                    attributeModifierSlot
                );
            }
            return builder.build();
        });
    }
    
    @Override
    public AttributeModifiersComponent getAttributeModifiers () {
        return trueAttributeModifiers.get();
    }
}
