//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.gui.components;

import net.minecraft.util.*;
import org.lwjgl.opengl.*;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.*;
import me.akaishin.cracked.features.future.gui.*;
import me.akaishin.cracked.features.future.gui.components.items.*;
import me.akaishin.cracked.features.future.gui.components.items.buttons.Button;
import me.akaishin.cracked.features.future.gui.futureutils.RenderUtilic;
import me.akaishin.cracked.features.modules.client.ClickGui;
import me.akaishin.cracked.features.modules.client.Colors;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.util.rendergui.element.*;
import me.akaishin.cracked.util.rendergui.ColorUtil;
import me.akaishin.cracked.util.rendergui.MathUtil;
import me.akaishin.cracked.util.rendergui.RenderUtil;

import java.awt.*;
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
        //add outline rgb custom
        private void drawOutline(float thickness, int color) {
        float totalItemHeight = 0.0f;
        if (this.open) {
            totalItemHeight = this.getTotalItemHeight() - 2.0f;
        }
        RenderUtil.drawLine(this.x, (float)this.y - 1.5f, this.x, (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x, (float)this.y - 1.5f, this.x + this.width, (float)this.y - 1.5f, thickness, ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x + this.width, (float)this.y - 1.5f, this.x + this.width, (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x, (float)(this.y + this.height) + totalItemHeight, this.x + this.width, (float)(this.y + this.height) + totalItemHeight, thickness, ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drag(mouseX, mouseY);
        final float totalItemHeight = this.open ? (this.getTotalItemHeight() - 2.0f) : 0.0f;
        int color = -7829368;
        if (ClickGui.getInstance().devSettings.getValue()) {
         color = (Boolean)ClickGui.getInstance().colorSync.getValue() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB((Integer)ClickGui.getInstance().topRed.getValue(), (Integer)ClickGui.getInstance().topGreen.getValue(), (Integer)ClickGui.getInstance().topBlue.getValue(), (Integer)ClickGui.getInstance().topAlpha.getValue());
        }

        if ((Boolean)ClickGui.getInstance().rainbowRolling.getValue() && (Boolean)ClickGui.getInstance().colorSync.getValue()  && (Boolean)Colors.INSTANCE.rainbow.getValue()) {
            RenderUtil.drawGradientRect((float)this.x, this.y - 1.5f, (float)this.width, (float)(this.height - 4), HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)), HUD.getInstance().colorMap.get(MathUtil.clamp(this.y + this.height - 4, 0, this.renderer.scaledHeight)));
        }
        else {
            RenderUtil.drawRect((float)this.x, this.y - 1.5f, (float)(this.x + this.width), (float)(this.y + this.height - 6), color);
        }
        if (ClickGui.getInstance().frameSettings.getValue()) {
            final int n;
            color = (n = (ClickGui.getInstance().isEnabled() ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB(ClickGui.getInstance().frameRed.getValue(), ClickGui.getInstance().frameGreen.getValue(), ClickGui.getInstance().frameBlue.getValue(), ClickGui.getInstance().frameAlpha.getValue())));
            RenderUtil.drawRect((float)this.x, this.y + 11.0f, (float)(this.x + this.width), (float)(this.y + this.height - 6), ((boolean)ClickGui.getInstance().colorSync.getValue()) ? Colors.INSTANCE.getCurrentColor().getRGB() : ColorUtil.toARGB(ClickGui.getInstance().frameRed.getValue(), ClickGui.getInstance().frameGreen.getValue(), ClickGui.getInstance().frameBlue.getValue(), ClickGui.getInstance().frameAlpha.getValue()));
        }
        if (this.open) {
            RenderUtil.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.y + this.height + totalItemHeight, ColorUtil.toARGB(10, 10, 10, ClickGui.getInstance().backgroundAlpha.getValue()));
            //add outline new rgb custom
            if (ClickGui.getInstance().outlineNew.getValue().booleanValue()) {
                this.drawOutline(ClickGui.getInstance().outlineThickness.getValue().floatValue(), color);
            }
            
            if (ClickGui.getInstance().outline.getValue()) {
                /*if (ClickGui.getInstance().rainbowRolling.getValue()) {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel(7425);
                    GL11.glBegin(1);
                    Color currentColor = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)));
                    GL11.glColor4f(currentColor.getRed() / 255.0f, currentColor.getGreen() / 255.0f, currentColor.getBlue() / 255.0f, currentColor.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    float currentHeight = this.getHeight() - 1.5f;
                    for (final Item item : this.getItems()) {
                        currentColor = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + (currentHeight += item.getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f(currentColor.getRed() / 255.0f, currentColor.getGreen() / 255.0f, currentColor.getBlue() / 255.0f, currentColor.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)this.x, this.y + currentHeight, 0.0f);
                        GL11.glVertex3f((float)this.x, this.y + currentHeight, 0.0f);
                    }
                    currentColor = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + this.height + totalItemHeight), 0, this.renderer.scaledHeight)));
                    GL11.glColor4f(currentColor.getRed() / 255.0f, currentColor.getGreen() / 255.0f, currentColor.getBlue() / 255.0f, currentColor.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + totalItemHeight, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + totalItemHeight, 0.0f);
                    for (final Item item : this.getItems()) {
                        currentColor = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)(this.y + (currentHeight -= item.getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f(currentColor.getRed() / 255.0f, currentColor.getGreen() / 255.0f, currentColor.getBlue() / 255.0f, currentColor.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + currentHeight, 0.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + currentHeight, 0.0f);
                    }
                    GL11.glVertex3f((float)(this.x + this.width), (float)this.y, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel(7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }*/
                //else {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel(7425);
                    GL11.glBegin(2);
                    final Color outlineColor = ClickGui.getInstance().colorSync.getValue() ? new Color(Colors.INSTANCE.getCurrentColorHex()) : new Color(Cracked.colorManager.getColorAsIntFullAlpha());
                    GL11.glColor4f((float)outlineColor.getRed(), (float)outlineColor.getGreen(), (float)outlineColor.getBlue(), (float)outlineColor.getAlpha());
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + totalItemHeight, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y + this.height + totalItemHeight, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel(7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                //}
            }
        }
        Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 3.0f, this.y - 4.0f - CrackedHackGui.getClickGui().getTextOffset(), -1);
        

/*----------------------------------------------FutureArrow----------------------------------- */
        if (!open) {
            if (this.angle > 0) {
                this.angle -= 6;
                }
                } else if (this.angle < 180) {
                    this.angle += 6;
                    }

    if (ClickGui.getInstance().future.getValue()) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        //RenderUtilic.glColor(new Color(255, 255, 255, 255));
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
            CrackedHackGui.getClickGui().getComponents().forEach(component -> {
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
