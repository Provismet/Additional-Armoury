package com.provismet.AdditionalArmoury.items;

import com.provismet.AdditionalArmoury.registries.AAEnchantmentComponentTypes;
import com.provismet.AdditionalArmoury.registries.AAParticleTypes;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.AdditionalArmoury.utility.Util;
import com.provismet.CombatPlusCore.items.AbstractMeleeWeapon;
import com.provismet.CombatPlusCore.items.component.MeleeWeaponComponent;
import com.provismet.CombatPlusCore.registries.CPCDataComponentTypes;
import com.provismet.CombatPlusCore.utility.CPCEnchantmentHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;

public class MaceItem extends AbstractMeleeWeapon {
    private static final float BASE_ATTACK_DAMAGE = 6f;
    private static final float BASE_ATTACK_SPEED = -3.5f;

    public MaceItem (Properties settings) {
        super(settings);
    }

    public static ItemAttributeModifiers createDefaultMaceAttributes (ToolMaterial material) {
        return Util.createAttributes(material, BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static ItemAttributeModifiers createDefaultMaceAttributes (AAToolMaterial material) {
        return material.createAttributeComponent(BASE_ATTACK_DAMAGE, BASE_ATTACK_SPEED);
    }

    public static Item.Properties createDefaultMaceSettings (ToolMaterial material, Item.Properties settings) {
        return Util.applyToolSettings(material, settings)
            .attributes(createDefaultMaceAttributes(material))
            .component(DataComponents.WEAPON, new Weapon(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createMelee(material.attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    public static Item.Properties createDefaultMaceSettings (AAToolMaterial material, Item.Properties settings) {
        return Util.applyToolSettings(material.baseMaterial(), settings)
            .attributes(createDefaultMaceAttributes(material))
            .component(DataComponents.WEAPON, new Weapon(1))
            .component(CPCDataComponentTypes.MELEE_WEAPON, MeleeWeaponComponent.createMelee(material.baseMaterial().attackDamageBonus() + BASE_ATTACK_DAMAGE));
    }

    @Override
    public void postCriticalHit (ItemStack itemStack, LivingEntity user, LivingEntity target) {
        if (user.level() instanceof ServerLevel serverWorld) {
            float shreddingDuration = CPCEnchantmentHelper.modifyValue(AAEnchantmentComponentTypes.EFFECT_DURATION, serverWorld, itemStack, 40f);
            target.addEffect(new MobEffectInstance(AAStatusEffects.SHATTERED, (int)shreddingDuration), user);
            serverWorld.sendParticles(AAParticleTypes.SHATTER, target.getX(), target.getBbHeight() + target.getY() + 0.5f, target.getZ(), 1, 0, 0, 0, 0);
        }
    }
}
