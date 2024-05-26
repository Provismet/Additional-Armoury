package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.registries.AAEnchantments;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
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
        int shredding = EnchantmentHelper.getLevel(AAEnchantments.SHREDDING, itemStack);
        int dismantle = EnchantmentHelper.getLevel(AAEnchantments.DISMANTLE, itemStack);
        target.addStatusEffect(new StatusEffectInstance(AAStatusEffects.SHATTERED, 40 + shredding * 20), user);

        if (user.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(AAParticleTypes.SHATTER, target.getX(), target.getHeight() + target.getY() + 0.5f, target.getZ(), 1, 0, 0, 0, 0);

            if (dismantle > 0) {
                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    if (slot.isArmorSlot() && !target.getEquippedStack(slot).isEmpty()) {
                        target.getEquippedStack(slot).damage(2 * dismantle, target, slot);
                    }
                }
            }
        }
    }
}
