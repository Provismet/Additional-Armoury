package com.provismet.additionalarmoury.registries;

import com.provismet.lilylib.renderers.WorldItemEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class AAEntityRenderers {
    public static void register () {
        EntityRenderers.register(AAEntityTypes.FIREBALL, ThrownItemRenderer::new);
        EntityRenderers.register(AAEntityTypes.FROSTBALL, ThrownItemRenderer::new);
        EntityRenderers.register(AAEntityTypes.GHOSTLY_ORB, ThrownItemRenderer::new);
        EntityRenderers.register(AAEntityTypes.WIND_TORNADO, context -> new ThrownItemRenderer<>(context, 2f, false));
        EntityRenderers.register(AAEntityTypes.MAGIC_MISSILE, ThrownItemRenderer::new);
        EntityRenderers.register(AAEntityTypes.BOOMERANG, WorldItemEntityRenderer::new);
    }
}
