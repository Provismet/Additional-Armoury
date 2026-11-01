package com.provismet.additionalarmoury.datagen.generator;

import com.provismet.additionalarmoury.utility.AADamageTypes;
import com.provismet.lilylib.datagen.provider.LilyDamageTypeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class DamageTypeGenerator extends LilyDamageTypeProvider {
    public DamageTypeGenerator (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (HolderLookup.Provider registries, DamageConsumer consumer) {
        consumer.add(AADamageTypes.FIREBALL);
        consumer.add(AADamageTypes.FROSTBALL);
        consumer.add(AADamageTypes.ERUPTION);
        consumer.add(AADamageTypes.WIND_TORNADO);
        consumer.add(AADamageTypes.GHOSTLY_ORB);
        consumer.add(AADamageTypes.MAGIC_MISSILE);
        consumer.add(AADamageTypes.BOOMERANG);
    }
}
