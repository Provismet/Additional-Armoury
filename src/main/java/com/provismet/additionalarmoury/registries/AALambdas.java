package com.provismet.additionalarmoury.registries;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import com.provismet.additionalarmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.additionalarmoury.entity.FireballSpellEntity;
import com.provismet.additionalarmoury.entity.FrostballSpellEntity;
import com.provismet.additionalarmoury.entity.GhostlySpellEntity;
import com.provismet.additionalarmoury.entity.MissileSpellEntity;
import com.provismet.additionalarmoury.entity.WindTornadoSpellEntity;
import com.provismet.additionalarmoury.particle.effects.SpellChargeParticleEffect;
import com.provismet.additionalarmoury.particle.effects.SpellRingParticleEffect;
import com.provismet.additionalarmoury.utility.AADamageTypes;
import com.provismet.additionalarmoury.utility.registry.AARegistries;
import com.provismet.CombatPlusCore.api.enchantment.effect.singleEntity.CodeExecutionSingleEntityEffect;
import com.provismet.CombatPlusCore.utility.CombatPlusRegistries;
import com.provismet.lilylib.util.Relations;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AALambdas {
    public static void register () {
        register("boost", (world, level, context, user, pos) -> {
            double dx = -Mth.sin(user.getYHeadRot() / Mth.RAD_TO_DEG);
            double dz = Mth.cos(user.getYHeadRot() / Mth.RAD_TO_DEG);
            Vec3 velocity = new Vec3(dx, 0.0, dz).scale(1.25).add(0.0, 0.2, 0.0);

            if (user.onGround()) user.move(MoverType.SELF, new Vec3(0.0, 0.2, 0.0));
            user.push(velocity);
            user.hurtMarked = true;
        });

        register("jump", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) living.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40));
            user.fallDistance = 0f;

            Vec3 velocity = user.getDeltaMovement();
            user.setDeltaMovement(velocity.x, 0.75, velocity.z);
            user.hurtMarked = true;
        });

        register("fireball", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                FireballSpellEntity fireballSpell = new FireballSpellEntity(world, living);
                world.addFreshEntity(fireballSpell);
            }
        });

        register("frostball", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                FrostballSpellEntity frostball = new FrostballSpellEntity(world, living);
                world.addFreshEntity(frostball);
            }
        });

        register("eruption", (world, level, context, user, pos) -> {
            final int RADIUS = 5;
            if (user.onGround() && user instanceof LivingEntity livingUser) {
                List<Entity> others = user.level().getEntities(user, user.getBoundingBox().inflate(RADIUS, 0, RADIUS));
                for (Entity otherEntity : others) {
                    if (otherEntity instanceof LivingEntity living && !Relations.isFriendly(living, livingUser)) {
                        otherEntity.hurtServer(world, AADamageTypes.ERUPTION.createDamageSource(user), 1f);
                        double dx = user.getX() - living.getX();
                        double dz = user.getZ() - living.getZ();

                        living.knockback(1, dx, dz);
                        living.push(0, 0.5, 0);
                    }
                }

                if (user.level() instanceof ServerLevel serverWorld) {
                    BlockPos position = user.blockPosition();

                    for (int x = -RADIUS; x <= RADIUS; ++x) {
                        for (int z = -RADIUS; z <= RADIUS; ++z) {
                            BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                            BlockState lowerOffset = user.level().getBlockState(newPosition.relative(Direction.DOWN));
                            if (lowerOffset.isRedstoneConductor(user.level(), position)) {
                                if (user.getRandom().nextFloat() > 0.6) serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.defaultBlockState()), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                                else serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COBBLESTONE.defaultBlockState()), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                            }
                        }
                    }
                }
            }
        });

        register("gale", (world, level, context, user, pos) -> {
           if (user instanceof LivingEntity living) {
               WindTornadoSpellEntity windSpell = new WindTornadoSpellEntity(world, living);
               world.addFreshEntity(windSpell);

               WindTornadoSpellEntity windSpell2 = new WindTornadoSpellEntity(world, living, 5f);
               world.addFreshEntity(windSpell2);

               WindTornadoSpellEntity windSpell3 = new WindTornadoSpellEntity(world, living, -5f);
               world.addFreshEntity(windSpell3);
           }
        });

        register("ghostly_orb", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                GhostlySpellEntity ghostSpell = new GhostlySpellEntity(world, living);
                world.addFreshEntity(ghostSpell);
            }
        });

        register("missile", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                MissileSpellEntity missile = new MissileSpellEntity(world, living);
                world.addFreshEntity(missile);
            }
        });

        register("explosion", (world, level, context, user, pos) -> {
            world.explode(user, user.getX(), user.getY(), user.getZ(), 10f, true, Level.ExplosionInteraction.TNT);
            user.hurtServer(world, user.damageSources().explosion(user, user), 141f); // Yes that is actually how much damage this would deal.
        });

        registerIncantation("explosion_tick", (world, level, context, user, remainingUseTicks) -> {
            world.sendParticles(new SpellChargeParticleEffect(ARGB.vector3fFromRGB24(user.getRandom().nextIntBetweenInclusive(0x000000, 0xFFFFFF)), 0.1f), user.getX(), user.getY(), user.getZ(), 1, 0, 0, 0, 0);
            if (remainingUseTicks == 120) world.sendParticles(new SpellRingParticleEffect(2f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
            else if (remainingUseTicks == 80) world.sendParticles(new SpellRingParticleEffect(4f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
            else if (remainingUseTicks == 40) world.sendParticles(new SpellRingParticleEffect(7f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
        });

        registerIncantation("eruption_tick", (world, level, context, user, remainingUseTicks) -> {
            final int RADIUS = 5;
            if (remainingUseTicks % 10 == 0 && user.onGround()) {
                if (world instanceof ServerLevel serverWorld) {
                    BlockPos position = user.blockPosition();

                    for (int x = -RADIUS; x <= RADIUS; ++x) {
                        for (int z = -RADIUS; z <= RADIUS; ++z) {
                            if (user.getRandom().nextFloat() > 0.66) continue;

                            BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                            BlockState lowerOffset = world.getBlockState(newPosition.relative(Direction.DOWN));
                            if (lowerOffset.isRedstoneConductor(world, position)) {
                                serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, lowerOffset), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                            }
                        }
                    }
                }
            }
        });
    }

    private static void register (String name, CodeExecutionSingleEntityEffect.Lambda lambda) {
        Registry.register(CombatPlusRegistries.SINGLE_ENTITY_LAMBDA, AdditionalArmouryMain.identifier(name), lambda);
    }

    private static void registerIncantation (String name, LambdaIncantationEffect.Lambda lambda) {
        Registry.register(AARegistries.INCANTATION_LAMBDA, AdditionalArmouryMain.identifier(name), lambda);
    }
}
