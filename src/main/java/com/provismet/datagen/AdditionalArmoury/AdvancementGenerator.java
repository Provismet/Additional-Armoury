package com.provismet.datagen.AdditionalArmoury;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.lootconditions.ItemUseTimeLootCondition;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EnchantedItemTrigger;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.advancements.criterion.UsingItemTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

public class AdvancementGenerator extends FabricAdvancementProvider {
    protected AdvancementGenerator (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement (HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        ItemStack enchantedStaff = AAItems.STAFF.getDefaultInstance();
        enchantedStaff.enchant(AAEnchantments.BOOST.getEntryOrThrow(registryLookup), 1);

        ItemStack bakuretsu = AAItems.STAFF.getDefaultInstance();
        bakuretsu.enchant(AAEnchantments.EXPLOSION.getEntryOrThrow(registryLookup), 1);

        Criterion<EnchantedItemTrigger.TriggerInstance> staffCondition = CriteriaTriggers.ENCHANTED_ITEM.createCriterion(new EnchantedItemTrigger.TriggerInstance(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryLookup.lookupOrThrow(Registries.ITEM), AAItems.STAFF).build()), MinMaxBounds.Ints.ANY));
        AdvancementHolder enchanter = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("story/enchant_item"));
        AdvancementHolder enchantStaff = Advancement.Builder.advancement().parent(enchanter)
            .display(
                ItemStackTemplate.fromNonEmptyStack(enchantedStaff),
                this.buildTranslationKey("enchant_staff.title"),
                this.buildTranslationKey("enchant_staff.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion("enchanted_staff", staffCondition)
            .save(consumer, AdditionalArmouryMain.identifier("story/enchant_staff").toString());

        Criterion<UsingItemTrigger.TriggerInstance> meguminCondition = CriteriaTriggers.USING_ITEM.createCriterion(
            new UsingItemTrigger.TriggerInstance(
                Optional.of(ContextAwarePredicate.create(new ItemUseTimeLootCondition(159))), // 159 is one tick less than the Explosion charge time of 160.
                Optional.of(
                    ItemPredicate.Builder.item().withComponents(DataComponentMatchers.Builder.components()
                        .partial(
                            DataComponentPredicates.ENCHANTMENTS,
                            EnchantmentsPredicate.enchantments(
                                List.of(new EnchantmentPredicate(AAEnchantments.EXPLOSION.getEntryOrThrow(registryLookup), MinMaxBounds.Ints.atLeast(1)))
                            )
                        ).build()
                    ).build())));
        Advancement.Builder.advancement().parent(enchantStaff)
            .display(
                ItemStackTemplate.fromNonEmptyStack(bakuretsu),
                this.buildTranslationKey("archwizard.title"),
                this.buildTranslationKey("archwizard.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion("used_explosion_magic", meguminCondition)
            .save(consumer, AdditionalArmouryMain.identifier("story/explosion_magic").toString());

        AdvancementHolder dragonBreath = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("end/dragon_breath"));
        Advancement.Builder.advancement().parent(dragonBreath)
            .display(
                ItemStackTemplate.fromNonEmptyStack(PotionContents.createItemStack(AAItems.DIAMOND_DAGGER, Potions.NIGHT_VISION)),
                this.buildTranslationKey("tipped_dagger.title"),
                this.buildTranslationKey("tipped_dagger.description"),
                null,
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion(
                "crafted_tipped_dagger",
                RecipeCraftedTrigger.TriggerInstance.craftedItem(ResourceKey.create(Registries.RECIPE, AdditionalArmouryMain.identifier("tipped_dagger")))
            )
            .save(consumer, AdditionalArmouryMain.identifier("end/craft_tipped_dagger").toString());

        AdvancementHolder ancientDebris = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("nether/obtain_ancient_debris"));
        Advancement.Builder.advancement().parent(ancientDebris)
            .display(
                AAItems.OVERNETHER_CHESTPLATE,
                this.buildTranslationKey("overnether.title"),
                this.buildTranslationKey("overnether.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true, false
            )
            .rewards(AdvancementRewards.Builder.experience(100))
            .addCriterion("overnether_armour",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    AAItems.OVERNETHER_HELMET,
                    AAItems.OVERNETHER_CHESTPLATE,
                    AAItems.OVERNETHER_LEGGINGS,
                    AAItems.OVERNETHER_BOOTS
                )
            )
            .save(consumer, AdditionalArmouryMain.identifier("nether/overnether_armour").toString());

        Advancement.Builder.advancement().parent(ancientDebris)
            .display(
                AAItems.ENDERNETHER_CHESTPLATE,
                this.buildTranslationKey("endernether.title"),
                this.buildTranslationKey("endernether.description"),
                null,
                AdvancementType.CHALLENGE,
                true,
                true, false
            )
            .rewards(AdvancementRewards.Builder.experience(100))
            .addCriterion("endernether_armour",
                InventoryChangeTrigger.TriggerInstance.hasItems(
                    AAItems.ENDERNETHER_HELMET,
                    AAItems.ENDERNETHER_CHESTPLATE,
                    AAItems.ENDERNETHER_LEGGINGS,
                    AAItems.ENDERNETHER_BOOTS
                )
            )
            .save(consumer, AdditionalArmouryMain.identifier("nether/endernether_armour").toString());
    }
    
    private Component buildTranslationKey (String suffix) {
        return Component.translatable("advancement.additional-armoury." + suffix);
    }
}
