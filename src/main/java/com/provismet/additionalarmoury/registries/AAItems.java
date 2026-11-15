package com.provismet.additionalarmoury.registries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.provismet.additionalarmoury.references.AABlockItemIds;
import com.provismet.additionalarmoury.references.AAItemIds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import com.provismet.additionalarmoury.item.*;

import com.provismet.additionalarmoury.utility.Util;

public class AAItems {
    public static final Item OVERNETHER_INGOT = register(AAItemIds.OVERNETHER_INGOT, new Item.Properties().fireResistant());
    public static final Item ENDERNETHER_INGOT = register(AAItemIds.ENDERNETHER_INGOT, new Item.Properties().fireResistant());

    public static final BlockItem OVERNETHER_BLOCK = register(AABlockItemIds.OVERNETHER_BLOCK.item(), settings -> new BlockItem(AABlocks.OVERNETHER_BLOCK, settings.useBlockDescriptionPrefix()));
    public static final BlockItem ENDERNETHER_BLOCK = register(AABlockItemIds.ENDERNETHER_BLOCK.item(), settings -> new BlockItem(AABlocks.ENDERNETHER_BLOCK, settings.useBlockDescriptionPrefix()));

    public static final Item OVERNETHER_UPGRADE_SMITHING_TEMPLATE = register(AAItemIds.OVERNETHER_UPGRADE_SMITHING_TEMPLATE, AASmithingTemplateItem::createOvernetherSmithingTemplate);
    public static final Item ENDERNETHER_UPGRADE_SMITHING_TEMPLATE = register(AAItemIds.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE, AASmithingTemplateItem::createEndernetherSmithingTemplate);

