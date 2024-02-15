package com.provismet.datagen.AdditionalArmoury;

import java.util.Optional;
import java.util.function.Consumer;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.EnchantedItemCriterion;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeCraftedCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AdvancementGenerator extends FabricAdvancementProvider {
    protected AdvancementGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement (Consumer<AdvancementEntry> consumer) {
        ItemStack enchantedStaff = AAItems.STAFF.getDefaultStack();
        enchantedStaff.addEnchantment(AAEnchantments.BOOST, 1);

        ItemStack bakuretsu = AAItems.STAFF.getDefaultStack();
        bakuretsu.addEnchantment(AAEnchantments.EXPLOSION, 1);

        AdvancementCriterion<EnchantedItemCriterion.Conditions> staffCondition = Criteria.ENCHANTED_ITEM.create(new EnchantedItemCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.create().items(AAItems.STAFF).build()), NumberRange.IntRange.ANY));
        AdvancementEntry enchanter = Advancement.Builder.create().build(new Identifier("story/enchant_item"));
        AdvancementEntry enchantStaff = Advancement.Builder.create().parent(enchanter)
            .display(
                enchantedStaff,
                this.buildTranslationKey("enchant_staff.title"),
                this.buildTranslationKey("enchant_staff.description"),
                null,
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion("enchanted_staff", staffCondition)
            .build(consumer, AdditionalArmouryMain.identifier("story/enchant_staff").toString());
        
        Advancement.Builder.create().parent(enchantStaff)
            .display(
                bakuretsu,
                this.buildTranslationKey("archwizard.title"),
                this.buildTranslationKey("archwizard.description"),
                null,
                AdvancementFrame.CHALLENGE,
                true,
                true,
                false
            )
            .criterion("used_explosion_magic", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
            .build(consumer, AdditionalArmouryMain.identifier("story/explosion_magic").toString());

        AdvancementEntry dragonBreath = Advancement.Builder.create().build(new Identifier("end/dragon_breath"));
        Advancement.Builder.create().parent(dragonBreath)
            .display(
                PotionUtil.setPotion(AAItems.DIAMOND_DAGGER.getDefaultStack(), Potions.POISON),
                this.buildTranslationKey("tipped_dagger.title"),
                this.buildTranslationKey("tipped_dagger.description"),
                null,
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion("crafted_tipped_dagger", RecipeCraftedCriterion.Conditions.create(AdditionalArmouryMain.identifier("tipped_dagger")))
            .build(consumer, AdditionalArmouryMain.identifier("end/craft_tipped_dagger").toString());

        AdvancementEntry ancientDebris = Advancement.Builder.create().build(new Identifier("nether/obtain_ancient_debris"));
        Advancement.Builder.create().parent(ancientDebris)
            .display(
                AAItems.OVERNETHER_CHESTPLATE,
                this.buildTranslationKey("overnether.title"),
                this.buildTranslationKey("overnether.description"),
                null,
                AdvancementFrame.CHALLENGE,
                true,
                true, false
            )
            .rewards(AdvancementRewards.Builder.experience(100))
            .criterion("overnether_armour",
                InventoryChangedCriterion.Conditions.items(
                    AAItems.OVERNETHER_HELMET,
                    AAItems.OVERNETHER_CHESTPLATE,
                    AAItems.OVERNETHER_LEGGINGS,
                    AAItems.OVERNETHER_BOOTS
                )
            )
            .build(consumer, AdditionalArmouryMain.identifier("nether/overnether_armour").toString());

        Advancement.Builder.create().parent(ancientDebris)
            .display(
                AAItems.ENDERNETHER_CHESTPLATE,
                this.buildTranslationKey("endernether.title"),
                this.buildTranslationKey("endernether.description"),
                null,
                AdvancementFrame.CHALLENGE,
                true,
                true, false
            )
            .rewards(AdvancementRewards.Builder.experience(100))
            .criterion("endernether_armour",
                InventoryChangedCriterion.Conditions.items(
                    AAItems.ENDERNETHER_HELMET,
                    AAItems.ENDERNETHER_CHESTPLATE,
                    AAItems.ENDERNETHER_LEGGINGS,
                    AAItems.ENDERNETHER_BOOTS
                )
            )
            .build(consumer, AdditionalArmouryMain.identifier("nether/endernether_armour").toString());
    }
    
    private Text buildTranslationKey (String suffix) {
        return Text.translatable("advancement.additional-armoury." + suffix);
    }
}
