package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.AdditionalArmouryMain;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class AASounds {
    public static final SoundEvent BOOMERANG_THROW = SoundEvent.of(AdditionalArmouryMain.identifier("player.boomerang.throw"));
    public static final SoundEvent STAFF_CAST = SoundEvent.of(AdditionalArmouryMain.identifier("player.staff.cast"));

    private static void register (SoundEvent soundEvent) {
        Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
    }

    public static void register () {
        register(BOOMERANG_THROW);
        register(STAFF_CAST);
    }
}
