package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.items.StaffItem;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public abstract class StaffEnchantment extends Enchantment {
    public final int colour;
    public final int maxUses;
    public final int chargeTime;

    protected StaffEnchantment (Rarity weight, int colour, int maxUses, int chargeTime) {
        super(weight, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        this.colour = colour;
        this.maxUses = maxUses;
        this.chargeTime = chargeTime;
    }

    @Override
    protected boolean canAccept (Enchantment other) {
        return super.canAccept(other) &&
            !(other instanceof MendingEnchantment) &&
            !(other instanceof UnbreakingEnchantment) &&
            !(other instanceof StaffEnchantment);
    }
    
    @Override
    public boolean isAcceptableItem (ItemStack stack) {
        return stack.getItem() instanceof StaffItem;
    }

    public abstract void castSpell (ItemStack stack, LivingEntity user);
}
