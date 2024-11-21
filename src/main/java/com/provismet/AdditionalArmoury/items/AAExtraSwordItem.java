package com.provismet.AdditionalArmoury.items;

import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;

public class AAExtraSwordItem extends SwordItem {
    public AAExtraSwordItem (AAToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributeModifiers(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
    }
}
