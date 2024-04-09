package com.alpha67.AMCBase.entity.render;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import com.alpha67.AMCBase.AMCBase;

public class ModBoatRenderer extends BoatRenderer {
    private static final ResourceLocation BOAT_TEXTURE =
            new ResourceLocation(AMCBase.MOD_ID, "textures/entity/boat/redwood.png");

    public ModBoatRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getEntityTexture(BoatEntity entity) {
        return BOAT_TEXTURE;
    }
}
