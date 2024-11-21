//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.buttons;

import java.util.*;
import org.lwjgl.input.*;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.components.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.utils.RenderMethods;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;
import me.akaishin.cracked.features.setting.*;


public class Slider extends Button
{
    public Setting setting;
    private final Number min;
    private final Number max;
    private final int difference;
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = (Number) setting.getMin();
        this.max = (Number) setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 14;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.dragSetting(mouseX, mouseY);
        RenderMethods.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077);
        RenderMethods.drawRect(this.x, this.y, (((Number) this.setting.getValue()).floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).alpha.getValue()) : Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).hoverAlpha.getValue()));
        Cracked.textManager.drawStringWithShadow(this.getName() + " \u00A77" + ((this.setting.getValue() instanceof Float) ? this.setting.getValue() : Double.valueOf(((Number) this.setting.getValue()).doubleValue())), this.x + 2.3f, this.y - 1.7f - FutureGuiExeter.getFutureGuiOld().getTextOffset(), -1);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : FutureGuiExeter.getFutureGuiOld().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() + 8.0f && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private void dragSetting(final int mouseX, final int mouseY) {
        if (this.isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(mouseX);
        }
    }
    
    public int getHeight() {
        return 14;
    }
    
    private void setSettingFromX(final int mouseX) {
        final float percent = (mouseX - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            final double result = (Double) this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0 * result) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            final float result2 = (Float) this.setting.getMin() + this.difference * percent;
            this.setting.setValue(Math.round(10.0f * result2) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((Integer) this.setting.getMin() + (int)(this.difference * percent));
        }
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    private float part() {
        return ((Number) this.setting.getValue()).floatValue() - this.min.floatValue();
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}
