//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.components.items.buttons;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.util.ColorUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.MathUtil;
import me.akaishin.cracked.features.future.guinew.gui.util.RenderUtil;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.setting.Setting;

public class StringButton extends Button {
   private final Setting setting;
   public boolean isListening;
   private CurrentString currentString;

   public StringButton(Setting setting) {
      super(setting.getName());
      this.currentString = new CurrentString("");
      this.setting = setting;
      this.width = 13;
   }

   public static String removeLastChar(final String str) {
      String output = "";
      if (str != null && str.length() > 0) {
          output = str.substring(0, str.length() - 1);
      }
      return output;
  }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if ((Boolean)GuiFuture.getInstance().rainbowRolling.getValue()) {
         int color = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         int color1 = ColorUtil.changeAlpha((Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), (Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue());
         RenderUtil.drawGradientRect(this.x, this.y, (float)this.width + 7.4F, (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : color) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515), this.getState() ? (!this.isHovering(mouseX, mouseY) ? (Integer)HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)) : color1) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
      } else {
         RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4F, this.y + (float)this.height - 0.5F, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).hoverAlpha.getValue()) : Cracked.colorManagerf.getColorWithAlpha((Integer)((GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class)).alpha.getValue())) : (!this.isHovering(mouseX, mouseY) ? 290805077 : -2007673515));
      }

      if (this.isListening) {
         Cracked.textManager.drawStringWithShadow(this.currentString.getString() + Cracked.textManager.getIdleSign(), this.x + 2.3F, this.y - 1.7F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), this.getState() ? -1 : -5592406);
      } else {
         Cracked.textManager.drawStringWithShadow((this.setting.shouldRenderName() ? this.setting.getName() + " \u00a77" : "") + this.setting.getValue(), this.x + 2.3F, this.y - 1.7F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), this.getState() ? -1 : -5592406);
      }

   }

    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_NOTE_HARP, 1.0f));
        }
    }
    
    public void onKeyTyped(final char typedChar, final int keyCode) {
        if (this.isListening) {
            if (keyCode == 1) {
                return;
            }
            if (keyCode == 28) {
                this.enterString();
            }
            else if (keyCode == 14) {
                this.setString(removeLastChar(this.currentString.getString()));
            }
            else {
                Label_0122: {
                    if (keyCode == 47) {
                        if (!Keyboard.isKeyDown(157)) {
                            if (!Keyboard.isKeyDown(29)) {
                                break Label_0122;
                            }
                        }
                        try {
                            this.setString(this.currentString.getString() + Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
                if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                    this.setString(this.currentString.getString() + typedChar);
                }
            }
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setValue(this.setting.getDefaultValue());
        }
        else {
            this.setting.setValue(this.currentString.getString());
        }
        this.setString("");
        super.onMouseClick();
    }
    
    public int getHeight() {
        return 14;
    }
    
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    public boolean getState() {
        return !this.isListening;
    }
    
    public void setString(final String newString) {
        this.currentString = new CurrentString(newString);
    }
    
    public static class CurrentString
    {
        private final String string;
        
        public CurrentString(final String string) {
            this.string = string;
        }
        
        public String getString() {
            return this.string;
        }
    }
}
