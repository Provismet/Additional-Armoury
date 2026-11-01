package com.provismet.additionalarmoury;

import com.provismet.additionalarmoury.api.AdditionalArmouryEntrypointClient;
import com.provismet.additionalarmoury.registries.AAEntityRenderers;
import com.provismet.additionalarmoury.registries.AAParticleFactories;

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
