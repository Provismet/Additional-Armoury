package com.provismet.AdditionalArmoury.items;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.provismet.AdditionalArmoury.registries.AAItems;

import net.minecraft.block.Block;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public enum AAToolMaterials implements ToolMaterial {
    OVERNETHER(2031, 9.0f, 3.0f, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1f, () -> Ingredient.ofItems(AAItems.OVERNETHER_INGOT)),
    ENDERNETHER(2031, 9.0f, 3.0f, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, EntityAttributes.GENERIC_ATTACK_SPEED, 0.2f, () -> Ingredient.ofItems(AAItems.ENDERNETHER_INGOT));

    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> inverseTag;
    private final Supplier<Ingredient> repairIngredient;
    private final RegistryEntry<EntityAttribute> customAttribute;
    private final float customAttributeValue;

    private AAToolMaterials (int itemDurability, float miningSpeed, float attackDamage, int enchantability, TagKey<Block> inverseTag, RegistryEntry<EntityAttribute> customAttribute, float customAttributeValue, Supplier<Ingredient> repairIngredient) {
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.customAttribute = customAttribute;
        this.inverseTag = inverseTag;
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
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability () {
        return this.enchantability;
    }

    public RegistryEntry<EntityAttribute> getCustomAttribute () {
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
