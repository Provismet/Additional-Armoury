package com.provismet.AdditionalArmoury.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.provismet.AdditionalArmoury.registries.AAStatusEffects;
import com.provismet.CombatPlusCore.interfaces.MeleeWeapon;
import com.provismet.CombatPlusCore.utility.AttributeIdentifiers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MaceItem extends ToolItem implements MeleeWeapon {
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public MaceItem (ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        
        if (material instanceof AAToolMaterials extraMat && extraMat.getCustomAttribute() == EntityAttributes.GENERIC_ATTACK_SPEED)
            attackSpeed += extraMat.getCustomAttributeValue();

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));

        if (material instanceof AAToolMaterials extraMat && extraMat.getCustomAttribute() != null && extraMat.getCustomAttribute() != EntityAttributes.GENERIC_ATTACK_SPEED) 
            builder.put(extraMat.getCustomAttribute(), new EntityAttributeModifier(AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE, "Additional Armoury: Weapon Modifier", extraMat.getCustomAttributeValue(), EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    public MaceItem (ToolMaterial material, Settings settings) {
        this(material, 6, -3.5f, settings);
    }

    @Override
    public boolean canMine (BlockState state, World world, BlockPos pos, PlayerEntity user) {
        return !user.isCreative();
    }

    @Override
    public float getWeaponDamage () {
        return this.attackDamage;
    }

    @Override
    public void postCriticalHit (ItemStack itemStack, LivingEntity user, LivingEntity target) {
        target.addStatusEffect(new StatusEffectInstance(AAStatusEffects.SHATTERED, 60), user);
    }
    
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers (EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
