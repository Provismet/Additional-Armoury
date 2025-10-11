package com.provismet.datagen.AdditionalArmoury;

import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.AdditionalArmoury.utility.tags.AADamageTypeTags;
import com.provismet.lilylib.datagen.tag.LilyTagProviders;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends LilyTagProviders.LilyDamageTypeTagProvider {
    public DamageTypeTagGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup) {
        this.builder(AADamageTypeTags.STAFF_SPELLS)
            .add(AADamageTypes.FIREBALL.getKey())
            .add(AADamageTypes.FROSTBALL.getKey())
            .add(AADamageTypes.ERUPTION.getKey())
            .add(AADamageTypes.GHOSTLY_ORB.getKey())
            .add(AADamageTypes.MAGIC_MISSILE.getKey())
            .add(AADamageTypes.WIND_TORNADO.getKey());

        this.builder(DamageTypeTags.IS_PROJECTILE)
            .addOptionalTag(AADamageTypeTags.STAFF_SPELLS)
            .add(AADamageTypes.BOOMERANG.getKey());

        this.builder(DamageTypeTags.IS_FIRE)
            .add(AADamageTypes.FIREBALL.getKey());

        this.builder(DamageTypeTags.IS_FREEZING)
            .add(AADamageTypes.FROSTBALL.getKey());
    }
}
