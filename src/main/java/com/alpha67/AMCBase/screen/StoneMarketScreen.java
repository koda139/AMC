package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.StoneMarketContainer;
import com.alpha67.AMCBase.network.ButtonPacketT;
import com.alpha67.AMCBase.tileentity.StoneMarketTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class StoneMarketScreen extends ContainerScreen<StoneMarketContainer> {

    private int x;
    private int y;
    private int z;
    private BlockPos pos;
    private World world;
    private PlayerEntity player;


    private final ResourceLocation GUI = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/stone_market_gui.png");

    public StoneMarketScreen(StoneMarketContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.pos = screenContainer.BlockPos;
        this.world = screenContainer.world;
        this.player = screenContainer.player;

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

        ITextComponent name = ITextComponent.getTextComponentOrEmpty("Sell");
        String test = String.valueOf(System.currentTimeMillis());

        StoneMarketTile BlockEntity = (StoneMarketTile) world.getTileEntity(pos);
        double data = this.getContainer().getDataContainer();

        System.out.println(data);

        this.font.drawString(matrixStack, "Process: " + data, 5, 29, -12829636);




        //this.font.drawText(matrixStack, time, 50, 50);
        this.minecraft.fontRenderer.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(test), 7, this.getYSize() - 93, 4210752);


        this.addButton(new Button(60, 47, 35, 20, name, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonPacketT(this.pos));
                System.out.println(pos);
            }
        }));



        //this.addButton(new Button(100, 100, 200, 10, name, e -> {if(true){System.out.println("salut");}}));
        //this.minecraft.fontRenderer.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(salut), 7, this.getYSize() - 93, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }


    @Override
    public void init() {
        super.init();

    }


}
