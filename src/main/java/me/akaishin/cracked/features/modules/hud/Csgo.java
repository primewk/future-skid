package me.akaishin.cracked.features.modules.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.util.ColorUtil;
import me.akaishin.cracked.util.MathUtil;
import me.akaishin.cracked.util.RenderUtil;

import java.awt.Color;
import java.util.Objects;

public class Csgo
        extends Module {
	public Setting<Pages> page = this.register(new Setting<Pages>("Page", Pages.Watermark));
	public Setting<Colorsp> colorp = this.register(new Setting<Colorsp>("ColorPage", Colorsp.First, v -> this.page.getValue() == Pages.BarColor));
	public Setting<Integer> posY = this.register(new Setting<Integer>("PosY", 2, 0, 512, v -> this.page.getValue() == Pages.Watermark));
	public Setting<Double> barPosY = this.register(new Setting<Double>("BarPosY", 0.0, -2.0, 15.0, v -> this.page.getValue() == Pages.Watermark));
	public Setting<Double> textPosY = this.register(new Setting<Double>("TextPosY", 0.0, -6.0, 3.0, v -> this.page.getValue() == Pages.Watermark));
	public Setting<Integer> bgRed = this.register(new Setting<Integer>("BackgroundRed", 20, 0, 255, v -> this.page.getValue() == Pages.Background));
	public Setting<Integer> bgGreen = this.register(new Setting<Integer>("BackgroundGreen", 20, 0, 255, v -> this.page.getValue() == Pages.Background));
	public Setting<Integer> bgBlue = this.register(new Setting<Integer>("BackgroundBlue", 20, 0, 255, v -> this.page.getValue() == Pages.Background));
	public Setting<Integer> bgAlpha = this.register(new Setting<Integer>("BackgroundAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.Background));
	public Setting<Integer> bRed = this.register(new Setting<Integer>("BorderRed", 0, 0, 255, v -> this.page.getValue() == Pages.Border));
	public Setting<Integer> bGreen = this.register(new Setting<Integer>("BorderGreen", 0, 0, 255, v -> this.page.getValue() == Pages.Border));
	public Setting<Integer> bBlue = this.register(new Setting<Integer>("BorderBlue", 0, 0, 255, v -> this.page.getValue() == Pages.Border));
	public Setting<Integer> bAlpha = this.register(new Setting<Integer>("BorderAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.Border));
	public Setting<Integer> firstRed = this.register(new Setting<Integer>("FirstRed", 135, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.First));
	public Setting<Integer> firstGreen = this.register(new Setting<Integer>("FirstGreen", 135, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.First));
	public Setting<Integer> firtBlue = this.register(new Setting<Integer>("FirstBlue", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.First));
	public Setting<Integer> firstAlpha = this.register(new Setting<Integer>("FirstAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.First));
	public Setting<Integer> secondRed = this.register(new Setting<Integer>("SecondRed", 0, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Second));
	public Setting<Integer> secondGreen = this.register(new Setting<Integer>("SecondGreen", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Second));
	public Setting<Integer> secondBlue = this.register(new Setting<Integer>("SecondBlue", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Second));
	public Setting<Integer> secondAlpha = this.register(new Setting<Integer>("SecondAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Second));
	public Setting<Integer> thirdRed = this.register(new Setting<Integer>("ThirdRed", 135, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Third));
	public Setting<Integer> thirdGreen = this.register(new Setting<Integer>("ThirdGreen", 135, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Third));
	public Setting<Integer> thirdBlue = this.register(new Setting<Integer>("ThirdBlue", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Third));
	public Setting<Integer> thirdAlpha = this.register(new Setting<Integer>("ThirdAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Third));
	public Setting<Integer> fourthRed = this.register(new Setting<Integer>("FourthRed", 0, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Fourth));
	public Setting<Integer> fourthGreen = this.register(new Setting<Integer>("FourthGreen", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Fourth));
	public Setting<Integer> fourthBlue = this.register(new Setting<Integer>("FourthBlue", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Fourth));
	public Setting<Integer> fourthAlpha = this.register(new Setting<Integer>("FourthAlpha", 255, 0, 255, v -> this.page.getValue() == Pages.BarColor && this.colorp.getValue() == Colorsp.Fourth));
	
    public Csgo() {
        super("Csgo", "Hot csgo watermark", Module.Category.HUD, true, false, false);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (Csgo.nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
			String ping = this.getPing((EntityPlayer)Csgo.mc.player) + "ms";
            String fpsText = Minecraft.debugFPS + "fps ";
			String name = mc.player.getDisplayNameString();
			String server = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toLowerCase() : Csgo.mc.getCurrentServerData().serverIP.toLowerCase();
			String Cracked = "Cracked";
			String text = Cracked + " | " + server + " | " + ping + " | " + fpsText;
            float width = Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 6;
            int height = 20;
            int posX = 2;
            int posY = this.posY.getValue();
			double barPosY = this.barPosY.getValue();
			double textPosY = this.textPosY.getValue();
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 10.0f), height + 6, ColorUtil.toRGBA(this.bRed.getValue(), this.bGreen.getValue(), this.bBlue.getValue(), this.bAlpha.getValue()));
            RenderUtil.drawRectangleCorrectly(posX - 4, posY - 4, (int)(width + 11.0f), height + 7, ColorUtil.toRGBA(this.bRed.getValue(), this.bGreen.getValue(), this.bBlue.getValue(), this.bAlpha.getValue()));
            Csgo.drawRect(posX, posY, (float)posX + width + 2.0f, posY + height, new Color(this.bgRed.getValue(), this.bgGreen.getValue(), this.bgBlue.getValue(), this.bgAlpha.getValue()).getRGB());
            Csgo.drawRect((double)posX + 2.5, (double)posY + 2.5, (double)((float)posX + width) - 0.5, (double)posY + 4.5, new Color(this.bgRed.getValue(), this.bgGreen.getValue(), this.bgBlue.getValue(), this.bgAlpha.getValue()).getRGB());
            Csgo.drawGradientSideways(4.0, (posY + barPosY) + 3, 4.0f + width / 3.0f, (posY + barPosY) + 4, new Color(this.firstRed.getValue(), this.firstGreen.getValue(), this.firtBlue.getValue(), this.firstAlpha.getValue()).getRGB(), new Color(this.secondRed.getValue(), this.secondGreen.getValue(), this.secondBlue.getValue(), this.secondAlpha.getValue()).getRGB());
            Csgo.drawGradientSideways(4.0f + width / 3.0f, (posY + barPosY) + 3, 4.0f + width / 3.0f * 2.0f, (posY + barPosY) + 4, new Color(this.secondRed.getValue(), this.secondGreen.getValue(), this.secondBlue.getValue(), this.secondAlpha.getValue()).getRGB(), new Color(this.thirdRed.getValue(), this.thirdGreen.getValue(), this.thirdBlue.getValue(), this.thirdAlpha.getValue()).getRGB());
            Csgo.drawGradientSideways(4.0f + width / 3.0f * 2.0f, (posY + barPosY) + 3, width / 3.0f * 3.0f + 1.0f, (posY + barPosY) + 4, new Color(this.thirdRed.getValue(), this.thirdGreen.getValue(), this.thirdBlue.getValue(), this.thirdAlpha.getValue()).getRGB(), new Color(this.fourthRed.getValue(), this.fourthGreen.getValue(), this.fourthBlue.getValue(), this.fourthAlpha.getValue()).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, (float)(4 + posX), (float)(8 + (posY + textPosY)), -1);
        }
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f, (float)f1, (float)f2, (float)f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
		GL11.glBlendFunc((int) 770, (int) 771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    private int getPing(EntityPlayer player) {
        int ping = 0;
        try {
            ping = (int)MathUtil.clamp((float)Objects.requireNonNull(mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1.0f, 300.0f);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return ping;
    }
	
	public static enum Pages {
		Watermark,
		BarColor,
        Background,
        Border;
    }
	
	public static enum Colorsp {
		First,
		Second,
        Third,
        Fourth;
    }
}
