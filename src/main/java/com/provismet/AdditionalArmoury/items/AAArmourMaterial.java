package com.provismet.AdditionalArmoury.items;

import java.util.Map;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public record AAArmourMaterial (ArmorMaterial baseMaterial, RegistryEntry<EntityAttribute> additionalAttribute, Map<EquipmentType, AttributeEntry> additionalAttributeValues) {
    public Item.Settings applySettings (Item.Settings settings, EquipmentType equipmentType) {
        return settings.maxDamage(equipmentType.getMaxDamage(this.baseMaterial.durability()))
            .attributeModifiers(this.createAttributeModifiers(equipmentType))
            .enchantable(this.baseMaterial.enchantmentValue())
            .component(
                DataComponentTypes.EQUIPPABLE,
                EquippableComponent.builder(equipmentType.getEquipmentSlot())
                    .equipSound(this.baseMaterial.equipSound())
                    .model(this.baseMaterial.modelId())
                    .build()
            )
            .repairable(this.baseMaterial.repairIngredient());
    }

    public AttributeModifiersComponent createAttributeModifiers (EquipmentType equipmentType) {
        int defence = this.baseMaterial.defense().getOrDefault(equipmentType, 0);

        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(equipmentType.getEquipmentSlot());
        Identifier attributeIdentifier = Identifier.ofVanilla("equipment." + equipmentType.getName());
        builder.add(EntityAttributes.ARMOR, new EntityAttributeModifier(attributeIdentifier, defence, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(
            EntityAttributes.ARMOR_TOUGHNESS,
            new EntityAttributeModifier(attributeIdentifier, this.baseMaterial.toughness(), EntityAttributeModifier.Operation.ADD_VALUE),
            attributeModifierSlot
        );
        if (this.baseMaterial.knockbackResistance() > 0) {
            builder.add(
                EntityAttributes.KNOCKBACK_RESISTANCE,
                new EntityAttributeModifier(attributeIdentifier, this.baseMaterial.knockbackResistance(), EntityAttributeModifier.Operation.ADD_VALUE),
                attributeModifierSlot
            );
        }

        if (this.additionalAttribute != null) {
            AttributeEntry entry = this.additionalAttributeValues.getOrDefault(equipmentType, AttributeEntry.add(0));
            builder.add(
                this.additionalAttribute,
                new EntityAttributeModifier(attributeIdentifier, entry.value(), entry.operation()),
                attributeModifierSlot
            );
        }

        return builder.build();
    }

    public record AttributeEntry (EntityAttributeModifier.Operation operation, double value) {
        public static AttributeEntry add (double value) {
            return new AttributeEntry(EntityAttributeModifier.Operation.ADD_VALUE, value);
        }

        public static AttributeEntry multiplyBase (double value) {
            return new AttributeEntry(EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE, value);
        }

        public static AttributeEntry multiplyTotal (double value) {
            return new AttributeEntry(EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, value);
        }
    }
}
