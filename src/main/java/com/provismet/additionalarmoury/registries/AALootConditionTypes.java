package com.provismet.additionalarmoury.registries;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.lootconditions.ItemUseTimeLootCondition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public abstract class AALootConditionTypes {
    public static void register () {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, AdditionalArmouryMain.identifier("item_use_time"), ItemUseTimeLootCondition.CODEC);
    }
}
