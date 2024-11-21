package me.akaishin.cracked.features.modules.misc;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;

public class InventoryMove
extends Module {
    private static InventoryMove INSTANCE = new InventoryMove();
    public final Setting<Boolean> shift = this.register(new Setting<>("Sneak", false));

    public InventoryMove() {
    super("InventoryMove", "InventoryMove GooD", Module.Category.MISC, true, false, false);
        this.setInstance();
    }

    public static InventoryMove INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new InventoryMove();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

