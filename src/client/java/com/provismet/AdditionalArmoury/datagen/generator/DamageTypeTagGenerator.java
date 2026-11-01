package com.provismet.AdditionalArmoury.datagen.generator;

import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.AdditionalArmoury.utility.tags.AADamageTypeTags;
import com.provismet.CombatPlusCore.api.tag.CombatPlusDamageTypeTags;import com.provismet.lilylib.datagen.tag.LilyTagProviders;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.DamageTypeTags;
import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends LilyTagProviders.LilyDamageTypeTagProvider {
    public DamageTypeTagGenerator (FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags (HolderLookup.Provider wrapperLookup) {
        this.builder(AADamageTypeTags.STAFF_SPELLS)
            .add(AADamageTypes.FIREBALL.getKey())
            .add(AADamageTypes.FROSTBALL.getKey())
            .add(AADamageTypes.ERUPTION.getKey())
            .add(AADamageTypes.GHOSTLY_ORB.getKey())
            .add(AADamageTypes.MAGIC_MISSILE.getKey())
            .add(AADamageTypes.WIND_TORNADO.getKey());

        this.builder(CombatPlusDamageTypeTags.IS_MAGIC)
            .addOptionalTag(AADamageTypeTags.STAFF_SPELLS);

        this.builder(DamageTypeTags.IS_PROJECTILE)
            .addOptionalTag(AADamageTypeTags.STAFF_SPELLS)
            .add(AADamageTypes.BOOMERANG.getKey());

        this.builder(DamageTypeTags.IS_FIRE)
            .add(AADamageTypes.FIREBALL.getKey());

        this.builder(DamageTypeTags.IS_FREEZING)
            .add(AADamageTypes.FROSTBALL.getKey());
    }
}
