package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.items.key.AAEquipmentAssetKeys;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Util;

import java.util.EnumMap;

public class AAArmourMaterials {
    private static final ArmorMaterial INNER_OVERNETHER = new ArmorMaterial(
        ArmorMaterials.NETHERITE.durability(),
        ArmorMaterials.NETHERITE.defense(),
        ArmorMaterials.NETHERITE.enchantmentValue(),
        ArmorMaterials.NETHERITE.equipSound(),
        ArmorMaterials.NETHERITE.toughness(),
        0f,
        AAItemTags.REPAIRS_OVERNETHER_ARMOUR,
        AAEquipmentAssetKeys.OVERNETHER
    );

    private static final ArmorMaterial INNER_ENDERNETHER = new ArmorMaterial(
        ArmorMaterials.NETHERITE.durability(),
        ArmorMaterials.NETHERITE.defense(),
        ArmorMaterials.NETHERITE.enchantmentValue(),
        ArmorMaterials.NETHERITE.equipSound(),
        ArmorMaterials.NETHERITE.toughness(),
        0f,
        AAItemTags.REPAIRS_ENDERNETHER_ARMOUR,
        AAEquipmentAssetKeys.ENDERNETHER
    );

    public static final AAArmourMaterial OVERNETHER = new AAArmourMaterial(
        INNER_OVERNETHER,
        EntityAttributes.MAX_HEALTH,
        Util.make(new EnumMap<>(EquipmentType.class), map -> {
            map.put(EquipmentType.BOOTS, AAArmourMaterial.AttributeEntry.add(1));
            map.put(EquipmentType.LEGGINGS, AAArmourMaterial.AttributeEntry.add(2));
            map.put(EquipmentType.CHESTPLATE, AAArmourMaterial.AttributeEntry.add(2));
            map.put(EquipmentType.HELMET, AAArmourMaterial.AttributeEntry.add(1));
            map.put(EquipmentType.BODY, AAArmourMaterial.AttributeEntry.add(4.5));
        })
    );

    public static final AAArmourMaterial ENDERNETHER = new AAArmourMaterial(
        INNER_ENDERNETHER,
        EntityAttributes.MOVEMENT_SPEED,
        Util.make(new EnumMap<>(EquipmentType.class), map -> {
            map.put(EquipmentType.BOOTS, AAArmourMaterial.AttributeEntry.multiplyTotal(0.05));
            map.put(EquipmentType.LEGGINGS, AAArmourMaterial.AttributeEntry.multiplyTotal(0.1));
            map.put(EquipmentType.CHESTPLATE, AAArmourMaterial.AttributeEntry.multiplyTotal(0.1));
            map.put(EquipmentType.HELMET, AAArmourMaterial.AttributeEntry.multiplyTotal(0.05));
            map.put(EquipmentType.BODY, AAArmourMaterial.AttributeEntry.multiplyTotal(0.25));
        })
    );
}
