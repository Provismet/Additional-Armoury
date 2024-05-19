package com.provismet.AdditionalArmoury.utility;

import com.provismet.AdditionalArmoury.items.AAToolMaterials;
import com.provismet.CombatPlusCore.utility.AttributeIdentifiers;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class Util {
    public static boolean isFriendly (LivingEntity entity1, LivingEntity entity2) {
        if (entity1 == entity2) return true;
        else return Util.friendlyInternal(entity1, entity2) && Util.friendlyInternal(entity2, entity1);
    }

    private static boolean friendlyInternal (LivingEntity entity1, LivingEntity entity2) {
        if (entity1 instanceof Tameable tame && tame.getOwner() == entity2) return true;
        if (entity1.getAttacker() == entity2) return false;
        if (entity1.getAttacking() == entity2) return false;
        if (entity1.getScoreboardTeam() != null) {
            if (entity1.getScoreboardTeam() == entity2.getScoreboardTeam()) return true;
            if (entity2.getScoreboardTeam() != null) return false;
        }
        if (entity1 instanceof HostileEntity != entity2 instanceof HostileEntity) return false;
        return true;
    }

    public static AttributeModifiersComponent createAttributes (ToolMaterial material, float baseAttackDamage, float baseAttackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(
                    SwordItem.ATTACK_DAMAGE_MODIFIER_ID,
                    "Weapon modifier",
                    baseAttackDamage + material.getAttackDamage(),
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
            );

        float bonusAttackSpeed = 0f;

        if (material instanceof AAToolMaterials extraMaterial && extraMaterial.getCustomAttribute() == EntityAttributes.GENERIC_ATTACK_SPEED) {
            bonusAttackSpeed = extraMaterial.getCustomAttributeValue();
        }

        builder.add(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(
                SwordItem.ATTACK_SPEED_MODIFIER_ID,
                "Weapon modifier",
                baseAttackSpeed + bonusAttackSpeed,
                EntityAttributeModifier.Operation.ADD_VALUE
            ),
            AttributeModifierSlot.MAINHAND
        );

        if (material instanceof AAToolMaterials extraMaterial && extraMaterial.getCustomAttribute() != null && extraMaterial.getCustomAttribute() != EntityAttributes.GENERIC_ATTACK_SPEED) {
            builder.add(
                extraMaterial.getCustomAttribute(),
                new EntityAttributeModifier(
                    AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                    "Additional Armoury: Weapon modifier",
                    extraMaterial.getCustomAttributeValue(),
                    EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
            );
        }

        return builder.build();
    }
}
