package me.akaishin.cracked.features.modules.client;

import me.akaishin.cracked.features.modules.*;
import me.akaishin.cracked.features.setting.*;

public class MainMenu extends Module
{
    private static MainMenu INSTANCE;
    public Setting<Boolean> mainScreen;
    public Setting<Boolean> particles;
    
    public MainMenu() {
        super("MainMenu", "MainMenuScreen", Category.CLIENT, true, false, false);
        this.mainScreen = (Setting<Boolean>)this.register(new Setting("MainScreen", true));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", true));
        this.setInstance();
    }
    
    public static MainMenu getInstance() {
        if (MainMenu.INSTANCE == null) {
            MainMenu.INSTANCE = new MainMenu();
        }
        return MainMenu.INSTANCE;
    }
    
    private void setInstance() {
        MainMenu.INSTANCE = this;
    }
    
    //@Override
    public void onRender3D() {
    }
    
    static {
        MainMenu.INSTANCE = new MainMenu();
    }
}
