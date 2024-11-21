//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.guifutureold.futuregui;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.guifutureold.futuregui.components.Component;
import me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.Item;
import me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.buttons.Button;
import me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.buttons.ModuleButton;
import me.akaishin.cracked.features.future.guifutureold.futuregui.utils.ColorUtil;
import me.akaishin.cracked.features.future.guifutureold.futuregui.utils.RenderMethods;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;


public class FutureGuiExeter extends GuiScreen
{
    private static FutureGuiExeter FutureGuiOld;
    private static FutureGuiExeter INSTANCE;
    private final ArrayList<Component> components;
    
    public FutureGuiExeter() {
        this.components = new ArrayList<Component>();
        this.setInstance();
        this.load();
    }
    
    public static FutureGuiExeter getInstance() {
        if (FutureGuiExeter.INSTANCE == null) {
            FutureGuiExeter.INSTANCE = new FutureGuiExeter();
        }
        return FutureGuiExeter.INSTANCE;
    }
    
    public static FutureGuiExeter getFutureGuiOld() {
        final FutureGuiExeter FutureGuiOld = FutureGuiExeter.FutureGuiOld;
        return getInstance();
    }
    
    private void setInstance() {
        FutureGuiExeter.INSTANCE = this;
    }
    
    private void load() {
        int x = -80;
        for (final Module.Category category : Cracked.moduleManager.getCategories()) {
            final ArrayList<Component> components2 = this.components;
            final String name = category.getName();
            x += 100;
            components2.add(new Component(name, x, 15, true) {

                public void setupItems() {
                    Cracked.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton((Button) new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort((item1, item2) -> item1.getName().compareTo(item2.getName())));
    }
    
    public void updateModule(final Module module) {
        for (final Component component : this.components) {
            for (final Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) {
                    continue;
                }
                final ModuleButton button = (ModuleButton)item;
                final Module mod = button.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(mod)) {
                    continue;
                }
                button.initSettings();
                break;
            }
        }
    }


    public static void drawCompleteImage(final float posX, final float posY, final int width, final int height) {
        GL11.glPushMatrix();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)height, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)width, (float)height, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)width, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

/*--------------------------------IMAGE LOGO GUI-------------------------- */
public void drawImageLogo() {
    final ResourceLocation logo = new ResourceLocation("textures/waifu.png");
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    ModuleButton.mc.getTextureManager().bindTexture(logo);
    drawCompleteImage(FutureGuiB8.INSTANCE.xWaifu.getValue().intValue(), FutureGuiB8.INSTANCE.yWaifu.getValue().intValue(), FutureGuiB8.INSTANCE.widthWaifu.getValue(), FutureGuiB8.INSTANCE.heightWaifu.getValue());
    //drawCompleteImage(728.0f, 255.0f, 207, 267);
    ModuleButton.mc.getTextureManager().bindTexture(logo);
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
}
/*---------------------------------------------------------- */

    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
      if ((Boolean)FutureGuiB8.getInstance().backgroundScreen.getValue()) {
         //this.drawDefaultBackground();
        RenderMethods.drawRect(0.0F, 0.0F, this.mc.displayWidth, this.mc.displayHeight, ColorUtil.toRGBA(14, 14, 14, 120)); //Render
    }
        //GlStateManager.pushMatrix();
        //GlStateManager.enableBlend();
        //Posible errror remaping puting xding
        //else drawwefewfReact
        //GlStateManager.disableBlend();
        //GlStateManager.popMatrix();
        
        final ScaledResolution res = new ScaledResolution(this.mc);

        if ((Boolean)FutureGuiB8.getInstance().logoWaifu.getValue()) {
            this.drawImageLogo();
            }
        this.checkMouseWheel();
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int releaseButton) {
        this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public final ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        }
        else if (dWheel > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    public Component getComponentByName(final String name) {
        for (final Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return component;
        }
        return null;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
    }
    
    static {
        FutureGuiExeter.INSTANCE = new FutureGuiExeter();
    }
}
