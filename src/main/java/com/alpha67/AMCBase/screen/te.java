package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.compressorBlockContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class te extends ContainerScreen<compressorBlockContainer> {

    private int x;
    private int y;
    private int z;
    private BlockPos pos;
    private World world;
    private PlayerEntity player;

    int imagex;
    int imagey;

    int modifX;
    int modifY;


    private final ResourceLocation GUI_off = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_off.png");
//    private final ResourceLocation GUI_cobble = new ResourceLocation(AMCBase.MOD_ID,
  //          "textures/gui/compressor_block_gui_off_cobble.png");
    //private final ResourceLocation GUI_on = new ResourceLocation(AMCBase.MOD_ID,
      //      "textures/gui/compressor_block_gui_on.png");



    public te(compressorBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        //this.pos = screenContainer.BlockPos;
       // this.x = pos.getX();
       // this.y = pos.getY();
       // this.z = pos.getZ();
        //this.world = screenContainer.world;
        //this.player = screenContainer.player;
        this.imagex = 176;
        this.imagey = 166;


    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);


    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI_off);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 166);

        /*

        if (state == 0)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_off);
            int i = (this.width - this.xSize) / 2;
            int j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);
        }

        else if (state == 1)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_cobble);
            int i = (this.width - this.xSize) / 2;
            int j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

        }

        else if (state == 2)
        {
            this.minecraft.getTextureManager().bindTexture(GUI_on);
            int i = (this.width - this.xSize) / 2;
            int j = (this.height - this.ySize) / 2;
            this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

        }*/


    }


    @Override
    public void init() {

    }
}
