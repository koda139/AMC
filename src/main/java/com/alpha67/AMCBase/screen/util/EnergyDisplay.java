/*
 * This file ("EnergyDisplay.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package com.alpha67.AMCBase.screen.util;

import com.alpha67.AMCBase.tileentity.util.CustomEnergyStorage;
import com.alpha67.AMCBase.util.AssetUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class EnergyDisplay extends AbstractGui {

    private CustomEnergyStorage rfReference;
    private int x;
    private int y;
    private boolean outline;
    private boolean drawTextNextTo;

    public EnergyDisplay(int x, int y, CustomEnergyStorage rfReference, boolean outline, boolean drawTextNextTo) {
        this.setData(x, y, rfReference, outline, drawTextNextTo);
    }

    public EnergyDisplay(int x, int y, CustomEnergyStorage rfReference) {
        this(x, y, rfReference, false, false);
    }

    public void setData(int x, int y, CustomEnergyStorage rfReference, boolean outline, boolean drawTextNextTo) {
        this.x = x;
        this.y = y;
        this.rfReference = rfReference;
        this.outline = outline;
        this.drawTextNextTo = drawTextNextTo;
    }

    public void draw(MatrixStack matrices) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(AssetUtil.GUI_INVENTORY_LOCATION);

        int barX = this.x;
        int barY = this.y;

        if (this.outline) {
            //this.blit(matrices, this.x, this.y, 52, 163, 26, 93);

            barX += 4;
            barY += 4;
        }
        this.blit(matrices, barX, barY, 18, 171, 18, 85);
        // 8 59

      if (this.rfReference.getEnergyStored() > 0) {
            int i = this.rfReference.getEnergyStored() * 57 / this.rfReference.getMaxEnergyStored();

            float[] color = AssetUtil.getWheelColor(mc.world.getGameTime() % 256);
            //System.out.println("render");
            RenderSystem.color3f(color[0] / 255F, color[1] / 255F, color[2] / 255F);
            this.blit(matrices, barX + 1, barY + 58 - i, 36, 172, 6, i);
            //RenderSystem.color3f(1F, 1F, 1F);
        }

        if (this.drawTextNextTo) {
            //this.drawString(mc.font, this.getOverlayText(), barX + 25, barY + 78, StringUtil.DECIMAL_COLOR_WHITE);
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY) {
        if (this.isMouseOver(mouseX, mouseY)) {
            Minecraft mc = Minecraft.getInstance();

            List<StringTextComponent> text = new ArrayList<>();
            text.add(new StringTextComponent(this.getOverlayText()));
            GuiUtils.drawHoveringText(matrices, text, mouseX, mouseY, mc.getMainWindow().getWidth(), mc.getMainWindow().getHeight(), -1, mc.fontRenderer);
        }
    }

    private boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + (this.outline
            ? 26
            : 18) && mouseY < this.y + (this.outline
            ? 93
            : 85);
    }

    private String getOverlayText() {
        String over;
        over = this.rfReference.getEnergyStored() + "/" +this.rfReference.getMaxEnergyStored() + " FE";
        return over;
    }
}