    public static final Item OVERNETHER_SWORD = register(AAItemIds.OVERNETHER_SWORD, Util.applySwordSettings(AAToolMaterial.OVERNETHER, 3f, -2.4f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final Item ENDERNETHER_SWORD = register(AAItemIds.ENDERNETHER_SWORD, Util.applySwordSettings(AAToolMaterial.ENDERNETHER, 3f, -2.4f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final AxeItem OVERNETHER_AXE = register(AAItemIds.OVERNETHER_AXE, settings -> new AAExtraAxeItem(AAToolMaterial.OVERNETHER, 5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final AxeItem ENDERNETHER_AXE = register(AAItemIds.ENDERNETHER_AXE, settings -> new AAExtraAxeItem(AAToolMaterial.ENDERNETHER, 5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final Item OVERNETHER_PICKAXE = register(AAItemIds.OVERNETHER_PICKAXE, Util.applyPickaxeSettings(AAToolMaterial.OVERNETHER, 1f, -2.8f, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_PICKAXE = register(AAItemIds.ENDERNETHER_PICKAXE, Util.applyPickaxeSettings(AAToolMaterial.ENDERNETHER, 1f, -2.8f, new Item.Properties().fireResistant()));
    public static final ShovelItem OVERNETHER_SHOVEL = register(AAItemIds.OVERNETHER_SHOVEL, settings -> new AAExtraShovelItem(AAToolMaterial.OVERNETHER, 1.5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final ShovelItem ENDERNETHER_SHOVEL = register(AAItemIds.ENDERNETHER_SHOVEL, settings -> new AAExtraShovelItem(AAToolMaterial.ENDERNETHER, 1.5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final HoeItem OVERNETHER_HOE = register(AAItemIds.OVERNETHER_HOE, settings -> new AAExtraHoeItem(AAToolMaterial.OVERNETHER, -3f, 0f, settings.stacksTo(1).fireResistant()));
    public static final HoeItem ENDERNETHER_HOE = register(AAItemIds.ENDERNETHER_HOE, settings -> new AAExtraHoeItem(AAToolMaterial.ENDERNETHER, -3f, 0f, settings.stacksTo(1).fireResistant()));
    public static final Item OVERNETHER_SPEAR = register(AAItemIds.OVERNETHER_SPEAR, Util.applySpearSettings(AAToolMaterial.OVERNETHER, 1.15f, 1.075f, 0.4f, 2.5f, 9f, 5.5F, 5.1f, 8.75f, 4.6f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final Item ENDERNETHER_SPEAR = register(AAItemIds.ENDERNETHER_SPEAR, Util.applySpearSettings(AAToolMaterial.ENDERNETHER, 1.15f, 1.075f, 0.2f, 2.5f, 9f, 5.5F, 5.1f, 8.75f, 4.6f, new Item.Properties().stacksTo(1).fireResistant()));

    public static final DaggerItem WOODEN_DAGGER = register(AAItemIds.WOODEN_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.WOOD, settings)));
    public static final DaggerItem STONE_DAGGER = register(AAItemIds.STONE_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.STONE, settings)));
    public static final DaggerItem COPPER_DAGGER = register(AAItemIds.COPPER_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.COPPER, settings)));
    public static final DaggerItem GOLDEN_DAGGER = register(AAItemIds.GOLDEN_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.GOLD, settings)));
    public static final DaggerItem IRON_DAGGER = register(AAItemIds.IRON_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.IRON, settings)));
    public static final DaggerItem DIAMOND_DAGGER = register(AAItemIds.DIAMOND_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.DIAMOND, settings)));
    public static final DaggerItem NETHERITE_DAGGER = register(AAItemIds.NETHERITE_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.NETHERITE, settings)));
    public static final DaggerItem OVERNETHER_DAGGER = register(AAItemIds.OVERNETHER_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final DaggerItem ENDERNETHER_DAGGER = register(AAItemIds.ENDERNETHER_DAGGER, settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final MaceItem WOODEN_MACE = register(AAItemIds.WOODEN_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.WOOD, settings)));
    public static final MaceItem STONE_MACE = register(AAItemIds.STONE_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.STONE, settings)));
    public static final MaceItem COPPER_MACE = register(AAItemIds.COPPER_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.COPPER, settings)));
    public static final MaceItem GOLDEN_MACE = register(AAItemIds.GOLDEN_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.GOLD, settings)));
    public static final MaceItem IRON_MACE = register(AAItemIds.IRON_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.IRON, settings)));
    public static final MaceItem DIAMOND_MACE = register(AAItemIds.DIAMOND_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.DIAMOND, settings)));
    public static final MaceItem NETHERITE_MACE = register(AAItemIds.NETHERITE_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.NETHERITE, settings)));
    public static final MaceItem OVERNETHER_MACE = register(AAItemIds.OVERNETHER_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final MaceItem ENDERNETHER_MACE = register(AAItemIds.ENDERNETHER_MACE, settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final Item OVERNETHER_HELMET = register(AAItemIds.OVERNETHER_HELMET, Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.HELMET, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_CHESTPLATE = register(AAItemIds.OVERNETHER_CHESTPLATE, Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_LEGGINGS = register(AAItemIds.OVERNETHER_LEGGINGS, Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.LEGGINGS, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_BOOTS = register(AAItemIds.OVERNETHER_BOOTS, Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.BOOTS, new Item.Properties().fireResistant()));

    public static final Item ENDERNETHER_HELMET = register(AAItemIds.ENDERNETHER_HELMET, Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.HELMET, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_CHESTPLATE = register(AAItemIds.ENDERNETHER_CHESTPLATE, Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_LEGGINGS = register(AAItemIds.ENDERNETHER_LEGGINGS, Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.LEGGINGS, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_BOOTS = register(AAItemIds.ENDERNETHER_BOOTS, Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.BOOTS, new Item.Properties().fireResistant()));

    public static final StaffItem STAFF = register(AAItemIds.STAFF, settings -> new StaffItem(settings.stacksTo(1).enchantable(1)));
    public static final BoomerangItem BOOMERANG = register(AAItemIds.BOOMERANG, settings -> new BoomerangItem(settings.stacksTo(1).durability(256).enchantable(1)));

    // Projectile Items (these exist so that magic projectiles can use them for rendering, they are not obtainable in survival)
    public static final Item FIREBALL = register(AAItemIds.FIREBALL_SPELL, settings -> new Item(settings.fireResistant()));
    public static final Item FROSTBALL = register(AAItemIds.FROSTBALL_SPELL);
    public static final Item GHOSTLY_ORB = register(AAItemIds.GHOSTLY_ORB_SPELL);
    public static final Item WIND_TORNADO = register(AAItemIds.WIND_TORNADO_SPELL);
    public static final Item MAGIC_MISSILE = register(AAItemIds.MISSILE_SPELL);
    public static final List<Item> ITEM_PROJECTILES = Arrays.asList(FIREBALL, FROSTBALL, GHOSTLY_ORB, WIND_TORNADO, MAGIC_MISSILE);

    // List representations so I don't have to update the generators and other registries.
    public static final List<DaggerItem> DAGGERS = Arrays.asList(WOODEN_DAGGER, STONE_DAGGER, COPPER_DAGGER, GOLDEN_DAGGER, IRON_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER, OVERNETHER_DAGGER, ENDERNETHER_DAGGER);
    public static final List<MaceItem> MACES = Arrays.asList(WOODEN_MACE, STONE_MACE, COPPER_MACE, GOLDEN_MACE, IRON_MACE, DIAMOND_MACE, NETHERITE_MACE, OVERNETHER_MACE, ENDERNETHER_MACE);
    public static final List<Item> OVERNETHER_ARMOUR = Arrays.asList(OVERNETHER_HELMET, OVERNETHER_CHESTPLATE, OVERNETHER_LEGGINGS, OVERNETHER_BOOTS);
    public static final List<Item> ENDERNETHER_ARMOUR = Arrays.asList(ENDERNETHER_HELMET, ENDERNETHER_CHESTPLATE, ENDERNETHER_LEGGINGS, ENDERNETHER_BOOTS);

    private static <T extends Item> T register (ResourceKey<Item> key, Function<Item.Properties, T> registryFunction, Item.Properties settings) {
        T item = registryFunction.apply(settings.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }

    private static <T extends Item> T register (ResourceKey<Item> key, Function<Item.Properties, T> registryFunction) {
        return register(key, registryFunction, new Item.Properties());
    }

    private static Item register (ResourceKey<Item> key, Item.Properties settings) {
        return register(key, Item::new, settings);
    }

    private static Item register (ResourceKey<Item> key) {
        return register(key, Item::new);
    }

    public static void init () {
        // Do nothing, just load the class.
    }
}
