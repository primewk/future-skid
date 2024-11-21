package me.akaishin.cracked.util.soundutil;

import java.io.BufferedInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SoundUtill {
    public static void playSound(final ResourceLocation rl) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(Minecraft.getMinecraft().getResourceManager().getResource(rl).getInputStream());
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                    clip.open(inputStream);
                    clip.start();
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            }
        }).start();
    }
}