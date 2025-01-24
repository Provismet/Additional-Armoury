package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.lootconditions.ItemUseTimeLootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class AALootConditionTypes {
    public static final LootConditionType ITEM_USE_TIME = Registry.register(Registries.LOOT_CONDITION_TYPE, AdditionalArmouryMain.identifier("item_use_time"), new LootConditionType(ItemUseTimeLootCondition.CODEC));

    public static void init () {}
}
