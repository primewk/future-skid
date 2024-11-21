package me.akaishin.cracked.features.future.guinew.gui.components.items.buttons;

import java.util.Iterator;

import org.lwjgl.input.Mouse;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.components.Component;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.MathUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.setting.Setting;

public class Slider extends Button {
   private final Number min;
   private final Number max;
   private final int difference;
   public Setting setting;

   public Slider(Setting setting) {
      super(setting.getName());
      this.setting = setting;
      this.min = (Number)setting.getMin();
      this.max = (Number)setting.getMax();
      this.difference = this.max.intValue() - this.min.intValue();
      this.width = 15;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.dragSetting(mouseX, mouseY);
      RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4F, this.y + (float)this.height - 0.5F, !this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515);
      if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue()) {
         int color = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         int color1 = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         RenderUtil.drawGradientRect(this.x, this.y, ((Number)this.setting.getValue()).floatValue() <= this.min.floatValue() ? 0.0F : ((float)this.width + 7.4F) * this.partialMultiplier(), (float)this.height - 0.5F, !this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : color, !this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : color1);
      } else {
         RenderUtil.drawRect(this.x, this.y, ((Number)this.setting.getValue()).floatValue() <= this.min.floatValue() ? this.x : this.x + ((float)this.width + 7.4F) * this.partialMultiplier(), this.y + (float)this.height - 0.5F, !this.isHovering(mouseX, mouseY) ? Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue()) : Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).alpha.getValue()));
      }

      Cracked.textManager.drawStringWithShadow(this.getName() + " \u00a77" + (this.setting.getValue() instanceof Float ? (Number)this.setting.getValue() : ((Number)this.setting.getValue()).doubleValue()), this.x + 2.3F, this.y - 1.7F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), -1);
   }

   public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.isHovering(mouseX, mouseY)) {
          this.setSettingFromX(mouseX);
      }
  }
    
    public boolean isHovering(final int mouseX, final int mouseY) {
        for (final Component component : FutureNewGui.getClickGuiFutureX().getComponents()) {
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
