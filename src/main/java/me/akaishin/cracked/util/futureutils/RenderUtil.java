//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

/*      */ package me.akaishin.cracked.util.futureutils;
/*      */ import net.minecraft.client.renderer.BufferBuilder;
/*      */ import net.minecraft.client.renderer.GlStateManager;
           import net.minecraft.client.renderer.RenderItem;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.culling.Frustum;
/*      */ import net.minecraft.client.renderer.culling.ICamera;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ public class RenderUtil implements Util {
/*      */   public static RenderItem Field4504;
/*      */   public static ICamera Field4505;
/*      */   
/*      */   static {
/*   54 */     Field4504 = Field3935.getRenderItem();
/*   55 */     Field4505 = (ICamera)new Frustum();
/*      */   }


/*      */   public static void Method510(float x, float y, float w, float h, int color) {
/* 2004 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/* 2005 */     float red = (color >> 16 & 0xFF) / 255.0F;
/* 2006 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 2007 */     float blue = (color & 0xFF) / 255.0F;
/* 2008 */     Tessellator tessellator = Tessellator.getInstance();
/* 2009 */     BufferBuilder bufferbuilder = tessellator.getBuffer();
/* 2010 */     GlStateManager.enableBlend();
/* 2011 */     GlStateManager.disableTexture2D();
/* 2012 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 2013 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 2014 */     bufferbuilder.pos(x, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 2015 */     bufferbuilder.pos(w, h, 0.0D).color(red, green, blue, alpha).endVertex();
/* 2016 */     bufferbuilder.pos(w, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 2017 */     bufferbuilder.pos(x, y, 0.0D).color(red, green, blue, alpha).endVertex();
/* 2018 */     tessellator.draw();
/* 2019 */     GlStateManager.enableTexture2D();
/* 2020 */     GlStateManager.disableBlend();
/*      */   }


/*      */   
/*      */   public static final void Method4138(float left, float top, float right, float bottom, int color) {
/* 2923 */     GL11.glEnable(3042);
/* 2924 */     GL11.glEnable(2848);
/* 2925 */     Method510(left, top, right, bottom, color);
/* 2926 */     GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 2927 */     Method510(left * 2.0F - 1.0F, top * 2.0F, left * 2.0F, bottom * 2.0F - 1.0F, color);
/* 2928 */     Method510(left * 2.0F, top * 2.0F - 1.0F, right * 2.0F, top * 2.0F, color);
/* 2929 */     Method510(right * 2.0F, top * 2.0F, right * 2.0F + 1.0F, bottom * 2.0F - 1.0F, color);
/* 2930 */     Method510(left * 2.0F, bottom * 2.0F - 1.0F, right * 2.0F, bottom * 2.0F, color);
/* 2931 */     GL11.glDisable(3042);
/* 2932 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
            }
}

