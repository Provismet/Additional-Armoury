package com.provismet.AdditionalArmoury.enchantments.staff;

import java.util.List;

import com.provismet.AdditionalArmoury.utility.AADamageSources;
import com.provismet.AdditionalArmoury.utility.Util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EruptionEnchantment extends StaffEnchantment {
    public EruptionEnchantment () {
        super(Rarity.UNCOMMON, 0x8E6345, 64, 40);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.isOnGround()) {
            List<Entity> others = user.getWorld().getOtherEntities(user, user.getBoundingBox().expand(5, 0, 5));
            for (Entity otherEntity : others) {
                if (otherEntity instanceof LivingEntity living && !Util.isFriendly(living, user)) {
                    otherEntity.damage(AADamageSources.eruption(user), 1f);
                    otherEntity.addVelocity(0, 1, 0);
                }
            }

            if (user.getWorld().isClient()) {
                BlockPos position = user.getBlockPos();
                
                for (int x = -2; x <= 2; ++x) {
                    for (int z = -2; z <= 2; ++z) {
                        BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                        BlockState lowerOffset = user.getWorld().getBlockState(newPosition.offset(Direction.DOWN));
                        if (lowerOffset.isSolidBlock(user.getWorld(), position)) {
                            if (user.getRandom().nextFloat() > 0.6) user.getWorld().addBlockBreakParticles(newPosition, Blocks.MAGMA_BLOCK.getDefaultState());
                            else user.getWorld().addBlockBreakParticles(newPosition, Blocks.COBBLESTONE.getDefaultState());
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
            if (!world.isClient()) return;
            BlockPos position = user.getBlockPos();
            
            for (int x = -2; x <= 2; ++x) {
                for (int z = -2; z <= 2; ++z) {
                    if (user.getRandom().nextFloat() > 0.4) continue;

                    BlockPos newPosition = new BlockPos(position.getX() + x, position.getY(), position.getZ() + z);
                    BlockState lowerOffset = world.getBlockState(newPosition.offset(Direction.DOWN));
                    if (lowerOffset.isSolidBlock(world, position)) {
                        world.addBlockBreakParticles(newPosition, lowerOffset);
                    }
                }
            }
        }
    }
}
