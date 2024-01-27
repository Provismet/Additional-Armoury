package com.provismet.AdditionalArmoury.registries;

import com.provismet.AdditionalArmoury.renderers.WorldItemRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class AAEntityRenderers {
    public static void register () {
        EntityRendererRegistry.register(AAEntityTypes.FIREBALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.FROSTBALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.GHOSTLY_ORB, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.WIND_TORNADO, context -> new FlyingItemEntityRenderer<>(context, 2f, false));
        EntityRendererRegistry.register(AAEntityTypes.MAGIC_MISSILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.BOOMERANG, WorldItemRenderer::new);
    }
}
