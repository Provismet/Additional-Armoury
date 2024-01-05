package com.provismet.AdditionalArmoury.items;

import java.util.EnumMap;
import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Util;

public class AAExtraArmourItem extends ArmorItem {
    private static final EnumMap<Type, UUID> MODIFIERS = Util.make(new EnumMap<>(Type.class), uuidMap -> {
        uuidMap.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    private final Multimap<EntityAttribute, EntityAttributeModifier> trueAttributeModifiers;

    public AAExtraArmourItem (AAArmourMaterials material, Type type, Settings settings) {
        super(material, type, settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
        
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS.get((Object)type);
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", material.getProtection(type), EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", material.getToughness(), EntityAttributeModifier.Operation.ADDITION));
        if (material.getCustomAttribute() != null) {
            builder.put(material.getCustomAttribute(), new EntityAttributeModifier(uUID, "Additional Armoury: Custom attribute", material.getCustomAttributeValue(type), EntityAttributeModifier.Operation.ADDITION));
        }
        this.trueAttributeModifiers = builder.build();
    }
    
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers (EquipmentSlot slot) {
        if (slot == this.type.getEquipmentSlot()) {
            return this.trueAttributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
