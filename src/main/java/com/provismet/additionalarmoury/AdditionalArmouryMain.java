package com.provismet.additionalarmoury;

import com.provismet.additionalarmoury.api.AdditionalArmouryEntrypointMain;
import com.provismet.additionalarmoury.config.AASettings;
import com.provismet.additionalarmoury.registries.AABlocks;
import com.provismet.additionalarmoury.registries.AADataComponentTypes;
import com.provismet.additionalarmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.additionalarmoury.registries.AAEnchantments;
import com.provismet.additionalarmoury.registries.AAEntityTypes;
import com.provismet.additionalarmoury.registries.AAIncantationEffects;
import com.provismet.additionalarmoury.registries.AAItemGroups;
import com.provismet.additionalarmoury.registries.AAItems;
import com.provismet.additionalarmoury.registries.AALambdas;
import com.provismet.additionalarmoury.registries.AALootConditionTypes;
import com.provismet.additionalarmoury.registries.AAParticleTypes;
import com.provismet.additionalarmoury.registries.AARecipeSerializers;
import com.provismet.additionalarmoury.registries.AASounds;
import com.provismet.additionalarmoury.registries.AAStatusEffects;
import com.provismet.additionalarmoury.utility.registry.AARegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
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
            HolderLookup.RegistryLookup<Enchantment> enchantmentLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

            if (source.isBuiltin() || AASettings.shouldOverrideDatapacks()) {
                if (BuiltInLootTables.NETHER_BRIDGE.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(ContextIntProviders.binomial(1, 0.05f))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder()
                                .withEnchantment(AAEnchantments.EXPLOSION.getEntryOrThrow(enchantmentLookup)))));
                }
                else if (BuiltInLootTables.DESERT_PYRAMID.equals(id) || BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(ContextIntProviders.binomial(1, 0.25f))
                            .add(LootItem.lootTableItem(AAItems.BOOMERANG)));
                }
                else if (BuiltInLootTables.ANCIENT_CITY.equals(id) || BuiltInLootTables.ANCIENT_CITY_ICE_BOX.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(ContextIntProviders.binomial(1, 0.2f))
                            .add(LootItem.lootTableItem(AAItems.OVERNETHER_UPGRADE_SMITHING_TEMPLATE)));
                }
                else if (BuiltInLootTables.END_CITY_TREASURE.equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(ContextIntProviders.binomial(1, 0.2f))
                            .add(LootItem.lootTableItem(AAItems.ENDERNETHER_UPGRADE_SMITHING_TEMPLATE)));
                }
                else if (EntityTypes.EVOKER.getDefaultLootTable().isPresent() && EntityTypes.EVOKER.getDefaultLootTable().get().equals(id)) {
                    tableBuilder.withPool(
                        LootPool.lootPool().setRolls(ContextIntProviders.binomial(1, 0.25f))
                            .add(LootItem.lootTableItem(AAItems.STAFF).setWeight(3))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder()
                                .withOneOf(HolderSet.direct(
                                    AAEnchantments.GHOSTLY_ORB.getEntryOrThrow(enchantmentLookup),
                                    AAEnchantments.MAGIC_MISSILE.getEntryOrThrow(enchantmentLookup)
                                ))).setWeight(6))
                            .add(LootItem.lootTableItem(AAItems.STAFF).apply(new EnchantRandomlyFunction.Builder()
                                .withOneOf(HolderSet.direct(
                                    AAEnchantments.GHOSTLY_ORB.getEntryOrThrow(enchantmentLookup),
                                    AAEnchantments.MAGIC_MISSILE.getEntryOrThrow(enchantmentLookup)
                                ))).setWeight(1)));
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
