package com.provismet.AdditionalArmoury.enchantments.staff;

import java.util.List;

import com.provismet.AdditionalArmoury.utility.AADamageSources;
import com.provismet.AdditionalArmoury.utility.Util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EruptionEnchantment extends StaffEnchantment {
    private static final int RADIUS = 5;

    public EruptionEnchantment () {
        super(Rarity.UNCOMMON, 0x7F3C18, 64, 30);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.isOnGround()) {
            List<Entity> others = user.getWorld().getOtherEntities(user, user.getBoundingBox().expand(RADIUS, 0, RADIUS));
            for (Entity otherEntity : others) {
                if (otherEntity instanceof LivingEntity living && !Util.isFriendly(living, user)) {
                    otherEntity.damage(AADamageSources.eruption(user), 1f);
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
            return true;
        }
        return false;
    }

    @Override
    public void onChargeTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
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
    }
}
