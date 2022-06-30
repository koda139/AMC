package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.LightningChannelerContainer;
import com.alpha67.AMCBase.container.compressorBlockContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CompressorBlockScreen extends ContainerScreen<compressorBlockContainer> {
    private final ResourceLocation GUI_off = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_off.png");
   /* private final ResourceLocation GUI_cobble = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_off_cobble.png");
    private final ResourceLocation GUI_on = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_on.png");*/

    int state;
    int i;
    int j;

    public CompressorBlockScreen(compressorBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
/*
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        if (this.state == 0)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_off);
            i = (this.width - this.xSize) / 2;
            j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);
        }

        else if (this.state == 1)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_cobble);
            i = (this.width - this.xSize) / 2;
            j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

        }

        else if (this.state == 2)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_on);
            i = (this.width - this.xSize) / 2;
            j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

        }*/
    }

    @Override
    public void tick()
    {
       // this.state = 0;
        //this.state = this.getContainer().getState();
    }
}
