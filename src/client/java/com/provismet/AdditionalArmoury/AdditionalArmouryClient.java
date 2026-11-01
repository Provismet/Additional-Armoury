package com.provismet.AdditionalArmoury;

import com.provismet.AdditionalArmoury.api.AdditionalArmouryEntrypointClient;
import com.provismet.AdditionalArmoury.registries.AAEntityRenderers;
import com.provismet.AdditionalArmoury.registries.AAParticleFactories;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AdditionalArmouryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient () {
        AAParticleFactories.register();
        AAEntityRenderers.register();

        FabricLoader.getInstance().getEntrypointContainers(AdditionalArmouryMain.MODID, AdditionalArmouryEntrypointClient.class).forEach(
            entrypoint -> {
                String otherModId = entrypoint.getProvider().getMetadata().getId();
                try {
                    entrypoint.getEntrypoint().onInitializeClient();
                }
                catch (Exception e) {
                    AdditionalArmouryMain.LOGGER.error("Mod " + otherModId + " caused an error during inter-mod initialisation: ", e);
                }
            }
        );
    }
}
