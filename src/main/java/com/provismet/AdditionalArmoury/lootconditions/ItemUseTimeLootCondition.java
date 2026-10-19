package com.provismet.AdditionalArmoury.lootconditions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.AdditionalArmoury.registries.AALootConditionTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record ItemUseTimeLootCondition (int useTime) implements LootItemCondition {
    public static final MapCodec<ItemUseTimeLootCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(ExtraCodecs.NON_NEGATIVE_INT.fieldOf("use_time").forGetter(ItemUseTimeLootCondition::useTime))
            .apply(instance, ItemUseTimeLootCondition::new)
    );

    @Override
    public LootItemConditionType getType () {
        return AALootConditionTypes.ITEM_USE_TIME;
    }

    @Override
    public boolean test (LootContext lootContext) {
        if (!lootContext.hasParameter(LootContextParams.THIS_ENTITY)) return false;

        Entity entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);
        if (entity instanceof LivingEntity living) {
            return living.getTicksUsingItem() == this.useTime;
        }
        return false;
    }
}
