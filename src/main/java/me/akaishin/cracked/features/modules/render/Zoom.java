//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.render;

import me.akaishin.cracked.features.modules.*;

public class Zoom extends Module
{
    public Zoom() {
        super("Zoom", "Zoom", Category.MISC, true, false, true);
    }
    
    @Override
    public void onUpdate() {
        Zoom.mc.gameSettings.fovSetting = 10.0f;
    }
    
    @Override
    public void onDisable() {
        Zoom.mc.gameSettings.fovSetting = 120.0f;
    }
}
