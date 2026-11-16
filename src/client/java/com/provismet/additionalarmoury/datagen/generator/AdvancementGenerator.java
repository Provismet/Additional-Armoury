package com.provismet.additionalarmoury.datagen.generator;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.lootconditions.ItemUseTimeLootCondition;
import com.provismet.additionalarmoury.registries.AAEnchantments;
import com.provismet.additionalarmoury.registries.AAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.EnchantmentPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.EnchantedItemTrigger;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.RecipeCraftedTrigger;
import net.minecraft.advancements.triggers.UsingItemTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator extends FabricAdvancementProvider {
    public AdvancementGenerator (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement (HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        ItemEnchantments.Mutable boostMutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        boostMutable.set(AAEnchantments.BOOST.getEntryOrThrow(registryLookup), 1);

        ItemEnchantments.Mutable meguMutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        meguMutable.set(AAEnchantments.EXPLOSION.getEntryOrThrow(registryLookup), 1);

        Criterion<EnchantedItemTrigger.TriggerInstance> staffCondition = CriteriaTriggers.ENCHANTED_ITEM.createCriterion(new EnchantedItemTrigger.TriggerInstance(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(registryLookup.lookupOrThrow(Registries.ITEM), AAItems.STAFF).build()), MinMaxBounds.Ints.ANY));
        AdvancementHolder enchanter = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("story/enchant_item"));
        AdvancementHolder enchantStaff = Advancement.Builder.advancement().parent(enchanter)
            .display(
                new ItemStackTemplate(AAItems.STAFF, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS, boostMutable.toImmutable()).build()),
                buildTranslationKey("enchant_staff.title"),
                buildTranslationKey("enchant_staff.description"),
                AdvancementType.GOAL,
                true,
                true,
                false
            )
            .addCriterion("enchanted_staff", staffCondition)
            .save(consumer, AdditionalArmouryMain.identifier("story/enchant_staff"));

        Criterion<UsingItemTrigger.TriggerInstance> meguminCondition = CriteriaTriggers.USING_ITEM.createCriterion(
            new UsingItemTrigger.TriggerInstance(
                Optional.of(Holder.direct(new ItemUseTimeLootCondition(159))), // 159 is one tick less than the Explosion charge time of 160.
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
                new ItemStackTemplate(AAItems.STAFF, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS, meguMutable.toImmutable()).build()),
                buildTranslationKey("archwizard.title"),
                buildTranslationKey("archwizard.description"),
                AdvancementType.CHALLENGE,
                true,
                true,
                false
            )
            .addCriterion("used_explosion_magic", meguminCondition)
            .save(consumer, AdditionalArmouryMain.identifier("story/explosion_magic"));

        AdvancementHolder ancientDebris = Advancement.Builder.advancement().build(Identifier.withDefaultNamespace("nether/obtain_ancient_debris"));
        Advancement.Builder.advancement().parent(ancientDebris)
            .display(
                AAItems.OVERNETHER_CHESTPLATE,
                buildTranslationKey("overnether.title"),
                buildTranslationKey("overnether.description"),
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
            .save(consumer, AdditionalArmouryMain.identifier("nether/overnether_armour"));

        Advancement.Builder.advancement().parent(ancientDebris)
            .display(
                AAItems.ENDERNETHER_CHESTPLATE,
                buildTranslationKey("endernether.title"),
                buildTranslationKey("endernether.description"),
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
            .save(consumer, AdditionalArmouryMain.identifier("nether/endernether_armour"));
    }
    
    public static Component buildTranslationKey (String suffix) {
        return Component.translatable("advancement.additional-armoury." + suffix);
    }
}
