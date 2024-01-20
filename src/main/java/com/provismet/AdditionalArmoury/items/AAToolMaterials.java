package com.provismet.AdditionalArmoury.items;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum AAToolMaterials implements ToolMaterial {
    OVERNETHER(MiningLevels.NETHERITE, 2031, 9.0f, 3.0f, 15, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1f, () -> Ingredient.ofItems(AAItems.OVERNETHER_INGOT)),
    ENDERNETHER(MiningLevels.NETHERITE, 2031, 9.0f, 3.0f, 15, EntityAttributes.GENERIC_ATTACK_SPEED, 0.2f, () -> Ingredient.ofItems(AAItems.ENDERNETHER_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;
    private final EntityAttribute customAttribute;
    private final float customAttributeValue;

    private AAToolMaterials (int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, EntityAttribute customAttribute, float customAttributeValue, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.customAttribute = customAttribute;
        this.customAttributeValue = customAttributeValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getDurability () {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier () {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage () {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel () {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability () {
        return this.enchantability;
    }

    public EntityAttribute getCustomAttribute () {
        return this.customAttribute;
    }

    public float getCustomAttributeValue () {
        return this.customAttributeValue;
    }

    @Override
    public Ingredient getRepairIngredient () {
        return this.repairIngredient.get();
    }
}
