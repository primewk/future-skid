package me.akaishin.cracked.manager;

import java.util.ArrayList;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.notifications.old.NotificationsOld;

public class NotificationManager {
    private final ArrayList<NotificationsOld> notifications = new ArrayList();

    public void handleNotifications(int posY) {
        for (int i = 0; i < this.getNotifications().size(); ++i) {
            this.getNotifications().get(i).onDraw(posY);
            posY -= Cracked.moduleManager.getModuleByClass(HUD.class).renderer.getFontHeight() + 5;
        }
    }

    public void addNotification(String text, long duration) {
        this.getNotifications().add(new NotificationsOld(text, duration));
    }

    public ArrayList<NotificationsOld> getNotifications() {
        return this.notifications;
    }
}

