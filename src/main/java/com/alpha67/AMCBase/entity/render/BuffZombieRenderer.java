package com.alpha67.AMCBase.entity.render;

import com.alpha67.AMCBase.entity.custom.BuffZombieEntity;
import com.alpha67.AMCBase.entity.model.BuffZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import com.alpha67.AMCBase.AMCBase;

public class BuffZombieRenderer extends MobRenderer<BuffZombieEntity, BuffZombieModel<BuffZombieEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(AMCBase.MOD_ID, "textures/entity/buff_zombie.png");

    public BuffZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BuffZombieModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(BuffZombieEntity entity) {
        return TEXTURE;
    }
}
