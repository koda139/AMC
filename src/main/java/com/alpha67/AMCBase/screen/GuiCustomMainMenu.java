package com.alpha67.AMCBase.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AccessibilityScreen;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridgeScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.BrandingControl;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Random;

public class GuiCustomMainMenu extends MainMenuScreen {


    public GuiCustomMainMenu(boolean fade) {
        super(fade);
        //this.showFadeInAnimation = fade;
        //this.showTitleWronglySpelled = (double)(new Random()).nextFloat() < 1.0E-4D;
    }


    @Override
    protected void init() {
       // addDefaultButtons();
        //this.minecraft.setConnectedToRealms(false);this.minecraft.setConnectedToRealms(false);
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

    /*    if (this.firstRenderTime == 0L && this.showFadeInAnimation) {
            this.firstRenderTime = Util.milliTime();
        }

        float f = this.showFadeInAnimation ? (float)(Util.milliTime() - this.firstRenderTime) / 1000.0F : 1.0F;
        fill(matrixStack, 0, 0, this.width, this.height, -1);
        this.panorama.render(partialTicks, MathHelper.clamp(f, 0.0F, 1.0F));
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        this.minecraft.getTextureManager().bindTexture(PANORAMA_OVERLAY_TEXTURES);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.showFadeInAnimation ? (float)MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
        blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
        float f1 = this.showFadeInAnimation ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
        int l = MathHelper.ceil(f1 * 255.0F) << 24;
        if ((l & -67108864) != 0) {
            this.minecraft.getTextureManager().bindTexture(MINECRAFT_TITLE_TEXTURES);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, f1);
            if (this.showTitleWronglySpelled) {
                this.blitBlackOutline(j, 30, (p_238660_2_, p_238660_3_) -> {
                    this.blit(matrixStack, p_238660_2_ + 0, p_238660_3_, 0, 0, 99, 44);
                    this.blit(matrixStack, p_238660_2_ + 99, p_238660_3_, 129, 0, 27, 44);
                    this.blit(matrixStack, p_238660_2_ + 99 + 26, p_238660_3_, 126, 0, 3, 44);
                    this.blit(matrixStack, p_238660_2_ + 99 + 26 + 3, p_238660_3_, 99, 0, 26, 44);
                    this.blit(matrixStack, p_238660_2_ + 155, p_238660_3_, 0, 45, 155, 44);
                });
            } else {
                this.blitBlackOutline(j, 30, (p_238657_2_, p_238657_3_) -> {
                    this.blit(matrixStack, p_238657_2_ + 0, p_238657_3_, 0, 0, 155, 44);
                    this.blit(matrixStack, p_238657_2_ + 155, p_238657_3_, 0, 45, 155, 44);
                });
            }

            this.minecraft.getTextureManager().bindTexture(MINECRAFT_TITLE_EDITION);
            blit(matrixStack, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
            net.minecraftforge.client.ForgeHooksClient.renderMainMenu(this, matrixStack, this.font, this.width, this.height, l);
            if (this.splashText != null) {
                RenderSystem.pushMatrix();
                RenderSystem.translatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
                RenderSystem.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Util.milliTime() % 1000L) / 1000.0F * ((float)Math.PI * 2F)) * 0.1F);
                f2 = f2 * 100.0F / (float)(this.font.getStringWidth(this.splashText) + 32);
                RenderSystem.scalef(f2, f2, f2);
                drawCenteredString(matrixStack, this.font, this.splashText, 0, -8, 16776960 | l);
                RenderSystem.popMatrix();
            }

            String s = "Minecraft " + SharedConstants.getVersion().getName();
            if (this.minecraft.isDemo()) {
                s = s + " Demo";
            } else {
                s = s + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" + this.minecraft.getVersionType());
            }

            if (this.minecraft.isModdedClient()) {
                s = s + I18n.format("menu.modded");
            }

            net.minecraftforge.fml.BrandingControl.forEachLine(true, true, (brdline, brd) ->
                    drawString(matrixStack, this.font, brd, 2, this.height - ( 10 + brdline * (this.font.FONT_HEIGHT + 1)), 16777215 | l)
            );

            net.minecraftforge.fml.BrandingControl.forEachAboveCopyrightLine((brdline, brd) ->
                    drawString(matrixStack, this.font, brd, this.width - font.getStringWidth(brd), this.height - (10 + (brdline + 1) * ( this.font.FONT_HEIGHT + 1)), 16777215 | l)
            );

            drawString(matrixStack, this.font, "Copyright Mojang AB. Do not distribute!", this.widthCopyrightRest, this.height - 10, 16777215 | l);
            if (mouseX > this.widthCopyrightRest && mouseX < this.widthCopyrightRest + this.widthCopyright && mouseY > this.height - 10 && mouseY < this.height) {
                fill(matrixStack, this.widthCopyrightRest, this.height - 1, this.widthCopyrightRest + this.widthCopyright, this.height, 16777215 | l);
            }

            for(Widget widget : this.buttons) {
                widget.setAlpha(f1);
            }*/

          //  super.render(matrixStack, mouseX, mouseY, partialTicks);

          //  modUpdateNotification.render(matrixStack, mouseX, mouseY, partialTicks);

        }

    }


/*
    private void addDefaultButtons() {
        int buttonHeight = this.height / 4 + 48;
        int buttonWidth = this.width / 2;

        //Singleplayer Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight, 200, 20, new TranslationTextComponent("menu.singleplayer"), (p_213089_1_) -> {
            this.minecraft.displayGuiScreen(new WorldSelectionScreen(this));
        }));

        //Multiplayer Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight + 24 * 1, 200, 20, new TranslationTextComponent("menu.multiplayer"), (p_213086_1_) -> {
            this.minecraft.displayGuiScreen(new MultiplayerScreen(this));
        }));

        //Realms Button
        this.addButton(new Button(buttonWidth + 2, buttonHeight + 24 * 2, 98, 20, new TranslationTextComponent("menu.online"), (p_213095_1_) -> {
            RealmsBridgeScreen realmsbridgescreen = new RealmsBridgeScreen();
        }));

        //Mods Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight + 24 * 2, 98, 20, new TranslationTextComponent("fml.menu.mods"), button -> {
            this.minecraft.displayGuiScreen(new ModListScreen(this));
        }));

        //Language Button
        this.addButton(new ImageButton(buttonWidth - 124, buttonHeight + 72 + 12, 20, 20, 0, 106, 20, Widget.WIDGETS_LOCATION, 256, 256, (p_213090_1_) -> {
            this.minecraft.displayGuiScreen(new LanguageScreen(this, this.minecraft.gameSettings, this.minecraft.getLanguageManager()));
        }, new TranslationTextComponent("narrator.button.language")));

        //Options Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight + 72 + 12, 98, 20, new TranslationTextComponent("menu.options"), (p_213096_1_) -> {
            this.minecraft.displayGuiScreen(new OptionsScreen(this, this.minecraft.gameSettings));
        }));

        //Quit Button
        this.addButton(new Button(buttonWidth + 2, buttonHeight + 72 + 12, 98, 20, new TranslationTextComponent("menu.quit"), (p_213094_1_) -> {
            this.minecraft.shutdown();
        }));

        //Accessibility Options Button
        this.addButton(new ImageButton(buttonWidth + 104, buttonHeight + 72 + 12, 20, 20, 0, 0, 20, ACCESSIBILITY_TEXTURES, 32, 64, (p_213088_1_) -> {
            this.minecraft.displayGuiScreen(new AccessibilityScreen(this, this.minecraft.gameSettings));
        }, new TranslationTextComponent("narrator.button.accessibility")));
    }*/


