//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.client;

import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.setting.*;

public class Particles extends Module
{
    private static Particles INSTANCE;
    public Setting<Integer> particleLength;
    public Setting<Integer> particlered;
    public Setting<Integer> particlegreen;
    public Setting<Integer> particleblue;

    
    public Particles() {
        super("Particles", "Sex", Category.CLIENT, true, false, false);
        this.particleLength = (Setting<Integer>)this.register(new Setting("ParticleLength", 60, 0, 300));
        this.particlered = (Setting<Integer>)this.register(new Setting("ParticleRed", 0, 0, 255));
        this.particlegreen = (Setting<Integer>)this.register(new Setting("ParticleGreen", 255, 0, 255));
        this.particleblue = (Setting<Integer>)this.register(new Setting("ParticleBlue", 0, 0, 255));
        this.setInstance();
    }
    
    public static Particles getInstance() {
        if (Particles.INSTANCE == null) {
            Particles.INSTANCE = new Particles();
        }
        return Particles.INSTANCE;
    }
    
    private void setInstance() {
        Particles.INSTANCE = this;
    }
    
    static {
        Particles.INSTANCE = new Particles();
    }
    
}
