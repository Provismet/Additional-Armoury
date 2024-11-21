package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.utility.item.AttributeIdentifiers;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

import java.util.Map;

public record AAToolMaterial (ToolMaterial baseMaterial, RegistryEntry<EntityAttribute> additionalAttribute, float attributeValue) {
    public static final AAToolMaterial OVERNETHER = new AAToolMaterial(alterNetherite(AAItemTags.OVERNETHER_TOOL_MATERIALS), EntityAttributes.ATTACK_KNOCKBACK, 1f);
    public static final AAToolMaterial ENDERNETHER = new AAToolMaterial(alterNetherite(AAItemTags.ENDERNETHER_TOOL_MATERIALS), EntityAttributes.ATTACK_SPEED, 0.2f);

    private static ToolMaterial alterNetherite (TagKey<Item> repairItems) {
        return new ToolMaterial(
            ToolMaterial.NETHERITE.incorrectBlocksForDrops(),
            ToolMaterial.NETHERITE.durability(),
            ToolMaterial.NETHERITE.speed(),
            ToolMaterial.DIAMOND.attackDamageBonus(),
            ToolMaterial.NETHERITE.enchantmentValue(),
            repairItems
        );
    }

    public AttributeModifiersComponent createComponentFrom (AttributeModifiersComponent baseComponent) {
        return baseComponent.with(
            this.additionalAttribute,
            new EntityAttributeModifier(
                AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                this.attributeValue,
                EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        );
    }

    public AttributeModifiersComponent createAttributeComponent (float baseAttackDamage, float baseAttackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(
                    Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                    baseAttackDamage + this.baseMaterial.attackDamageBonus(),
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
            );

        if (this.additionalAttribute == EntityAttributes.ATTACK_SPEED) {
            baseAttackSpeed += this.attributeValue;
        }

        builder.add(
            EntityAttributes.ATTACK_SPEED,
            new EntityAttributeModifier(
                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                baseAttackSpeed,
                EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        );

        // The order determines the tooltip order.
        if (this.additionalAttribute != null && this.additionalAttribute != EntityAttributes.ATTACK_SPEED) {
            builder.add(
                this.additionalAttribute,
                new EntityAttributeModifier(
                    AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                    this.attributeValue,
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
            );
        }

        return builder.build();
    }
}
