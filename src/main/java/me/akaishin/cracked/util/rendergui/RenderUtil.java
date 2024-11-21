//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.util.rendergui;

import net.minecraft.client.renderer.culling.*;
import java.awt.*;
import net.minecraft.client.renderer.vertex.*;
import me.akaishin.cracked.util.rendergui.*;
import me.akaishin.cracked.util.rendergui.element.IUtil;
import me.akaishin.cracked.util.rendergui.element.Quad;
import me.akaishin.cracked.util.rendergui.element.Rainbow;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;

import org.lwjgl.opengl.*;
public class RenderUtil implements IUtil {
    public static RenderItem itemRender;
    public static ICamera camera;
    private static final Tessellator tessellator;
    private static final BufferBuilder builder;



    //Clickgui Render --------------------------------------------------------------------------------
        public static void drawRectCol(final float x, final float y, final float width, final float height, final Color color) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    

    public static void drawBorder(final float x, final float y, final float width, final float height, final Color color) {
        drawRectCol(x - 1.0f, y - 1.0f, 1.0f, height + 2.0f, color);
        drawRectCol(x + width, y - 1.0f, 1.0f, height + 2.0f, color);
        drawRectCol(x, y - 1.0f, width, 1.0f, color);
        drawRectCol(x, y + height, width, 1.0f, color);
    }


    public static void drawGradientRect(final float x, final float y, final float w, final float h, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos(x + (double)w, (double)y, 0.0).color(f2, f3, f4, f).endVertex();
        vertexbuffer.pos((double)x, (double)y, 0.0).color(f2, f3, f4, f).endVertex();
        vertexbuffer.pos((double)x, y + (double)h, 0.0).color(f6, f7, f8, f5).endVertex();
        vertexbuffer.pos(x + (double)w, y + (double)h, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }


    public static void drawRect(final float x, final float y, final float w, final float h, final int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        final float red = (color >> 16 & 0xFF) / 255.0f;
        final float green = (color >> 8 & 0xFF) / 255.0f;
        final float blue = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)x, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)h, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)w, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }



            //Particles Render ---------------------------------------------------------------------
        public static void drawLine(final float x, final float y, final float x1, final float y1, final float thickness, final int hex) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(thickness);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)x, (double)y, 0.0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos((double)x1, (double)y1, 0.0).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
       

        //none -------------------------------
        public static void drawRectangleCorrectly(final int x, final int y, final int w, final int h, final int color) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(x, y, x + w, y + h, color);
    }


        //KuroGui Utils
    public static void drawRainbowX(final Quad quad, final float hue, final float sat, final float bright, final float speed, final int alpha) {
        drawRainbowX(quad, hue, sat, bright, speed, 0.5f, alpha);
    }
    
    public static void drawRainbowX(final Quad quad, final float hue, final float sat, final float bright, final float speed, final float pixelSpeed, final int alpha) {
        final Rainbow rainbow = new Rainbow();
        rainbow.update(hue);
        for (float a = quad.getX() + pixelSpeed; a <= quad.getX1(); a += pixelSpeed) {
            rainbow.update(speed);
            final Color color = rainbow.getColor(0.0f, sat, bright);
            drawBox(new Quad(a - pixelSpeed, quad.getY(), a, quad.getY1()), alpha(alpha, color));
        }
    }

        //Texture Render Inventory -------------------------------------------------------------------------------------
    public static void drawTexturedRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height, final int zLevel) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder2 = tessellator.getBuffer();
        BufferBuilder2.begin(7, DefaultVertexFormats.POSITION_TEX);
        BufferBuilder2.pos((double)x, (double)(y + height), (double)zLevel).tex((double)(textureX * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)(x + width), (double)y, (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)(textureY * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)x, (double)y, (double)zLevel).tex((double)(textureX * 0.00390625f), (double)(textureY * 0.00390625f)).endVertex();
        tessellator.draw();
    }




    public static void drawBox(final Quad quad, final Color color) {
        setup();
        final double x = Math.min(quad.getX(), quad.getX1());
        final double y = Math.min(quad.getY(), quad.getY1());
        final double x2 = Math.max(quad.getX(), quad.getX1());
        final double y2 = Math.max(quad.getY(), quad.getY1());
        RenderUtil.builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil.builder.pos(x2, y, 0.0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.builder.pos(x, y, 0.0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.builder.pos(x, y2, 0.0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.builder.pos(x2, y2, 0.0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil.tessellator.draw();
        end();
    }
    private static void setup() {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }
    private static void end() {
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }
        static {
        RenderUtil.itemRender = RenderUtil.mc.getRenderItem();
        RenderUtil.camera = (ICamera)new Frustum();
        tessellator = Tessellator.getInstance();
        builder = RenderUtil.tessellator.getBuffer();
    }


    public static Color alpha(final int alpha, final Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }



    
}
