package com.alpha67.AMCBase.screen;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.container.ATMBlockContainer;
import com.alpha67.AMCBase.network.ButtonATM;
import com.alpha67.AMCBase.network.ButtonMarket;
import com.alpha67.AMCBase.screen.util.EnergyDisplay;
import com.alpha67.AMCBase.tileentity.ATMBlockTile;
import com.alpha67.AMCBase.util.AssetUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class ATMBlockScreen extends ContainerScreen<ATMBlockContainer> {
    private final ResourceLocation GUI_off = new ResourceLocation(AMCBase.MOD_ID,
            "textures/gui/atm_block_gui.png");

    private static final ResourceLocation RES_LOC = AssetUtil.getGuiLocation("gui_coal_generator");
    private final ATMBlockTile generator;
    private EnergyDisplay energy;

    private int x;
    private int y;
    private int z;
    private BlockPos pos;

    int avanc;
    double money;

    public ATMBlockScreen(ATMBlockContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.generator = screenContainer.generator;
        this.pos = screenContainer.BlockPos;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }


    @Override
    public void init() {
        super.init();
        this.energy = new EnergyDisplay(this.guiLeft + 10, this.guiTop - 10, this.generator.storage);
        //this.avanc = this.generator.avanc;

    }

    @Override
    public void render(@Nonnull MatrixStack matrices, int x, int y, float f) {
        renderBackground(matrices);
        super.render(matrices, x, y, f);
        this.energy.render(matrices, x, y);

        money = this.getContainer().getMoneyContainer();

        this.font.drawString(matrices, String.valueOf(this.money) + " CF", this.guiLeft + 110, this.guiTop -24, 11896576);

        ITextComponent a = ITextComponent.getTextComponentOrEmpty("50");
        this.addButton(new Button(this.guiLeft + 28, this.guiTop - 24, 35, 20, a, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonATM(this.pos, 0));
            }
        }));

        ITextComponent b = ITextComponent.getTextComponentOrEmpty("100");
        this.addButton(new Button(this.guiLeft + 25, this.guiTop + 10, 40, 20, b, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonATM(this.pos, 1));
            }
        }));

        ITextComponent c = ITextComponent.getTextComponentOrEmpty("500");
        this.addButton(new Button(this.guiLeft + 25, this.guiTop + 50, 40, 20, c, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonATM(this.pos, 2));
            }
        }));

        ITextComponent d = ITextComponent.getTextComponentOrEmpty("send");
        this.addButton(new Button(this.guiLeft + 119, this.guiTop + 58, 46, 20, d, e -> {
            if (true) {
                AMCBase.PACKET_HANDLER.sendToServer(new ButtonATM(this.pos, 3));
            }
        }));


        // System.out.println(this.avanc);
        //renderTooltip(matrices, x, y);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(MatrixStack matrices, float f, int x, int y) {

        this.minecraft.getTextureManager().bindTexture(GUI_off);
        i = (this.width - this.xSize) / 2;
        j = (this.height - this.ySize) / 2;
       this.blit(matrices, i, j-33, 0, 0, this.xSize, this.ySize+33, 176, 200);

        this.font.drawString(matrices, String.valueOf(avanc) + "%", this.guiLeft + 145, this.guiTop + 58, -12829636);

       this.energy.draw(matrices);

    }

    int state;
    int i;
    int j;

    int energyValue;

    @Override
    public void tick()
    {
        //avanc = this.getContainer().getAvanc();
      //  System.out.println(avanc);
    }
}
