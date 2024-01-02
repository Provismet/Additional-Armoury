package com.provismet.AdditionalArmoury;

import com.provismet.AdditionalArmoury.registries.AAEntityRenderers;
import com.provismet.AdditionalArmoury.registries.AAParticleFactories;
import com.provismet.AdditionalArmoury.registries.ColourRegistry;

import net.fabricmc.api.ClientModInitializer;

public class AdditionalArmouryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient () {
        ColourRegistry.register();
        AAParticleFactories.register();
        AAEntityRenderers.register();
    }
}
