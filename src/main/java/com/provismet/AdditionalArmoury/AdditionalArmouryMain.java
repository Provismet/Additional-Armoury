package com.provismet.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AABlocks;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAEntityTypes;
import com.provismet.AdditionalArmoury.registries.AAIncantationEffects;
import com.provismet.AdditionalArmoury.registries.AAItemGroups;
import com.provismet.AdditionalArmoury.registries.AAItems;
import com.provismet.AdditionalArmoury.registries.AALambdas;
import com.provismet.AdditionalArmoury.registries.AALootConditionTypes;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import com.provismet.AdditionalArmoury.registries.AARecipeSerializers;
import com.provismet.AdditionalArmoury.registries.AASounds;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import com.provismet.CombatPlusCore.loot.functions.EnchantRandomlyFromKeyLootFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provismet.AdditionalArmoury.api.AdditionalArmouryEntrypointMain;
import com.provismet.AdditionalArmoury.config.AASettings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

public class AdditionalArmouryMain implements ModInitializer {
    public static final String MODID = "additional-armoury";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier identifier (String path) {
        return Identifier.of(MODID, path);
    }

    @Override
    public void onInitialize () {
        AARegistries.init();
        AALootConditionTypes.init();
        AAEnchantmentComponentTypes.init();
        AAIncantationEffects.register();
        AADataComponentTypes.register();
        AALambdas.register();
        AASounds.register();
        AABlocks.register();
        AAItems.register();
        AAItemGroups.register();
        AARecipeSerializers.register();
        AAParticleTypes.register();
        AAEntityTypes.register();
        AAStatusEffects.register();
        AASettings.read();

        LootTableEvents.MODIFY.register((id, tableBuilder, source) -> {
            if (source.isBuiltin() || AASettings.shouldOverrideDatapacks()) {
                if (LootTables.NETHER_BRIDGE_CHEST.equals(id)) {
                    tableBuilder.pool(
                        LootPool.builder().rolls(BinomialLootNumberProvider.create(1, 0.05f))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyFromKeyLootFunction.create().option(AAEnchantments.EXPLOSION)))
                    );
                }
                else if (LootTables.DESERT_PYRAMID_CHEST.equals(id) || LootTables.JUNGLE_TEMPLE_DISPENSER_CHEST.equals(id)) {
                    tableBuilder.pool(
                        LootPool.builder().rolls(BinomialLootNumberProvider.create(1, 0.25f))
                            .with(ItemEntry.builder(AAItems.BOOMERANG))
                    );
                }
                else if (LootTables.ANCIENT_CITY_CHEST.equals(id) || LootTables.ANCIENT_CITY_ICE_BOX_CHEST.equals(id)) {
                    tableBuilder.pool(
                        LootPool.builder().rolls(BinomialLootNumberProvider.create(1, 0.2f))
                            .with(ItemEntry.builder(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE))
                    );
                }
                else if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                    tableBuilder.pool(
                        LootPool.builder().rolls(BinomialLootNumberProvider.create(1, 0.2f))
                            .with(ItemEntry.builder(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE))
                    );
                }
                else if (EntityType.EVOKER.getLootTableId().equals(id)) {
                    tableBuilder.pool(
                        LootPool.builder().rolls(BinomialLootNumberProvider.create(1, 0.25f))
                            .with(ItemEntry.builder(AAItems.STAFF).weight(3))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyFromKeyLootFunction.create()
                                .option(AAEnchantments.GHOSTLY_ORB)
                                .option(AAEnchantments.MAGIC_MISSILE)).weight(6))
                            .with(ItemEntry.builder(AAItems.STAFF).apply(EnchantRandomlyFromKeyLootFunction.create()
                                .option(AAEnchantments.GHOSTLY_ORB)
                                .option(AAEnchantments.MAGIC_MISSILE)).weight(1))
                    );
                }
            }
        });

        FabricLoader.getInstance().getEntrypointContainers(MODID, AdditionalArmouryEntrypointMain.class).forEach(
            entrypoint -> {
                String otherModId = entrypoint.getProvider().getMetadata().getId();
                try {
                    entrypoint.getEntrypoint().onInitialize();
                }
                catch (Exception e) {
                    LOGGER.error("Mod {} caused an error during inter-mod initialisation: ", otherModId, e);
                }
            }
        );
    }
}
