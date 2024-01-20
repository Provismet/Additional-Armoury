package com.provismet.AdditionalArmoury.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.provismet.CombatPlusCore.utility.AttributeIdentifiers;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.AxeItem;

public class AAExtraAxeItem extends AxeItem {
    private final float extraAttackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> extraAttributeModifiers;

    public AAExtraAxeItem (AAToolMaterials toolMaterial, float attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.extraAttackDamage = attackDamage + toolMaterial.getAttackDamage();
        if (toolMaterial.getCustomAttribute() == EntityAttributes.GENERIC_ATTACK_SPEED) attackSpeed += toolMaterial.getCustomAttributeValue();

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", (double)this.extraAttackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));

        if (toolMaterial.getCustomAttribute() != null && toolMaterial.getCustomAttribute() != EntityAttributes.GENERIC_ATTACK_SPEED) {
            builder.put(toolMaterial.getCustomAttribute(), new EntityAttributeModifier(AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE, "Additional Armoury: Tool modifier", (double)toolMaterial.getCustomAttributeValue(), EntityAttributeModifier.Operation.ADDITION));
        }

        this.extraAttributeModifiers = builder.build();
    }

    @Override
    public float getAttackDamage() {
        return this.extraAttackDamage;
    }
    
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.extraAttributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
