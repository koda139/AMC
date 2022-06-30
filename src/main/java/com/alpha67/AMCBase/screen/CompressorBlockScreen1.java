package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.container.compressorBlockContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;


public class CompressorBlockScreen1 extends ContainerScreen<compressorBlockContainer> {
    public CompressorBlockScreen1(compressorBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
/*
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


    private final ResourceLocation GUI = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui.png");

    public CompressorBlockScreen1(compressorBlockContainer1 screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.pos = screenContainer.BlockPos;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.world = screenContainer.world;
        this.player = screenContainer.player;
        this.imagex = 176;
        this.imagey = 166;


    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

        ITextComponent name = ITextComponent.getTextComponentOrEmpty("Sell");
        String test = String.valueOf(System.currentTimeMillis());

        CompressorBlockTile1 BlockEntity = (CompressorBlockTile1) world.getTileEntity(pos);
//        double data = this.getContainer().getDataContainer();

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        //int i = this.guiLeft;
       // int j = this.guiTop;

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0-2, 176, 166, 176, 166);

    }


    @Override
    public void init() {
        super.init();

    }*/


}
