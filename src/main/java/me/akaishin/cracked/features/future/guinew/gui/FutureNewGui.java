//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.future.gui.components.items.buttons.Button;
import me.akaishin.cracked.features.future.guinew.gui.components.Component;
import me.akaishin.cracked.features.future.guinew.gui.components.items.Item;
import me.akaishin.cracked.features.future.guinew.gui.components.items.buttons.ModuleButton;
import me.akaishin.cracked.features.future.guinew.gui.effect.Snow;
import me.akaishin.cracked.features.future.guinew.gui.effect.Particle.ParticleSystem;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.modules.client.Colors;
import me.akaishin.cracked.features.modules.client.GuiFuture;

public class FutureNewGui extends GuiScreen {

    private static FutureNewGui FutureHackGui;
    private static FutureNewGui INSTANCE;
    private final ArrayList<Component> components;
    private ArrayList<Snow> _snowList;
    public ParticleSystem particleSystem;
    
   final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
   Random random = new Random();

   public FutureNewGui() {
      this.components = new ArrayList<Component>();
      this._snowList = new ArrayList<Snow>();
      this.setInstance();
      this.load();
   }

    public static FutureNewGui getInstance() {
        if (FutureNewGui.INSTANCE == null) {
            FutureNewGui.INSTANCE = new FutureNewGui();
        }
        return FutureNewGui.INSTANCE;
    }
    
    public static FutureNewGui getClickGuiFutureX() {
        final FutureNewGui FutureHackGui = FutureNewGui.FutureHackGui;
        return getInstance();
    }
    
    private void setInstance() {
        FutureNewGui.INSTANCE = this;
    }

   private void load() {
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            for (int y = 0; y < 3; ++y) {
                final Snow snow = new Snow(25 * i, y * -50, random.nextInt(3) + 1, random.nextInt(2) + 1);
                this._snowList.add(snow);
            }
        }
        int x = -80;
        for (final Module.Category category : Cracked.moduleManager.getCategories()) {
            final ArrayList<Component> components2 = this.components;
            final String name = category.getName();
            x += 100;
            components2.add(new Component(name, x, 15, true) {

                public void setupItems() {
                    Cracked.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton((ModuleButton) new ModuleButton(module));
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
    drawCompleteImage(GuiFuture.INSTANCE.xWaifu.getValue().intValue(), GuiFuture.INSTANCE.yWaifu.getValue().intValue(), GuiFuture.INSTANCE.widthWaifu.getValue(), GuiFuture.INSTANCE.heightWaifu.getValue());
    //drawCompleteImage(728.0f, 255.0f, 207, 267);
    ModuleButton.mc.getTextureManager().bindTexture(logo);
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
}
/*---------------------------------------------------------- */

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if ((Boolean)GuiFuture.getInstance().backgroundScreen.getValue()) {
         //this.drawDefaultBackground();
        RenderUtil.drawRect(0.0F, 0.0F, this.mc.displayWidth, this.mc.displayHeight, ColorUtil.toRGBA(14, 14, 14, 120)); //Render
    }
    if ((Boolean)GuiFuture.getInstance().logoWaifu.getValue()) {
      this.drawImageLogo();
    }

      ScaledResolution res = new ScaledResolution(this.mc);
      this.checkMouseWheel();
      this.components.forEach((components) -> {
         components.drawScreen(mouseX, mouseY, partialTicks);
      });
      ScaledResolution sr = new ScaledResolution(this.mc);
      if ((Boolean)GuiFuture.getInstance().gradiant.getValue()) {
         this.drawGradientRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0, (Boolean)GuiFuture.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor().getRGB() : (new Color((Integer)GuiFuture.getInstance().gradiantred.getValue(), (Integer)GuiFuture.getInstance().gradiantgreen.getValue(), (Integer)GuiFuture.getInstance().gradiantblue.getValue(), (Integer)GuiFuture.getInstance().gradiantalpha.getValue() / 2)).getRGB());
      }

      this.checkMouseWheel();
      this.components.forEach((components) -> {
         components.drawScreen(mouseX, mouseY, partialTicks);
      });
      if (!this._snowList.isEmpty() && (Boolean)GuiFuture.getInstance().snowing.getValue()) {
         this._snowList.forEach((snow) -> {
            snow.Update(res);
         });
      }

      if (this.particleSystem != null && (Boolean)GuiFuture.getInstance().particles.getValue()) {
         this.particleSystem.render(mouseX, mouseY);
      } else {
         this.particleSystem = new ParticleSystem(new ScaledResolution(this.mc));
      }

   }

   public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
      this.components.forEach((components) -> {
         components.mouseClicked(mouseX, mouseY, clickedButton);
      });
   }

   public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
      this.components.forEach((components) -> {
         components.mouseReleased(mouseX, mouseY, releaseButton);
      });
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public final ArrayList<Component> getComponents() {
      return this.components;
   }

   public void checkMouseWheel() {
      int dWheel = Mouse.getDWheel();
      if (dWheel < 0) {
         this.components.forEach((component) -> {
            component.setY(component.getY() - 10);
         });
      } else if (dWheel > 0) {
         this.components.forEach((component) -> {
            component.setY(component.getY() + 10);
         });
      }

   }

   public int getTextOffset() {
      return -6;
   }

   public Component getComponentByName(String name) {
      Iterator var2 = this.components.iterator();

      Component component;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         component = (Component)var2.next();
      } while(!component.getName().equalsIgnoreCase(name));

      return component;
   }

   public void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      this.components.forEach((component) -> {
         component.onKeyTyped(typedChar, keyCode);
      });
   }

   public void updateScreen() {
      if (this.particleSystem != null) {
         this.particleSystem.update();
      }

   }
}
