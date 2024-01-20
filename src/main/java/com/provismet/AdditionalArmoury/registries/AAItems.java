package com.provismet.AdditionalArmoury.registries;

import java.util.Arrays;
import java.util.List;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.items.AAExtraAxeItem;
import com.provismet.AdditionalArmoury.items.AAExtraHoeItem;
import com.provismet.AdditionalArmoury.items.AAExtraPickaxeItem;
import com.provismet.AdditionalArmoury.items.AAExtraShovelItem;
import com.provismet.AdditionalArmoury.items.AAExtraSwordItem;
import com.provismet.AdditionalArmoury.items.AASmithingTemplateItem;
import com.provismet.AdditionalArmoury.items.AAToolMaterials;
import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.items.MaceItem;
import com.provismet.AdditionalArmoury.items.StaffItem;
import com.provismet.AdditionalArmoury.items.projectiles.FireballItem;
import com.provismet.AdditionalArmoury.items.projectiles.FrostballItem;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAItems {
    public static final Item OVERNETHER_INGOT = new Item(new FabricItemSettings().fireproof());
    public static final Item ENDERNETHER_INGOT = new Item(new FabricItemSettings().fireproof());

    public static final Item OVERNETHER_UPGRADE_SMITHING_TEMPLATE = AASmithingTemplateItem.createOvernetherSmithingTemplate();
    public static final Item ENDERNETHER_UPGRADE_SMITHING_TEMPLATE = AASmithingTemplateItem.createEndernetherSmithingTemplate();

    public static final SwordItem OVERNETHER_SWORD = new AAExtraSwordItem(AAToolMaterials.OVERNETHER, 3, -2.4f, new FabricItemSettings().maxCount(1).fireproof());
    public static final SwordItem ENDERNETHER_SWORD = new AAExtraSwordItem(AAToolMaterials.ENDERNETHER, 3, -2.4f, new FabricItemSettings().maxCount(1).fireproof());
    public static final AxeItem OVERNETHER_AXE = new AAExtraAxeItem(AAToolMaterials.OVERNETHER, 5, -3f, new FabricItemSettings().maxCount(1).fireproof());
    public static final AxeItem ENDERNETHER_AXE = new AAExtraAxeItem(AAToolMaterials.ENDERNETHER, 5, -3f, new FabricItemSettings().maxCount(1).fireproof());
    public static final PickaxeItem OVERNETHER_PICKAXE = new AAExtraPickaxeItem(AAToolMaterials.OVERNETHER, 1, -2.8f, new FabricItemSettings().maxCount(1).fireproof());
    public static final PickaxeItem ENDERNETHER_PICKAXE = new AAExtraPickaxeItem(AAToolMaterials.ENDERNETHER, 1, -2.8f, new FabricItemSettings().maxCount(1).fireproof());
    public static final ShovelItem OVERNETHER_SHOVEL = new AAExtraShovelItem(AAToolMaterials.OVERNETHER, 1.5f, -3f, new FabricItemSettings().maxCount(1).fireproof());
    public static final ShovelItem ENDERNETHER_SHOVEL = new AAExtraShovelItem(AAToolMaterials.ENDERNETHER, 1.5f, -3f, new FabricItemSettings().maxCount(1).fireproof());
    public static final HoeItem OVERNETHER_HOE = new AAExtraHoeItem(AAToolMaterials.OVERNETHER, -4, 0f, new FabricItemSettings().maxCount(1).fireproof());
    public static final HoeItem ENDERNETHER_HOE = new AAExtraHoeItem(AAToolMaterials.ENDERNETHER, -4, 0f, new FabricItemSettings().maxCount(1).fireproof());

    public static final DaggerItem WOODEN_DAGGER = new DaggerItem(ToolMaterials.WOOD, new FabricItemSettings().maxCount(1), 0x372910);
    public static final DaggerItem STONE_DAGGER = new DaggerItem(ToolMaterials.STONE, new FabricItemSettings().maxCount(1), 0x494949);
    public static final DaggerItem GOLDEN_DAGGER = new DaggerItem(ToolMaterials.GOLD, new FabricItemSettings().maxCount(1), 0x825D16);
    public static final DaggerItem IRON_DAGGER = new DaggerItem(ToolMaterials.IRON, new FabricItemSettings().maxCount(1), 0x444444);
    public static final DaggerItem DIAMOND_DAGGER = new DaggerItem(ToolMaterials.DIAMOND, new FabricItemSettings().maxCount(1), 0x0E3F36);
    public static final DaggerItem NETHERITE_DAGGER = new DaggerItem(ToolMaterials.NETHERITE, new FabricItemSettings().maxCount(1).fireproof(), 0x4A2940);
    public static final DaggerItem OVERNETHER_DAGGER = new DaggerItem(AAToolMaterials.OVERNETHER, new FabricItemSettings().maxCount(1).fireproof(), 0x082832);
    public static final DaggerItem ENDERNETHER_DAGGER = new DaggerItem(AAToolMaterials.ENDERNETHER, new FabricItemSettings().maxCount(1).fireproof(), 0x643C64);

    public static final MaceItem WOODEN_MACE = new MaceItem(ToolMaterials.WOOD, new FabricItemSettings().maxCount(1));
    public static final MaceItem STONE_MACE = new MaceItem(ToolMaterials.STONE, new FabricItemSettings().maxCount(1));
    public static final MaceItem GOLDEN_MACE = new MaceItem(ToolMaterials.GOLD, new FabricItemSettings().maxCount(1));
    public static final MaceItem IRON_MACE = new MaceItem(ToolMaterials.IRON, new FabricItemSettings().maxCount(1));
    public static final MaceItem DIAMOND_MACE = new MaceItem(ToolMaterials.DIAMOND, new FabricItemSettings().maxCount(1));
    public static final MaceItem NETHERITE_MACE = new MaceItem(ToolMaterials.NETHERITE, new FabricItemSettings().maxCount(1).fireproof());
    public static final MaceItem OVERNETHER_MACE = new MaceItem(AAToolMaterials.OVERNETHER, new FabricItemSettings().maxCount(1).fireproof());
    public static final MaceItem ENDERNETHER_MACE = new MaceItem(AAToolMaterials.ENDERNETHER, new FabricItemSettings().maxCount(1).fireproof());

    public static final StaffItem STAFF = new StaffItem(new FabricItemSettings().maxCount(1));

    // Projectile Items (these exist so that magic projectiles can use them for rendering, they are not obtainable in survival)
    public static final FireballItem FIREBALL = new FireballItem(new FabricItemSettings().fireproof());
    public static final FrostballItem FROSTBALL = new FrostballItem(new FabricItemSettings());
    public static final List<Item> ITEM_PROJECTILES = Arrays.asList(FIREBALL, FROSTBALL);

    // List representations so I don't have to update the generators and other registries.
    public static final List<DaggerItem> DAGGERS = Arrays.asList(WOODEN_DAGGER, STONE_DAGGER, GOLDEN_DAGGER, IRON_DAGGER, DIAMOND_DAGGER, NETHERITE_DAGGER, OVERNETHER_DAGGER, ENDERNETHER_DAGGER);
    public static final List<MaceItem> MACES = Arrays.asList(WOODEN_MACE, STONE_MACE, GOLDEN_MACE, IRON_MACE, DIAMOND_MACE, NETHERITE_MACE, OVERNETHER_MACE, ENDERNETHER_MACE);

    private static void register (Item item, String name) {
        Registry.register(Registries.ITEM, AdditionalArmouryMain.identifier(name), item);
    }

    public static void register () {
        register(OVERNETHER_INGOT, "overnether_ingot");
        register(ENDERNETHER_INGOT, "endernether_ingot");
        register(OVERNETHER_UPGRADE_SMITHING_TEMPLATE, "overnether_upgrade_smithing_template");
        register(ENDERNETHER_UPGRADE_SMITHING_TEMPLATE, "endernether_upgrade_smithing_template");

        register(OVERNETHER_SWORD, "overnether_sword");
        register(OVERNETHER_AXE, "overnether_axe");
        register(OVERNETHER_PICKAXE, "overnether_pickaxe");
        register(OVERNETHER_SHOVEL, "overnether_shovel");
        register(OVERNETHER_HOE, "overnether_hoe");

        register(ENDERNETHER_SWORD, "endernether_sword");
        register(ENDERNETHER_AXE, "endernether_axe");
        register(ENDERNETHER_PICKAXE, "endernether_pickaxe");
        register(ENDERNETHER_SHOVEL, "endernether_shovel");
        register(ENDERNETHER_HOE, "endernether_hoe");

        register(WOODEN_DAGGER, "wooden_dagger");
        register(STONE_DAGGER, "stone_dagger");
        register(GOLDEN_DAGGER, "golden_dagger");
        register(IRON_DAGGER, "iron_dagger");
        register(DIAMOND_DAGGER, "diamond_dagger");
        register(NETHERITE_DAGGER, "netherite_dagger");
        register(OVERNETHER_DAGGER, "overnether_dagger");
        register(ENDERNETHER_DAGGER, "endernether_dagger");

        register(WOODEN_MACE, "wooden_mace");
        register(STONE_MACE, "stone_mace");
        register(GOLDEN_MACE, "golden_mace");
        register(IRON_MACE, "iron_mace");
        register(DIAMOND_MACE, "diamond_mace");
        register(NETHERITE_MACE, "netherite_mace");
        register(OVERNETHER_MACE, "overnether_mace");
        register(ENDERNETHER_MACE, "endernether_mace");

        register(STAFF, "staff");

        register(FIREBALL, "fireball_spell");
        register(FROSTBALL, "frostball_spell");
    }
}
