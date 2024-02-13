package com.provismet.AdditionalArmoury.enchantments.staff;

import com.provismet.AdditionalArmoury.entity.WindTornadoSpellEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class GaleEnchantment extends AbstractStaffEnchantment {
    public GaleEnchantment () {
        super(Rarity.UNCOMMON, 0xFFFFFF, 32, 30);
    }

    @Override
    public boolean castSpell (ItemStack stack, LivingEntity user) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            WindTornadoSpellEntity windSpell = new WindTornadoSpellEntity(serverWorld, user);
            serverWorld.spawnEntity(windSpell);

            WindTornadoSpellEntity windSpell2 = new WindTornadoSpellEntity(serverWorld, user, 5f);
            serverWorld.spawnEntity(windSpell2);

            WindTornadoSpellEntity windSpell3 = new WindTornadoSpellEntity(serverWorld, user, -5f);
            serverWorld.spawnEntity(windSpell3);
        }
        return true;
    }
}
