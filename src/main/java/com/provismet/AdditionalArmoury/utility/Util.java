package com.provismet.AdditionalArmoury.utility;

import com.provismet.AdditionalArmoury.items.AAArmourMaterial;
import com.provismet.AdditionalArmoury.items.AAToolMaterial;
import com.provismet.CombatPlusCore.items.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CPCDataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.EquipmentType;

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
        return settings.maxCount(1)
            .maxDamage(material.durability())
            .enchantable(material.enchantmentValue())
            .repairable(material.repairItems());
    }

    public static Item.Settings applySwordSettings (AAToolMaterial material, float attack, float speed, Item.Settings settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .sword(material.baseMaterial(), attack, speed)
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.baseMaterial().attackDamageBonus() + attack))
            .attributeModifiers(material.createAttributeComponent(attack, speed));
    }

    public static Item.Settings applyPickaxeSettings (AAToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .pickaxe(material.baseMaterial(), attackDamage, attackSpeed)
            .attributeModifiers(material.createAttributeComponent(attackDamage, attackSpeed));
    }

    public static Item.Settings applyArmourSettings (AAArmourMaterial material, EquipmentType type, Item.Settings settings) {
        return settings.armor(material.baseMaterial(), type)
            .attributeModifiers(material.createAttributeModifiers(type));
    }
}
