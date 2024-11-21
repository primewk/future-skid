//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.futuregui.components.items.buttons;

import net.minecraft.init.*;
import net.minecraft.client.audio.*;

import java.util.*;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.future.futuregui.*;
import me.akaishin.cracked.features.future.futuregui.components.*;
import me.akaishin.cracked.features.future.futuregui.components.items.*;
import me.akaishin.cracked.features.modules.client.FutureGui;
import me.akaishin.cracked.util.futureutils.*;

public class Button extends Item
{
    private boolean state;
    
    public Button(final String name) {
        super(name);
        this.height = 15;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.Method510(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Cracked.colorFutureClient.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGui.class).alpha.getValue()) : Cracked.colorFutureClient.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(FutureGui.class).hoverAlpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? Cracked.colorFutureClient.getColorWithAlpha(30) : -2007673515 ));
        Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - FutureGuiOpen.getFutureGuiPro().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.onMouseClick();
        }
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_NOTE_HARP, 1.0f));
    }
    
    public void toggle() {
    }
    
    public boolean getState() {
        return this.state;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : FutureGuiOpen.getFutureGuiPro().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
}
