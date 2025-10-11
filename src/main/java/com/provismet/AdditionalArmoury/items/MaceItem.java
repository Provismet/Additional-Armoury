package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;
import com.provismet.CombatPlusCore.items.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CPCDataComponentTypes;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;

public class MaceItem extends AbstractMeleeWeapon {
    private static final float BASE_ATTACK_DAMAGE = 6f;
    private static final float BASE_ATTACK_SPEED = -3.5f;

    public MaceItem (Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createDefaultMaceAttributes (ToolMaterial material) {
        return Util.createAttributes(material, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static AttributeModifiersComponent createDefaultMaceAttributes (AAToolMaterial material) {
        return material.createAttributeComponent(BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static Item.Settings createDefaultMaceSettings (ToolMaterial material, Item.Settings settings) {
        return Util.applyToolSettings(material, settings)
            .attributeModifiers(createDefaultMaceAttributes(material))
            .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createMelee(material.attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    public static Item.Settings createDefaultMaceSettings (AAToolMaterial material, Item.Settings settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .attributeModifiers(createDefaultMaceAttributes(material))
            .component(DataComponentTypes.WEAPON, new WeaponComponent(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createMelee(material.baseMaterial().attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    @Override
    public void postCriticalHit (ItemStack itemStack, LivingEntity user, LivingEntity target) {
        if (user.getEntityWorld() instanceof ServerWorld serverWorld) {
            float shreddingDuration = CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.EFFECT_DURATION, serverWorld, itemStack, 40f);
            target.addStatusEffect(new StatusEffectInstance(AAStatusEffects.SHATTERED, (int)shreddingDuration), user);
            serverWorld.spawnParticles(AAParticleTypes.SHATTER, target.getX(), target.getHeight() + target.getY() + 0.5f, target.getZ(), 1, 0, 0, 0, 0);
        }
    }
}
