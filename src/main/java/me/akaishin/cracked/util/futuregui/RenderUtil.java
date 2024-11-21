//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*      */ package me.akaishin.cracked.util.futuregui;
/*      */ import java.awt.Color;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Objects;
/*      */ import me.akaishin.cracked.Cracked;
/*      */ import me.akaishin.cracked.features.modules.client.Colors;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.model.ModelBiped;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.OpenGlHelper;
/*      */ import net.minecraft.client.renderer.RenderGlobal;
/*      */ import net.minecraft.client.renderer.RenderItem;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.culling.Frustum;
/*      */ import net.minecraft.client.renderer.culling.ICamera;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.shader.Framebuffer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.Vec2f;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.world.World;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.EXTFramebufferObject;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.util.glu.Disk;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import org.lwjgl.util.glu.Sphere;
/*      */ 
/*      */ public class RenderUtil implements Util {
/*   43 */   private static final Frustum frustrum = new Frustum();
/*   44 */   private static final FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
/*   45 */   private static final IntBuffer viewport = BufferUtils.createIntBuffer(16);
/*   46 */   private static final FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
/*   47 */   private static final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
/*   48 */   public static RenderItem itemRender = mc.getRenderItem();
/*   49 */   public static ICamera camera = (ICamera)new Frustum();
/*   50 */   private static boolean depth = GL11.glIsEnabled(2896);
/*   51 */   private static boolean texture = GL11.glIsEnabled(3042);
/*   52 */   private static boolean clean = GL11.glIsEnabled(3553);
/*   53 */   private static boolean bind = GL11.glIsEnabled(2929);
/*   54 */   private static boolean override = GL11.glIsEnabled(2848);
/*      */   
/*      */   public static int deltaTime;
/*      */   
/*      */   public static void boxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n, boolean bl3) {
/*   59 */     AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - (mc.getRenderManager()).viewerPosX, blockPos.getY() - (mc.getRenderManager()).viewerPosY, blockPos.getZ() - (mc.getRenderManager()).viewerPosZ, (blockPos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (blockPos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (blockPos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
/*   61 */     if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + (mc.getRenderManager()).viewerPosX, axisAlignedBB.minY + (mc.getRenderManager()).viewerPosY, axisAlignedBB.minZ + (mc.getRenderManager()).viewerPosZ, axisAlignedBB.maxX + (mc.getRenderManager()).viewerPosX, axisAlignedBB.maxY + (mc.getRenderManager()).viewerPosY, axisAlignedBB.maxZ + (mc.getRenderManager()).viewerPosZ))) {
/*   62 */       double d = 0.0D;
/*   63 */       double d2 = 0.0D;
/*   64 */       double d3 = 0.0D;
/*   65 */       double d4 = 0.0D;
/*   66 */       double d5 = 0.0D;
/*   67 */       double d6 = 0.0D;
/*   68 */       GlStateManager.pushMatrix();
/*   69 */       GlStateManager.enableBlend();
/*   70 */       GlStateManager.disableDepth();
/*   71 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*   72 */       GlStateManager.disableTexture2D();
/*   73 */       GlStateManager.depthMask(false);
/*   74 */       GL11.glEnable(2848);
/*   75 */       GL11.glHint(3154, 4354);
/*   76 */       GL11.glLineWidth(f);
/*   77 */       double d8 = mc.playerController.curBlockDamageMP;
/*      */ 
/*      */       
/*   80 */       if (bl3) {
/*      */         
/*   82 */         d6 = axisAlignedBB.minX + 0.5D - d8 / 2.0D;
/*   83 */         d5 = axisAlignedBB.minY + 0.5D - d8 / 2.0D;
/*   84 */         d4 = axisAlignedBB.minZ + 0.5D - d8 / 2.0D;
/*   85 */         d3 = axisAlignedBB.maxX - 0.5D + d8 / 2.0D;
/*   86 */         d2 = axisAlignedBB.maxY - 0.5D + d8 / 2.0D;
/*   87 */         d = axisAlignedBB.maxZ - 0.5D + d8 / 2.0D;
/*      */       } 
/*   89 */       AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(d6, d5, d4, d3, d2, d);
/*   90 */       if (bl2) {
/*   91 */         drawFilledBox(axisAlignedBB2, (new Color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, n / 255.0F)).getRGB());
/*      */       }
/*   93 */       GL11.glDisable(2848);
/*   94 */       GlStateManager.depthMask(true);
/*   95 */       GlStateManager.enableDepth();
/*   96 */       GlStateManager.enableTexture2D();
/*   97 */       GlStateManager.disableBlend();
/*   98 */       GlStateManager.popMatrix();
/*      */     } 
/*      */   }
/*      */   public static Color getRainbowAlpha(int speed, int offset, float s, float b, int alpha) {
/*  102 */     float hue = (float)((System.currentTimeMillis() + offset) % speed);
/*  103 */     Color c = new Color(Color.getHSBColor(hue / speed, s, b).getRGB());
/*  104 */     return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
/*      */   }
/*      */   public static void prepareGL() {
/*  107 */     GL11.glBlendFunc(770, 771);
/*  108 */     GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  109 */     GlStateManager.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(5.0675106F) ^ 0x7F22290C));
/*  110 */     GlStateManager.disableTexture2D();
/*  111 */     GlStateManager.depthMask(false);
/*  112 */     GlStateManager.enableBlend();
/*  113 */     GlStateManager.disableDepth();
/*  114 */     GlStateManager.disableLighting();
/*  115 */     GlStateManager.disableCull();
/*  116 */     GlStateManager.enableAlpha();
/*  117 */     GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(11.925059F) ^ 0x7EBECD0B), Float.intBitsToFloat(Float.floatToIntBits(18.2283F) ^ 0x7E11D38F), Float.intBitsToFloat(Float.floatToIntBits(9.73656F) ^ 0x7E9BC8F3));
/*      */   }
/*      */   
/*      */   public static void releaseGL() {
/*  121 */     GlStateManager.enableCull();
/*  122 */     GlStateManager.depthMask(true);
/*  123 */     GlStateManager.enableTexture2D();
/*  124 */     GlStateManager.enableBlend();
/*  125 */     GlStateManager.enableDepth();
/*  126 */     GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(12.552789F) ^ 0x7EC8D839), Float.intBitsToFloat(Float.floatToIntBits(7.122752F) ^ 0x7F63ED96), Float.intBitsToFloat(Float.floatToIntBits(5.4278784F) ^ 0x7F2DB12E));
/*  127 */     GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(10.5715685F) ^ 0x7EA92525), Float.intBitsToFloat(Float.floatToIntBits(4.9474883F) ^ 0x7F1E51D3), Float.intBitsToFloat(Float.floatToIntBits(4.9044757F) ^ 0x7F1CF177), Float.intBitsToFloat(Float.floatToIntBits(9.482457F) ^ 0x7E97B825));
/*      */   }
/*      */ 
/*      */   

