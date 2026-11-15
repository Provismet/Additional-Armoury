package com.provismet.additionalarmoury.references;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public interface AAItemIds {
    ResourceKey<Item> OVERNETHER_INGOT = create("overnether_ingot");
    ResourceKey<Item> ENDERNETHER_INGOT = create("endernether_ingot");
    ResourceKey<Item> OVERNETHER_UPGRADE_SMITHING_TEMPLATE = create("overnether_upgrade_smithing_template");
    ResourceKey<Item> ENDERNETHER_UPGRADE_SMITHING_TEMPLATE = create("endernether_upgrade_smithing_template");
    ResourceKey<Item> OVERNETHER_SWORD = create("overnether_sword");
    ResourceKey<Item> ENDERNETHER_SWORD = create("endernether_sword");
    ResourceKey<Item> OVERNETHER_AXE = create("overnether_axe");
    ResourceKey<Item> ENDERNETHER_AXE = create("endernether_axe");
    ResourceKey<Item> OVERNETHER_PICKAXE = create("overnether_pickaxe");
    ResourceKey<Item> ENDERNETHER_PICKAXE = create("endernether_pickaxe");
    ResourceKey<Item> OVERNETHER_SHOVEL = create("overnether_shovel");
    ResourceKey<Item> ENDERNETHER_SHOVEL = create("endernether_shovel");
    ResourceKey<Item> OVERNETHER_HOE = create("overnether_hoe");
    ResourceKey<Item> ENDERNETHER_HOE = create("endernether_hoe");
    ResourceKey<Item> OVERNETHER_SPEAR = create("overnether_spear");
    ResourceKey<Item> ENDERNETHER_SPEAR = create("endernether_spear");
    ResourceKey<Item> WOODEN_DAGGER = create("wooden_dagger");
    ResourceKey<Item> STONE_DAGGER = create("stone_dagger");
    ResourceKey<Item> COPPER_DAGGER = create("copper_dagger");
    ResourceKey<Item> GOLDEN_DAGGER = create("golden_dagger");
    ResourceKey<Item> IRON_DAGGER = create("iron_dagger");
    ResourceKey<Item> DIAMOND_DAGGER = create("diamond_dagger");
    ResourceKey<Item> NETHERITE_DAGGER = create("netherite_dagger");
    ResourceKey<Item> OVERNETHER_DAGGER = create("overnether_dagger");
    ResourceKey<Item> ENDERNETHER_DAGGER = create("endernether_dagger");
    ResourceKey<Item> WOODEN_MACE = create("wooden_mace");
    ResourceKey<Item> STONE_MACE = create("stone_mace");
    ResourceKey<Item> COPPER_MACE = create("copper_mace");
    ResourceKey<Item> GOLDEN_MACE = create("golden_mace");
    ResourceKey<Item> IRON_MACE = create("iron_mace");
    ResourceKey<Item> DIAMOND_MACE = create("diamond_mace");
    ResourceKey<Item> NETHERITE_MACE = create("netherite_mace");
    ResourceKey<Item> OVERNETHER_MACE = create("overnether_mace");
    ResourceKey<Item> ENDERNETHER_MACE = create("endernether_mace");
    ResourceKey<Item> OVERNETHER_HELMET = create("overnether_helmet");
    ResourceKey<Item> OVERNETHER_CHESTPLATE = create("overnether_chestplate");
    ResourceKey<Item> OVERNETHER_LEGGINGS = create("overnether_leggings");
    ResourceKey<Item> OVERNETHER_BOOTS = create("overnether_boots");
    ResourceKey<Item> ENDERNETHER_HELMET = create("endernether_helmet");
    ResourceKey<Item> ENDERNETHER_CHESTPLATE = create("endernether_chestplate");
    ResourceKey<Item> ENDERNETHER_LEGGINGS = create("endernether_leggings");
    ResourceKey<Item> ENDERNETHER_BOOTS = create("endernether_boots");
    ResourceKey<Item> STAFF = create("staff");
    ResourceKey<Item> BOOMERANG = create("boomerang");
    ResourceKey<Item> FIREBALL_SPELL = create("fireball_spell");
    ResourceKey<Item> FROSTBALL_SPELL = create("frostball_spell");
    ResourceKey<Item> GHOSTLY_ORB_SPELL = create("ghostly_orb_spell");
    ResourceKey<Item> WIND_TORNADO_SPELL = create("wind_tornado_spell");
    ResourceKey<Item> MISSILE_SPELL = create("missile_spell");

    private static ResourceKey<Item> create (String path) {
        return ResourceKey.create(Registries.ITEM, AdditionalArmouryMain.identifier(path));
    }
}
