//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.hud;

import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import me.akaishin.cracked.event.events.*;
import me.akaishin.cracked.features.*;
import me.akaishin.cracked.features.modules.Module.Category;
import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.modules.client.*;
import me.akaishin.cracked.features.setting.*;
import me.akaishin.cracked.util.logo.*;
import me.akaishin.cracked.util.logo.*;
import net.minecraft.client.gui.*;

public class Logo extends Module
{
    public static final ResourceLocation mark;
    public Setting<Integer> imageX;
    public Setting<Integer> imageY;
    public Setting<Integer> imageWidth;
    public Setting<Integer> imageHeight;
    private int color;
    
    public Logo() {
        super("Logo", "Puts a logo there (there)", Category.HUD, false, false, false);
        this.imageX = (Setting<Integer>)this.register(new Setting("logoX", 0, (-700), 1200));//Test negative 
        this.imageY = (Setting<Integer>)this.register(new Setting("logoY", 0, (-700), 1200));//Test negative
        this.imageWidth = (Setting<Integer>)this.register(new Setting("logoWidth", 97, 0, 1000));
        this.imageHeight = (Setting<Integer>)this.register(new Setting("logoHeight", 97, 0, 1000));
    }
    
    public void renderLogo() {
        final int width = this.imageWidth.getValue();
        final int height = this.imageHeight.getValue();
        final int x = this.imageX.getValue();
        final int y = this.imageY.getValue();
        IUtil.mc.renderEngine.bindTexture(Logo.mark);
        GlStateManager.color(255.0f, 255.0f, 255.0f);
        Gui.drawScaledCustomSizeModalRect(x - 2, y - 36, 7.0f, 7.0f, width - 7, height - 7, width, height, (float)width, (float)height);
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (!Feature.fullNullCheck()) {
            final int width = this.renderer.scaledWidth;
            final int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
            if (this.enabled.getValue()) {
                this.renderLogo();
            }
        }
    }
    
    static {
        mark = new ResourceLocation("textures/logo.png");
    }
}
