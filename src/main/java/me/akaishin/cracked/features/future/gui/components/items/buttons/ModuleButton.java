//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.future.gui.components.items.buttons;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.future.gui.*;
import me.akaishin.cracked.features.future.gui.components.items.Item;
import me.akaishin.cracked.features.future.gui.futureutils.RenderUtilic;
import me.akaishin.cracked.features.modules.Module;

import me.akaishin.cracked.features.modules.client.ClickGui;
import me.akaishin.cracked.features.setting.*;
import me.akaishin.cracked.util.rendergui.element.*;
import me.akaishin.cracked.util.rendergui.*;

public class ModuleButton extends Button
{
    private final Module module;
    private List<Item> items;
    private boolean subOpen;
    private int progress; //future gear option
    
    public ModuleButton(final Module module) {
        super(module.getName());
        this.items = new ArrayList<Item>();
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
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (!this.items.isEmpty()) {
            final ClickGui gui = Cracked.moduleManager.getModuleByClass(ClickGui.class);
            Cracked.textManager.drawStringWithShadow(((boolean)gui.openCloseChange.getValue()) ? (this.subOpen ? gui.close.getValue() : gui.open.getValue()) : gui.moduleButton.getValue(), this.x - 1.5f + this.width - 7.4f, this.y - 2.0f - CrackedHackGui.getClickGui().getTextOffset(), -1);
        }

        if (ClickGui.getInstance().future.getValue()) {
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
        if (this.isHovering(mouseX, mouseY)) {
            RenderUtil.drawRect((float)(mouseX + 10), (float)mouseY, (float)(mouseX + 10 + this.renderer.getStringWidth(this.module.getDescription())), (float)(mouseY + 10), ColorUtil.toRGBA(0, 0, 0, 50));
            RenderUtil.drawBorder((float)(mouseX + 10), (float)mouseY, (float)this.renderer.getStringWidth(this.module.getDescription()), 10.0f, new Color(ColorUtil.toRGBA(ClickGui.getInstance().newred.getCurrentState(), ClickGui.getInstance().newgreen.getCurrentState(), ClickGui.getInstance().newblue.getCurrentState(), 0)));
            this.renderer.drawStringWithShadow(this.module.getDescription(), (float)(mouseX + 10), (float)mouseY, -1);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (!this.items.isEmpty()) {
            if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
                this.subOpen = !this.subOpen;
                IUtil.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
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
