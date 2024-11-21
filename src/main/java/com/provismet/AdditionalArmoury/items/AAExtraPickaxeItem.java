package com.provismet.AdditionalArmoury.items;

import net.minecraft.item.PickaxeItem;
import net.minecraft.text.Text;

public class AAExtraPickaxeItem extends PickaxeItem {
    public AAExtraPickaxeItem (AAToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributeModifiers(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
    }
}
