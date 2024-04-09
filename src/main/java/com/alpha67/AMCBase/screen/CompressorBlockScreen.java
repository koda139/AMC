package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.compressorBlockContainer;
import com.alpha67.AMCBase.screen.util.EnergyDisplay;
import com.alpha67.AMCBase.tileentity.CompressorBlockTile;
import com.alpha67.AMCBase.util.AssetUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class CompressorBlockScreen extends ContainerScreen<compressorBlockContainer> {
    private final ResourceLocation GUI_off = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_off.png");
   /* private final ResourceLocation GUI_cobble = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_off_cobble.png");
    private final ResourceLocation GUI_on = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/compressor_block_gui_on.png");*/

    private static final ResourceLocation RES_LOC = AssetUtil.getGuiLocation("gui_coal_generator");
    private final CompressorBlockTile generator;
    private EnergyDisplay energy;

    int avanc;


    @Override
    public void init() {
        super.init();
        this.energy = new EnergyDisplay(this.guiLeft + 19, this.guiTop + 10, this.generator.storage);
        //this.avanc = this.generator.avanc;

    }

    @Override
    public void render(@Nonnull MatrixStack matrices, int x, int y, float f) {
        renderBackground(matrices);
        super.render(matrices, x, y, f);
        this.energy.render(matrices, x, y);

       // System.out.println(this.avanc);
        //renderTooltip(matrices, x, y);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(MatrixStack matrices, float f, int x, int y) {

        this.minecraft.getTextureManager().bindTexture(GUI_off);
        i = (this.width - this.xSize) / 2;
        j = (this.height - this.ySize) / 2;
       this.blit(matrices, i, j, 0, 0, this.xSize, this.ySize, 176, 167);

        this.font.drawString(matrices, String.valueOf(avanc) + "%", this.guiLeft + 145, this.guiTop + 58, -12829636);

       this.energy.draw(matrices);

    }

    int state;
    int i;
    int j;

    int energyValue;


    public CompressorBlockScreen(compressorBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.generator = screenContainer.generator;
    }


    @Override
    public void tick()
    {
        avanc = this.getContainer().getAvanc();
      //  System.out.println(avanc);
    }
}
