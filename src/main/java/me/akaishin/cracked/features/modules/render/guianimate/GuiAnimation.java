package me.akaishin.cracked.features.modules.render.guianimate;

import me.akaishin.cracked.util.guianimateutils.FadeUtils;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;

public class GuiAnimation
extends Module {

    public static GuiAnimation INSTANCE = new GuiAnimation();
    
        static {
        GuiAnimation.INSTANCE = new GuiAnimation();
    }
    private final Setting<Integer> inventoryTime = this.register(new Setting<>("InventoryTime", 500, 0, 2000));
    public static final FadeUtils inventoryFade;

    public GuiAnimation() {
        super("GuiAnimation", "GuiAnimation Good Animation", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static GuiAnimation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuiAnimation();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        inventoryFade.setLength(this.inventoryTime.getValue());
        if (GuiAnimation.mc.currentScreen == null) {
            inventoryFade.reset();
        }
    }

    static {
        inventoryFade = new FadeUtils(500L);
    }
}

