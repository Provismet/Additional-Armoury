package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.utility.AATags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public abstract class AbstractStaffEnchantment extends Enchantment {
    public final int colour;
    public final int maxUses;
    public final int chargeTime;

    protected AbstractStaffEnchantment (int weight, Cost minCost, Cost maxCost, int anvilCost, int colour, int maxUses, int chargeTime) {
        super(Enchantment.properties(
                AATags.ItemTags.STAFF_ENCHANTABLE,
                weight,
                1,
                minCost,
                maxCost,
                anvilCost,
                EquipmentSlot.MAINHAND
        ));
        this.colour = colour;
        this.maxUses = maxUses;
        this.chargeTime = chargeTime;
    }

    @Override
    protected boolean canAccept (Enchantment other) {
        return false;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer () {
        return false;
    }

    /**
     * Override this if you want the spell particles to be a different colour than the staff head.
     * @return The colour in 0xRRGGBB format.
     */
    public int getColour (Random random) {
        return this.colour;
    }

    public void onChargeTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {

    }

    public abstract boolean castSpell (ItemStack stack, LivingEntity user);
}
