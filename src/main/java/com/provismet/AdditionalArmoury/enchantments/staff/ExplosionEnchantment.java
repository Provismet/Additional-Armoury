package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;
import com.provismet.AdditionalArmoury.particles.effects.SpellRingParticleEffect;

import net.minecraft.advancement.Advancement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ExplosionEnchantment extends AbstractStaffEnchantment {
    public ExplosionEnchantment () {
        super(Rarity.VERY_RARE, 0xCE0000, 16, 160);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            Explosion explosion = serverWorld.createExplosion(user, user.getX(), user.getY(), user.getZ(), 10f, true, World.ExplosionSourceType.TNT);
            user.damage(user.getDamageSources().explosion(explosion), 141f); // Yes that is actually how much damage this would deal.

            if (user instanceof ServerPlayerEntity serverPlayer) {
                try { // I don't expect this to break, and it never has in testing. But I have no idea how advancements work.
                    Advancement archWizard = MinecraftClient.getInstance().getServer().getAdvancementLoader().get(AdditionalArmouryMain.identifier("story/explosion_magic"));
                    for (String criterion : serverPlayer.getAdvancementTracker().getProgress(archWizard).getUnobtainedCriteria()) {
                        serverPlayer.getAdvancementTracker().grantCriterion(archWizard, criterion);
                    }
                }
                catch (Exception e) {
                    AdditionalArmouryMain.LOGGER.error("Failed to grant Arch-Wizard advancement to Player: " + serverPlayer.getName().getString());
                }
            }
        }
        return true;
    }

    @Override
    public void onChargeTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.onChargeTick(world, user, stack, remainingUseTicks);
        
        if (remainingUseTicks == 120) world.addParticle(new SpellRingParticleEffect(2f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 0, 0, 0);
        else if (remainingUseTicks == 80) world.addParticle(new SpellRingParticleEffect(4f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 0, 0, 0);
        else if (remainingUseTicks == 40) world.addParticle(new SpellRingParticleEffect(7f, remainingUseTicks), user.getX(), user.getY() + 0.1, user.getZ(), 0, 0, 0);
    }

    @Override
    public int getColour (Random random) {
        if (random.nextBoolean()) return super.getColour(random);
        else return random.nextBetween(0, 0xFFFFFF);
    }
    
    @Override
    public boolean isTreasure () {
        return true;
    }
}
