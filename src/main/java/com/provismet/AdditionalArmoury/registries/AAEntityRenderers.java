package com.provismet.AdditionalArmoury.registries;

import com.provismet.lilylib.renderers.WorldItemEntityRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Environment(EnvType.CLIENT)
public class AAEntityRenderers {
    public static void register () {
        EntityRendererRegistry.register(AAEntityTypes.FIREBALL, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.FROSTBALL, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.GHOSTLY_ORB, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.WIND_TORNADO, context -> new ThrownItemRenderer<>(context, 2f, false));
        EntityRendererRegistry.register(AAEntityTypes.MAGIC_MISSILE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(AAEntityTypes.BOOMERANG, WorldItemEntityRenderer::new);
    }
}
