//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.future.guinew.gui.components.items.buttons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.*;
import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.gui.futureutils.RenderUtilic;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.future.guinew.gui.components.items.Item;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.client.GuiFuture;
import me.akaishin.cracked.features.setting.Bind;
import me.akaishin.cracked.features.setting.Setting;

public class ModuleButton extends Button {
   private final Module module;
   private List<Item> items = new ArrayList();
   private boolean subOpen;
   private int progress; //future gear option

   public ModuleButton(Module module) {
      super(module.getName());
      this.module = module;
      this.progress = 0; //future gear = 0
      this.initSettings();
   }

   public void initSettings() {
    final ArrayList<Item> newItems = new ArrayList<Item>();
    if (!this.module.getSettings().isEmpty()) {
        for (final Setting setting : this.module.getSettings()) {
            if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled")) {
                newItems.add((Item)new BooleanButton(setting));
            }
            if (setting.getValue() instanceof Bind && !this.module.getName().equalsIgnoreCase("Hud")) {
                newItems.add((Item)new BindButton(setting));
            }
            if (setting.getValue() instanceof String || setting.getValue() instanceof Character) {
                newItems.add((Item)new StringButton(setting));
            }
            if (setting.isNumberSetting()) {
                if (setting.hasRestriction()) {
                    newItems.add((Item)new Slider(setting));
                    continue;
                }
                newItems.add((Item)new UnlimitedSlider(setting));
            }
            if (!setting.isEnumSetting()) {
                continue;
            }
            newItems.add((Item)new EnumButton(setting));
        }
    }
    this.items = newItems;
}

/*-----------------------FurtureGear------------------------ */
public static float calculateRotation(float var0) {
    if ((var0 %= 360.0F) >= 180.0F) {
        var0 -= 360.0F;
    }

    if (var0 < -180.0F) {
        var0 += 360.0F;
    }

    return var0;
}
/*---------------------------------------------------------- */



   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (!this.items.isEmpty()) {
         GuiFuture gui = (GuiFuture)Cracked.moduleManager.getModuleByClass(GuiFuture.class);
         Cracked.textManager.drawStringWithShadow((Boolean)gui.openCloseChange.getValue() ? (this.subOpen ? (String)gui.close.getValue() : (String)gui.open.getValue()) : (String)gui.moduleButton.getValue(), this.x - 1.5F + (float)this.width - 7.4F, this.y - 2.0F - (float)FutureNewGui.getClickGuiFutureX().getTextOffset(), -1);
            }

        if (GuiFuture.getInstance().future.getValue()) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
//            RenderMethods.glColor(new Color(0.0F, 0.0F, 100.0F, 1.0F));
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gear.png"));
            GlStateManager.translate(getX() + getWidth() - 6.7F, getY() + 7.7F - 0.3F, 0.0F);
            GlStateManager.rotate(calculateRotation((float)this.progress), 0.0F, 0.0F, 1.0F);
            RenderUtilic.drawModalRect(-5, -5, 0.0F, 0.0F, 10, 10, 10, 10, 10.0F, 10.0F);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }

         if (this.subOpen) {
            float height = 1.0f;
            ++progress;
            for (final Item item : this.items) {
                if (!item.isHidden()) {
                    item.setLocation(this.x + 1.0f, this.y + (height += 15.0f));
                    item.setHeight(15);
                    item.setWidth(this.width - 9);
                    item.drawScreen(mouseX, mouseY, partialTicks);
                }
                item.update();
            }
        }

   }

   public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
    super.mouseClicked(mouseX, mouseY, mouseButton);
    if (!this.items.isEmpty()) {
        if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
            this.subOpen = !this.subOpen;
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
        if (this.subOpen) {
            for (final Item item : this.items) {
                if (item.isHidden()) {
                    continue;
                }
                item.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
}

public void onKeyTyped(final char typedChar, final int keyCode) {
    super.onKeyTyped(typedChar, keyCode);
    if (!this.items.isEmpty() && this.subOpen) {
        for (final Item item : this.items) {
            if (item.isHidden()) {
                continue;
            }
            item.onKeyTyped(typedChar, keyCode);
        }
    }
}

public int getHeight() {
    if (this.subOpen) {
        int height = 14;
        for (final Item item : this.items) {
            if (item.isHidden()) {
                continue;
            }
            height += item.getHeight() + 1;
        }
        return height + 2;
    }
    return 14;
}

public Module getModule() {
    return this.module;
}

public void toggle() {
    this.module.toggle();
}

public boolean getState() {
    return this.module.isEnabled();
}
}
