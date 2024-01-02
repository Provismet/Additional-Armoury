package com.provismet.AdditionalArmoury.items;

import java.util.function.Predicate;

import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.Vanishable;

public class CrossbowRepeaterItem extends RangedWeaponItem implements Vanishable {
    public CrossbowRepeaterItem (Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getProjectiles () {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProjectiles'");
    }

    @Override
    public int getRange () {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRange'");
    }
}
