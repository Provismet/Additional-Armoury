package com.provismet.AdditionalArmoury.items;

import net.minecraft.item.ShovelItem;
import net.minecraft.text.Text;

public class AAExtraShovelItem extends ShovelItem {
    public AAExtraShovelItem (AAToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributeModifiers(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
    }
}
