//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.misc;

import com.mojang.text2speech.*;

import me.akaishin.cracked.features.modules.*;

public class NarratorAu extends Module
{
    private final Narrator narrator;
    
    public NarratorAu() {
        super("TestSound", "narrator but sex", Category.MISC, true, false, false);
        this.narrator = Narrator.getNarrator();
    }
    
    @Override
    public void onEnable() {
        if (this.isNull()) {
            return;
        }
        this.narrator.say("Enable!");
        this.narrator.say("hello");
        this.narrator.say("iuiuiuuuiuiuuuuuuiuuu");
        this.narrator.say("ññññññ");
        this.narrator.say("what?");
        this.narrator.say("Disable");
    }
    
    @Override
    public void onDisable() {
        if (this.isNull()) {
            return;
        }
        this.narrator.clear();
    }
}
