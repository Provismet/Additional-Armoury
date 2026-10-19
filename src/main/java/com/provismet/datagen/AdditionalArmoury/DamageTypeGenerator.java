package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.lilylib.datagen.provider.LilyDamageTypeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class DamageTypeGenerator extends LilyDamageTypeProvider {
    protected DamageTypeGenerator (FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
