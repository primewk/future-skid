package me.akaishin.cracked.features.notifications.old;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.util.RenderUtil;
import me.akaishin.cracked.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class NotificationsOld {
    private final String text;
    private final long disableTime;
    private final float width;
    private final Timer timer = new Timer();

    public NotificationsOld(String text, long disableTime) {
        this.text = text;
        this.disableTime = disableTime;
        this.width = Cracked.moduleManager.getModuleByClass(HUD.class).renderer.getStringWidth(text);
        this.timer.reset();
    }

    public void onDraw(int y) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        if (this.timer.passedMs(this.disableTime)) {
            Cracked.notificationManager.getNotifications().remove(this);
        }
        RenderUtil.drawRect((float) (scaledResolution.getScaledWidth() - 4) - this.width, y, scaledResolution.getScaledWidth() - 2, y + Cracked.moduleManager.getModuleByClass(HUD.class).renderer.getFontHeight() + 3, 0x75000000);
        Cracked.moduleManager.getModuleByClass(HUD.class).renderer.drawString(this.text, (float) scaledResolution.getScaledWidth() - this.width - 3.0f, y + 2, -1, true);
    }
}

