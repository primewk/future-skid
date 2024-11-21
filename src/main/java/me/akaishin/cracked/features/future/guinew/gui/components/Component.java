//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.future.gui.futureutils.RenderUtilic;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.components.items.Item;
import me.akaishin.cracked.features.future.guinew.gui.components.items.buttons.Button;
import me.akaishin.cracked.features.future.guinew.gui.components.items.buttons.ModuleButton;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.MathUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.client.Colors;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.modules.hud.HUD;

public class Component extends Feature {
   private final ArrayList<Item> items = new ArrayList();
    private Minecraft minecraft = Minecraft.getMinecraft();
   public boolean drag;
   private int x;
   private int y;
   private int x2;
   private int y2;
   private int angle; //Future Ext
   private int width;
   private int height;
   private boolean open;
   private boolean hidden;

   public Component(String name, int x, int y, boolean open) {
      super(name);
      this.x = x;
      this.y = y;
      this.angle = 180; //Future Ext
      this.width = 96;
      this.height = 18;
      this.open = open;
      this.setupItems();
   }

   public void setupItems() {
   }

   private void drag(int mouseX, int mouseY) {
      if (this.drag) {
         this.x = this.x2 + mouseX;
         this.y = this.y2 + mouseY;
      }
   }

   private void drawOutline(float thickness, int color) {
      float totalItemHeight = 0.0F;
      if (this.open) {
         totalItemHeight = this.getTotalItemHeight() - 2.0F;
      }

      RenderUtil.drawLine((float)this.x, (float)this.y - 1.5F, (float)this.x, (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA((Integer)GuiFuture.getInstance().o_red.getValue(), (Integer)GuiFuture.getInstance().o_green.getValue(), (Integer)GuiFuture.getInstance().o_blue.getValue(), (Integer)GuiFuture.getInstance().o_alpha.getValue()));
      RenderUtil.drawLine((float)this.x, (float)this.y - 1.5F, (float)(this.x + this.width), (float)this.y - 1.5F, thickness, ColorUtil.toRGBA((Integer)GuiFuture.getInstance().o_red.getValue(), (Integer)GuiFuture.getInstance().o_green.getValue(), (Integer)GuiFuture.getInstance().o_blue.getValue(), (Integer)GuiFuture.getInstance().o_alpha.getValue()));
      RenderUtil.drawLine((float)(this.x + this.width), (float)this.y - 1.5F, (float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA((Integer)GuiFuture.getInstance().o_red.getValue(), (Integer)GuiFuture.getInstance().o_green.getValue(), (Integer)GuiFuture.getInstance().o_blue.getValue(), (Integer)GuiFuture.getInstance().o_alpha.getValue()));
      RenderUtil.drawLine((float)this.x, (float)(this.y + this.height) + totalItemHeight, (float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA((Integer)GuiFuture.getInstance().o_red.getValue(), (Integer)GuiFuture.getInstance().o_green.getValue(), (Integer)GuiFuture.getInstance().o_blue.getValue(), (Integer)GuiFuture.getInstance().o_alpha.getValue()));
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drag(mouseX, mouseY);
      float totalItemHeight = this.open ? this.getTotalItemHeight() - 2.0F : 0.0F;
      int color = -7829368;
      if (this.open) {
         RenderUtil.drawRect((float)this.x, (float)this.y + 14.0F, (float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, 0);
         if ((Boolean)GuiFuture.getInstance().outline2.getValue()) {
            this.drawOutline((Float)GuiFuture.getInstance().outlineThickness.getValue(), color);
         }
      }

      if ((Boolean)GuiFuture.getInstance().frameSettings.getValue()) {
         color = ColorUtil.toARGB((Integer)GuiFuture.getInstance().frameRed.getValue(), (Integer)GuiFuture.getInstance().frameGreen.getValue(), (Integer)GuiFuture.getInstance().frameBlue.getValue(), (Integer)GuiFuture.getInstance().frameAlpha.getValue());
         RenderUtil.drawRect((float)this.x, (float)this.y + 11.0F, (float)(this.x + this.width), (float)(this.y + this.height - 6), (Boolean)GuiFuture.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColor().getRGB() : ColorUtil.toARGB((Integer)GuiFuture.getInstance().frameRed.getValue(), (Integer)GuiFuture.getInstance().frameGreen.getValue(), (Integer)GuiFuture.getInstance().frameBlue.getValue(), (Integer)GuiFuture.getInstance().frameAlpha.getValue()));
      }

      if ((Boolean)GuiFuture.getInstance().devSettings.getValue()) {
         color = (Boolean)GuiFuture.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB((Integer)GuiFuture.getInstance().topRed.getValue(), (Integer)GuiFuture.getInstance().topGreen.getValue(), (Integer)GuiFuture.getInstance().topBlue.getValue(), (Integer)GuiFuture.getInstance().topAlpha.getValue());
      }

      if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue() && (Boolean)GuiFuture.getInstance().colorSync.getValue() && (Boolean)Colors.INSTANCE.rainbow.getValue()) {
         RenderUtil.drawGradientRect((float)this.x, (float)this.y - 1.5F, (float)this.width, (float)(this.height - 4), (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)), (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y + this.height - 4, 0, this.renderer.scaledHeight)));
      } else {
         RenderUtil.drawRect((float)this.x, (float)this.y - 1.5F, (float)(this.x + this.width), (float)(this.y + this.height - 6), color);
      }

      if (this.open) {
         RenderUtil.drawRect((float)this.x, (float)this.y + 12.5F, (float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, ColorUtil.toRGBA((Integer)GuiFuture.getInstance().b_red.getValue(), (Integer)GuiFuture.getInstance().b_green.getValue(), (Integer)GuiFuture.getInstance().b_blue.getValue(), (Integer)GuiFuture.getInstance().b_alpha.getValue()));
      }

      if (this.open) {
         RenderUtil.drawRect((float)this.x, (float)this.y + 12.5F, (float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, 0);
         if ((Boolean)GuiFuture.getInstance().outline.getValue()) {
            Color currentColor;
            if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue()) {
               GlStateManager.disableTexture2D();
               GlStateManager.enableBlend();
               GlStateManager.disableAlpha();
               GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
               GlStateManager.shadeModel(7425);
               GL11.glBegin(1);
               currentColor = new Color((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)));
               GL11.glColor4f((float)currentColor.getRed() / 255.0F, (float)currentColor.getGreen() / 255.0F, (float)currentColor.getBlue() / 255.0F, (float)currentColor.getAlpha() / 255.0F);
               GL11.glVertex3f((float)(this.x + this.width), (float)this.y - 1.5F, 0.0F);
               GL11.glVertex3f((float)this.x, (float)this.y - 1.5F, 0.0F);
               GL11.glVertex3f((float)this.x, (float)this.y - 1.5F, 0.0F);
               float currentHeight = (float)this.getHeight() - 1.5F;

               for (final Item item : this.getItems()) {
                  currentColor = new Color((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (currentHeight += (float)item.getHeight() + 1.5F)), 0, this.renderer.scaledHeight)));
                  GL11.glColor4f((float)currentColor.getRed() / 255.0F, (float)currentColor.getGreen() / 255.0F, (float)currentColor.getBlue() / 255.0F, (float)currentColor.getAlpha() / 255.0F);
                  GL11.glVertex3f((float)this.x, (float)this.y + currentHeight, 0.0F);
                  GL11.glVertex3f((float)this.x, (float)this.y + currentHeight, 0.0F);
               }

               currentColor = new Color((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)(this.y + this.height) + totalItemHeight), 0, this.renderer.scaledHeight)));
               GL11.glColor4f((float)currentColor.getRed() / 255.0F, (float)currentColor.getGreen() / 255.0F, (float)currentColor.getBlue() / 255.0F, (float)currentColor.getAlpha() / 255.0F);
               GL11.glVertex3f((float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, 0.0F);
               GL11.glVertex3f((float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, 0.0F);

               for (final Item item : this.getItems()) {
                  currentColor = new Color((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (currentHeight -= (float)item.getHeight() + 1.5F)), 0, this.renderer.scaledHeight)));
                  GL11.glColor4f((float)currentColor.getRed() / 255.0F, (float)currentColor.getGreen() / 255.0F, (float)currentColor.getBlue() / 255.0F, (float)currentColor.getAlpha() / 255.0F);
                  GL11.glVertex3f((float)(this.x + this.width), (float)this.y + currentHeight, 0.0F);
                  GL11.glVertex3f((float)(this.x + this.width), (float)this.y + currentHeight, 0.0F);
               }

