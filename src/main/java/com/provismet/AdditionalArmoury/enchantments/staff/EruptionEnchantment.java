package com.provismet.AdditionalArmoury.enchantments.staff;

import java.util.List;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class EruptionEnchantment extends StaffEnchantment {
    public EruptionEnchantment () {
        super(Rarity.UNCOMMON, 0x8E6345, 64, 40);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        List<Entity> others = user.getWorld().getOtherEntities(user, user.getBoundingBox().expand(5, 0, 5));
        for (Entity otherEntity : others) {
            if (otherEntity instanceof LivingEntity) otherEntity.addVelocity(0, 1, 0);
            AdditionalArmouryMain.LOGGER.info("Found other entity.");
        }
        return true;
    }
}
