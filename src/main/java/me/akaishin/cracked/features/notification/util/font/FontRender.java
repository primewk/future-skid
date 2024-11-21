package me.akaishin.cracked.features.notification.util.font;

import me.akaishin.cracked.features.notification.util.Util;
import me.akaishin.cracked.Cracked;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class FontRender{
    public static boolean isCustomFontEnabled() {
        return true;
    }
	
	public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static float drawStringWithShadow(String text, float x, float y, int color) {
        return drawStringWithShadow(text, (int) x, (int) y, color);
    }

    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.depthMask((boolean) false);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
    }

    public static float drawString(String text, float x, float y, int color) {
        return drawString(text, (int) x, (int) y, color);
    }

    // ints

    public static float drawString5(String text, float x, float y, int color) {
            return Cracked.fontRenderer5.drawString(text, x, y, color);
    }

    public static float drawCentString5(String text, float x, float y, int color) {
        return Cracked.fontRenderer5.drawString(text, x - getStringWidth5(text) / 2f, y, color);
    }

    public static int getStringWidth5(String str) {
        return Cracked.fontRenderer5.getStringWidth(str);
    }
    public static int getFontHeight5() {
        return Cracked.fontRenderer5.getHeight() + 2;
    }

}