//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.buttons;

import net.minecraft.init.*;
import me.akaishin.cracked.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.utils.RenderMethods;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;
import me.akaishin.cracked.features.setting.*;
import net.minecraft.client.audio.*;

public class UnlimitedSlider extends Button
{
    public Setting setting;
    
    public UnlimitedSlider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 14;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
            RenderMethods.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(mouseX, mouseY) ? Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).alpha.getValue()) : Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).hoverAlpha.getValue()));
        Cracked.textManager.drawStringWithShadow(" - " + this.setting.getName() + " \u00A77" + this.setting.getValue() + "\u00A7r +", this.x + 2.3f, this.y - 1.7f - FutureGuiExeter.getFutureGuiOld().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_NOTE_HARP, 1.0f));
            if (this.isRight(mouseX)) {
                if (this.setting.getValue() instanceof Double) {
                    this.setting.setValue((Double) this.setting.getValue() + 1.0);
                }
                else if (this.setting.getValue() instanceof Float) {
                    this.setting.setValue((Float) this.setting.getValue() + 1.0f);
                }
                else if (this.setting.getValue() instanceof Integer) {
                    this.setting.setValue((Integer) this.setting.getValue() + 1);
                }
            }
            else if (this.setting.getValue() instanceof Double) {
                this.setting.setValue((Double) this.setting.getValue() - 1.0);
            }
            else if (this.setting.getValue() instanceof Float) {
                this.setting.setValue((Float) this.setting.getValue() - 1.0f);
            }
            else if (this.setting.getValue() instanceof Integer) {
                this.setting.setValue((Integer) this.setting.getValue() - 1);
            }
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public int getHeight() {
        return 14;
    }
    
    public void toggle() {
    }
    
    public boolean getState() {
        return true;
    }
    
    public boolean isRight(final int x) {
        return x > this.x + (this.width + 7.4f) / 2.0f;
    }
}
