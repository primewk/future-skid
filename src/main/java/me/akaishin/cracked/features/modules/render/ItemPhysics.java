//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.render;

import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.setting.*;

public class ItemPhysics extends Module
{
    public static ItemPhysics INSTANCE;
    public final Setting<Float> Scaling;
    
    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
        this.Scaling = (Setting<Float>)this.register(new Setting("Scaling", 0.5f, 0.0f, 10.0f));
        this.setInstance();
    }
    
    public static ItemPhysics getInstance() {
        if (ItemPhysics.INSTANCE == null) {
            ItemPhysics.INSTANCE = new ItemPhysics();
        }
        return ItemPhysics.INSTANCE;
    }
    
    private void setInstance() {
        ItemPhysics.INSTANCE = this;
    }
    
    static {
        ItemPhysics.INSTANCE = new ItemPhysics();
    }
}
