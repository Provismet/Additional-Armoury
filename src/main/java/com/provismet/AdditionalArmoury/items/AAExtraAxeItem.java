package com.provismet.AdditionalArmoury.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;

public class AAExtraAxeItem extends AxeItem {
    public AAExtraAxeItem (AAToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributes(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.buildAndValidateComponents(Component.translatable(this.descriptionId), settings.effectiveModel());
    }
}
