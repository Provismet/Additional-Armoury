package com.provismet.AdditionalArmoury.renderers;

import com.provismet.AdditionalArmoury.entity.interfaces.WorldItemEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class WorldItemRenderer<T extends Entity> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;

    public WorldItemRenderer (Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render (T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack itemStack;
        if (entity instanceof WorldItemEntity itemEntity) itemStack = itemEntity.getStack();
        else return;

        matrices.push();
        BakedModel model = this.itemRenderer.getModel(itemStack, entity.getWorld(), null, entity.getId());
        float rx = itemEntity.getXRotation(tickDelta);
        float ry = itemEntity.getYRotation(tickDelta);
        float rz = itemEntity.getZRotation(tickDelta);
        
        float dx = itemEntity.getXOffset(tickDelta);
        float dy = itemEntity.getYOffset(tickDelta);
        float dz = itemEntity.getZOffset(tickDelta);

        matrices.translate(dx, dy, dz);
        if (rx != 0) matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rx));
        if (ry != 0) matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(ry));
        if (rz != 0) matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rz));
        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.GROUND, false, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV, model);
        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    @SuppressWarnings("deprecation")
    public Identifier getTexture (T entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}
