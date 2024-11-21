package com.provismet.AdditionalArmoury.items;

import net.minecraft.item.AxeItem;
import net.minecraft.text.Text;

public class AAExtraAxeItem extends AxeItem {
    public AAExtraAxeItem (AAToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributeModifiers(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
    }
}
