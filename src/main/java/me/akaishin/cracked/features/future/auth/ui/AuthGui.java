package me.akaishin.cracked.features.future.auth.ui;


import me.akaishin.cracked.Cracked;
//import me.akaishin.cracked.features.future.auth.ui.font.CustomFont;
//import me.akaishin.cracked.features.future.auth.ui.shaders.*;
//import me.akaishin.cracked.features.future.auth.ui.widgets.*;
//import me.akaishin.cracked.features.future.auth.ui.util.RoundedShader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import javafx.scene.layout.Background;

import java.awt.*;
import java.io.*;
import java.net.*;

public class AuthGui extends GuiScreen {
    private GuiButton submitButton;
    private String authMessage = "Por favor, inicie sesion para continuar";
    private final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("textures/auth-screen.png");

    public AuthGui() {
    }

    @Override
    public void initGui() {
        this.buttonList.add(this.submitButton = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 42, "Iniciar sesion"));
        this.buttonList.add(this.submitButton = new GuiButton(1, this.width / 2 - 100, this.height / 2 - 12, "Discord"));
        this.buttonList.add(this.submitButton = new GuiButton(3, this.width / 2 - 100, this.height / 2 + 22, "Youtube"));
        this.buttonList.add(this.submitButton = new GuiButton(2, this.width / 2 - 100, this.height / 2 + 52, "WebSite"));
    }

    @Override
    public void updateScreen() {
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        //Button 0
        if (button.id == 0) {
            // Autenticar usuario 
            this.authMessage = "Iniciando sesión...";
            //KeyManager.hwidCheck();  //Check user login // AUTHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH REMOVE

                this.authMessage = "Sesión iniciada correctamente";
                Cracked.isOpenAuthGui = false;
                this.mc.displayGuiScreen(null);
            }
            //Button 1
        if (button.id == 1) {
            String url = "https://discord.gg/73cNP2EACT";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            }
            //Button 2
        if (button.id == 2) {
            String url = "https://discord.gg/73cNP2EACT";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            }
        if (button.id == 3) {
            String url = "https://www.youtube.com/channel/UClX8jvZvZIoyY7JnAiKLfNA";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            }
        }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //this.drawDefaultBackground();
        mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        this.drawCenteredString(this.fontRenderer, this.authMessage, this.width / 2, this.height / 2 - 100, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        } else {
        }
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
}