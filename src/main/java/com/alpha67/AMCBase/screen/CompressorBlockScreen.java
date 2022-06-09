package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.compressorBlockContainer;
import com.alpha67.AMCBase.network.ButtonPacketT;
import com.alpha67.AMCBase.tileentity.CompressorBlockTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class CompressorBlockScreen extends ContainerScreen<compressorBlockContainer> {

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

    public CompressorBlockScreen(compressorBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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

        CompressorBlockTile BlockEntity = (CompressorBlockTile) world.getTileEntity(pos);
        double data = this.getContainer().getDataContainer();



        this.font.drawString(matrixStack, "money : " + data, this.guiLeft + 5, this.guiTop + 23, -16737997);
        //this.font.drawString(matrixStack, "stone price: " + stone, this.guiLeft + 5, this.guiTop + 40, -16750900);
        if (world.getBlockState(new BlockPos((int) x+1, (int) y, (int) z)).getBlock() == Blocks.COBBLESTONE) {
          //  this.font.drawString(matrixStack, "max price: " + maxPrice, this.guiLeft + 5, this.guiTop + 57, -6750208);

            System.out.println("okk");
        }




        //this.font.drawText(matrixStack, time, 50, 50);
        //this.minecraft.fontRenderer.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(test), this.guiLeft + 122, this.getYSize() + 93, 4210752);
        System.out.println(world.getBlockState(new BlockPos((int) x, (int) y+1, (int) z)).getBlock());

        this.addButton(new Button(this.guiLeft + 119, this.guiTop + 63, 35, 18, name, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonPacketT(this.pos));
                System.out.println("okk");
            }
        }));



        //this.addButton(new Button(100, 100, 200, 10, name, e -> {if(true){System.out.println("salut");}}));
        //this.minecraft.fontRenderer.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(salut), 7, this.getYSize() - 93, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        //int i = this.guiLeft;
       // int j = this.guiTop;

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize+4, 176, 166);

    }


    @Override
    public void init() {
        super.init();

    }


}
