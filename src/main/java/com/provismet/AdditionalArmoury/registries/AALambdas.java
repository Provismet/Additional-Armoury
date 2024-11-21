package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.enchantment.incantation.LambdaIncantationEffect;
import com.provismet.AdditionalArmoury.entity.FireballSpellEntity;
import com.provismet.AdditionalArmoury.entity.FrostballSpellEntity;
import com.provismet.AdditionalArmoury.entity.GhostlySpellEntity;
import com.provismet.AdditionalArmoury.entity.MissileSpellEntity;
import com.provismet.AdditionalArmoury.entity.WindTornadoSpellEntity;
import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;
import com.provismet.AdditionalArmoury.particles.effects.SpellRingParticleEffect;
import com.provismet.AdditionalArmoury.utility.AADamageTypes;
import com.provismet.AdditionalArmoury.utility.registry.AARegistries;
import com.provismet.CombatPlusCore.enchantment.effect.singleEntity.CodeExecutionSingleEntityEffect;
import com.provismet.CombatPlusCore.utility.CPCRegistries;
import com.provismet.lilylib.util.Relations;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class AALambdas {
    public static void register () {
        register("boost", (world, level, context, user, pos) -> {
            AdditionalArmouryMain.LOGGER.info("Boost activated.");
            double dx = -MathHelper.sin(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
            double dz = MathHelper.cos(user.getHeadYaw() / MathHelper.DEGREES_PER_RADIAN);
            Vec3d velocity = new Vec3d(dx, 0.0, dz).multiply(1.25).add(0.0, 0.2, 0.0);

            if (user.isOnGround()) user.move(MovementType.SELF, new Vec3d(0.0, 0.2, 0.0));
            user.addVelocity(velocity);
            user.velocityModified = true;
        });

        register("jump", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40));
            user.fallDistance = 0f;

            Vec3d velocity = user.getVelocity();
            user.setVelocity(velocity.x, 0.75, velocity.z);
            user.velocityModified = true;
        });

        register("fireball", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                FireballSpellEntity fireballSpell = new FireballSpellEntity(world, living);
                world.spawnEntity(fireballSpell);
            }
        });

        register("frostball", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                FrostballSpellEntity frostball = new FrostballSpellEntity(world, living);
                world.spawnEntity(frostball);
            }
        });

        register("eruption", (world, level, context, user, pos) -> {
            final int RADIUS = 5;
            if (user.isOnGround() && user instanceof LivingEntity livingUser) {
                List<Entity> others = user.getWorld().getOtherEntities(user, user.getBoundingBox().expand(RADIUS, 0, RADIUS));
                for (Entity otherEntity : others) {
                    if (otherEntity instanceof LivingEntity living && !Relations.isFriendly(living, livingUser)) {
                        otherEntity.damage(world, AADamageTypes.ERUPTION.createDamageSource(user), 1f);
                        double dx = user.getX() - living.getX();
                        double dz = user.getZ() - living.getZ();

                        living.takeKnockback(1, dx, dz);
                        living.addVelocity(0, 0.5, 0);
                    }
                }

                if (user.getWorld() instanceof ServerWorld serverWorld) {
                    BlockPos position = user.getBlockPos();

                    for (int x = -RADIUS; x <= RADIUS; ++x) {
                        for (int z = -RADIUS; z <= RADIUS; ++z) {
                            BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                            BlockState lowerOffset = user.getWorld().getBlockState(newPosition.offset(Direction.DOWN));
                            if (lowerOffset.isSolidBlock(user.getWorld(), position)) {
                                if (user.getRandom().nextFloat() > 0.6) serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.getDefaultState()), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                                else serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.COBBLESTONE.getDefaultState()), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                            }
                        }
                    }
                }
            }
        });

        register("gale", (world, level, context, user, pos) -> {
           if (user instanceof LivingEntity living) {
               WindTornadoSpellEntity windSpell = new WindTornadoSpellEntity(world, living);
               world.spawnEntity(windSpell);

               WindTornadoSpellEntity windSpell2 = new WindTornadoSpellEntity(world, living, 5f);
               world.spawnEntity(windSpell2);

               WindTornadoSpellEntity windSpell3 = new WindTornadoSpellEntity(world, living, -5f);
               world.spawnEntity(windSpell3);
           }
        });

        register("ghostly_orb", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                GhostlySpellEntity ghostSpell = new GhostlySpellEntity(world, living);
                world.spawnEntity(ghostSpell);
            }
        });

        register("missile", (world, level, context, user, pos) -> {
            if (user instanceof LivingEntity living) {
                MissileSpellEntity missile = new MissileSpellEntity(world, living);
                world.spawnEntity(missile);
            }
        });

        register("explosion", (world, level, context, user, pos) -> {
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 10f, true, World.ExplosionSourceType.TNT);
            user.damage(world, user.getDamageSources().explosion(user, user), 141f); // Yes that is actually how much damage this would deal.

            if (user instanceof ServerPlayerEntity serverPlayer) {
                try { // I don't expect this to break, and it never has in testing. But I have no idea how advancements work.
                    AdvancementEntry archWizard = MinecraftClient.getInstance().getServer().getAdvancementLoader().get(AdditionalArmouryMain.identifier("story/explosion_magic"));
                    for (String criterion : serverPlayer.getAdvancementTracker().getProgress(archWizard).getUnobtainedCriteria()) {
                        serverPlayer.getAdvancementTracker().grantCriterion(archWizard, criterion);
                    }
                }
                catch (Exception e) {
                    AdditionalArmouryMain.LOGGER.error("Failed to grant Arch-Wizard advancement to Player: {}", serverPlayer.getName().getString());
                }
            }
        });

        registerIncantation("explosion_tick", (world, level, context, user, remainingUseTicks) -> {
            world.spawnParticles(new SpellChargeParticleEffect(Vec3d.unpackRgb(user.getRandom().nextBetween(0x000000, 0xFFFFFF)).toVector3f(), 0.1f), user.getX(), user.getY(), user.getZ(), 1, 0, 0, 0, 0);
            if (remainingUseTicks == 120) world.spawnParticles(new SpellRingParticleEffect(2f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
            else if (remainingUseTicks == 80) world.spawnParticles(new SpellRingParticleEffect(4f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
            else if (remainingUseTicks == 40) world.spawnParticles(new SpellRingParticleEffect(7f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 1, 0, 0, 0, 0);
        });

        registerIncantation("eruption_tick", (world, level, context, user, remainingUseTicks) -> {
            final int RADIUS = 5;
            if (remainingUseTicks % 10 == 0 && user.isOnGround()) {
                if (world instanceof ServerWorld serverWorld) {
                    BlockPos position = user.getBlockPos();

                    for (int x = -RADIUS; x <= RADIUS; ++x) {
                        for (int z = -RADIUS; z <= RADIUS; ++z) {
                            if (user.getRandom().nextFloat() > 0.66) continue;

                            BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                            BlockState lowerOffset = world.getBlockState(newPosition.offset(Direction.DOWN));
                            if (lowerOffset.isSolidBlock(world, position)) {
                                serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, lowerOffset), x + user.getX(), user.getY() + 0.1, z + user.getZ(), 20, 0, 0, 0, 0.15f);
                            }
                        }
                    }
                }
            }
        });
    }

    private static void register (String name, CodeExecutionSingleEntityEffect.Lambda lambda) {
        Registry.register(CPCRegistries.SINGLE_ENTITY_LAMBDA, AdditionalArmouryMain.identifier(name), lambda);
    }

    private static void registerIncantation (String name, LambdaIncantationEffect.Lambda lambda) {
        Registry.register(AARegistries.INCANTATION_LAMBDA, AdditionalArmouryMain.identifier(name), lambda);
    }
}
