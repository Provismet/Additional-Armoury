package com.provismet.additionalarmoury.lootconditions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

public record ItemUseTimeLootCondition (int useTime) implements LootItemCondition {
    public static final MapCodec<ItemUseTimeLootCondition> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(ExtraCodecs.NON_NEGATIVE_INT.fieldOf("use_time").forGetter(ItemUseTimeLootCondition::useTime))
            .apply(instance, ItemUseTimeLootCondition::new)
    );

    @Override
    public boolean test (LootContext lootContext) {
        if (!lootContext.hasParameter(LootContextParams.THIS_ENTITY)) return false;

        Entity entity = lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);
        if (entity instanceof LivingEntity living) {
            return living.getTicksUsingItem() == this.useTime;
        }
        return false;
    }

    @Override
    @NotNull
    public MapCodec<? extends LootItemCondition> codec () {
        return CODEC;
    }
}
