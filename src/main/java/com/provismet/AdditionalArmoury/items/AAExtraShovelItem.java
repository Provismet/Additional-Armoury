package com.provismet.AdditionalArmoury.items;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class AAExtraShovelItem extends ShovelItem {
    public AAExtraShovelItem (AAToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material.baseMaterial(), attackDamage, attackSpeed, properties);
        properties.attributes(material.createAttributeComponent(attackDamage, attackSpeed));
        DataComponentInitializers.Initializer<Item> componentInitializer = properties.finalizeInitializer(Component.translatable(this.descriptionId), properties.effectiveModel());
        BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(properties.itemIdOrThrow(), componentInitializer);
    }
}
