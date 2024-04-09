/*
 * This file ("AssetUtil.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package com.alpha67.AMCBase.util;

import com.alpha67.AMCBase.AMCBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.netty.util.internal.StringUtil;
import mekanism.common.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public final class AssetUtil {

    public static final int MAX_LIGHT_X = 0xF000F0;
    public static final int MAX_LIGHT_Y = 0xF000F0;

    public static final ResourceLocation GUI_INVENTORY_LOCATION = getGuiLocation("gui_inventory");

    public static ResourceLocation getGuiLocation(String file) {
        return new ResourceLocation(AMCBase.MOD_ID, "textures/gui/" + file + ".png");
    }

    public static ResourceLocation getBookletGuiLocation(String file) {
        return getGuiLocation("booklet/" + file);
    }

    @OnlyIn(Dist.CLIENT)
    public static void displayNameString(MatrixStack matrices, FontRenderer font, int xSize, int yPositionOfMachineText, String text) {
        font.drawString(matrices, text, xSize / 2f - font.getStringWidth(text) / 2f, yPositionOfMachineText, 0xFFFFFF);
    }

   /* @OnlyIn(Dist.CLIENT)
    public static void displayNameString(MatrixStack matrices, FontRenderer font, int xSize, int yPositionOfMachineText, TileEntityBase tile) {
        displayNameString(matrices, font, xSize, yPositionOfMachineText, StringUtil.(tile.getNameForTranslation()));
    }*/

    //    public static void renderBlockInWorld(Block block, int meta) {
    //        renderItemInWorld(new ItemStack(block, 1, meta), combinedLightIn, combinedOverlayIn, matrices, buffer);
    //    }


    //    @OnlyIn(Dist.CLIENT)
    //    public static void renderStateInWorld(BlockState state, IWorldReader world, BlockPos pos, float brightness) {
    //        Minecraft.getInstance().getTextureManager().bindTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE);
    //        IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(state);
    //        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
    //        int i = Minecraft.getInstance().getBlockColors().colorMultiplier(state, world, pos, 0);
    //
    //        float r = (i >> 16 & 255) / 255F;
    //        float g = (i >> 8 & 255) / 255F;
    //        float b = (i & 255) / 255F;
    //
    //        Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelRenderer().renderModelBrightnessColor(state, model, brightness, r, g, b);
    //    }

    @OnlyIn(Dist.CLIENT)
    public static void renderItemWithoutScrewingWithColors(ItemStack stack) {
/*        if (StackUtil.isValid(stack)) {
            Minecraft mc = Minecraft.getInstance();
            ItemRenderer renderer = mc.getItemRenderer();
            TextureManager manager = mc.getTextureManager();

            IBakedModel model = renderer.getItemModelWithOverrides(stack, null, null);

            manager.bind(TextureMap.LOCATION_BLOCKS_TEXTURE);
            manager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
            GlStateManager._enableRescaleNormal();
            GlStateManager._enableBlend();
            GlStateManager._pushMatrix();
            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.FIXED, false);
            renderer.renderItem(stack, model);
            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager._popMatrix();
            GlStateManager._disableRescaleNormal();
            GlStateManager._disableBlend();
            manager.bind(TextureMap.LOCATION_BLOCKS_TEXTURE);
            manager.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
        }*/
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderStackToGui(ItemStack stack, int x, int y, float scale) {
/*        GlStateManager._pushMatrix();
        GlStateManager._enableBlend();
        GlStateManager._blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        GlStateManager._enableRescaleNormal();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scale, scale, scale);

        Minecraft mc = Minecraft.getInstance();
        boolean flagBefore = mc.font.getUnicodeFlag();
        mc.font.setUnicodeFlag(false);
        Minecraft.getInstance().getRenderItem().renderItemAndEffectIntoGUI(stack, 0, 0);
        Minecraft.getInstance().getRenderItem().renderItemOverlayIntoGUI(mc.font, stack, 0, 0, null);
        mc.font.setUnicodeFlag(flagBefore);

        RenderHelper.turnOff();
        GlStateManager._popMatrix();*/
    }

    //Copied from Gui.class and changed
    @OnlyIn(Dist.CLIENT)
    public static void drawHorizontalGradientRect(int left, int top, int right, int bottom, int startColor, int endColor, float zLevel) {
/*        float f = (startColor >> 24 & 255) / 255.0F;
        float f1 = (startColor >> 16 & 255) / 255.0F;
        float f2 = (startColor >> 8 & 255) / 255.0F;
        float f3 = (startColor & 255) / 255.0F;
        float f4 = (endColor >> 24 & 255) / 255.0F;
        float f5 = (endColor >> 16 & 255) / 255.0F;
        float f6 = (endColor >> 8 & 255) / 255.0F;
        float f7 = (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager._enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager._shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder renderer = tessellator.getBuilder();
        renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        renderer.vertex(left, top, zLevel).color(f1, f2, f3, f).endVertex();
        renderer.vertex(left, bottom, zLevel).color(f1, f2, f3, f).endVertex();
        renderer.vertex(right, bottom, zLevel).color(f5, f6, f7, f4).endVertex();
        renderer.vertex(right, top, zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.end();
        GlStateManager._shadeModel(7424);
        GlStateManager._disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();*/
    }

//    @OnlyIn(Dist.CLIENT)
//    public static void renderNameTag(String tag, double x, double y, double z) {
//        FontRenderer fontrenderer = Minecraft.getInstance().font;
//        float f = 1.6F;
//        float f1 = 0.016666668F * f;
//        GlStateManager._pushMatrix();
//        GlStateManager.translate(x, y, z);
//        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(-Minecraft.getInstance().getEntityRenderDispatcher().playerViewY, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(Minecraft.getInstance().getEntityRenderDispatcher().playerViewX, 1.0F, 0.0F, 0.0F);
//        GlStateManager.scale(-f1, -f1, f1);
//        GlStateManager._disableLighting();
//        GlStateManager._depthMask(false);
//        GlStateManager.disableDepth();
//        GlStateManager._enableBlend();
//        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder renderer = tessellator.getBuilder();
//        int i = 0;
//        int j = fontrenderer.width(tag) / 2;
//        GlStateManager.disableTexture2D();
//        renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
//        renderer.vertex(-j - 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
//        renderer.vertex(-j - 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
//        renderer.vertex(j + 1, 8 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
//        renderer.vertex(j + 1, -1 + i, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
//        tessellator.end();
//        GlStateManager.enableTexture2D();
//        fontrenderer.draw(tag, -fontrenderer.width(tag) / 2, i, 553648127);
//        GlStateManager.enableDepth();
//        GlStateManager._depthMask(true);
//        fontrenderer.draw(tag, -fontrenderer.width(tag) / 2, i, -1);
//        GlStateManager._enableLighting();
//        GlStateManager._disableBlend();
//        GlStateManager.color3arg(1.0F, 1.0F, 1.0F, 1.0F);
//        GlStateManager._popMatrix();


    public static float[] getWheelColor(float pos) {
        if (pos < 85.0f) {
            return new float[]{pos * 3.0F, 255.0f - pos * 3.0f, 0.0f};
        }
        if (pos < 170.0f) {
            return new float[]{255.0f - (pos -= 85.0f) * 3.0f, 0.0f, pos * 3.0f};
        }
        return new float[]{0.0f, (pos -= 170.0f) * 3.0f, 255.0f - pos * 3.0f};
    }
}
