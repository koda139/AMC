package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.CoalGeneratorContainer;
import com.alpha67.AMCBase.container.market.DiamondMarketContainer;
import com.alpha67.AMCBase.network.ButtonMarket;
import com.alpha67.AMCBase.screen.util.EnergyDisplay;
import com.alpha67.AMCBase.tileentity.CoalGeneratorTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {

    private int x;
    private int y;
    private int z;
    private BlockPos pos;

    int imagex;
    int imagey;

    int modifX;
    int modifY;

    private final CoalGeneratorTile generator;
    private EnergyDisplay energy;


    private final ResourceLocation GUI = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/coal_generator_gui.png");

    public CoalGeneratorScreen(CoalGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.pos = screenContainer.BlockPos;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.generator = screenContainer.generator;
        this.imagex = 196+modifX;
        this.imagey = 171+modifY;


    }

    @Override
    public void init() {
        super.init();
        this.energy = new EnergyDisplay(this.guiLeft + 15, this.guiTop + 10, this.generator.storage);
        //this.avanc = this.generator.avanc;

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

      this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

        ITextComponent name = ITextComponent.getTextComponentOrEmpty("Sell");
        String test = String.valueOf(System.currentTimeMillis());


        this.energy.render(matrixStack, mouseX, mouseY);


        //this.addButton(new Button(100, 100, 200, 10, name, e -> {if(true){System.out.println("salut");}}));
        //this.minecraft.fontRenderer.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(salut), 7, this.getYSize() - 93, 4210752);
    }

    int i;
    int j;


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
      //  this.minecraft.getTextureManager().bindTexture(GUI);
        //int i = this.guiLeft;
       // int j = this.guiTop;

        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        i = (this.width - this.xSize) / 2;
        j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

       this.energy.draw(matrixStack);

    }


}
