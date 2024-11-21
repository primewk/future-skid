//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.components.items.buttons;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.MathUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class BooleanButton extends Button {
   private final Setting setting;

   public BooleanButton(Setting setting) {
      super(setting.getName());
      this.setting = setting;
      this.width = 13;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue()) {
         int color = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         int color1 = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         RenderUtil.drawGradientRect(this.x, this.y, (float)this.width + 7.4F, (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : color) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515), this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)) : color1) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
      } else {
         RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4F, this.y + (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue()) : Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
      }

      Cracked.textManager.drawStringWithShadow(this.getName(), this.x + 2.3F, this.y - 1.7F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), this.getState() ? -1 : -5592406);
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
