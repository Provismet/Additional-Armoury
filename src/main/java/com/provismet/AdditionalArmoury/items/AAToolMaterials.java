package com.provismet.AdditionalArmoury.items;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum AAToolMaterials implements ToolMaterial {
    OVERNETHER(MiningLevels.NETHERITE, 2031, 9.0f, 4.0f, 15, () -> Ingredient.ofItems(AAItems.OVERNETHER_INGOT)),
    ENDERNETHER(MiningLevels.NETHERITE, 2031, 9.0f, 4.0f, 15, () -> Ingredient.ofItems(AAItems.ENDERNETHER_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private AAToolMaterials (int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
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

    @Override
    public Ingredient getRepairIngredient () {
        return this.repairIngredient.get();
    }
}
