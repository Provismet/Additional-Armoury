package com.provismet.AdditionalArmoury.utility;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class Util {
    public static AttributeModifiersComponent createAttributes (ToolMaterial material, float baseAttackDamage, float baseAttackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(
                    Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                    baseAttackDamage + material.attackDamageBonus(),
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
            );

        builder.add(
            EntityAttributes.ATTACK_SPEED,
            new EntityAttributeModifier(
                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                baseAttackSpeed,
                EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        );

        return builder.build();
    }

    public static Item.Settings applyToolSettings (ToolMaterial material, Item.Settings settings) {
        return settings.maxCount(1).maxDamage(material.durability()).enchantable(material.enchantmentValue());
    }
}