/*      */ 
/*      */   
/*      */   
/*      */   public static AxisAlignedBB interpolateAxis(AxisAlignedBB bb) {
/*  158 */     return new AxisAlignedBB(bb.minX - (mc.getRenderManager()).viewerPosX, bb.minY - (mc.getRenderManager()).viewerPosY, bb.minZ - (mc.getRenderManager()).viewerPosZ, bb.maxX - (mc.getRenderManager()).viewerPosX, bb.maxY - (mc.getRenderManager()).viewerPosY, bb.maxZ - (mc.getRenderManager()).viewerPosZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawTexturedRect(int x, int y, int textureX, int textureY, int width, int height, int zLevel) {
/*  163 */     Tessellator tessellator = Tessellator.getInstance();
/*  164 */     BufferBuilder BufferBuilder2 = tessellator.getBuffer();
/*  165 */     BufferBuilder2.begin(7, DefaultVertexFormats.POSITION_TEX);
/*  166 */     BufferBuilder2.pos(x, (y + height), zLevel).tex((textureX * 0.00390625F), ((textureY + height) * 0.00390625F)).endVertex();
/*  167 */     BufferBuilder2.pos((x + width), (y + height), zLevel).tex(((textureX + width) * 0.00390625F), ((textureY + height) * 0.00390625F)).endVertex();
/*  168 */     BufferBuilder2.pos((x + width), y, zLevel).tex(((textureX + width) * 0.00390625F), (textureY * 0.00390625F)).endVertex();
/*  169 */     BufferBuilder2.pos(x, y, zLevel).tex((textureX * 0.00390625F), (textureY * 0.00390625F)).endVertex();
/*  170 */     tessellator.draw();
/*      */   }
/*      */ 
/*      */   
/*      */ 
/*      */   

/*      */   
/*      */ 
/*      */  
/*      */   
/*      */   public static void drawGradientRect(int x, int y, int w, int h, int startColor, int endColor) {
/*  550 */     float f = (startColor >> 24 & 0xFF) / 255.0F;
/*  551 */     float f1 = (startColor >> 16 & 0xFF) / 255.0F;
/*  552 */     float f2 = (startColor >> 8 & 0xFF) / 255.0F;
/*  553 */     float f3 = (startColor & 0xFF) / 255.0F;
/*  554 */     float f4 = (endColor >> 24 & 0xFF) / 255.0F;
/*  555 */     float f5 = (endColor >> 16 & 0xFF) / 255.0F;
/*  556 */     float f6 = (endColor >> 8 & 0xFF) / 255.0F;
/*  557 */     float f7 = (endColor & 0xFF) / 255.0F;
/*  558 */     GlStateManager.disableTexture2D();
/*  559 */     GlStateManager.enableBlend();
/*  560 */     GlStateManager.disableAlpha();
/*  561 */     GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  562 */     GlStateManager.shadeModel(7425);
/*  563 */     Tessellator tessellator = Tessellator.getInstance();
/*  564 */     BufferBuilder vertexbuffer = tessellator.getBuffer();
/*  565 */     vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  566 */     vertexbuffer.pos(x + w, y, 0.0D).color(f1, f2, f3, f).endVertex();
/*  567 */     vertexbuffer.pos(x, y, 0.0D).color(f1, f2, f3, f).endVertex();
/*  568 */     vertexbuffer.pos(x, y + h, 0.0D).color(f5, f6, f7, f4).endVertex();
/*  569 */     vertexbuffer.pos(x + w, y + h, 0.0D).color(f5, f6, f7, f4).endVertex();
/*  570 */     tessellator.draw();
/*  571 */     GlStateManager.shadeModel(7424);
/*  572 */     GlStateManager.disableBlend();
/*  573 */     GlStateManager.enableAlpha();
/*  574 */     GlStateManager.enableTexture2D();
/*      */   }
/*      */ 
/*      */  
/*      */   
/*      */   public static void drawProperGradientBlockOutline(AxisAlignedBB bb, Color startColor, Color midColor, Color endColor, float linewidth) {
/*  593 */     float red = endColor.getRed() / 255.0F;
/*  594 */     float green = endColor.getGreen() / 255.0F;
/*  595 */     float blue = endColor.getBlue() / 255.0F;
/*  596 */     float alpha = endColor.getAlpha() / 255.0F;
/*  597 */     float red1 = midColor.getRed() / 255.0F;
/*  598 */     float green1 = midColor.getGreen() / 255.0F;
/*  599 */     float blue1 = midColor.getBlue() / 255.0F;
/*  600 */     float alpha1 = midColor.getAlpha() / 255.0F;
/*  601 */     float red2 = startColor.getRed() / 255.0F;
/*  602 */     float green2 = startColor.getGreen() / 255.0F;
/*  603 */     float blue2 = startColor.getBlue() / 255.0F;
/*  604 */     float alpha2 = startColor.getAlpha() / 255.0F;
/*  605 */     double dif = (bb.maxY - bb.minY) / 2.0D;
/*  606 */     GlStateManager.pushMatrix();
/*  607 */     GlStateManager.enableBlend();
/*  608 */     GlStateManager.disableDepth();
/*  609 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  610 */     GlStateManager.disableTexture2D();
/*  611 */     GlStateManager.depthMask(false);
/*  612 */     GL11.glEnable(2848);
/*  613 */     GL11.glHint(3154, 4354);
/*  614 */     GL11.glLineWidth(linewidth);
/*  615 */     GL11.glBegin(1);
/*  616 */     GL11.glColor4d(red, green, blue, alpha);
/*  617 */     GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
/*  618 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
/*  619 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
/*  620 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
/*  621 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
/*  622 */     GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
/*  623 */     GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
/*  624 */     GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
/*  625 */     GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
/*  626 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  627 */     GL11.glVertex3d(bb.minX, bb.minY + dif, bb.minZ);
/*  628 */     GL11.glVertex3d(bb.minX, bb.minY + dif, bb.minZ);
/*  629 */     GL11.glColor4f(red2, green2, blue2, alpha2);
/*  630 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
/*  631 */     GL11.glColor4d(red, green, blue, alpha);
/*  632 */     GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
/*  633 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  634 */     GL11.glVertex3d(bb.minX, bb.minY + dif, bb.maxZ);
/*  635 */     GL11.glVertex3d(bb.minX, bb.minY + dif, bb.maxZ);
/*  636 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  637 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
/*  638 */     GL11.glColor4d(red, green, blue, alpha);
/*  639 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
/*  640 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  641 */     GL11.glVertex3d(bb.maxX, bb.minY + dif, bb.maxZ);
/*  642 */     GL11.glVertex3d(bb.maxX, bb.minY + dif, bb.maxZ);
/*  643 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  644 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
/*  645 */     GL11.glColor4d(red, green, blue, alpha);
/*  646 */     GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
/*  647 */     GL11.glColor4d(red1, green1, blue1, alpha1);
/*  648 */     GL11.glVertex3d(bb.maxX, bb.minY + dif, bb.minZ);
/*  649 */     GL11.glVertex3d(bb.maxX, bb.minY + dif, bb.minZ);
/*  650 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  651 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
/*  652 */     GL11.glColor4d(red2, green2, blue2, alpha2);
/*  653 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
/*  654 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
/*  655 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
/*  656 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
/*  657 */     GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
/*  658 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
/*  659 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
/*  660 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
/*  661 */     GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
/*  662 */     GL11.glEnd();
/*  663 */     GL11.glDisable(2848);
/*  664 */     GlStateManager.depthMask(true);
/*  665 */     GlStateManager.enableDepth();
/*  666 */     GlStateManager.enableTexture2D();
/*  667 */     GlStateManager.disableBlend();
/*  668 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawGradientBlockOutline(AxisAlignedBB bb, Color startColor, Color endColor, float linewidth) {
/*  673 */     float red = startColor.getRed() / 255.0F;
/*  674 */     float green = startColor.getGreen() / 255.0F;
/*  675 */     float blue = startColor.getBlue() / 255.0F;
/*  676 */     float alpha = startColor.getAlpha() / 255.0F;
/*  677 */     float red1 = endColor.getRed() / 255.0F;
/*  678 */     float green1 = endColor.getGreen() / 255.0F;
/*  679 */     float blue1 = endColor.getBlue() / 255.0F;
/*  680 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  681 */     GlStateManager.pushMatrix();
/*  682 */     GlStateManager.enableBlend();
/*  683 */     GlStateManager.disableDepth();
/*  684 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  685 */     GlStateManager.disableTexture2D();
/*  686 */     GlStateManager.depthMask(false);
/*  687 */     GL11.glEnable(2848);
/*  688 */     GL11.glHint(3154, 4354);
/*  689 */     GL11.glLineWidth(linewidth);
/*  690 */     Tessellator tessellator = Tessellator.getInstance();
/*  691 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/*  692 */     bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/*  693 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  694 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  695 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  696 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  697 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  698 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  699 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  700 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  701 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  702 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  703 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  704 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  705 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  706 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  707 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  708 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  709 */     tessellator.draw();
/*  710 */     GL11.glDisable(2848);
/*  711 */     GlStateManager.depthMask(true);
/*  712 */     GlStateManager.enableDepth();
/*  713 */     GlStateManager.enableTexture2D();
/*  714 */     GlStateManager.disableBlend();
/*  715 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   

/*      */   
/*      */   public static void drawGradientFilledBox(AxisAlignedBB bb, Color startColor, Color endColor) {
/*  727 */     GlStateManager.pushMatrix();
/*  728 */     GlStateManager.enableBlend();
/*  729 */     GlStateManager.disableDepth();
/*  730 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  731 */     GlStateManager.disableTexture2D();
/*  732 */     GlStateManager.depthMask(false);
/*  733 */     float alpha = endColor.getAlpha() / 255.0F;
/*  734 */     float red = endColor.getRed() / 255.0F;
/*  735 */     float green = endColor.getGreen() / 255.0F;
/*  736 */     float blue = endColor.getBlue() / 255.0F;
/*  737 */     float alpha1 = startColor.getAlpha() / 255.0F;
/*  738 */     float red1 = startColor.getRed() / 255.0F;
/*  739 */     float green1 = startColor.getGreen() / 255.0F;
/*  740 */     float blue1 = startColor.getBlue() / 255.0F;
/*  741 */     Tessellator tessellator = Tessellator.getInstance();
/*  742 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/*  743 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  744 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  745 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  746 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  747 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  748 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  749 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  750 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  751 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  752 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  753 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  754 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  755 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  756 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  757 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  758 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  759 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  760 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  761 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  762 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  763 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  764 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/*  765 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/*  766 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*  767 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/*  768 */     tessellator.draw();
/*  769 */     GlStateManager.depthMask(true);
/*  770 */     GlStateManager.enableDepth();
/*  771 */     GlStateManager.enableTexture2D();
/*  772 */     GlStateManager.disableBlend();
/*  773 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawGradientRect(float x, float y, float w, float h, int startColor, int endColor) {
/*  778 */     float f = (startColor >> 24 & 0xFF) / 255.0F;
/*  779 */     float f1 = (startColor >> 16 & 0xFF) / 255.0F;
/*  780 */     float f2 = (startColor >> 8 & 0xFF) / 255.0F;
/*  781 */     float f3 = (startColor & 0xFF) / 255.0F;
/*  782 */     float f4 = (endColor >> 24 & 0xFF) / 255.0F;
/*  783 */     float f5 = (endColor >> 16 & 0xFF) / 255.0F;
/*  784 */     float f6 = (endColor >> 8 & 0xFF) / 255.0F;
/*  785 */     float f7 = (endColor & 0xFF) / 255.0F;
/*  786 */     GlStateManager.disableTexture2D();
/*  787 */     GlStateManager.enableBlend();
/*  788 */     GlStateManager.disableAlpha();
/*  789 */     GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/*  790 */     GlStateManager.shadeModel(7425);
/*  791 */     Tessellator tessellator = Tessellator.getInstance();
/*  792 */     BufferBuilder vertexbuffer = tessellator.getBuffer();
/*  793 */     vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  794 */     vertexbuffer.pos(x + w, y, 0.0D).color(f1, f2, f3, f).endVertex();
/*  795 */     vertexbuffer.pos(x, y, 0.0D).color(f1, f2, f3, f).endVertex();
/*  796 */     vertexbuffer.pos(x, y + h, 0.0D).color(f5, f6, f7, f4).endVertex();
/*  797 */     vertexbuffer.pos(x + w, y + h, 0.0D).color(f5, f6, f7, f4).endVertex();
/*  798 */     tessellator.draw();
/*  799 */     GlStateManager.shadeModel(7424);
/*  800 */     GlStateManager.disableBlend();
/*  801 */     GlStateManager.enableAlpha();
/*  802 */     GlStateManager.enableTexture2D();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFilledCircle(double x, double y, double z, Color color, double radius) {
/*  807 */     Tessellator tessellator = Tessellator.getInstance();
/*  808 */     BufferBuilder builder = tessellator.getBuffer();
/*  809 */     builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawGradientBoxTest(BlockPos pos, Color startColor, Color endColor) {}
/*      */ 
/*      */   
/*      */   public static void blockESP(BlockPos b, Color c, double length, double length2) {
/*  818 */     blockEsp(b, c, length, length2);
/*      */   }
/*      */ 
/*      */   

/*      */ 
/*      */   
/*      */   public static void drawSexyBoxPhobosIsRetardedFuckYouESP(AxisAlignedBB a, Color boxColor, Color outlineColor, float lineWidth, boolean outline, boolean box, boolean colorSync, float alpha, float scale, float slab) {
/*  833 */     double f = 0.5D * (1.0F - scale);
/*  834 */     AxisAlignedBB bb = interpolateAxis(new AxisAlignedBB(a.minX + f, a.minY + f + (1.0F - slab), a.minZ + f, a.maxX - f, a.maxY - f, a.maxZ - f));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  842 */     float rB = boxColor.getRed() / 255.0F;
/*  843 */     float gB = boxColor.getGreen() / 255.0F;
/*  844 */     float bB = boxColor.getBlue() / 255.0F;
/*  845 */     float aB = boxColor.getAlpha() / 255.0F;
/*  846 */     float rO = outlineColor.getRed() / 255.0F;
/*  847 */     float gO = outlineColor.getGreen() / 255.0F;
/*  848 */     float bO = outlineColor.getBlue() / 255.0F;
/*  849 */     float aO = outlineColor.getAlpha() / 255.0F;
/*  850 */     if (colorSync) {
/*  851 */       rB = Colors.INSTANCE.getCurrentColor().getRed() / 255.0F;
/*  852 */       gB = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0F;
/*  853 */       bB = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0F;
/*  854 */       rO = Colors.INSTANCE.getCurrentColor().getRed() / 255.0F;
/*  855 */       gO = Colors.INSTANCE.getCurrentColor().getGreen() / 255.0F;
/*  856 */       bO = Colors.INSTANCE.getCurrentColor().getBlue() / 255.0F;
/*      */     } 
/*  858 */     if (alpha > 1.0F) alpha = 1.0F; 
/*  859 */     aB *= alpha;
/*  860 */     aO *= alpha;
/*  861 */     if (box) {
/*  862 */       GlStateManager.pushMatrix();
/*  863 */       GlStateManager.enableBlend();
/*  864 */       GlStateManager.disableDepth();
/*  865 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  866 */       GlStateManager.disableTexture2D();
/*  867 */       GlStateManager.depthMask(false);
/*  868 */       GL11.glEnable(2848);
/*  869 */       GL11.glHint(3154, 4354);
/*  870 */       RenderGlobal.renderFilledBox(bb, rB, gB, bB, aB);
/*  871 */       GL11.glDisable(2848);
/*  872 */       GlStateManager.depthMask(true);
/*  873 */       GlStateManager.enableDepth();
/*  874 */       GlStateManager.enableTexture2D();
/*  875 */       GlStateManager.disableBlend();
/*  876 */       GlStateManager.popMatrix();
/*      */     } 
/*  878 */     if (outline) {
/*  879 */       GlStateManager.pushMatrix();
/*  880 */       GlStateManager.enableBlend();
/*  881 */       GlStateManager.disableDepth();
/*  882 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  883 */       GlStateManager.disableTexture2D();
/*  884 */       GlStateManager.depthMask(false);
/*  885 */       GL11.glEnable(2848);
/*  886 */       GL11.glHint(3154, 4354);
/*  887 */       GL11.glLineWidth(lineWidth);
/*  888 */       Tessellator tessellator = Tessellator.getInstance();
/*  889 */       BufferBuilder bufferbuilder = tessellator.getBuffer();
/*  890 */       bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/*  891 */       bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  892 */       bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  893 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  894 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  895 */       bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  896 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  897 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  898 */       bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  899 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  900 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  901 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  902 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(rO, gO, bO, aO).endVertex();
/*  903 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  904 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  905 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  906 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(rO, gO, bO, aO).endVertex();
/*  907 */       tessellator.draw();
/*  908 */       GL11.glDisable(2848);
/*  909 */       GlStateManager.depthMask(true);
/*  910 */       GlStateManager.enableDepth();
/*  911 */       GlStateManager.enableTexture2D();
/*  912 */       GlStateManager.disableBlend();
/*  913 */       GlStateManager.popMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   
/*      */   public static void glScissor(float x, float y, float x1, float y1, ScaledResolution sr) {
/*  929 */     GL11.glScissor((int)(x * sr.getScaleFactor()), (int)(mc.displayHeight - y1 * sr.getScaleFactor()), (int)((x1 - x) * sr.getScaleFactor()), (int)((y1 - y) * sr.getScaleFactor()));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawLine(float x, float y, float x1, float y1, float thickness, int hex) {
/*  934 */     float red = (hex >> 16 & 0xFF) / 255.0F;
/*  935 */     float green = (hex >> 8 & 0xFF) / 255.0F;
/*  936 */     float blue = (hex & 0xFF) / 255.0F;
/*  937 */     float alpha = (hex >> 24 & 0xFF) / 255.0F;
/*  938 */     GlStateManager.pushMatrix();
/*  939 */     GlStateManager.disableTexture2D();
/*  940 */     GlStateManager.enableBlend();
/*  941 */     GlStateManager.disableAlpha();
/*  942 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  943 */     GlStateManager.shadeModel(7425);
/*  944 */     GL11.glLineWidth(thickness);
/*  945 */     GL11.glEnable(2848);
/*  946 */     GL11.glHint(3154, 4354);
/*  947 */     Tessellator tessellator = Tessellator.getInstance();
/*  948 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/*  949 */     bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/*  950 */     bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
/*  951 */     bufferbuilder.pos(x1, y1, 0.0D).color(red, green, blue, alpha).endVertex();
/*  952 */     tessellator.draw();
/*  953 */     GlStateManager.shadeModel(7424);
/*  954 */     GL11.glDisable(2848);
/*  955 */     GlStateManager.disableBlend();
/*  956 */     GlStateManager.enableAlpha();
/*  957 */     GlStateManager.enableTexture2D();
/*  958 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawBox(BlockPos pos, Color color) {
/*  963 */     AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - (mc.getRenderManager()).viewerPosX, pos.getY() - (mc.getRenderManager()).viewerPosY, pos.getZ() - (mc.getRenderManager()).viewerPosZ, (pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);

/*  965 */     if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + (mc.getRenderManager()).viewerPosX, bb.minY + (mc.getRenderManager()).viewerPosY, bb.minZ + (mc.getRenderManager()).viewerPosZ, bb.maxX + (mc.getRenderManager()).viewerPosX, bb.maxY + (mc.getRenderManager()).viewerPosY, bb.maxZ + (mc.getRenderManager()).viewerPosZ))) {
/*  966 */       GlStateManager.pushMatrix();
/*  967 */       GlStateManager.enableBlend();
/*  968 */       GlStateManager.disableDepth();
/*  969 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  970 */       GlStateManager.disableTexture2D();
/*  971 */       GlStateManager.depthMask(false);
/*  972 */       GL11.glEnable(2848);
/*  973 */       GL11.glHint(3154, 4354);
/*  974 */       RenderGlobal.renderFilledBox(bb, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*  975 */       GL11.glDisable(2848);
/*  976 */       GlStateManager.depthMask(true);
/*  977 */       GlStateManager.enableDepth();
/*  978 */       GlStateManager.enableTexture2D();
/*  979 */       GlStateManager.disableBlend();
/*  980 */       GlStateManager.popMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawBetterGradientBox(BlockPos pos, Color startColor, Color endColor) {
/*  986 */     float red = startColor.getRed() / 255.0F;
/*  987 */     float green = startColor.getGreen() / 255.0F;
/*  988 */     float blue = startColor.getBlue() / 255.0F;
/*  989 */     float alpha = startColor.getAlpha() / 255.0F;
/*  990 */     float red1 = endColor.getRed() / 255.0F;
/*  991 */     float green1 = endColor.getGreen() / 255.0F;
/*  992 */     float blue1 = endColor.getBlue() / 255.0F;
/*  993 */     float alpha1 = endColor.getAlpha() / 255.0F;
/*  994 */     AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - (mc.getRenderManager()).viewerPosX, pos.getY() - (mc.getRenderManager()).viewerPosY, pos.getZ() - (mc.getRenderManager()).viewerPosZ, (pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
/*  995 */     Tessellator tessellator = Tessellator.getInstance();
/*  996 */     BufferBuilder builder = tessellator.getBuffer();
/*  997 */     GlStateManager.pushMatrix();
/*  998 */     GlStateManager.enableBlend();
/*  999 */     GlStateManager.disableDepth();
/* 1000 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1001 */     GlStateManager.disableTexture2D();
/* 1002 */     GlStateManager.depthMask(false);
/* 1003 */     GL11.glEnable(2848);
/* 1004 */     GL11.glHint(3154, 4354);
/* 1005 */     builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
/* 1006 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1007 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1008 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1009 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1010 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1011 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1012 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1013 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1014 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1015 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1016 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1017 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1018 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1019 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1020 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1021 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1022 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1023 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1024 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1025 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1026 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1027 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1028 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1029 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1030 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1031 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1032 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1033 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1034 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1035 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawBetterGradientBox(BlockPos pos, Color startColor, Color midColor, Color endColor) {
/* 1040 */     float red = startColor.getRed() / 255.0F;
/* 1041 */     float green = startColor.getGreen() / 255.0F;
/* 1042 */     float blue = startColor.getBlue() / 255.0F;
/* 1043 */     float alpha = startColor.getAlpha() / 255.0F;
/* 1044 */     float red1 = endColor.getRed() / 255.0F;
/* 1045 */     float green1 = endColor.getGreen() / 255.0F;
/* 1046 */     float blue1 = endColor.getBlue() / 255.0F;
/* 1047 */     float alpha1 = endColor.getAlpha() / 255.0F;
/* 1048 */     float red2 = midColor.getRed() / 255.0F;
/* 1049 */     float green2 = midColor.getGreen() / 255.0F;
/* 1050 */     float blue2 = midColor.getBlue() / 255.0F;
/* 1051 */     float alpha2 = midColor.getAlpha() / 255.0F;
/* 1052 */     AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - (mc.getRenderManager()).viewerPosX, pos.getY() - (mc.getRenderManager()).viewerPosY, pos.getZ() - (mc.getRenderManager()).viewerPosZ, (pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
/* 1053 */     double offset = (bb.maxY - bb.minY) / 2.0D;
/* 1054 */     Tessellator tessellator = Tessellator.getInstance();
/* 1055 */     BufferBuilder builder = tessellator.getBuffer();
/* 1056 */     GlStateManager.pushMatrix();
/* 1057 */     GlStateManager.enableBlend();
/* 1058 */     GlStateManager.disableDepth();
/* 1059 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1060 */     GlStateManager.disableTexture2D();
/* 1061 */     GlStateManager.depthMask(false);
/* 1062 */     GL11.glEnable(2848);
/* 1063 */     GL11.glHint(3154, 4354);
/* 1064 */     builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
/* 1065 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1066 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1067 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1068 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1069 */     builder.pos(bb.minX, bb.minY + offset, bb.minZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1070 */     builder.pos(bb.minX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1071 */     builder.pos(bb.minX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1072 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1073 */     builder.pos(bb.maxX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1074 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1075 */     builder.pos(bb.minX, bb.minY + offset, bb.minZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1076 */     builder.pos(bb.minX, bb.minY + offset, bb.minZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1077 */     builder.pos(bb.minX, bb.minY + offset, bb.minZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1078 */     builder.pos(bb.minX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1079 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1080 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1081 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1082 */     builder.pos(bb.minX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1083 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1084 */     builder.pos(bb.maxX, bb.minY + offset, bb.maxZ).color(red2, green2, blue2, alpha2).endVertex();
/* 1085 */     tessellator.draw();
/* 1086 */     GL11.glDisable(2848);
/* 1087 */     GlStateManager.depthMask(true);
/* 1088 */     GlStateManager.enableDepth();
/* 1089 */     GlStateManager.enableTexture2D();
/* 1090 */     GlStateManager.disableBlend();
/* 1091 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawEvenBetterGradientBox(BlockPos pos, Color startColor, Color midColor, Color endColor) {
/* 1096 */     float red = startColor.getRed() / 255.0F;
/* 1097 */     float green = startColor.getGreen() / 255.0F;
/* 1098 */     float blue = startColor.getBlue() / 255.0F;
/* 1099 */     float alpha = startColor.getAlpha() / 255.0F;
/* 1100 */     float red1 = endColor.getRed() / 255.0F;
/* 1101 */     float green1 = endColor.getGreen() / 255.0F;
/* 1102 */     float blue1 = endColor.getBlue() / 255.0F;
/* 1103 */     float alpha1 = endColor.getAlpha() / 255.0F;
/* 1104 */     float red2 = midColor.getRed() / 255.0F;
/* 1105 */     float green2 = midColor.getGreen() / 255.0F;
/* 1106 */     float blue2 = midColor.getBlue() / 255.0F;
/* 1107 */     float alpha2 = midColor.getAlpha() / 255.0F;
/* 1108 */     AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - (mc.getRenderManager()).viewerPosX, pos.getY() - (mc.getRenderManager()).viewerPosY, pos.getZ() - (mc.getRenderManager()).viewerPosZ, (pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
/* 1109 */     Tessellator tessellator = Tessellator.getInstance();
/* 1110 */     BufferBuilder builder = tessellator.getBuffer();
/* 1111 */     GlStateManager.pushMatrix();
/* 1112 */     GlStateManager.enableBlend();
/* 1113 */     GlStateManager.disableDepth();
/* 1114 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1115 */     GlStateManager.disableTexture2D();
/* 1116 */     GlStateManager.depthMask(false);
/* 1117 */     GL11.glEnable(2848);
/* 1118 */     GL11.glHint(3154, 4354);
/* 1119 */     builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
/* 1120 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1121 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1122 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1123 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1124 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1125 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1126 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1127 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1128 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1129 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1130 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1131 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1132 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1133 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1134 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1135 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1136 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1137 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1138 */     builder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1139 */     builder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1140 */     builder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1141 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1142 */     builder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
/* 1143 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1144 */     builder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1145 */     builder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1146 */     builder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1147 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1148 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1149 */     builder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1150 */     tessellator.draw();
/* 1151 */     GL11.glDisable(2848);
/* 1152 */     GlStateManager.depthMask(true);
/* 1153 */     GlStateManager.enableDepth();
/* 1154 */     GlStateManager.enableTexture2D();
/* 1155 */     GlStateManager.disableBlend();
/* 1156 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */
/*      */ 

/*      */ 
/*      */   
/*      */ 
/*      */   
/*      */   public static void drawBlockOutline(AxisAlignedBB bb, Color color, float linewidth) {
/* 1212 */     float red = color.getRed() / 255.0F;
/* 1213 */     float green = color.getGreen() / 255.0F;
/* 1214 */     float blue = color.getBlue() / 255.0F;
/* 1215 */     float alpha = color.getAlpha() / 255.0F;
/* 1216 */     GlStateManager.pushMatrix();
/* 1217 */     GlStateManager.enableBlend();
/* 1218 */     GlStateManager.disableDepth();
/* 1219 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1220 */     GlStateManager.disableTexture2D();
/* 1221 */     GlStateManager.depthMask(false);
/* 1222 */     GL11.glEnable(2848);
/* 1223 */     GL11.glHint(3154, 4354);
/* 1224 */     GL11.glLineWidth(linewidth);
/* 1225 */     Tessellator tessellator = Tessellator.getInstance();
/* 1226 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1227 */     bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/* 1228 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1229 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1230 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1231 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1232 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1233 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1234 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1235 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1236 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1237 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1238 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1239 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1240 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1241 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1242 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1243 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1244 */     tessellator.draw();
/* 1245 */     GL11.glDisable(2848);
/* 1246 */     GlStateManager.depthMask(true);
/* 1247 */     GlStateManager.enableDepth();
/* 1248 */     GlStateManager.enableTexture2D();
/* 1249 */     GlStateManager.disableBlend();
/* 1250 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */ 
/*      */   
/*      */   public static void drawText(AxisAlignedBB pos, String text) {
/* 1285 */     if (pos == null || text == null) {
/*      */       return;
/*      */     }
/* 1288 */     GlStateManager.pushMatrix();
/* 1289 */     glBillboardDistanceScaled((float)pos.minX + 0.5F, (float)pos.minY + 0.5F, (float)pos.minZ + 0.5F, (EntityPlayer)mc.player, 1.0F);
/* 1290 */     GlStateManager.disableDepth();
/* 1291 */     GlStateManager.translate(-(Cracked.textManager.getStringWidth(text) / 2.0D), 0.0D, 0.0D);
/* 1292 */     Cracked.textManager.drawStringWithShadow(text, 0.0F, 0.0F, -5592406);
/* 1293 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   

/*      */   
/*      */   public static void blockEsp(BlockPos blockPos, Color c, double length, double length2) {
/* 1305 */     double x = blockPos.getX() - mc.renderManager.renderPosX;
/* 1306 */     double y = blockPos.getY() - mc.renderManager.renderPosY;
/* 1307 */     double z = blockPos.getZ() - mc.renderManager.renderPosZ;
/* 1308 */     GL11.glPushMatrix();
/* 1309 */     GL11.glBlendFunc(770, 771);
/* 1310 */     GL11.glEnable(3042);
/* 1311 */     GL11.glLineWidth(2.0F);
/* 1312 */     GL11.glDisable(3553);
/* 1313 */     GL11.glDisable(2929);
/* 1314 */     GL11.glDepthMask(false);
/* 1315 */     GL11.glColor4d((c.getRed() / 255.0F), (c.getGreen() / 255.0F), (c.getBlue() / 255.0F), 0.25D);
/* 1316 */     drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length), 0.0F, 0.0F, 0.0F, 0.0F);
/* 1317 */     GL11.glColor4d(0.0D, 0.0D, 0.0D, 0.5D);
/* 1318 */     drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0D, z + length));
/* 1319 */     GL11.glLineWidth(2.0F);
/* 1320 */     GL11.glEnable(3553);
/* 1321 */     GL11.glEnable(2929);
/* 1322 */     GL11.glDepthMask(true);
/* 1323 */     GL11.glDisable(3042);
/* 1324 */     GL11.glPopMatrix();
/* 1325 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawRect(float x, float y, float w, float h, int color) {
/* 1330 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1331 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1332 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1333 */     float blue = (color & 0xFF) / 255.0F;
/* 1334 */     Tessellator tessellator = Tessellator.getInstance();
/* 1335 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1336 */     GlStateManager.enableBlend();
/* 1337 */     GlStateManager.disableTexture2D();
/* 1338 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1339 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 1340 */     bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1341 */     bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1342 */     bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1343 */     bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1344 */     tessellator.draw();
/* 1345 */     GlStateManager.enableTexture2D();
/* 1346 */     GlStateManager.disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
/* 1351 */     Tessellator ts = Tessellator.getInstance();
/* 1352 */     BufferBuilder vb = ts.getBuffer();
/* 1353 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1354 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1355 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1356 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1357 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1358 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1359 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1360 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1361 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1362 */     ts.draw();
/* 1363 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1364 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1365 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1366 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1367 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1368 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1369 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1370 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1371 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1372 */     ts.draw();
/* 1373 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1374 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1375 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1376 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1377 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1378 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1379 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1380 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1381 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1382 */     ts.draw();
/* 1383 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1384 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1385 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1386 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1387 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1388 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1389 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1390 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1391 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1392 */     ts.draw();
/* 1393 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1394 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1395 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1396 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1397 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1398 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1399 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1400 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1401 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1402 */     ts.draw();
/* 1403 */     vb.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 1404 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1405 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1406 */     vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1407 */     vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1408 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1409 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
/* 1410 */     vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1411 */     vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1412 */     ts.draw();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
/* 1417 */     Tessellator tessellator = Tessellator.getInstance();
/* 1418 */     BufferBuilder vertexbuffer = tessellator.getBuffer();
/* 1419 */     vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
/* 1420 */     vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
/* 1421 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
/* 1422 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
/* 1423 */     vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
/* 1424 */     vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
/* 1425 */     tessellator.draw();
/* 1426 */     vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
/* 1427 */     vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
/* 1428 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
/* 1429 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
/* 1430 */     vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
/* 1431 */     vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
/* 1432 */     tessellator.draw();
/* 1433 */     vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
/* 1434 */     vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
/* 1435 */     vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
/* 1436 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
/* 1437 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
/* 1438 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
/* 1439 */     vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
/* 1440 */     vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
/* 1441 */     vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
/* 1442 */     tessellator.draw();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glrendermethod() {
/* 1447 */     GL11.glEnable(3042);
/* 1448 */     GL11.glBlendFunc(770, 771);
/* 1449 */     GL11.glEnable(2848);
/* 1450 */     GL11.glLineWidth(2.0F);
/* 1451 */     GL11.glDisable(3553);
/* 1452 */     GL11.glEnable(2884);
/* 1453 */     GL11.glDisable(2929);
/* 1454 */     double viewerPosX = (mc.getRenderManager()).viewerPosX;
/* 1455 */     double viewerPosY = (mc.getRenderManager()).viewerPosY;
/* 1456 */     double viewerPosZ = (mc.getRenderManager()).viewerPosZ;
/* 1457 */     GL11.glPushMatrix();
/* 1458 */     GL11.glTranslated(-viewerPosX, -viewerPosY, -viewerPosZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glStart(float n, float n2, float n3, float n4) {
/* 1463 */     glrendermethod();
/* 1464 */     GL11.glColor4f(n, n2, n3, n4);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glEnd() {
/* 1469 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1470 */     GL11.glPopMatrix();
/* 1471 */     GL11.glEnable(2929);
/* 1472 */     GL11.glEnable(3553);
/* 1473 */     GL11.glDisable(3042);
/* 1474 */     GL11.glDisable(2848);
/*      */   }
/*      */ 
/*      */   

/*      */ 
/*      */   
/*      */   public static void drawOutlinedBox(AxisAlignedBB axisAlignedBB) {
/* 1484 */     GL11.glBegin(1);
/* 1485 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1486 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1487 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1488 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1489 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1490 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1491 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1492 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1493 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1494 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1495 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ);
/* 1496 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1497 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1498 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1499 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ);
/* 1500 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1501 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1502 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1503 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1504 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1505 */     GL11.glVertex3d(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1506 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1507 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ);
/* 1508 */     GL11.glVertex3d(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ);
/* 1509 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFilledBoxESPN(BlockPos pos, Color color) {
/* 1514 */     AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - (mc.getRenderManager()).viewerPosX, pos.getY() - (mc.getRenderManager()).viewerPosY, pos.getZ() - (mc.getRenderManager()).viewerPosZ, (pos.getX() + 1) - (mc.getRenderManager()).viewerPosX, (pos.getY() + 1) - (mc.getRenderManager()).viewerPosY, (pos.getZ() + 1) - (mc.getRenderManager()).viewerPosZ);
/* 1515 */     int rgba = ColorUtil.toRGBA(color);
/* 1516 */     drawFilledBox(bb, rgba);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFilledBox(AxisAlignedBB bb, int color) {
/* 1521 */     GlStateManager.pushMatrix();
/* 1522 */     GlStateManager.enableBlend();
/* 1523 */     GlStateManager.disableDepth();
/* 1524 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1525 */     GlStateManager.disableTexture2D();
/* 1526 */     GlStateManager.depthMask(false);
/* 1527 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1528 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1529 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1530 */     float blue = (color & 0xFF) / 255.0F;
/* 1531 */     Tessellator tessellator = Tessellator.getInstance();
/* 1532 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1533 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 1534 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1535 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1536 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1537 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1538 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1539 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1540 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1541 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1542 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1543 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1544 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1545 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1546 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1547 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1548 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1549 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1550 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1551 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1552 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1553 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1554 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1555 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1556 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1557 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1558 */     tessellator.draw();
/* 1559 */     GlStateManager.depthMask(true);
/* 1560 */     GlStateManager.enableDepth();
/* 1561 */     GlStateManager.enableTexture2D();
/* 1562 */     GlStateManager.disableBlend();
/* 1563 */     GlStateManager.popMatrix();
/*      */   }
/*      */   public static AxisAlignedBB fixBB(AxisAlignedBB axisAlignedBB) {
/* 1566 */     AxisAlignedBB bb = axisAlignedBB;
/* 1567 */     return new AxisAlignedBB(bb.minX - (mc.getRenderManager()).viewerPosX, bb.minY - (mc.getRenderManager()).viewerPosY, bb.minZ - (mc.getRenderManager()).viewerPosZ, bb.maxX - (mc.getRenderManager()).viewerPosX, bb.maxY - (mc.getRenderManager()).viewerPosY, bb.maxZ - (mc.getRenderManager()).viewerPosZ);
/*      */   }
/*      */   
/*      */   public static void drawBoundingBox(AxisAlignedBB bb, float width, int color) {
/* 1571 */     GlStateManager.pushMatrix();
/* 1572 */     GlStateManager.enableBlend();
/* 1573 */     GlStateManager.disableDepth();
/* 1574 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1575 */     GlStateManager.disableTexture2D();
/* 1576 */     GlStateManager.depthMask(false);
/* 1577 */     GL11.glEnable(2848);
/* 1578 */     GL11.glHint(3154, 4354);
/* 1579 */     GL11.glLineWidth(width);
/* 1580 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1581 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1582 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1583 */     float blue = (color & 0xFF) / 255.0F;
/* 1584 */     Tessellator tessellator = Tessellator.getInstance();
/* 1585 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1586 */     bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/* 1587 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1588 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1589 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1590 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1591 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1592 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1593 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1594 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1595 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1596 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1597 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1598 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1599 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1600 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1601 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1602 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1603 */     tessellator.draw();
/* 1604 */     GL11.glDisable(2848);
/* 1605 */     GlStateManager.depthMask(true);
/* 1606 */     GlStateManager.enableDepth();
/* 1607 */     GlStateManager.enableTexture2D();
/* 1608 */     GlStateManager.disableBlend();
/* 1609 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glBillboard(float x, float y, float z) {
/* 1614 */     float scale = 0.02666667F;
/* 1615 */     GlStateManager.translate(x - (mc.getRenderManager()).renderPosX, y - (mc.getRenderManager()).renderPosY, z - (mc.getRenderManager()).renderPosZ);
/* 1616 */     GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
/* 1617 */     GlStateManager.rotate(-mc.player.rotationYaw, 0.0F, 1.0F, 0.0F);
/* 1618 */     GlStateManager.rotate(mc.player.rotationPitch, (mc.gameSettings.thirdPersonView == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
/* 1619 */     GlStateManager.scale(-scale, -scale, scale);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glBillboardDistanceScaled(float x, float y, float z, EntityPlayer player, float scale) {
/* 1624 */     glBillboard(x, y, z);
/* 1625 */     int distance = (int)player.getDistance(x, y, z);
/* 1626 */     float scaleDistance = distance / 2.0F / (2.0F + 2.0F - scale);
/* 1627 */     if (scaleDistance < 1.0F) {
/* 1628 */       scaleDistance = 1.0F;
/*      */     }
/* 1630 */     GlStateManager.scale(scaleDistance, scaleDistance, scaleDistance);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawColoredBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
/* 1635 */     GlStateManager.pushMatrix();
/* 1636 */     GlStateManager.enableBlend();
/* 1637 */     GlStateManager.disableDepth();
/* 1638 */     GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 1639 */     GlStateManager.disableTexture2D();
/* 1640 */     GlStateManager.depthMask(false);
/* 1641 */     GL11.glEnable(2848);
/* 1642 */     GL11.glHint(3154, 4354);
/* 1643 */     GL11.glLineWidth(width);
/* 1644 */     Tessellator tessellator = Tessellator.getInstance();
/* 1645 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1646 */     bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/* 1647 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
/* 1648 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1649 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1650 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1651 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1652 */     bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1653 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1654 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1655 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1656 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1657 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1658 */     bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, 0.0F).endVertex();
/* 1659 */     bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1660 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, 0.0F).endVertex();
/* 1661 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 1662 */     bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
/* 1663 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 1664 */     bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, 0.0F).endVertex();
/* 1665 */     tessellator.draw();
/* 1666 */     GL11.glDisable(2848);
/* 1667 */     GlStateManager.depthMask(true);
/* 1668 */     GlStateManager.enableDepth();
/* 1669 */     GlStateManager.enableTexture2D();
/* 1670 */     GlStateManager.disableBlend();
/* 1671 */     GlStateManager.popMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawSphere(double x, double y, double z, float size, int slices, int stacks) {
/* 1676 */     Sphere s = new Sphere();
/* 1677 */     GL11.glPushMatrix();
/* 1678 */     GL11.glBlendFunc(770, 771);
/* 1679 */     GL11.glEnable(3042);
/* 1680 */     GL11.glLineWidth(1.2F);
/* 1681 */     GL11.glDisable(3553);
/* 1682 */     GL11.glDisable(2929);
/* 1683 */     GL11.glDepthMask(false);
/* 1684 */     s.setDrawStyle(100013);
/* 1685 */     GL11.glTranslated(x - mc.renderManager.renderPosX, y - mc.renderManager.renderPosY, z - mc.renderManager.renderPosZ);
/* 1686 */     s.draw(size, slices, stacks);
/* 1687 */     GL11.glLineWidth(2.0F);
/* 1688 */     GL11.glEnable(3553);
/* 1689 */     GL11.glEnable(2929);
/* 1690 */     GL11.glDepthMask(true);
/* 1691 */     GL11.glDisable(3042);
/* 1692 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */ 
/*      */   
/*      */   public static void drawCompleteImage(float posX, float posY, float width, float height) {
/* 1738 */     GL11.glPushMatrix();
/* 1739 */     GL11.glTranslatef(posX, posY, 0.0F);
/* 1740 */     GL11.glBegin(7);
/* 1741 */     GL11.glTexCoord2f(0.0F, 0.0F);
/* 1742 */     GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/* 1743 */     GL11.glTexCoord2f(0.0F, 1.0F);
/* 1744 */     GL11.glVertex3f(0.0F, height, 0.0F);
/* 1745 */     GL11.glTexCoord2f(1.0F, 1.0F);
/* 1746 */     GL11.glVertex3f(width, height, 0.0F);
/* 1747 */     GL11.glTexCoord2f(1.0F, 0.0F);
/* 1748 */     GL11.glVertex3f(width, 0.0F, 0.0F);
/* 1749 */     GL11.glEnd();
/* 1750 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawOutlineRect(float x, float y, float w, float h, int color) {
/* 1755 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 1756 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 1757 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 1758 */     float blue = (color & 0xFF) / 255.0F;
/* 1759 */     Tessellator tessellator = Tessellator.getInstance();
/* 1760 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1761 */     GlStateManager.enableBlend();
/* 1762 */     GlStateManager.disableTexture2D();
/* 1763 */     GlStateManager.glLineWidth(1.0F);
/* 1764 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1765 */     bufferbuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
/* 1766 */     bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1767 */     bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1768 */     bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1769 */     bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1770 */     tessellator.draw();
/* 1771 */     GlStateManager.enableTexture2D();
/* 1772 */     GlStateManager.disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void draw3DRect(float x, float y, float w, float h, Color startColor, Color endColor, float lineWidth) {
/* 1777 */     float alpha = startColor.getAlpha() / 255.0F;
/* 1778 */     float red = startColor.getRed() / 255.0F;
/* 1779 */     float green = startColor.getGreen() / 255.0F;
/* 1780 */     float blue = startColor.getBlue() / 255.0F;
/* 1781 */     float alpha1 = endColor.getAlpha() / 255.0F;
/* 1782 */     float red1 = endColor.getRed() / 255.0F;
/* 1783 */     float green1 = endColor.getGreen() / 255.0F;
/* 1784 */     float blue1 = endColor.getBlue() / 255.0F;
/* 1785 */     Tessellator tessellator = Tessellator.getInstance();
/* 1786 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 1787 */     GlStateManager.enableBlend();
/* 1788 */     GlStateManager.disableTexture2D();
/* 1789 */     GlStateManager.glLineWidth(lineWidth);
/* 1790 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1791 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 1792 */     bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1793 */     bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1794 */     bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1795 */     bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 1796 */     tessellator.draw();
/* 1797 */     GlStateManager.enableTexture2D();
/* 1798 */     GlStateManager.disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawClock(float x, float y, float radius, int slices, int loops, float lineWidth, boolean fill, Color color) {
/* 1803 */     Disk disk = new Disk();
/* 1804 */     int hourAngle = 180 + -(Calendar.getInstance().get(10) * 30 + Calendar.getInstance().get(12) / 2);
/* 1805 */     int minuteAngle = 180 + -(Calendar.getInstance().get(12) * 6 + Calendar.getInstance().get(13) / 10);
/* 1806 */     int secondAngle = 180 + -(Calendar.getInstance().get(13) * 6);
/* 1807 */     int totalMinutesTime = Calendar.getInstance().get(12);
/* 1808 */     int totalHoursTime = Calendar.getInstance().get(10);
/* 1809 */     if (fill) {
/* 1810 */       GL11.glPushMatrix();
/* 1811 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 1812 */       GL11.glBlendFunc(770, 771);
/* 1813 */       GL11.glEnable(3042);
/* 1814 */       GL11.glLineWidth(lineWidth);
/* 1815 */       GL11.glDisable(3553);
/* 1816 */       disk.setOrientation(100020);
/* 1817 */       disk.setDrawStyle(100012);
/* 1818 */       GL11.glTranslated(x, y, 0.0D);
/* 1819 */       disk.draw(0.0F, radius, slices, loops);
/* 1820 */       GL11.glEnable(3553);
/* 1821 */       GL11.glDisable(3042);
/* 1822 */       GL11.glPopMatrix();
/*      */     } else {
/* 1824 */       GL11.glPushMatrix();
/* 1825 */       GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 1826 */       GL11.glEnable(3042);
/* 1827 */       GL11.glLineWidth(lineWidth);
/* 1828 */       GL11.glDisable(3553);
/* 1829 */       GL11.glBegin(3);
/* 1830 */       ArrayList<Vec2f> hVectors = new ArrayList<>();
/* 1831 */       float hue = (float)(System.currentTimeMillis() % 7200L) / 7200.0F;
/* 1832 */       for (int i = 0; i <= 360; i++) {
/* 1833 */         Vec2f vec = new Vec2f(x + (float)Math.sin(i * Math.PI / 180.0D) * radius, y + (float)Math.cos(i * Math.PI / 180.0D) * radius);
/* 1834 */         hVectors.add(vec);
/*      */       } 
/* 1836 */       Color color1 = new Color(Color.HSBtoRGB(hue, 1.0F, 1.0F));
/* 1837 */       for (int j = 0; j < hVectors.size() - 1; j++) {
/* 1838 */         GL11.glColor4f(color1.getRed() / 255.0F, color1.getGreen() / 255.0F, color1.getBlue() / 255.0F, color1.getAlpha() / 255.0F);
/* 1839 */         GL11.glVertex3d(((Vec2f)hVectors.get(j)).x, ((Vec2f)hVectors.get(j)).y, 0.0D);
/* 1840 */         GL11.glVertex3d(((Vec2f)hVectors.get(j + 1)).x, ((Vec2f)hVectors.get(j + 1)).y, 0.0D);
/* 1841 */         color1 = new Color(Color.HSBtoRGB(hue += 0.0027777778F, 1.0F, 1.0F));
/*      */       } 
/* 1843 */       GL11.glEnd();
/* 1844 */       GL11.glEnable(3553);
/* 1845 */       GL11.glDisable(3042);
/* 1846 */       GL11.glPopMatrix();
/*      */     } 
/* 1848 */     drawLine(x, y, x + (float)Math.sin(hourAngle * Math.PI / 180.0D) * radius / 2.0F, y + (float)Math.cos(hourAngle * Math.PI / 180.0D) * radius / 2.0F, 1.0F, Color.WHITE.getRGB());
/* 1849 */     drawLine(x, y, x + (float)Math.sin(minuteAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), y + (float)Math.cos(minuteAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), 1.0F, Color.WHITE.getRGB());
/* 1850 */     drawLine(x, y, x + (float)Math.sin(secondAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), y + (float)Math.cos(secondAngle * Math.PI / 180.0D) * (radius - radius / 10.0F), 1.0F, Color.RED.getRGB());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void GLPre(float lineWidth) {
/* 1855 */     depth = GL11.glIsEnabled(2896);
/* 1856 */     texture = GL11.glIsEnabled(3042);
/* 1857 */     clean = GL11.glIsEnabled(3553);
/* 1858 */     bind = GL11.glIsEnabled(2929);
/* 1859 */     override = GL11.glIsEnabled(2848);
/* 1860 */     GLPre(depth, texture, clean, bind, override, lineWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void GlPost() {
/* 1865 */     GLPost(depth, texture, clean, bind, override);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void GLPre(boolean depth, boolean texture, boolean clean, boolean bind, boolean override, float lineWidth) {
/* 1870 */     if (depth) {
/* 1871 */       GL11.glDisable(2896);
/*      */     }
/* 1873 */     if (!texture) {
/* 1874 */       GL11.glEnable(3042);
/*      */     }
/* 1876 */     GL11.glLineWidth(lineWidth);
/* 1877 */     if (clean) {
/* 1878 */       GL11.glDisable(3553);
/*      */     }
/* 1880 */     if (bind) {
/* 1881 */       GL11.glDisable(2929);
/*      */     }
/* 1883 */     if (!override) {
/* 1884 */       GL11.glEnable(2848);
/*      */     }
/* 1886 */     GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/* 1887 */     GL11.glHint(3154, 4354);
/* 1888 */     GlStateManager.depthMask(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float[][] getBipedRotations(ModelBiped biped) {
/* 1893 */     float[][] rotations = new float[5][];
/* 1894 */     float[] headRotation = { biped.bipedHead.rotateAngleX, biped.bipedHead.rotateAngleY, biped.bipedHead.rotateAngleZ };
/* 1895 */     rotations[0] = headRotation;
/* 1896 */     float[] rightArmRotation = { biped.bipedRightArm.rotateAngleX, biped.bipedRightArm.rotateAngleY, biped.bipedRightArm.rotateAngleZ };
/* 1897 */     rotations[1] = rightArmRotation;
/* 1898 */     float[] leftArmRotation = { biped.bipedLeftArm.rotateAngleX, biped.bipedLeftArm.rotateAngleY, biped.bipedLeftArm.rotateAngleZ };
/* 1899 */     rotations[2] = leftArmRotation;
/* 1900 */     float[] rightLegRotation = { biped.bipedRightLeg.rotateAngleX, biped.bipedRightLeg.rotateAngleY, biped.bipedRightLeg.rotateAngleZ };
/* 1901 */     rotations[3] = rightLegRotation;
/* 1902 */     float[] leftLegRotation = { biped.bipedLeftLeg.rotateAngleX, biped.bipedLeftLeg.rotateAngleY, biped.bipedLeftLeg.rotateAngleZ };
/* 1903 */     rotations[4] = leftLegRotation;
/* 1904 */     return rotations;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void GLPost(boolean depth, boolean texture, boolean clean, boolean bind, boolean override) {
/* 1909 */     GlStateManager.depthMask(true);
/* 1910 */     if (!override) {
/* 1911 */       GL11.glDisable(2848);
/*      */     }
/* 1913 */     if (bind) {
/* 1914 */       GL11.glEnable(2929);
/*      */     }
/* 1916 */     if (clean) {
/* 1917 */       GL11.glEnable(3553);
/*      */     }
/* 1919 */     if (!texture) {
/* 1920 */       GL11.glDisable(3042);
/*      */     }
/* 1922 */     if (depth) {
/* 1923 */       GL11.glEnable(2896);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawArc(float cx, float cy, float r, float start_angle, float end_angle, int num_segments) {
/* 1929 */     GL11.glBegin(4);
/* 1930 */     int i = (int)(num_segments / 360.0F / start_angle) + 1;
/* 1931 */     while (i <= num_segments / 360.0F / end_angle) {
/* 1932 */       double previousangle = 6.283185307179586D * (i - 1) / num_segments;
/* 1933 */       double angle = 6.283185307179586D * i / num_segments;
/* 1934 */       GL11.glVertex2d(cx, cy);
/* 1935 */       GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
/* 1936 */       GL11.glVertex2d(cx + Math.cos(previousangle) * r, cy + Math.sin(previousangle) * r);
/* 1937 */       i++;
/*      */     } 
/* 1939 */     glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawArcOutline(float cx, float cy, float r, float start_angle, float end_angle, int num_segments) {
/* 1944 */     GL11.glBegin(2);
/* 1945 */     int i = (int)(num_segments / 360.0F / start_angle) + 1;
/* 1946 */     while (i <= num_segments / 360.0F / end_angle) {
/* 1947 */       double angle = 6.283185307179586D * i / num_segments;
/* 1948 */       GL11.glVertex2d(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
/* 1949 */       i++;
/*      */     } 
/* 1951 */     glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircleOutline(float x, float y, float radius) {
/* 1956 */     drawCircleOutline(x, y, radius, 0, 360, 40);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircleOutline(float x, float y, float radius, int start, int end, int segments) {
/* 1961 */     drawArcOutline(x, y, radius, start, end, segments);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle(float x, float y, float radius) {
/* 1966 */     drawCircle(x, y, radius, 0, 360, 64);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle(float x, float y, float radius, int start, int end, int segments) {
/* 1971 */     drawArc(x, y, radius, start, end, segments);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawOutlinedRoundedRectangle(int x, int y, int width, int height, float radius, float dR, float dG, float dB, float dA, float outlineWidth) {
/* 1976 */     drawRoundedRectangle(x, y, width, height, radius);
/* 1977 */     GL11.glColor4f(dR, dG, dB, dA);
/* 1978 */     drawRoundedRectangle(x + outlineWidth, y + outlineWidth, width - outlineWidth * 2.0F, height - outlineWidth * 2.0F, radius);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawRectangle(float x, float y, float width, float height) {
/* 1983 */     GL11.glEnable(3042);
/* 1984 */     GL11.glBlendFunc(770, 771);
/* 1985 */     GL11.glBegin(2);
/* 1986 */     GL11.glVertex2d(width, 0.0D);
/* 1987 */     GL11.glVertex2d(0.0D, 0.0D);
/* 1988 */     GL11.glVertex2d(0.0D, height);
/* 1989 */     GL11.glVertex2d(width, height);
/* 1990 */     glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawRectangleXY(float x, float y, float width, float height) {
/* 1995 */     GL11.glEnable(3042);
/* 1996 */     GL11.glBlendFunc(770, 771);
/* 1997 */     GL11.glBegin(2);
/* 1998 */     GL11.glVertex2d((x + width), y);
/* 1999 */     GL11.glVertex2d(x, y);
/* 2000 */     GL11.glVertex2d(x, (y + height));
/* 2001 */     GL11.glVertex2d((x + width), (y + height));
/* 2002 */     glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFilledRectangle(float x, float y, float width, float height) {
/* 2007 */     GL11.glEnable(3042);
/* 2008 */     GL11.glBlendFunc(770, 771);
/* 2009 */     GL11.glBegin(7);
/* 2010 */     GL11.glVertex2d((x + width), y);
/* 2011 */     GL11.glVertex2d(x, y);
/* 2012 */     GL11.glVertex2d(x, (y + height));
/* 2013 */     GL11.glVertex2d((x + width), (y + height));
/* 2014 */     glEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 

/*      */ 
/*      */   
/*      */   public static void drawTracerPointer(float x, float y, float size, float widthDiv, float heightDiv, boolean outline, float outlineWidth, int color) {
/* 2038 */     boolean blend = GL11.glIsEnabled(3042);
/* 2039 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 2040 */     GL11.glEnable(3042);
/* 2041 */     GL11.glDisable(3553);
/* 2042 */     GL11.glBlendFunc(770, 771);
/* 2043 */     GL11.glEnable(2848);
/* 2044 */     GL11.glPushMatrix();
/* 2045 */     hexColor(color);
/* 2046 */     GL11.glBegin(7);
/* 2047 */     GL11.glVertex2d(x, y);
/* 2048 */     GL11.glVertex2d((x - size / widthDiv), (y + size));
/* 2049 */     GL11.glVertex2d(x, (y + size / heightDiv));
/* 2050 */     GL11.glVertex2d((x + size / widthDiv), (y + size));
/* 2051 */     GL11.glVertex2d(x, y);
/* 2052 */     GL11.glEnd();
/* 2053 */     if (outline) {
/* 2054 */       GL11.glLineWidth(outlineWidth);
/* 2055 */       GL11.glColor4f(0.0F, 0.0F, 0.0F, alpha);
/* 2056 */       GL11.glBegin(2);
/* 2057 */       GL11.glVertex2d(x, y);
/* 2058 */       GL11.glVertex2d((x - size / widthDiv), (y + size));
/* 2059 */       GL11.glVertex2d(x, (y + size / heightDiv));
/* 2060 */       GL11.glVertex2d((x + size / widthDiv), (y + size));
/* 2061 */       GL11.glVertex2d(x, y);
/* 2062 */       GL11.glEnd();
/*      */     } 
/* 2064 */     GL11.glPopMatrix();
/* 2065 */     GL11.glEnable(3553);
/* 2066 */     if (!blend) {
/* 2067 */       GL11.glDisable(3042);
/*      */     }
/* 2069 */     GL11.glDisable(2848);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getRainbow(int speed, int offset, float s, float b) {
/* 2074 */     float hue = (float)((System.currentTimeMillis() + offset) % speed);
/* 2075 */     return Color.getHSBColor(hue / speed, s, b).getRGB();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void hexColor(int hexColor) {
/* 2080 */     float red = (hexColor >> 16 & 0xFF) / 255.0F;
/* 2081 */     float green = (hexColor >> 8 & 0xFF) / 255.0F;
/* 2082 */     float blue = (hexColor & 0xFF) / 255.0F;
/* 2083 */     float alpha = (hexColor >> 24 & 0xFF) / 255.0F;
/* 2084 */     GL11.glColor4f(red, green, blue, alpha);
/*      */   }
/*      */ 

/*      */ 

/*      */ 
/*      */   
/*      */   public static void drawRoundedRectangle(float x, float y, float width, float height, float radius) {
/* 2101 */     GL11.glEnable(3042);
/* 2102 */     drawArc(x + width - radius, y + height - radius, radius, 0.0F, 90.0F, 16);
/* 2103 */     drawArc(x + radius, y + height - radius, radius, 90.0F, 180.0F, 16);
/* 2104 */     drawArc(x + radius, y + radius, radius, 180.0F, 270.0F, 16);
/* 2105 */     drawArc(x + width - radius, y + radius, radius, 270.0F, 360.0F, 16);
/* 2106 */     GL11.glBegin(4);
/* 2107 */     GL11.glVertex2d((x + width - radius), y);
/* 2108 */     GL11.glVertex2d((x + radius), y);
/* 2109 */     GL11.glVertex2d((x + width - radius), (y + radius));
/* 2110 */     GL11.glVertex2d((x + width - radius), (y + radius));
/* 2111 */     GL11.glVertex2d((x + radius), y);
/* 2112 */     GL11.glVertex2d((x + radius), (y + radius));
/* 2113 */     GL11.glVertex2d((x + width), (y + radius));
/* 2114 */     GL11.glVertex2d(x, (y + radius));
/* 2115 */     GL11.glVertex2d(x, (y + height - radius));
/* 2116 */     GL11.glVertex2d((x + width), (y + radius));
/* 2117 */     GL11.glVertex2d(x, (y + height - radius));
/* 2118 */     GL11.glVertex2d((x + width), (y + height - radius));
/* 2119 */     GL11.glVertex2d((x + width - radius), (y + height - radius));
/* 2120 */     GL11.glVertex2d((x + radius), (y + height - radius));
/* 2121 */     GL11.glVertex2d((x + width - radius), (y + height));
/* 2122 */     GL11.glVertex2d((x + width - radius), (y + height));
/* 2123 */     GL11.glVertex2d((x + radius), (y + height - radius));
/* 2124 */     GL11.glVertex2d((x + radius), (y + height));
/* 2125 */     glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderOne(float lineWidth) {
/* 2130 */     checkSetupFBO();
/* 2131 */     GL11.glPushAttrib(1048575);
/* 2132 */     GL11.glDisable(3008);
/* 2133 */     GL11.glDisable(3553);
/* 2134 */     GL11.glDisable(2896);
/* 2135 */     GL11.glEnable(3042);
/* 2136 */     GL11.glBlendFunc(770, 771);
/* 2137 */     GL11.glLineWidth(lineWidth);
/* 2138 */     GL11.glEnable(2848);
/* 2139 */     GL11.glEnable(2960);
/* 2140 */     GL11.glClear(1024);
/* 2141 */     GL11.glClearStencil(15);
/* 2142 */     GL11.glStencilFunc(512, 1, 15);
/* 2143 */     GL11.glStencilOp(7681, 7681, 7681);
/* 2144 */     GL11.glPolygonMode(1032, 6913);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderTwo() {
/* 2149 */     GL11.glStencilFunc(512, 0, 15);
/* 2150 */     GL11.glStencilOp(7681, 7681, 7681);
/* 2151 */     GL11.glPolygonMode(1032, 6914);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderThree() {
/* 2156 */     GL11.glStencilFunc(514, 1, 15);
/* 2157 */     GL11.glStencilOp(7680, 7680, 7680);
/* 2158 */     GL11.glPolygonMode(1032, 6913);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderFour(Color color) {
/* 2163 */     setColor(color);
/* 2164 */     GL11.glDepthMask(false);
/* 2165 */     GL11.glDisable(2929);
/* 2166 */     GL11.glEnable(10754);
/* 2167 */     GL11.glPolygonOffset(1.0F, -2000000.0F);
/* 2168 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void renderFive() {
/* 2173 */     GL11.glPolygonOffset(1.0F, 2000000.0F);
/* 2174 */     GL11.glDisable(10754);
/* 2175 */     GL11.glEnable(2929);
/* 2176 */     GL11.glDepthMask(true);
/* 2177 */     GL11.glDisable(2960);
/* 2178 */     GL11.glDisable(2848);
/* 2179 */     GL11.glHint(3154, 4352);
/* 2180 */     GL11.glEnable(3042);
/* 2181 */     GL11.glEnable(2896);
/* 2182 */     GL11.glEnable(3553);
/* 2183 */     GL11.glEnable(3008);
/* 2184 */     GL11.glPopAttrib();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rotationHelper(float xAngle, float yAngle, float zAngle) {
/* 2189 */     GlStateManager.rotate(yAngle, 0.0F, 1.0F, 0.0F);
/* 2190 */     GlStateManager.rotate(zAngle, 0.0F, 0.0F, 1.0F);
/* 2191 */     GlStateManager.rotate(xAngle, 1.0F, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setColor(Color color) {
/* 2196 */     GL11.glColor4d(color.getRed() / 255.0D, color.getGreen() / 255.0D, color.getBlue() / 255.0D, color.getAlpha() / 255.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void checkSetupFBO() {
/* 2201 */     Framebuffer fbo = mc.framebuffer;
/* 2202 */     if (fbo != null && fbo.depthBuffer > -1) {
/* 2203 */       setupFBO(fbo);
/* 2204 */       fbo.depthBuffer = -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void setupFBO(Framebuffer fbo) {
/* 2210 */     EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.depthBuffer);
/* 2211 */     int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
/* 2212 */     EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferID);
/* 2213 */     EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, mc.displayWidth, mc.displayHeight);
/* 2214 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferID);
/* 2215 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferID);
/*      */   }
/*      */   
/*      */   public static class RenderTesselator
/*      */     extends Tessellator
/*      */   {
/* 2221 */     public static RenderTesselator INSTANCE = new RenderTesselator();
/*      */ 
/*      */     
/*      */     public RenderTesselator() {
/* 2225 */       super(2097152);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void prepare(int mode) {
/* 2230 */       prepareGL();
/* 2231 */       begin(mode);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void prepareGL() {
/* 2236 */       GL11.glBlendFunc(770, 771);
/* 2237 */       GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
/* 2238 */       GlStateManager.glLineWidth(1.5F);
/* 2239 */       GlStateManager.disableTexture2D();
/* 2240 */       GlStateManager.depthMask(false);
/* 2241 */       GlStateManager.enableBlend();
/* 2242 */       GlStateManager.disableDepth();
/* 2243 */       GlStateManager.disableLighting();
/* 2244 */       GlStateManager.disableCull();
/* 2245 */       GlStateManager.enableAlpha();
/* 2246 */       GlStateManager.color(1.0F, 1.0F, 1.0F);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void begin(int mode) {
/* 2251 */       INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void release() {
/* 2256 */       render();
/* 2257 */       releaseGL();
/*      */     }
/*      */ 
/*      */     
/*      */     public static void render() {
/* 2262 */       INSTANCE.draw();
/*      */     }
/*      */ 
/*      */     
/*      */     public static void releaseGL() {
/* 2267 */       GlStateManager.enableCull();
/* 2268 */       GlStateManager.depthMask(true);
/* 2269 */       GlStateManager.enableTexture2D();
/* 2270 */       GlStateManager.enableBlend();
/* 2271 */       GlStateManager.enableDepth();
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawBox(BlockPos blockPos, int argb, int sides) {
/* 2276 */       int a = argb >>> 24 & 0xFF;
/* 2277 */       int r = argb >>> 16 & 0xFF;
/* 2278 */       int g = argb >>> 8 & 0xFF;
/* 2279 */       int b = argb & 0xFF;
/* 2280 */       drawBox(blockPos, r, g, b, a, sides);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawBox(float x, float y, float z, int argb, int sides) {
/* 2285 */       int a = argb >>> 24 & 0xFF;
/* 2286 */       int r = argb >>> 16 & 0xFF;
/* 2287 */       int g = argb >>> 8 & 0xFF;
/* 2288 */       int b = argb & 0xFF;
/* 2289 */       drawBox(INSTANCE.getBuffer(), x, y, z, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
/* 2294 */       drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */ 
/*      */     
/*      */     public static BufferBuilder getBufferBuilder() {
/* 2299 */       return INSTANCE.getBuffer();
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawBox(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
/* 2304 */       if ((sides & 0x1) != 0) {
/* 2305 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/* 2306 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/* 2307 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/* 2308 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2310 */       if ((sides & 0x2) != 0) {
/* 2311 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/* 2312 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/* 2313 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/* 2314 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2316 */       if ((sides & 0x4) != 0) {
/* 2317 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/* 2318 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/* 2319 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/* 2320 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2322 */       if ((sides & 0x8) != 0) {
/* 2323 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/* 2324 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/* 2325 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/* 2326 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2328 */       if ((sides & 0x10) != 0) {
/* 2329 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/* 2330 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/* 2331 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/* 2332 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2334 */       if ((sides & 0x20) != 0) {
/* 2335 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/* 2336 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/* 2337 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/* 2338 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawLines(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
/* 2344 */       if ((sides & 0x11) != 0) {
/* 2345 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/* 2346 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2348 */       if ((sides & 0x12) != 0) {
/* 2349 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/* 2350 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2352 */       if ((sides & 0x21) != 0) {
/* 2353 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/* 2354 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2356 */       if ((sides & 0x22) != 0) {
/* 2357 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/* 2358 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2360 */       if ((sides & 0x5) != 0) {
/* 2361 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/* 2362 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2364 */       if ((sides & 0x6) != 0) {
/* 2365 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/* 2366 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2368 */       if ((sides & 0x9) != 0) {
/* 2369 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/* 2370 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2372 */       if ((sides & 0xA) != 0) {
/* 2373 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/* 2374 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2376 */       if ((sides & 0x14) != 0) {
/* 2377 */         buffer.pos(x, y, z).color(r, g, b, a).endVertex();
/* 2378 */         buffer.pos(x, (y + h), z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2380 */       if ((sides & 0x24) != 0) {
/* 2381 */         buffer.pos((x + w), y, z).color(r, g, b, a).endVertex();
/* 2382 */         buffer.pos((x + w), (y + h), z).color(r, g, b, a).endVertex();
/*      */       } 
/* 2384 */       if ((sides & 0x18) != 0) {
/* 2385 */         buffer.pos(x, y, (z + d)).color(r, g, b, a).endVertex();
/* 2386 */         buffer.pos(x, (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/* 2388 */       if ((sides & 0x28) != 0) {
/* 2389 */         buffer.pos((x + w), y, (z + d)).color(r, g, b, a).endVertex();
/* 2390 */         buffer.pos((x + w), (y + h), (z + d)).color(r, g, b, a).endVertex();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
/* 2396 */       GlStateManager.pushMatrix();
/* 2397 */       GlStateManager.enableBlend();
/* 2398 */       GlStateManager.disableDepth();
/* 2399 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/* 2400 */       GlStateManager.disableTexture2D();
/* 2401 */       GlStateManager.depthMask(false);
/* 2402 */       GL11.glEnable(2848);
/* 2403 */       GL11.glHint(3154, 4354);
/* 2404 */       GL11.glLineWidth(width);
/* 2405 */       Tessellator tessellator = Tessellator.getInstance();
/* 2406 */       BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 2407 */       bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
/* 2408 */       bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2409 */       bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2410 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2411 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2412 */       bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2413 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2414 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2415 */       bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2416 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2417 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2418 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2419 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
/* 2420 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2421 */       bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2422 */       bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2423 */       bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
/* 2424 */       tessellator.draw();
/* 2425 */       GL11.glDisable(2848);
/* 2426 */       GlStateManager.depthMask(true);
/* 2427 */       GlStateManager.enableDepth();
/* 2428 */       GlStateManager.enableTexture2D();
/* 2429 */       GlStateManager.disableBlend();
/* 2430 */       GlStateManager.popMatrix();
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawFullBox(AxisAlignedBB bb, BlockPos blockPos, float width, int argb, int alpha2) {
/* 2435 */       int a = argb >>> 24 & 0xFF;
/* 2436 */       int r = argb >>> 16 & 0xFF;
/* 2437 */       int g = argb >>> 8 & 0xFF;
/* 2438 */       int b = argb & 0xFF;
/* 2439 */       drawFullBox(bb, blockPos, width, r, g, b, a, alpha2);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawFullBox(AxisAlignedBB bb, BlockPos blockPos, float width, int red, int green, int blue, int alpha, int alpha2) {
/* 2444 */       prepare(7);
/* 2445 */       drawBox(blockPos, red, green, blue, alpha, 63);
/* 2446 */       release();
/* 2447 */       drawBoundingBox(bb, width, red, green, blue, alpha2);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawHalfBox(BlockPos blockPos, int argb, int sides) {
/* 2452 */       int a = argb >>> 24 & 0xFF;
/* 2453 */       int r = argb >>> 16 & 0xFF;
/* 2454 */       int g = argb >>> 8 & 0xFF;
/* 2455 */       int b = argb & 0xFF;
/* 2456 */       drawHalfBox(blockPos, r, g, b, a, sides);
/*      */     }
/*      */ 
/*      */     
/*      */     public static void drawHalfBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
/* 2461 */       drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0F, 0.5F, 1.0F, r, g, b, a, sides);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class GeometryMasks
/*      */   {
/* 2467 */     public static final HashMap<EnumFacing, Integer> FACEMAP = new HashMap<>();
/*      */     
/*      */     static {
/* 2470 */       FACEMAP.put(EnumFacing.DOWN, Integer.valueOf(1));
/* 2471 */       FACEMAP.put(EnumFacing.WEST, Integer.valueOf(16));
/* 2472 */       FACEMAP.put(EnumFacing.NORTH, Integer.valueOf(4));
/* 2473 */       FACEMAP.put(EnumFacing.SOUTH, Integer.valueOf(8));
/* 2474 */       FACEMAP.put(EnumFacing.EAST, Integer.valueOf(32));
/* 2475 */       FACEMAP.put(EnumFacing.UP, Integer.valueOf(2));
/*      */     }
/*      */     
/*      */     public static final class Line {
/*      */       public static final int DOWN_WEST = 17;
/*      */       public static final int UP_WEST = 18;
/*      */       public static final int DOWN_EAST = 33;
/*      */       public static final int UP_EAST = 34;
/*      */       public static final int DOWN_NORTH = 5;
/*      */       public static final int UP_NORTH = 6;
/*      */       public static final int DOWN_SOUTH = 9;
/*      */       public static final int UP_SOUTH = 10;
/*      */       public static final int NORTH_WEST = 20;
/*      */       public static final int NORTH_EAST = 36;
/*      */       public static final int SOUTH_WEST = 24;
/*      */       public static final int SOUTH_EAST = 40;
/*      */       public static final int ALL = 63;
/*      */     }
/*      */     
/*      */     public static final class Quad {
/*      */       public static final int DOWN = 1;
/*      */       public static final int UP = 2;
/*      */       public static final int NORTH = 4;
/*      */       public static final int SOUTH = 8;
/*      */       public static final int WEST = 16;
/*      */       public static final int EAST = 32;
/*      */       public static final int ALL = 63;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\akais\Downloads\1.8.0-release (1).jar!\me\earth\phobo\\util\RenderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
