//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.render;

import org.lwjgl.input.*;

import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.setting.*;

public class ThirdPerson extends Module
{
    public Setting<Boolean> onlyHold;
    public Setting<Bind> bind;
    
    public ThirdPerson() {
        super("ThirdPerson", "Third person camera but hold bind.", Category.RENDER, true, false, false);
        this.onlyHold = (Setting<Boolean>)this.register(new Setting("OnlyHoldBind", false));
        this.bind = (Setting<Bind>)this.register(new Setting("Bind:", new Bind(-1)));
    }
    
    @Override
    public void onUpdate() {
        if (this.bind.getCurrentState().getKey() > -1) {
            if (Keyboard.isKeyDown(this.bind.getCurrentState().getKey())) {
                ThirdPerson.mc.gameSettings.thirdPersonView = 1;
            }
            else {
                ThirdPerson.mc.gameSettings.thirdPersonView = 0;
            }
        }
    }
    
    @Override
    public void onEnable() {
        if (!this.onlyHold.getCurrentState()) {
            ThirdPerson.mc.gameSettings.thirdPersonView = 1;
        }
    }
    
    @Override
    public void onDisable() {
        if (!this.onlyHold.getCurrentState()) {
            ThirdPerson.mc.gameSettings.thirdPersonView = 0;
        }
    }
}
