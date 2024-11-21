package com.provismet.AdditionalArmoury.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.text.Text;

public class AAExtraArmourItem extends ArmorItem {
    public AAExtraArmourItem (AAArmourMaterial material, EquipmentType type, Settings settings) {
        super(material.baseMaterial(), type, settings);
        settings.attributeModifiers(material.createAttributeModifiers(type));
        this.components = settings.getValidatedComponents(Text.translatable(this.translationKey), settings.getModelId());
    }
}
