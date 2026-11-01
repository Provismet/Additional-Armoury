package com.provismet.additionalarmoury.item;

import com.provismet.additionalarmoury.utility.tags.AAItemTags;
import com.provismet.CombatPlusCore.utility.item.AttributeIdentifiers;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public record AAToolMaterial (ToolMaterial baseMaterial, Holder<Attribute> additionalAttribute, float attributeValue) {
    public static final AAToolMaterial OVERNETHER = new AAToolMaterial(alterNetherite(AAItemTags.OVERNETHER_TOOL_MATERIALS), Attributes.ATTACK_KNOCKBACK, 1f);
    public static final AAToolMaterial ENDERNETHER = new AAToolMaterial(alterNetherite(AAItemTags.ENDERNETHER_TOOL_MATERIALS), Attributes.ATTACK_SPEED, 0.2f);

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

    public ItemAttributeModifiers createComponentFrom (ItemAttributeModifiers baseComponent) {
        return baseComponent.withModifierAdded(
            this.additionalAttribute,
            new AttributeModifier(
                AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                this.attributeValue,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        );
    }

    public ItemAttributeModifiers createAttributeComponent (float baseAttackDamage, float baseAttackSpeed) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                    Item.BASE_ATTACK_DAMAGE_ID,
                    baseAttackDamage + this.baseMaterial.attackDamageBonus(),
                    AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
            );

        if (this.additionalAttribute == Attributes.ATTACK_SPEED) {
            baseAttackSpeed += this.attributeValue;
        }

        builder.add(
            Attributes.ATTACK_SPEED,
            new AttributeModifier(
                Item.BASE_ATTACK_SPEED_ID,
                baseAttackSpeed,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        );

        // The order determines the tooltip order.
        if (this.additionalAttribute != null && this.additionalAttribute != Attributes.ATTACK_SPEED) {
            builder.add(
                this.additionalAttribute,
                new AttributeModifier(
                    AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                    this.attributeValue,
                    AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
            );
        }

        return builder.build();
    }
}
