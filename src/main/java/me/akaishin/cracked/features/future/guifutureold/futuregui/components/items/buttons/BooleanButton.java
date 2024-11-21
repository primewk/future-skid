//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.guifutureold.futuregui.components.items.buttons;

import net.minecraft.init.*;
import me.akaishin.cracked.*;
import me.akaishin.cracked.features.future.guifutureold.futuregui.FutureGuiExeter;
import me.akaishin.cracked.features.future.guifutureold.futuregui.utils.RenderMethods;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;
import me.akaishin.cracked.features.setting.*;

import net.minecraft.client.audio.*;

public class BooleanButton extends Button
{
    private final Setting setting;
    
    public BooleanButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 14;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        RenderMethods.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).alpha.getValue()) : Cracked.colorExeterGuiManager.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGuiB8.class).hoverAlpha.getValue())) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077));
        Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 1.7f - FutureGuiExeter.getFutureGuiOld().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_NOTE_HARP, 1.0f));
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public void toggle() {
        this.setting.setValue(Boolean.valueOf(!((Boolean)this.setting.getValue()).booleanValue()));
    }
    
    @Override
    public boolean getState() {
        return ((Boolean)this.setting.getValue()).booleanValue();
    }
}
