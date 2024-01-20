package com.provismet.AdditionalArmoury.items;

import java.util.List;

import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class AASmithingTemplateItem extends SmithingTemplateItem {
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text OVERNETHER_UPGRADE_TEXT = Text.translatable("upgrade.additional-armoury.overnether_upgrade").formatted(TITLE_FORMATTING);
    private static final Text OVERNETHER_UPGRADE_INGREDIENTS_TEXT = Text.translatable("item.additional-armoury.overnether_upgrade.ingredients").formatted(DESCRIPTION_FORMATTING);
    private static final Text OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable("item.additional-armoury.overnether_upgrade.addition");
    
    private static final Text ENDERNETHER_UPGRADE_TEXT = Text.translatable("upgrade.additional-armoury.endernether_upgrade").formatted(TITLE_FORMATTING);
    private static final Text ENDERNETHER_UPGRADE_INGREDIENTS_TEXT = Text.translatable("item.additional-armoury.endernether_upgrade.ingredients").formatted(DESCRIPTION_FORMATTING);
    private static final Text ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable("item.additional-armoury.endernether_upgrade.addition");

    private static final Text NETHERITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("smithing_template.netherite_upgrade.base_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = new Identifier("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = new Identifier("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = new Identifier("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = new Identifier("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = new Identifier("item/empty_slot_ingot");

    public AASmithingTemplateItem (Text ingredientsText, Text titleText, Text additionsText) {
        super(
            NETHERITE_UPGRADE_APPLIES_TO_TEXT,
            ingredientsText,
            titleText,
            NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
            additionsText,
            AASmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            List.of(EMPTY_SLOT_INGOT_TEXTURE)
        );
    }

    public static AASmithingTemplateItem createOvernetherSmithingTemplate () {
        return new AASmithingTemplateItem(OVERNETHER_UPGRADE_INGREDIENTS_TEXT, OVERNETHER_UPGRADE_TEXT, OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT);
    }

    public static AASmithingTemplateItem createEndernetherSmithingTemplate () {
        return new AASmithingTemplateItem(ENDERNETHER_UPGRADE_INGREDIENTS_TEXT, ENDERNETHER_UPGRADE_TEXT, ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT);
    }
    
    private static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }
}
