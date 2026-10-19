package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.entity.AbstractSpellEntity;
import com.provismet.AdditionalArmoury.entity.BoomerangProjectileEntity;
import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;
import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;
import com.provismet.AdditionalArmoury.entity.GhostlySpellEntity;
import com.provismet.AdditionalArmoury.entity.MissileSpellEntity;
import com.provismet.AdditionalArmoury.entity.WindTornadoSpellEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

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
        Identifier id = AdditionalArmouryMain.identifier(name);
        return Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            id,
            EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(width, height)
                .clientTrackingRange(30)
                .updateInterval(10)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, id))
        );
    }

    private static <T extends Entity> EntityType<T> buildEntity (EntityType.EntityFactory<T> factory, String name, float width, float height) {
        Identifier id = AdditionalArmouryMain.identifier(name);
        return Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            id,
            EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(width, height)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, id))
        );
    }

    // Exists purely to force the variables to load.
    public static void register () {

    }
}
