//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.util.rendergui.element;

import java.awt.*;

public class Rainbow
{
    private float hue;
    
    public Rainbow() {
        this.hue = 0.0f;
    }
    
    public void update(final float val) {
        this.hue += val % 360.0f / 360.0f;
    }
    
    public Color getColor() {
        return new Color(Color.HSBtoRGB(this.hue, 1.0f, 1.0f));
    }
    
    public Color getColor(final float off) {
        return new Color(Color.HSBtoRGB((this.hue + off) % 360.0f, 1.0f, 1.0f));
    }
    
    public Color getColor(final float off, final float sat, final float bright) {
        return new Color(Color.HSBtoRGB((this.hue + off) % 360.0f, sat, bright));
    }
    
    public float getHue() {
        return this.hue;
    }
    
    public float getHueMultiplied() {
        return this.hue * 360.0f;
    }
    
    public void setHue(final float hue) {
        this.hue = hue;
    }
    
    private static float transform(final float max, final float val) {
        final float f0 = val / max;
        return f0 * 1.0f;
    }
    
    public static Color getColorStatic(final int alpha) {
        final Color color = new Color(Color.HSBtoRGB(transform(6500.0f, (float)(System.currentTimeMillis() % 6500L)), 1.0f, 1.0f));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static Color getColorStatic(final float off, final float speed, final int alpha) {
        return new Color(Color.HSBtoRGB(transform((float)(long)(6500.0f / speed), (float)((System.currentTimeMillis() + (long)off) % (long)(6500.0f / speed))), 1.0f, 1.0f));
    }
    
    public static Color getColorStatic(final float off, final float speed, final float sat, final float bright, final int alpha) {
        final Color color = new Color(Color.HSBtoRGB(transform((float)(long)(6500.0f / speed), (float)((System.currentTimeMillis() + (long)off) % (long)(6500.0f / speed))), sat, bright));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
