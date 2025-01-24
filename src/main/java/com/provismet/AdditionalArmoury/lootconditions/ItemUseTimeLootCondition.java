package com.provismet.AdditionalArmoury.lootconditions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AALootConditionTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.dynamic.Codecs;

public record ItemUseTimeLootCondition (int useTime) implements LootCondition {
    public static final MapCodec<ItemUseTimeLootCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(Codecs.NON_NEGATIVE_INT.fieldOf("use_time").forGetter(ItemUseTimeLootCondition::useTime))
            .apply(instance, ItemUseTimeLootCondition::new)
    );

    @Override
    public LootConditionType getType () {
        return AALootConditionTypes.ITEM_USE_TIME;
    }

    @Override
    public boolean test (LootContext lootContext) {
        if (!lootContext.hasParameter(LootContextParameters.THIS_ENTITY)) return false;

        Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
        if (entity instanceof LivingEntity living) {
            return living.getItemUseTime() == this.useTime;
        }
        return false;
    }
}
