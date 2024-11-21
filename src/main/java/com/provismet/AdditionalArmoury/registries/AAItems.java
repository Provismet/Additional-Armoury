package com.provismet.AdditionalArmoury.registries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.*;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AAItems {
    public static final Item OVERNETHER_INGOT = register("overnether_ingot", settings -> new Item(settings.fireproof()));
    public static final Item ENDERNETHER_INGOT = register("endernether_ingot", settings -> new Item(settings.fireproof()));

    public static final BlockItem OVERNETHER_BLOCK = register("overnether_block", settings -> new BlockItem(AABlocks.OVERNETHER_BLOCK, settings.useBlockPrefixedTranslationKey()));
    public static final BlockItem ENDERNETHER_BLOCK = register("endernether_block", settings -> new BlockItem(AABlocks.ENDERNETHER_BLOCK, settings.useBlockPrefixedTranslationKey()));

    public static final Item OVERNETHER_UPGRADE_SMITHING_TEMPLATE = register("overnether_upgrade_smithing_template", AASmithingTemplateItem::createOvernetherSmithingTemplate);
    public static final Item ENDERNETHER_UPGRADE_SMITHING_TEMPLATE = register("endernether_upgrade_smithing_template", AASmithingTemplateItem::createEndernetherSmithingTemplate);

    public static final SwordItem OVERNETHER_SWORD = register("overnether_sword", settings -> new AAExtraSwordItem(AAToolMaterial.OVERNETHER, 3f, -2.4f, settings.maxCount(1).fireproof()));
    public static final SwordItem ENDERNETHER_SWORD = register("endernether_sword", settings -> new AAExtraSwordItem(AAToolMaterial.ENDERNETHER, 3f, -2.4f, settings.maxCount(1).fireproof()));
    public static final AxeItem OVERNETHER_AXE = register("overnether_axe", settings -> new AAExtraAxeItem(AAToolMaterial.OVERNETHER, 5f, -3f, settings.maxCount(1).fireproof()));
    public static final AxeItem ENDERNETHER_AXE = register("endernether_axe", settings -> new AAExtraAxeItem(AAToolMaterial.ENDERNETHER, 5f, -3f, settings.maxCount(1).fireproof()));
    public static final PickaxeItem OVERNETHER_PICKAXE = register("overnether_pickaxe", settings -> new AAExtraPickaxeItem(AAToolMaterial.OVERNETHER, 1f, -2.8f, settings.maxCount(1).fireproof()));
    public static final PickaxeItem ENDERNETHER_PICKAXE = register("endernether_pickaxe", settings -> new AAExtraPickaxeItem(AAToolMaterial.ENDERNETHER, 1f, -2.8f, settings.maxCount(1).fireproof()));
    public static final ShovelItem OVERNETHER_SHOVEL = register("overnether_shovel", settings -> new AAExtraShovelItem(AAToolMaterial.OVERNETHER, 1.5f, -3f, settings.maxCount(1).fireproof()));
    public static final ShovelItem ENDERNETHER_SHOVEL = register("endernether_shovel", settings -> new AAExtraShovelItem(AAToolMaterial.ENDERNETHER, 1.5f, -3f, settings.maxCount(1).fireproof()));
    public static final HoeItem OVERNETHER_HOE = register("overnether_hoe", settings -> new AAExtraHoeItem(AAToolMaterial.OVERNETHER, -3f, 0f, settings.maxCount(1).fireproof()));
    public static final HoeItem ENDERNETHER_HOE = register("endernether_hoe", settings -> new AAExtraHoeItem(AAToolMaterial.ENDERNETHER, -3f, 0f, settings.maxCount(1).fireproof()));

    public static final DaggerItem WOODEN_DAGGER = register("wooden_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.WOOD, settings)));
    public static final DaggerItem STONE_DAGGER = register("stone_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.STONE, settings)));
    public static final DaggerItem GOLDEN_DAGGER = register("golden_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.GOLD, settings)));
    public static final DaggerItem IRON_DAGGER = register("iron_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.IRON, settings)));
    public static final DaggerItem DIAMOND_DAGGER = register("diamond_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.DIAMOND, settings)));
    public static final DaggerItem NETHERITE_DAGGER = register("netherite_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.NETHERITE, settings)));
    public static final DaggerItem OVERNETHER_DAGGER = register("overnether_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final DaggerItem ENDERNETHER_DAGGER = register("endernether_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final MaceItem WOODEN_MACE = register("wooden_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.WOOD, settings)));
    public static final MaceItem STONE_MACE = register("stone_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.STONE, settings)));
    public static final MaceItem GOLDEN_MACE = register("golden_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.GOLD, settings)));
    public static final MaceItem IRON_MACE = register("iron_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.IRON, settings)));
    public static final MaceItem DIAMOND_MACE = register("diamond_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.DIAMOND, settings)));
    public static final MaceItem NETHERITE_MACE = register("netherite_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.NETHERITE, settings)));
    public static final MaceItem OVERNETHER_MACE = register("overnether_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final MaceItem ENDERNETHER_MACE = register("endernether_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final ArmorItem OVERNETHER_HELMET = register("overnether_helmet", settings -> new AAExtraArmourItem(AAArmourMaterials.OVERNETHER, EquipmentType.HELMET, settings.fireproof()));
    public static final ArmorItem OVERNETHER_CHESTPLATE = register("overnether_chestplate", settings -> new AAExtraArmourItem(AAArmourMaterials.OVERNETHER, EquipmentType.CHESTPLATE, settings.fireproof()));
    public static final ArmorItem OVERNETHER_LEGGINGS = register("overnether_leggings", settings -> new AAExtraArmourItem(AAArmourMaterials.OVERNETHER, EquipmentType.LEGGINGS, settings.fireproof()));
    public static final ArmorItem OVERNETHER_BOOTS = register("overnether_boots", settings -> new AAExtraArmourItem(AAArmourMaterials.OVERNETHER, EquipmentType.BOOTS, settings.fireproof()));

    public static final ArmorItem ENDERNETHER_HELMET = register("endernether_helmet", settings -> new AAExtraArmourItem(AAArmourMaterials.ENDERNETHER, EquipmentType.HELMET, settings.fireproof()));
    public static final ArmorItem ENDERNETHER_CHESTPLATE = register("endernether_chestplate", settings -> new AAExtraArmourItem(AAArmourMaterials.ENDERNETHER, EquipmentType.CHESTPLATE, settings.fireproof()));
    public static final ArmorItem ENDERNETHER_LEGGINGS = register("endernether_leggings", settings -> new AAExtraArmourItem(AAArmourMaterials.ENDERNETHER, EquipmentType.LEGGINGS, settings.fireproof()));
    public static final ArmorItem ENDERNETHER_BOOTS = register("endernether_boots", settings -> new AAExtraArmourItem(AAArmourMaterials.ENDERNETHER, EquipmentType.BOOTS, settings.fireproof()));

    public static final StaffItem STAFF = register("staff", settings -> new StaffItem(settings.maxCount(1).enchantable(1)));
    public static final BoomerangItem BOOMERANG = register("boomerang", settings -> new BoomerangItem(settings.maxCount(1).maxDamage(256).enchantable(1)));

    // Projectile Items (these exist so that magic projectiles can use them for rendering, they are not obtainable in survival)
    public static final Item FIREBALL = register("fireball_spell", settings -> new Item(settings.fireproof()));
    public static final Item FROSTBALL = register("frostball_spell");
    public static final Item GHOSTLY_ORB = register("ghostly_orb_spell");
    public static final Item WIND_TORNADO = register("wind_tornado_spell");
    public static final Item MAGIC_MISSILE = register("missile_spell");
    public static final List<Item> ITEM_PROJECTILES = Arrays.asList(FIREBALL, FROSTBALL, GHOSTLY_ORB, WIND_TORNADO, MAGIC_MISSILE);

    // List representations so I don't have to update the generators and other registries.
    public static final List<DaggerItem> DAGGERS = Arrays.asList(WOODEN_DAGGER, STONE_DAGGER, GOLDEN_DAGGER, IRON_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER, OVERNETHER_DAGGER, ENDERNETHER_DAGGER);
    public static final List<MaceItem> MACES = Arrays.asList(WOODEN_MACE, STONE_MACE, GOLDEN_MACE, IRON_MACE, DIAMOND_MACE, NETHERITE_MACE, OVERNETHER_MACE, ENDERNETHER_MACE);
    public static final List<ArmorItem> OVERNETHER_ARMOUR = Arrays.asList(OVERNETHER_HELMET, OVERNETHER_CHESTPLATE, OVERNETHER_LEGGINGS, OVERNETHER_BOOTS);
    public static final List<ArmorItem> ENDERNETHER_ARMOUR = Arrays.asList(ENDERNETHER_HELMET, ENDERNETHER_CHESTPLATE, ENDERNETHER_LEGGINGS, ENDERNETHER_BOOTS);

    private static <T extends Item> T register (String name, Function<Item.Settings, T> registryFunction) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, AdditionalArmouryMain.identifier(name));
        T item = registryFunction.apply(new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
        return item;
    }

    private static Item register (String name) {
        return register(name, Item::new);
    }

    public static void init () {
        // Do nothing, just load the class.
    }
}
