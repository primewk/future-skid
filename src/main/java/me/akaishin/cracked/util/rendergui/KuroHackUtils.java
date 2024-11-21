//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.util.rendergui;

import me.akaishin.cracked.util.rendergui.element.*;
import me.akaishin.cracked.util.rendergui.*;
import java.awt.Color;
import java.util.Objects;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;


public class KuroHackUtils implements IUtil {
    
    public static Quad renderSkeetBox(final Quad quad) {
        RenderUtil.drawBox(quad, new Color(25, 26, 26, 255));
        quad.shrink(0.5f);
        RenderUtil.drawBox(quad, new Color(45, 45, 45, 255));
        quad.shrink(0.5f);
        RenderUtil.drawBox(quad, new Color(51, 51, 51, 255));
        quad.shrink(0.5f);
        RenderUtil.drawBox(quad, new Color(40, 40, 40, 255));
        quad.shrink(1.5f);
        RenderUtil.drawBox(quad, new Color(51, 51, 51, 255));
        quad.shrink(0.5f);
        RenderUtil.drawBox(quad, new Color(45, 45, 45, 255));
        quad.shrink(0.5f);
        RenderUtil.drawBox(quad, new Color(16, 16, 16, 255));
        final Quad rainbow = new Quad(quad.getX(), quad.getY(), quad.getX1(), quad.getY() + 0.5f);
        final Quad rainbow2 = new Quad(quad.getX(), quad.getY() + 0.5f, quad.getX1(), quad.getY() + 1.0f);
        RenderUtil.drawRainbowX(rainbow, 193.0f, 1.0f, 0.87f, 1.0f, 1.0f, 255);
        RenderUtil.drawRainbowX(rainbow2, 193.0f, 1.0f, 0.5f, 1.0f, 1.0f, 255);
        return quad;
    }


    public static void drawBox(final BlockPos pos, final Color color) {
        final AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, pos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, pos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, pos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, pos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, pos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + RenderUtil.mc.getRenderManager().viewerPosX, bb.minY + RenderUtil.mc.getRenderManager().viewerPosY, bb.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, bb.maxX + RenderUtil.mc.getRenderManager().viewerPosX, bb.maxY + RenderUtil.mc.getRenderManager().viewerPosY, bb.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(bb, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

}
