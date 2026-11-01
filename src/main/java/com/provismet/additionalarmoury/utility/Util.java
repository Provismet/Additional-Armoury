package com.provismet.additionalarmoury.utility;

import com.provismet.additionalarmoury.item.AAArmourMaterial;
import com.provismet.additionalarmoury.item.AAToolMaterial;
import com.provismet.CombatPlusCore.api.item.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CombatPlusDataComponentTypes;
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
            .component(CombatPlusDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createDual(material.baseMaterial().attackDamageBonus() + attack))
            .attributes(material.createAttributeComponent(attack, speed));
    }

    public static Item.Properties applySpearSettings (AAToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold, Item.Properties settings) {
        return settings.spear(material.baseMaterial(), attackDuration, damageMultiplier, delay, dismountTime, dismountThreshold, knockbackTime, knockbackThreshold, damageTime, damageThreshold)
            .component(CombatPlusDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createMelee(material.baseMaterial().attackDamageBonus()))
            .attributes(material.createAttributeComponent(0, 1f / attackDuration - 4f));
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
