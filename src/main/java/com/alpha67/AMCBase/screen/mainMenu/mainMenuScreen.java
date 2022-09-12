package com.alpha67.AMCBase.screen.mainMenu;

import com.alpha67.AMCBase.AMCBase;
import com.alpha67.AMCBase.util.openURL;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.BrandingControl;


import java.net.URI;
import java.net.URISyntaxException;

import static com.alpha67.AMCBase.screen.mainMenu.util.getOrCreateServerData;


public class mainMenuScreen extends MainMenuScreen {

    private final boolean showFadeInAnimation;
    private long firstRenderTime;

    public static final ResourceLocation BACKGROUND = new ResourceLocation(AMCBase.MOD_ID, "textures/gui/background.png");
    public final RenderSkybox panorama = new RenderSkybox(PANORAMA_RESOURCES);
    private static final ResourceLocation PANORAMA_OVERLAY_TEXTURES = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");

    private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
    public static final ResourceLocation CAPITALIUM_TITLE = new ResourceLocation(AMCBase.MOD_ID, "textures/gui/capitalium.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(AMCBase.MOD_ID, "textures/gui/button_texture.png");
    private static final ResourceLocation MINECRAFT_TITLE_EDITION = new ResourceLocation("textures/gui/title/edition.png");

    public mainMenuScreen(boolean showFadeInAnimation) {
        this.showFadeInAnimation = showFadeInAnimation;
    }

    @Override
    protected void init() {
        addDefaultButtons();
        this.minecraft.setConnectedToRealms(false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.firstRenderTime == 0L && this.showFadeInAnimation) {
            this.firstRenderTime = Util.milliTime();
        }

        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        float f = this.showFadeInAnimation ? (float)(Util.milliTime() - this.firstRenderTime) / 1000.0F : 1.0F;


        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        blit(stack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);

        float f1 = this.showFadeInAnimation ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;

        this.minecraft.getTextureManager().bindTexture(CAPITALIUM_TITLE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, f1);
        this.blitBlackOutline(j, 30, (p_238660_2_, p_238660_3_) -> {
            // stack x = position dans le jeu, y = position dans le jeu, uoffset = , voffset = , uwidth = , vheight
        this.blit(stack, p_238660_2_, p_238660_3_, 0, 0, 269, 42, 512, 512);
        });

        System.out.println(mouseX + "!!!" + mouseY);


        this.minecraft.getTextureManager().bindTexture(MINECRAFT_TITLE_EDITION);

        String s = "Minecraft " + SharedConstants.getVersion().getName();
        s = s + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" + this.minecraft.getVersionType());

        for (Widget widget : this.buttons) {
            widget.setAlpha(f1);
        }

        for (int is = 0; is < this.buttons.size(); ++is) {
            this.buttons.get(is).render(stack, mouseX, mouseY, partialTicks);
        }

        BrandingControl.forEachLine(true, true, (brdline, brd) -> drawString(stack, getFont(), brd, 2, this.height - (10 + brdline * (getFont().FONT_HEIGHT + 1)), 16777215));

        }


    @Override
    public void tick() {
        super.tick();
    }

    private void addDefaultButtons() {
        int buttonHeight = this.height / 4 + 48;
        int buttonWidth = this.width / 2;


        //connect to capitalium factory button
        this.addButton(new Button(buttonWidth - 100, buttonHeight, 200, 20, new TranslationTextComponent("Capitalium Factory"), (p_213086_1_) -> {
            connectToServer("capitalium-factory.fr");
        }));

        //Singleplayer Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight + 20*2, 200, 20, new TranslationTextComponent("menu.singleplayer"), (p_213089_1_) -> {
            this.minecraft.displayGuiScreen(new WorldSelectionScreen(this));
        }));
        //Options Button
        this.addButton(new Button(buttonWidth - 100, buttonHeight + 72 + 12, 98, 20, new TranslationTextComponent("menu.options"), (p_213096_1_) -> {
            this.minecraft.displayGuiScreen(new OptionsScreen(this, this.minecraft.gameSettings));
        }));
        //Quit Button
        this.addButton(new Button(buttonWidth + 2, buttonHeight + 72 + 12, 98, 20, new TranslationTextComponent("menu.quit"), (p_213094_1_) -> {
            this.minecraft.shutdown();
        }));

        this.addButton(new Button(buttonWidth - 100, buttonHeight + 24 * 1, 200, 20, new TranslationTextComponent("menu.multiplayer"), (p_213086_1_) -> {this.minecraft.displayGuiScreen(new MultiplayerScreen(this));}));

        //discord
        this.addButton(new ImageButton(420, 5, 40, 40, 0, 0, 40, BUTTON_TEXTURE, 120, 80, (p_213088_1_) -> {
            openURL.openURL("https://discord.gg/7BMSYQtw");
        }, new TranslationTextComponent("")));

        //tiktok
        this.addButton(new ImageButton(5, 5, 40, 40, 40, 0, 40, BUTTON_TEXTURE, 120, 80, (p_213088_1_) -> {
            openURL.openURL("https://www.tiktok.com/@capitalium_factory");
        }, new TranslationTextComponent("")));

        //site
        this.addButton(new ImageButton(420, 210, 40, 40, 80, 0, 40, BUTTON_TEXTURE, 120, 80, (p_213088_1_) -> {
            openURL.openURL("http://capitalium-factory.fr/");
        }, new TranslationTextComponent("")));

       // boolean mouseHover = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    public FontRenderer getFont() {
        return font;
    }

    public static void connectToServer(String IP)
    {
        System.out.println("the button was click");
        Minecraft mc = Minecraft.getInstance();
        ServerData data = getOrCreateServerData((String) IP);
        mc.displayGuiScreen(new ConnectingScreen(mc.currentScreen, mc, data));
    }

}