               GL11.glVertex3f((float)(this.x + this.width), (float)this.y, 0.0F);
               GL11.glEnd();
               GlStateManager.shadeModel(7424);
               GlStateManager.disableBlend();
               GlStateManager.enableAlpha();
               GlStateManager.enableTexture2D();
            } else {
               GlStateManager.disableTexture2D();
               GlStateManager.enableBlend();
               GlStateManager.disableAlpha();
               GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
               GlStateManager.shadeModel(7425);
               GL11.glBegin(2);
               currentColor = (Boolean)GuiFuture.getInstance().colorSync.getValue() ? new Color(Colors.INSTANCE.getCurrentColorHex()) : new Color(Cracked.colorManagerf.getColorAsIntFullAlpha());
               GL11.glColor4f((float)currentColor.getRed(), (float)currentColor.getGreen(), (float)currentColor.getBlue(), (float)currentColor.getAlpha());
               GL11.glVertex3f((float)this.x, (float)this.y - 1.5F, 0.0F);
               GL11.glVertex3f((float)(this.x + this.width), (float)this.y - 1.5F, 0.0F);
               GL11.glVertex3f((float)(this.x + this.width), (float)(this.y + this.height) + totalItemHeight, 0.0F);
               GL11.glVertex3f((float)this.x, (float)(this.y + this.height) + totalItemHeight, 0.0F);
               GL11.glEnd();
               GlStateManager.shadeModel(7424);
               GlStateManager.disableBlend();
               GlStateManager.enableAlpha();
               GlStateManager.enableTexture2D();
            }
         }
      }

      Cracked.textManager.drawStringWithShadow(this.getName(), (float)this.x + 3.0F, (float)this.y - 4.0F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), -1);
      
      /*----------------------------------------------FutureArrow----------------------------------- */
        if (!open) {
            if (this.angle > 0) {
                this.angle -= 6;
                }
                } else if (this.angle < 180) {
                    this.angle += 6;
                    }

    if (GuiFuture.getInstance().future.getValue()) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        //RenderUtilic.glColor(new Color(255, 255, 255, 255)); //remove glColor :(
        minecraft.getTextureManager().bindTexture(new ResourceLocation("textures/arrow.png"));
        GlStateManager.translate(getX() + getWidth() - 7, (getY() + 6) - 0.3F, 0.0F);
        GlStateManager.rotate(calculateRotation(angle), 0.0F, 0.0F, 1.0F);
        RenderUtilic.drawModalRect(-5, -5, 0.0F, 0.0F, 10, 10, 10, 10, 10.0F, 10.0F);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
