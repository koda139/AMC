package com.alpha67.AMCBase.entity.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.entity.custom.PigeonEntity;
import com.alpha67.AMCBase.entity.model.PigeonModel;

public class PigeonRenderer extends MobRenderer<PigeonEntity, PigeonModel<PigeonEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(AMCBase.MOD_ID, "textures/entity/pigeon.png");

    public PigeonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PigeonModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(PigeonEntity entity) {
        return TEXTURE;
    }
}
