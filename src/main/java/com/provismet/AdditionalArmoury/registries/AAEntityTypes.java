package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.entity.AbstractSpellEntity;
import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;
import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAEntityTypes {
    public static final EntityType<FireballSpellEntity> FIREBALL = AAEntityTypes.buildSpell(FireballSpellEntity::new, "fireball_spell");
    public static final EntityType<FrostballSpellEntity> FROSTBALL = AAEntityTypes.buildSpell(FrostballSpellEntity::new, "frostball_spell");
    public static final EntityType<BoomerangProjectileEntity> BOOMERANG = AAEntityTypes.buildEntity(BoomerangProjectileEntity::new, "boomerang_projectile", EntityDimensions.fixed(0.25f, 0.25f));

    private static <T extends AbstractSpellEntity> EntityType<T> buildSpell (EntityType.EntityFactory<T> factory, String name) {
        return Registry.register(
            Registries.ENTITY_TYPE,
            AdditionalArmouryMain.identifier(name),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeBlocks(30)
                .trackedUpdateRate(10)
                .build()
        );
    }

    private static <T extends Entity> EntityType<T> buildEntity (EntityType.EntityFactory<T> factory, String name, EntityDimensions dimensions) {
        return Registry.register(
            Registries.ENTITY_TYPE,
            AdditionalArmouryMain.identifier(name),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .dimensions(dimensions)
                .build()
        );
    }

    // Exists purely to force the variables to load.
    public static void register () {

    }
}
