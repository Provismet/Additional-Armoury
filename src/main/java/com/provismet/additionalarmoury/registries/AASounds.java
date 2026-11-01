package com.provismet.additionalarmoury.registries;

import com.provismet.additionalarmoury.AdditionalArmouryMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class AASounds {
    public static final SoundEvent BOOMERANG_THROW = SoundEvent.createVariableRangeEvent(AdditionalArmouryMain.identifier("player.boomerang.throw"));
    public static final SoundEvent STAFF_CAST = SoundEvent.createVariableRangeEvent(AdditionalArmouryMain.identifier("player.staff.cast"));

    private static void register (SoundEvent soundEvent) {
        Registry.register(BuiltInRegistries.SOUND_EVENT, soundEvent.location(), soundEvent);
    }

    public static void register () {
        register(BOOMERANG_THROW);
        register(STAFF_CAST);
    }
}
