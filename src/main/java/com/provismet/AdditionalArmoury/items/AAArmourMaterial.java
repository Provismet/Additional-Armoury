package com.provismet.AdditionalArmoury.items;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

public record AAArmourMaterial (ArmorMaterial baseMaterial, Holder<Attribute> additionalAttribute, Map<ArmorType, AttributeEntry> additionalAttributeValues) {
    public Item.Properties applySettings (Item.Properties settings, ArmorType equipmentType) {
        return settings.durability(equipmentType.getDurability(this.baseMaterial.durability()))
            .attributes(this.createAttributeModifiers(equipmentType))
            .enchantable(this.baseMaterial.enchantmentValue())
            .component(
                DataComponents.EQUIPPABLE,
                Equippable.builder(equipmentType.getSlot())
                    .setEquipSound(this.baseMaterial.equipSound())
                    .setAsset(this.baseMaterial.assetId())
                    .build()
            )
            .repairable(this.baseMaterial.repairIngredient());
    }

    public ItemAttributeModifiers createAttributeModifiers (ArmorType equipmentType) {
        int defence = this.baseMaterial.defense().getOrDefault(equipmentType, 0);

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        EquipmentSlotGroup attributeModifierSlot = EquipmentSlotGroup.bySlot(equipmentType.getSlot());
        Identifier attributeIdentifier = Identifier.withDefaultNamespace("equipment." + equipmentType.getName());
        builder.add(Attributes.ARMOR, new AttributeModifier(attributeIdentifier, defence, AttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(
            Attributes.ARMOR_TOUGHNESS,
            new AttributeModifier(attributeIdentifier, this.baseMaterial.toughness(), AttributeModifier.Operation.ADD_VALUE),
            attributeModifierSlot
        );
        if (this.baseMaterial.knockbackResistance() > 0) {
            builder.add(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(attributeIdentifier, this.baseMaterial.knockbackResistance(), AttributeModifier.Operation.ADD_VALUE),
                attributeModifierSlot
            );
        }

        if (this.additionalAttribute != null) {
            AttributeEntry entry = this.additionalAttributeValues.getOrDefault(equipmentType, AttributeEntry.add(0));
            builder.add(
                this.additionalAttribute,
                new AttributeModifier(attributeIdentifier, entry.value(), entry.operation()),
                attributeModifierSlot
            );
        }

        return builder.build();
    }

    public record AttributeEntry (AttributeModifier.Operation operation, double value) {
        public static AttributeEntry add (double value) {
            return new AttributeEntry(AttributeModifier.Operation.ADD_VALUE, value);
        }

        public static AttributeEntry multiplyBase (double value) {
            return new AttributeEntry(AttributeModifier.Operation.ADD_MULTIPLIED_BASE, value);
        }

        public static AttributeEntry multiplyTotal (double value) {
            return new AttributeEntry(AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, value);
        }
    }
}
