//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.components.items.buttons;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.components.Component;
import me.akaishin.cracked.features.future.guinew.gui.components.items.Item;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.MathUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.modules.hud.HUD;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class Button extends Item {
    private boolean state;

   public Button(String name) {
      super(name);
      this.height = 15;
   }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if ((Boolean)GuiFuture.getInstance().moduleOutline.getValue()) {
            RenderUtil.drawOutlineRect(this.x, this.y, this.x + (float)this.width, this.y + (float)this.height - 0.9F, this.getState() ? ColorUtil.toRGBA((Integer)GuiFuture.getInstance().moRed.getValue(), (Integer)GuiFuture.getInstance().moGreen.getValue(), (Integer)GuiFuture.getInstance().moBlue.getValue(), (Integer)GuiFuture.getInstance().moAlpha.getValue()) : ColorUtil.toRGBA((Integer)GuiFuture.getInstance().moRed.getValue(), (Integer)GuiFuture.getInstance().moGreen.getValue(), (Integer)GuiFuture.getInstance().moBlue.getValue(), (Integer)GuiFuture.getInstance().moAlpha.getValue()));
         }

         if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue()) {
            int color = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
            int color1 = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (float)this.width, (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : color) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515), this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)) : color1) : (!this.isHovering(mouseX, mouseY) ? ColorUtil.toRGBA((Integer)GuiFuture.getInstance().d_red.getValue(), (Integer)GuiFuture.getInstance().d_green.getValue(), (Integer)GuiFuture.getInstance().d_blue.getValue(), (Integer)GuiFuture.getInstance().d_alpha.getValue()) : -2007673515));
         } else {
            RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width, this.y + (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue()) : Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? ColorUtil.toRGBA((Integer)GuiFuture.getInstance().d_red.getValue(), (Integer)GuiFuture.getInstance().d_green.getValue(), (Integer)GuiFuture.getInstance().d_blue.getValue(), (Integer)GuiFuture.getInstance().d_alpha.getValue()) : -2007673515));
         }
   
         Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 2.3F, this.y - 2.0F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), this.getState() ? ColorUtil.toRGBA((Integer)GuiFuture.getInstance().textRed.getValue(), (Integer)GuiFuture.getInstance().textGreen.getValue(), (Integer)GuiFuture.getInstance().textBlue.getValue(), (Integer)GuiFuture.getInstance().textAlpha.getValue()) : ColorUtil.toRGBA((Integer)GuiFuture.getInstance().textRed2.getValue(), (Integer)GuiFuture.getInstance().textGreen2.getValue(), (Integer)GuiFuture.getInstance().textBlue2.getValue(), (Integer)GuiFuture.getInstance().textAlpha2.getValue()));
      }

 /*        if (GuiFuture.getInstance().rainbowRolling.getValue()) {
            final int color = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), Cracked.moduleManager.getModuleByClass(GuiFuture.class).hoverAlpha.getValue());
            final int color2 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), Cracked.moduleManager.getModuleByClass(GuiFuture.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (float)this.width, this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? color : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight))) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(mouseX, mouseY) ? color2 : HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight))) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(mouseX, mouseY) ? Cracked.colorManagerf.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(GuiFuture.class).alpha.getValue()) : Cracked.colorManagerf.getColorWithAlpha(Cracked.moduleManager.getModuleByClass(GuiFuture.class).hoverAlpha.getValue())) : (this.isHovering(mouseX, mouseY) ? -2007673515 : 290805077));
        }
        Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - FutureNewGui.getClickGuiFutureX().getTextOffset(), this.getState() ? -1 : -5592406);
    }*/

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
        for (final Component component : FutureNewGui.getClickGuiFutureX().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.height;
    }
}
