package com.provismet.AdditionalArmoury.items;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class AAArmourMaterials {
    public static final AAArmourMaterial OVERNETHER = new AAArmourMaterial(
        AAArmourMaterial.OVERNETHER,
        EntityAttributes.GENERIC_MAX_HEALTH,
        EntityAttributeModifier.Operation.ADD_VALUE,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 1.0);
            map.put(ArmorItem.Type.LEGGINGS, 2.0);
            map.put(ArmorItem.Type.CHESTPLATE, 2.0);
            map.put(ArmorItem.Type.HELMET, 1.0);
            map.put(ArmorItem.Type.BODY, 4.5);
        })
    );

    public static final AAArmourMaterial ENDERNETHER = new AAArmourMaterial(
        AAArmourMaterial.ENDERNETHER,
        EntityAttributes.GENERIC_MOVEMENT_SPEED,
        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL,
        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 0.05);
            map.put(ArmorItem.Type.LEGGINGS, 0.1);
            map.put(ArmorItem.Type.CHESTPLATE, 0.1);
            map.put(ArmorItem.Type.HELMET, 0.05);
            map.put(ArmorItem.Type.BODY, 0.25);
        })
    );
}
