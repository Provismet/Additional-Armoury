package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.lootconditions.ItemUseTimeLootCondition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public abstract class AALootConditionTypes {
    public static final LootItemConditionType ITEM_USE_TIME = Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, AdditionalArmouryMain.identifier("item_use_time"), new LootItemConditionType(ItemUseTimeLootCondition.CODEC));

    public static void init () {}
}
