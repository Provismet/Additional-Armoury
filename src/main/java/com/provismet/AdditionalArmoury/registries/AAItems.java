package com.provismet.AdditionalArmoury.registries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.*;

import com.provismet.AdditionalArmoury.utility.Util;

public class AAItems {
    public static final Item OVERNETHER_INGOT = register("overnether_ingot", new Item.Properties().fireResistant());
    public static final Item ENDERNETHER_INGOT = register("endernether_ingot", new Item.Properties().fireResistant());

    public static final BlockItem OVERNETHER_BLOCK = register("overnether_block", settings -> new BlockItem(AABlocks.OVERNETHER_BLOCK, settings.useBlockDescriptionPrefix()));
    public static final BlockItem ENDERNETHER_BLOCK = register("endernether_block", settings -> new BlockItem(AABlocks.ENDERNETHER_BLOCK, settings.useBlockDescriptionPrefix()));

    public static final Item OVERNETHER_UPGRADE_SMITHING_TEMPLATE = register("overnether_upgrade_smithing_template", AASmithingTemplateItem::createOvernetherSmithingTemplate);
    public static final Item ENDERNETHER_UPGRADE_SMITHING_TEMPLATE = register("endernether_upgrade_smithing_template", AASmithingTemplateItem::createEndernetherSmithingTemplate);

