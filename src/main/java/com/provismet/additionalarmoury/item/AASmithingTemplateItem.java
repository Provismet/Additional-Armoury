package com.provismet.additionalarmoury.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;

public class AASmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.BLUE;

    private static final Component OVERNETHER_UPGRADE_INGREDIENTS_TEXT = Component.translatable("item.additional-armoury.overnether_upgrade.ingredients").withStyle(DESCRIPTION_FORMATTING);
    private static final Component OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable("item.additional-armoury.overnether_upgrade.addition");
    
    private static final Component ENDERNETHER_UPGRADE_INGREDIENTS_TEXT = Component.translatable("item.additional-armoury.endernether_upgrade.ingredients").withStyle(DESCRIPTION_FORMATTING);
    private static final Component ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable("item.additional-armoury.endernether_upgrade.addition");

    private static final Component NETHERITE_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.netherite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
    private static final Component NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.netherite_upgrade.base_slot_description")));

    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.withDefaultNamespace("container/slot/ingot");

    public AASmithingTemplateItem (Component ingredientsText, Component additionsText, Item.Properties settings) {
        super(
            NETHERITE_UPGRADE_APPLIES_TO_TEXT,
            ingredientsText,
            NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
            additionsText,
            AASmithingTemplateItem.createNetheriteUpgradeIconList(),
            List.of(EMPTY_SLOT_INGOT_TEXTURE),
            settings
        );
    }

    public static AASmithingTemplateItem createOvernetherSmithingTemplate (Item.Properties settings) {
        return new AASmithingTemplateItem(OVERNETHER_UPGRADE_INGREDIENTS_TEXT, OVERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT, settings.rarity(Rarity.UNCOMMON));
    }

    public static AASmithingTemplateItem createEndernetherSmithingTemplate (Item.Properties settings) {
        return new AASmithingTemplateItem(ENDERNETHER_UPGRADE_INGREDIENTS_TEXT, ENDERNETHER_ADDITIONS_SLOT_DESCRIPTION_TEXT, settings.rarity(Rarity.UNCOMMON));
    }
    
    private static List<Identifier> createNetheriteUpgradeIconList() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE);
    }
}
