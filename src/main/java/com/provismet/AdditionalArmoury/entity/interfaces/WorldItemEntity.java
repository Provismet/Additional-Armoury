package com.provismet.AdditionalArmoury.entity.interfaces;

import net.minecraft.entity.FlyingItemEntity;

public interface WorldItemEntity extends FlyingItemEntity {
    public float getXRotation (float tickDelta);
    public float getYRotation (float tickDelta);
    public float getZRotation (float tickDelta);

    public float getXOffset (float tickDelta);
    public float getYOffset (float tickDelta);
    public float getZOffset (float tickDelta);
}
