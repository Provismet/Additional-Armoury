package com.provismet.additionalarmoury.item;

import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class AAExtraAxeItem extends AxeItem {
    public AAExtraAxeItem (AAToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material.baseMaterial(), attackDamage, attackSpeed, properties);
        properties.attributes(material.createAttributeComponent(attackDamage, attackSpeed));
        DataComponentInitializers.Initializer<Item> componentInitializer = properties.finalizeInitializer(Component.translatable(this.descriptionId), properties.effectiveModel());
        BuiltInRegistries.DATA_COMPONENT_INITIALIZERS.add(properties.itemIdOrThrow(), componentInitializer);
    }
}
