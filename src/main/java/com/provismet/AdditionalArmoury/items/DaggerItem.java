package com.provismet.AdditionalArmoury.items;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.provismet.CombatPlusCore.interfaces.DualWeapon;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.item.Vanishable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DaggerItem extends ToolItem implements DualWeapon, Vanishable {
    public static final String CURRENT_POTION_USE = "DaggerPotionDurability";
    public static final int MAX_POTION_USES = 64;

    private static final float POTION_DURATION_MOD = 0.125f;

    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public final int defaultTipColour;

    public DaggerItem (ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, int defaultTipColour) {
        super(material, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        this.defaultTipColour = defaultTipColour;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public DaggerItem (ToolMaterial material, Settings settings, int defaultTipColour) {
        this(material, 1, -2f, settings, defaultTipColour);
    }

    @Override
    public float getWeaponDamage () {
        return attackDamage;
    }

    @Override
    public void postChargedHit (ItemStack stack, LivingEntity user, LivingEntity target) {
        Potion potion = PotionUtil.getPotion(stack);
        for (StatusEffectInstance instance : potion.getEffects()) {
            target.addStatusEffect(
                new StatusEffectInstance(
                    instance.getEffectType(), Math.max(instance.mapDuration(i -> (int)(i * POTION_DURATION_MOD)), 1),
                    instance.getAmplifier(),
                    instance.isAmbient(),
                    instance.shouldShowParticles()
                ),
                user
            );
        }
        if (potion != Potions.EMPTY && this.decrementCurrentPotionUses(stack) <= 0) {
            PotionUtil.setPotion(stack, Potions.EMPTY);
            stack.removeSubNbt(PotionUtil.CUSTOM_POTION_EFFECTS_KEY);
        }
    }

    @Override
    public boolean canMine (BlockState state, World world, BlockPos pos, PlayerEntity user) {
        return !user.isCreative();
    }

    @Override
    public float getMiningSpeedMultiplier (ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0f;
        }
        return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.5f : 1.0f;
    }

    @Override
    public boolean postHit (ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean postMine (ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0f) {
            stack.damage(2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COBWEB);
    }

    @Override
    public ItemStack getDefaultStack () {
        return PotionUtil.setPotion(super.getDefaultStack(), Potions.EMPTY);
    }

    @Override
    public void appendTooltip (ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(stack, tooltip, POTION_DURATION_MOD);

        if (PotionUtil.getPotion(stack) != Potions.EMPTY)
            tooltip.add(Text.translatable("tooltip.additional-armoury.dagger_uses", this.getCurrentPotionUses(stack)));
    }

    @Override
    public String getTranslationKey (ItemStack stack) {
        Potion potion = PotionUtil.getPotion(stack);

        if (potion == null || potion == Potions.EMPTY) {
            return super.getTranslationKey();
        }
        return PotionUtil.getPotion(stack).finishTranslationKey(this.getTranslationKey() + ".effect.");
    }
    
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers (EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }

    public int getCurrentPotionUses (ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(CURRENT_POTION_USE)) return MAX_POTION_USES;
        else return nbt.getInt(CURRENT_POTION_USE);
    }

    public void setCurrentPotionUses (ItemStack stack, int uses) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(CURRENT_POTION_USE, uses);
    }

    public int decrementCurrentPotionUses (ItemStack stack) {
        int currentUses = this.getCurrentPotionUses(stack) - 1;
        this.setCurrentPotionUses(stack, currentUses);
        return currentUses;
    }
}
