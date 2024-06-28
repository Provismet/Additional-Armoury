package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;

import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;

public class MaceItem extends AbstractMeleeWeapon {
    public MaceItem (ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public static AttributeModifiersComponent createDefaultMaceAttributes (ToolMaterial material) {
        return Util.createAttributes(material, 6f, -3.5f);
    }

    @Override
    public void postCriticalHit (ItemStack itemStack, LivingEntity user, LivingEntity target) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            float shreddingDuration = CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.EFFECT_DURATION, serverWorld, itemStack, 40f);
            target.addStatusEffect(new StatusEffectInstance(AAStatusEffects.SHATTERED, (int)shreddingDuration), user);
            serverWorld.spawnParticles(AAParticleTypes.SHATTER, target.getX(), target.getHeight() + target.getY() + 0.5f, target.getZ(), 1, 0, 0, 0, 0);
        }
    }
}
