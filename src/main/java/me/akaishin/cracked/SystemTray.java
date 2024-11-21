package me.akaishin.cracked;

import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SystemTray {
    public static void sysTray() {
        if (java.awt.SystemTray.isSupported()) {
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            BufferedImage image = null;
            try {
                InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("textures/icons/icon-32x.png")).getInputStream();
                image = ImageIO.read(inputStream);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            MouseListener mouseListener = new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                }
            };
            TrayIcon trayIcon = new TrayIcon(image, "Cracked Client");
            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(mouseListener);
            try {
                tray.add(trayIcon);
            }
            catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }
}
