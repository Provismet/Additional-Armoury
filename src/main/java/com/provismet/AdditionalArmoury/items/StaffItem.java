package com.provismet.AdditionalArmoury.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.provismet.AdditionalArmoury.enchantments.staff.StaffEnchantment;
import com.provismet.AdditionalArmoury.particles.effects.SpellChargeParticleEffect;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StaffItem extends Item implements Vanishable {
    public static final String SPELL_COUNT = "spell_count";
    public static final String MAX_SPELL_COUNT = "max_spell_count";

    public StaffItem (Settings settings) {
        super(settings);
    }

    public static int getColour (ItemStack stack) {
        StaffEnchantment enchantment = StaffItem.getFirstStaffEnchantment(stack);
        if (enchantment == null) return 0xC18920;
        else return enchantment.colour;
    }

    protected static List<StaffEnchantment> getStaffEnchantments (ItemStack stack) {
        Iterator<Enchantment> iter = EnchantmentHelper.get(stack).keySet().iterator();
        List<StaffEnchantment> out = new ArrayList<>();
        
        while (iter.hasNext()) {
            Enchantment enchantment = iter.next();
            if (enchantment instanceof StaffEnchantment staffEnchantment) out.add(staffEnchantment);
        }
        return out;
    }

    @Nullable
    protected static StaffEnchantment getFirstStaffEnchantment (ItemStack stack)  {
        Iterator<Enchantment> iter = EnchantmentHelper.get(stack).keySet().iterator();
        
        while (iter.hasNext()) {
            Enchantment enchantment = iter.next();
            if (enchantment instanceof StaffEnchantment staffEnchantment) return staffEnchantment;
        }
        return null;
    }

    @Override
    public String getTranslationKey (ItemStack stack) {
        StaffEnchantment enchantment = StaffItem.getFirstStaffEnchantment(stack);

        if (enchantment == null) return this.getTranslationKey();
        else return this.getTranslationKey() + ".enchanted";
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        
        if (StaffItem.getFirstStaffEnchantment(itemStack) == null) {
            return TypedActionResult.pass(itemStack);
        }
        else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public void usageTick (World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        StaffEnchantment enchantment = StaffItem.getFirstStaffEnchantment(stack);

        if (enchantment != null) {
            enchantment.onChargeTick(world, user, stack, remainingUseTicks);
            if (remainingUseTicks == this.getMaxUseTime(stack)) {

            }

            if (world.isClient()) world.addParticle(new SpellChargeParticleEffect(Vec3d.unpackRgb(enchantment.getColour(user.getRandom())).toVector3f(), 0.1f), user.getX(), user.getY(), user.getZ(), 0, 0, 0);
        }
    }

    @Override
    public UseAction getUseAction (ItemStack stack) {
        if (StaffItem.getFirstStaffEnchantment(stack) == null) return super.getUseAction(stack);
        else return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime (ItemStack stack) {
        StaffEnchantment enchantment = StaffItem.getFirstStaffEnchantment(stack);
        if (enchantment == null) return 0;
        else return enchantment.chargeTime;
    }

    @Override
    public boolean hasGlint (ItemStack stack) {
        return false;
    }

    @Override
    public int getEnchantability () {
        return 1;
    }

    @Override
    public boolean isEnchantable (ItemStack stack) {
        return true;
    }

    @Override
    public boolean isItemBarVisible (ItemStack stack) {
        return this.getMaxUseCount(stack) > 0;
    }

    @Override
    public int getItemBarStep (ItemStack stack) {
        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) return 0;
        else return 13 - (int)(13f * (uses / maxUses));
    }

    @Override
    public int getItemBarColor (ItemStack stack) {
        float progress;

        float uses = this.getUseCount(stack);
        float maxUses = this.getMaxUseCount(stack);

        if (uses == 0f || maxUses == 0f) progress = 0f;
        else progress = uses / maxUses;

        return ColorHelper.Argb.lerp(progress, 0xFF3251FF, 0xFFB2BDFF);
    }

    @Override
    public ItemStack finishUsing (ItemStack stack, World world, LivingEntity user) {
        List<StaffEnchantment> enchantments = StaffItem.getStaffEnchantments(stack);
        boolean wasUsed = false;

        if (enchantments.size() > 0) {
            for (StaffEnchantment enchant : enchantments) {
                if (enchant.castSpell(stack, user)) wasUsed = true;
            }

            if (wasUsed && user instanceof PlayerEntity player && !player.isCreative()) {
                this.setMaxUseCount(stack, enchantments.get(0).maxUses);
                this.incrementUseCount(stack);
            }
        }

        if (wasUsed && user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, 20);
        }
        return stack;
    }

    public void setMaxUseCount (ItemStack stack, int maxUses) {
        stack.getOrCreateNbt().putInt(MAX_SPELL_COUNT, maxUses);
    }

    public int getMaxUseCount (ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(MAX_SPELL_COUNT)) return 0;
        else return nbt.getInt(MAX_SPELL_COUNT);
    }

    public void incrementUseCount (ItemStack stack) {
        this.setUseCount(stack, this.getUseCount(stack) + 1);
    }

    public int getUseCount (ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(SPELL_COUNT)) return 0;
        else return nbt.getInt(SPELL_COUNT);
    }

    public void setUseCount (ItemStack stack, int uses) {
        if (uses >= this.getMaxUseCount(stack)) {
            uses = 0;
            this.setMaxUseCount(stack, 0);
            stack.removeSubNbt("Enchantments");
            stack.removeSubNbt("StoredEnchantments");
        }
        stack.getOrCreateNbt().putInt(SPELL_COUNT, uses);
    }

    public void setMaxUses (ItemStack stack, int maxUses) {
        stack.getOrCreateNbt().putInt(MAX_SPELL_COUNT, maxUses);
    }
}
