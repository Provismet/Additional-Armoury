package com.provismet.AdditionalArmoury.items;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

public class AASmithingTemplateItem extends SmithingTemplateItem {
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    private static final Text OVERNETHER_UPGRADE_INGREDIENTS_TEXT = Text.translatable("item.additional-armoury.overnether_upgrade.ingredients").formatted(DESCRIPTION_FORMATTING);
    private static final Text OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable("item.additional-armoury.overnether_upgrade.addition");
    
    private static final Text ENDERNETHER_UPGRADE_INGREDIENTS_TEXT = Text.translatable("item.additional-armoury.endernether_upgrade.ingredients").formatted(DESCRIPTION_FORMATTING);
    private static final Text ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable("item.additional-armoury.endernether_upgrade.addition");

    private static final Text NETHERITE_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.netherite_upgrade.applies_to"))).formatted(DESCRIPTION_FORMATTING);
    private static final Text NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", Identifier.ofVanilla("smithing_template.netherite_upgrade.base_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("container/slot/ingot");

    public AASmithingTemplateItem (Text ingredientsText, Text additionsText, Item.Settings settings) {
        super(
            NETHERITE_UPGRADE_APPLIES_TO_TEXT,
            ingredientsText,
            NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
            additionsText,
            AASmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            List.of(EMPTY_SLOT_INGOT_TEXTURE),
            settings
        );
    }

    public static AASmithingTemplateItem createOvernetherSmithingTemplate (Item.Settings settings) {
        return new AASmithingTemplateItem(OVERNETHER_UPGRADE_INGREDIENTS_TEXT, OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT, settings.rarity(Rarity.UNCOMMON));
    }

    public static AASmithingTemplateItem createEndernetherSmithingTemplate (Item.Settings settings) {
        return new AASmithingTemplateItem(ENDERNETHER_UPGRADE_INGREDIENTS_TEXT, ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT, settings.rarity(Rarity.UNCOMMON));
    }
    
    private static List<Identifier> getNetheriteUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }
}