    public static final Item OVERNETHER_SWORD = register("overnether_sword", Util.applySwordSettings(AAToolMaterial.OVERNETHER, 3f, -2.4f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final Item ENDERNETHER_SWORD = register("endernether_sword", Util.applySwordSettings(AAToolMaterial.ENDERNETHER, 3f, -2.4f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final AxeItem OVERNETHER_AXE = register("overnether_axe", settings -> new AAExtraAxeItem(AAToolMaterial.OVERNETHER, 5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final AxeItem ENDERNETHER_AXE = register("endernether_axe", settings -> new AAExtraAxeItem(AAToolMaterial.ENDERNETHER, 5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final Item OVERNETHER_PICKAXE = register("overnether_pickaxe", Util.applyPickaxeSettings(AAToolMaterial.OVERNETHER, 1f, -2.8f, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_PICKAXE = register("endernether_pickaxe", Util.applyPickaxeSettings(AAToolMaterial.ENDERNETHER, 1f, -2.8f, new Item.Properties().fireResistant()));
    public static final ShovelItem OVERNETHER_SHOVEL = register("overnether_shovel", settings -> new AAExtraShovelItem(AAToolMaterial.OVERNETHER, 1.5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final ShovelItem ENDERNETHER_SHOVEL = register("endernether_shovel", settings -> new AAExtraShovelItem(AAToolMaterial.ENDERNETHER, 1.5f, -3f, settings.stacksTo(1).fireResistant()));
    public static final HoeItem OVERNETHER_HOE = register("overnether_hoe", settings -> new AAExtraHoeItem(AAToolMaterial.OVERNETHER, -3f, 0f, settings.stacksTo(1).fireResistant()));
    public static final HoeItem ENDERNETHER_HOE = register("endernether_hoe", settings -> new AAExtraHoeItem(AAToolMaterial.ENDERNETHER, -3f, 0f, settings.stacksTo(1).fireResistant()));
    public static final Item OVERNETHER_SPEAR = register("overnether_spear", Util.applySpearSettings(AAToolMaterial.OVERNETHER, 1.15f, 1.075f, 0.4f, 2.5f, 9f, 5.5F, 5.1f, 8.75f, 4.6f, new Item.Properties().stacksTo(1).fireResistant()));
    public static final Item ENDERNETHER_SPEAR = register("endernether_spear", Util.applySpearSettings(AAToolMaterial.ENDERNETHER, 1.15f, 1.075f, 0.2f, 2.5f, 9f, 5.5F, 5.1f, 8.75f, 4.6f, new Item.Properties().stacksTo(1).fireResistant()));

    public static final DaggerItem WOODEN_DAGGER = register("wooden_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.WOOD, settings)));
    public static final DaggerItem STONE_DAGGER = register("stone_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.STONE, settings)));
    public static final DaggerItem COPPER_DAGGER = register("copper_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.COPPER, settings)));
    public static final DaggerItem GOLDEN_DAGGER = register("golden_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.GOLD, settings)));
    public static final DaggerItem IRON_DAGGER = register("iron_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.IRON, settings)));
    public static final DaggerItem DIAMOND_DAGGER = register("diamond_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.DIAMOND, settings)));
    public static final DaggerItem NETHERITE_DAGGER = register("netherite_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(ToolMaterial.NETHERITE, settings)));
    public static final DaggerItem OVERNETHER_DAGGER = register("overnether_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final DaggerItem ENDERNETHER_DAGGER = register("endernether_dagger", settings -> new DaggerItem(DaggerItem.createDefaultDaggerSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final MaceItem WOODEN_MACE = register("wooden_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.WOOD, settings)));
    public static final MaceItem STONE_MACE = register("stone_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.STONE, settings)));
    public static final MaceItem COPPER_MACE = register("copper_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.COPPER, settings)));
    public static final MaceItem GOLDEN_MACE = register("golden_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.GOLD, settings)));
    public static final MaceItem IRON_MACE = register("iron_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.IRON, settings)));
    public static final MaceItem DIAMOND_MACE = register("diamond_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.DIAMOND, settings)));
    public static final MaceItem NETHERITE_MACE = register("netherite_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(ToolMaterial.NETHERITE, settings)));
    public static final MaceItem OVERNETHER_MACE = register("overnether_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.OVERNETHER, settings)));
    public static final MaceItem ENDERNETHER_MACE = register("endernether_mace", settings -> new MaceItem(MaceItem.createDefaultMaceSettings(AAToolMaterial.ENDERNETHER, settings)));

    public static final Item OVERNETHER_HELMET = register("overnether_helmet", Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.HELMET, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_CHESTPLATE = register("overnether_chestplate", Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_LEGGINGS = register("overnether_leggings", Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.LEGGINGS, new Item.Properties().fireResistant()));
    public static final Item OVERNETHER_BOOTS = register("overnether_boots", Util.applyArmourSettings(AAArmourMaterials.OVERNETHER, ArmorType.BOOTS, new Item.Properties().fireResistant()));

    public static final Item ENDERNETHER_HELMET = register("endernether_helmet", Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.HELMET, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_CHESTPLATE = register("endernether_chestplate", Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_LEGGINGS = register("endernether_leggings", Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.LEGGINGS, new Item.Properties().fireResistant()));
    public static final Item ENDERNETHER_BOOTS = register("endernether_boots", Util.applyArmourSettings(AAArmourMaterials.ENDERNETHER, ArmorType.BOOTS, new Item.Properties().fireResistant()));

    public static final StaffItem STAFF = register("staff", settings -> new StaffItem(settings.stacksTo(1).enchantable(1)));
    public static final BoomerangItem BOOMERANG = register("boomerang", settings -> new BoomerangItem(settings.stacksTo(1).durability(256).enchantable(1)));

    // Projectile Items (these exist so that magic projectiles can use them for rendering, they are not obtainable in survival)
    public static final Item FIREBALL = register("fireball_spell", settings -> new Item(settings.fireResistant()));
    public static final Item FROSTBALL = register("frostball_spell");
    public static final Item GHOSTLY_ORB = register("ghostly_orb_spell");
    public static final Item WIND_TORNADO = register("wind_tornado_spell");
    public static final Item MAGIC_MISSILE = register("missile_spell");
    public static final List<Item> ITEM_PROJECTILES = Arrays.asList(FIREBALL, FROSTBALL, GHOSTLY_ORB, WIND_TORNADO, MAGIC_MISSILE);

    // List representations so I don't have to update the generators and other registries.
    public static final List<DaggerItem> DAGGERS = Arrays.asList(WOODEN_DAGGER, STONE_DAGGER, COPPER_DAGGER, GOLDEN_DAGGER, IRON_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER, OVERNETHER_DAGGER, ENDERNETHER_DAGGER);
    public static final List<MaceItem> MACES = Arrays.asList(WOODEN_MACE, STONE_MACE, COPPER_MACE, GOLDEN_MACE, IRON_MACE, DIAMOND_MACE, NETHERITE_MACE, OVERNETHER_MACE, ENDERNETHER_MACE);
    public static final List<Item> OVERNETHER_ARMOUR = Arrays.asList(OVERNETHER_HELMET, OVERNETHER_CHESTPLATE, OVERNETHER_LEGGINGS, OVERNETHER_BOOTS);
    public static final List<Item> ENDERNETHER_ARMOUR = Arrays.asList(ENDERNETHER_HELMET, ENDERNETHER_CHESTPLATE, ENDERNETHER_LEGGINGS, ENDERNETHER_BOOTS);

    private static <T extends Item> T register (String name, Function<Item.Properties, T> registryFunction, Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, AdditionalArmouryMain.identifier(name));
        T item = registryFunction.apply(settings.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }

    private static <T extends Item> T register (String name, Function<Item.Properties, T> registryFunction) {
        return register(name, registryFunction, new Item.Properties());
    }

    private static Item register (String name, Item.Properties settings) {
        return register(name, Item::new, settings);
    }

    private static Item register (String name) {
        return register(name, Item::new);
    }

    public static void init () {
        // Do nothing, just load the class.
    }
}
