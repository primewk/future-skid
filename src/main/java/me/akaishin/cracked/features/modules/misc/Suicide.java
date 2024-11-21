package me.akaishin.cracked.features.modules.misc;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;

public class Suicide
        extends Module {
    public Suicide() {
        super("Suicide", "Auto suicide.", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        if (Suicide.mc.player != null) {
            Suicide.mc.player.sendChatMessage("/kill");
        }
        this.toggle();
    }
}