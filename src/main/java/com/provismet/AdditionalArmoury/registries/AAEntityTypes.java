package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.entity.AbstractSpellEntity;
import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;
import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;
import com.provismet.AdditionalArmoury.entity.GhostlySpellEntity;
import com.provismet.AdditionalArmoury.entity.MissileSpellEntity;
import com.provismet.AdditionalArmoury.entity.WindTornadoSpellEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AAEntityTypes {
    public static final EntityType<FireballSpellEntity> FIREBALL = AAEntityTypes.buildSpell(FireballSpellEntity::new, "fireball_spell");
    public static final EntityType<FrostballSpellEntity> FROSTBALL = AAEntityTypes.buildSpell(FrostballSpellEntity::new, "frostball_spell");
    public static final EntityType<GhostlySpellEntity> GHOSTLY_ORB = AAEntityTypes.buildSpell(GhostlySpellEntity::new, "ghostly_orb_spell");
    public static final EntityType<WindTornadoSpellEntity> WIND_TORNADO = AAEntityTypes.buildSpell(WindTornadoSpellEntity::new, "wind_tornado_spell", 1.5f, 1.5f);
    public static final EntityType<MissileSpellEntity> MAGIC_MISSILE = AAEntityTypes.buildSpell(MissileSpellEntity::new, "missile_spell");
    public static final EntityType<BoomerangProjectileEntity> BOOMERANG = AAEntityTypes.buildEntity(BoomerangProjectileEntity::new, "boomerang_projectile", 0.85f, 0.25f);

    private static <T extends AbstractSpellEntity> EntityType<T> buildSpell (EntityType.EntityFactory<T> factory, String name) {
        return AAEntityTypes.buildSpell(factory, name, 0.25f, 0.25f);
    }

    private static <T extends AbstractSpellEntity> EntityType<T> buildSpell (EntityType.EntityFactory<T> factory, String name, float width, float height) {
        return Registry.register(
            Registries.ENTITY_TYPE,
            AdditionalArmouryMain.identifier(name),
            EntityType.Builder.create(factory, SpawnGroup.MISC)
                .dimensions(width, height)
                .maxTrackingRange(30)
                .trackingTickInterval(10)
                .build()
        );
    }

    private static <T extends Entity> EntityType<T> buildEntity (EntityType.EntityFactory<T> factory, String name, float width, float height) {
        return Registry.register(
            Registries.ENTITY_TYPE,
            AdditionalArmouryMain.identifier(name),
            EntityType.Builder.create(factory, SpawnGroup.MISC)
                .dimensions(width, height)
                .build()
        );
    }

    // Exists purely to force the variables to load.
    public static void register () {

    }
}
