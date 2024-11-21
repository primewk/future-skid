//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.futuregui.components;

import net.minecraft.util.*;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.*;
import me.akaishin.cracked.features.future.futuregui.FutureGuiOpen;
import me.akaishin.cracked.features.future.futuregui.components.items.*;
import me.akaishin.cracked.features.future.futuregui.components.items.buttons.Button;
import me.akaishin.cracked.util.futureutils.RenderUtil;
import me.akaishin.cracked.features.modules.client.FutureGui;

import me.akaishin.cracked.features.future.gui.futureutils.RenderUtilic; //future gear arrow import
import me.akaishin.cracked.util.rendergui.element.*; //elements etc

import java.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class Component extends Feature {
    private final ArrayList<Item> items;
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
    
    public Component(final String name, final int x, final int y, final boolean open) {
        super(name);
        this.items = new ArrayList<Item>();
        this.hidden = false;
        this.x = x;
        this.y = y;
        this.angle = 180; //Future Ext
        this.width = 95;
        this.height = 18;
        this.open = open;
        this.setupItems();
    }
    
    public void setupItems() {
    }
    
    private void drag(final int mouseX, final int mouseY) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + mouseX;
        this.y = this.y2 + mouseY;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drag(mouseX, mouseY);
        final float totalItemHeight = this.open ? (this.getTotalItemHeight() - 2.0f) : 0.0f;
         int color = -7829368;
         int argb = 0;
        if (this.open) {
            //Render
            RenderUtil.Method510((float)this.x, this.y + 14.0f, (float)(this.x + this.width), this.y + this.height + totalItemHeight, 0);
        }
        //Import init color manager etc add alpha
             int n = color = Cracked.colorFutureClient.getColorWithAlpha(((Integer)((FutureGui)Cracked.moduleManager.getModuleByClass(FutureGui.class)).hoverAlpha.getValue()).intValue());
   
            RenderUtil.Method510(this.x, this.y - 1.5F, (this.x + this.width), (this.y + this.height - 6), color);
            if (this.open)
            {
               RenderUtil.Method510(this.x, this.y + 12.5F, (this.x + this.width), (this.y + this.height) + totalItemHeight, 1996488704);
               }
             if (this.open) {
                 RenderUtil.Method510(this.x, this.y + 12.5F, (this.x + this.width), (this.y + this.height) + totalItemHeight, 0);
            }     
             Cracked.textManager.drawStringWithShadow(getName(), this.x + 3.0F, this.y - 4.0F - FutureGuiOpen.getFutureGuiPro().getTextOffset(), -1);

/*----------------------------------------------FutureArrow----------------------------------- */
        if (!open) {
            if (this.angle > 0) {
                this.angle -= 6;
                }
                } else if (this.angle < 180) {
                    this.angle += 6;
                    }

    if (FutureGui.getInstance().future.getValue()) {
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
            //remove?????
            //ModuleButton.drawCompleteImage(this.x - 1.5F + this.height - 12.0F, this.y - 5.0F - FutureGuiOpen.getFutureGuiPro().getTextOffset(), 12, 11);
            //add texture arrow si se buega
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
            FutureGuiOpen.getFutureGuiPro().getComponents().forEach(component -> {
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
            IUtil.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
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
        this.items.add(button);
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
