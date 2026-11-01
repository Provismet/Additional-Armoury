package com.provismet.AdditionalArmoury.item;

import com.provismet.AdditionalArmoury.item.key.AAEquipmentAssetKeys;
import com.provismet.AdditionalArmoury.utility.tags.AAItemTags;
import net.minecraft.util.Util;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
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
        Attributes.MAX_HEALTH,
        Util.make(new EnumMap<>(ArmorType.class), map -> {
            map.put(ArmorType.BOOTS, AAArmourMaterial.AttributeEntry.add(1));
            map.put(ArmorType.LEGGINGS, AAArmourMaterial.AttributeEntry.add(2));
            map.put(ArmorType.CHESTPLATE, AAArmourMaterial.AttributeEntry.add(2));
            map.put(ArmorType.HELMET, AAArmourMaterial.AttributeEntry.add(1));
            map.put(ArmorType.BODY, AAArmourMaterial.AttributeEntry.add(4.5));
        })
    );

    public static final AAArmourMaterial ENDERNETHER = new AAArmourMaterial(
        INNER_ENDERNETHER,
        Attributes.MOVEMENT_SPEED,
        Util.make(new EnumMap<>(ArmorType.class), map -> {
            map.put(ArmorType.BOOTS, AAArmourMaterial.AttributeEntry.multiplyTotal(0.05));
            map.put(ArmorType.LEGGINGS, AAArmourMaterial.AttributeEntry.multiplyTotal(0.1));
            map.put(ArmorType.CHESTPLATE, AAArmourMaterial.AttributeEntry.multiplyTotal(0.1));
            map.put(ArmorType.HELMET, AAArmourMaterial.AttributeEntry.multiplyTotal(0.05));
            map.put(ArmorType.BODY, AAArmourMaterial.AttributeEntry.multiplyTotal(0.25));
        })
    );
}
