package com.provismet.AdditionalArmoury.registries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class AAEntityRenderers {
    public static void register () {
        EntityRendererRegistry.register(AAEntityTypes.FIREBALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.FROSTBALL, FlyingItemEntityRenderer::new);
    }
}
