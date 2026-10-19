package com.provismet.AdditionalArmoury.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ShovelItem;

public class AAExtraShovelItem extends ShovelItem {
    public AAExtraShovelItem (AAToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(material.baseMaterial(), attackDamage, attackSpeed, settings);
        settings.attributes(material.createAttributeComponent(attackDamage, attackSpeed));
        this.components = settings.buildAndValidateComponents(Component.translatable(this.descriptionId), settings.effectiveModel());
    }
}
