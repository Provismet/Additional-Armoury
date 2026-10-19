package com.provismet.AdditionalArmoury.utility;

import com.provismet.AdditionalArmoury.items.AAArmourMaterial;
import com.provismet.AdditionalArmoury.items.AAToolMaterial;
import com.provismet.CombatPlusCore.items.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CPCDataComponentTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;

public class Util {
    public static ItemAttributeModifiers createAttributes (ToolMaterial material, float baseAttackDamage, float baseAttackSpeed) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                    Item.BASE_ATTACK_DAMAGE_ID,
                    baseAttackDamage + material.attackDamageBonus(),
                    AttributeModifier.Operation.ADD_VALUE
                ),
                EquipmentSlotGroup.MAINHAND
            );

        builder.add(
            Attributes.ATTACK_SPEED,
            new AttributeModifier(
                Item.BASE_ATTACK_SPEED_ID,
                baseAttackSpeed,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        );

        return builder.build();
    }

    public static Item.Properties applyToolSettings (ToolMaterial material, Item.Properties settings) {
        return settings.stacksTo(1)
            .durability(material.durability())
            .enchantable(material.enchantmentValue())
            .repairable(material.repairItems());
    }

    public static Item.Properties applySwordSettings (AAToolMaterial material, float attack, float speed, Item.Properties settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .sword(material.baseMaterial(), attack, speed)
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.baseMaterial().attackDamageBonus() + attack))
            .attributes(material.createAttributeComponent(attack, speed));
    }

    public static Item.Properties applyPickaxeSettings (AAToolMaterial material, float attackDamage, float attackSpeed, Item.Properties settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .pickaxe(material.baseMaterial(), attackDamage, attackSpeed)
            .attributes(material.createAttributeComponent(attackDamage, attackSpeed));
    }

    public static Item.Properties applyArmourSettings (AAArmourMaterial material, ArmorType type, Item.Properties settings) {
        return settings.humanoidArmor(material.baseMaterial(), type)
            .attributes(material.createAttributeModifiers(type));
    }
}