/*--------------------------------------------------------------------------------------------- */
      
      
      if (this.open) {
         float y = this.getY() + this.getHeight() - 3.0f;
         for (final Item item2 : this.getItems()) {
             if (item2.isHidden()) {
                 continue;
             }
             item2.setLocation(this.x + 2.0f, y);
             item2.setWidth(this.getWidth() - 4);
             item2.drawScreen(mouseX, mouseY, partialTicks);
             y += item2.getHeight() + 1.5f;
         }
     }

   }

    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.x2 = this.x - mouseX;
            this.y2 = this.y - mouseY;
            FutureNewGui.getClickGuiFutureX().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
                return;
            });
            this.drag = true;
            return;
        }
        if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
            this.open = !this.open;
            Component.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
    }

   public void mouseReleased(final int mouseX, final int mouseY, final int releaseButton) {
      if (releaseButton == 0) {
          this.drag = false;
      }
      if (!this.open) {
          return;
      }
      this.getItems().forEach(item -> item.mouseReleased(mouseX, mouseY, releaseButton));
  }
  
  public void onKeyTyped(final char typedChar, final int keyCode) {
      if (!this.open) {
          return;
      }
      this.getItems().forEach(item -> item.onKeyTyped(typedChar, keyCode));
  }
  
  public void addButton(final Button button) {
      this.items.add((Button)button);
  }
  
  public int getX() {
      return this.x;
  }
  
  public void setX(final int x) {
      this.x = x;
  }
  
  public int getY() {
      return this.y;
  }
  
  public void setY(final int y) {
      this.y = y;
  }
  
  public int getWidth() {
      return this.width;
  }
  
  public void setWidth(final int width) {
      this.width = width;
  }
  
  public int getHeight() {
      return this.height;
  }
  
  public void setHeight(final int height) {
      this.height = height;
  }
  
  public boolean isHidden() {
      return this.hidden;
  }
  
  public void setHidden(final boolean hidden) {
      this.hidden = hidden;
  }
  
  public boolean isOpen() {
      return this.open;
  }
  
  public final ArrayList<Item> getItems() {
      return this.items;
  }
  
  private boolean isHovering(final int mouseX, final int mouseY) {
      return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
  }

  /*----------------------------------------------FutureArrowFix----------------------------------- */
        //added this method in, just to fix shit. It is from uz1 class in future
        public static float calculateRotation(float var0) {
            if ((var0 %= 360.0F) >= 180.0F) {
                var0 -= 360.0F;
            }
    
            if (var0 < -180.0F) {
                var0 += 360.0F;
            }
    
            return var0;
        }
/*--------------------------------------------------------------------------------------------- */

  private float getTotalItemHeight() {
   float height = 0.0f;
   for (final Item item : this.getItems()) {
       height += item.getHeight() + 1.5f;
   }
   return height;
}
}
