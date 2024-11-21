//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.util.autocrystal;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import javax.annotation.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.gui.*;

public class Wrapper
{
    public static final Minecraft mc;
    
    @Nullable
    public static EntityPlayerSP getPlayer() {
        return Wrapper.mc.player;
    }
    
    @Nullable
    public static WorldClient getWorld() {
        return Wrapper.mc.world;
    }
    
    public static FontRenderer getFontRenderer() {
        return Wrapper.mc.fontRenderer;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
