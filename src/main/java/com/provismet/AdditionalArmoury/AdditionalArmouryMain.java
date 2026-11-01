package com.provismet.AdditionalArmoury;

import com.provismet.AdditionalArmoury.api.AdditionalArmouryEntrypointMain;
import com.provismet.AdditionalArmoury.config.AASettings;
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
import com.provismet.CombatPlusCore.api.loot.functions.EnchantRandomlyFromKeyLootFunction;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdditionalArmouryMain implements ModInitializer {
    public static final String MODID = "additional-armoury";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier identifier (String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    @Override
    public void onInitialize () {
        AARegistries.init();
        AALootConditionTypes.register();
        AAEnchantmentComponentTypes.init();
        AAIncantationEffects.register();
        AADataComponentTypes.register();
        AALambdas.register();
        AASounds.register();
        AABlocks.init();
        AAItems.init();
        AAItemGroups.register();
        AARecipeSerializers.register();
        AAParticleTypes.register();
        AAEntityTypes.register();
        AAStatusEffects.register();
        AASettings.read();

        LootTableEvents.MODIFY.register((id, tableBuilder, source, registries) -> {
            if (source.isBuiltin() || AASettings.shouldOverrideDatapacks()) {
                if (BuiltInLootTables.NETHER_BRIDGE.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.05f))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFromKeyLootFunction.create().option(AAEnchantments.EXPLOSION)))
                    );
                }
                else if (BuiltInLootTables.DESERT_PYRAMID.equals(id) || BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.25f))
                            .add(LootItem.lootTableItem(AAItems.BOOMERANG))
                    );
                }
                else if (BuiltInLootTables.ANCIENT_CITY.equals(id) || BuiltInLootTables.ANCIENT_CITY_ICE_BOX.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.2f))
                            .add(LootItem.lootTableItem(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE))
                    );
                }
                else if (BuiltInLootTables.END_CITY_TREASURE.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.2f))
                            .add(LootItem.lootTableItem(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE))
                    );
                }
                else if (EntityType.EVOKER.getDefaultLootTable().isPresent() && EntityType.EVOKER.getDefaultLootTable().get().equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(BinomialDistributionGenerator.binomial(1, 0.25f))
                            .add(LootItem.lootTableItem(AAItems.STAFF).setWeight(3))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFromKeyLootFunction.create()
                                .option(AAEnchantments.GHOSTLY_ORB)
                                .option(AAEnchantments.MAGIC_MISSILE)).setWeight(6))
                            .add(LootItem.lootTableItem(AAItems.STAFF).apply(EnchantRandomlyFromKeyLootFunction.create()
                                .option(AAEnchantments.GHOSTLY_ORB)
                                .option(AAEnchantments.MAGIC_MISSILE)).setWeight(1))
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
