package me.akaishin.cracked.features.modules.misc;

import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.modules.Module;
import net.minecraft.inventory.ContainerChest;

public class AntiChestGui
extends Module {
    public AntiChestGui() {
        super("AntiChestGui", "AntiChestGui no chest", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Feature.fullNullCheck()) {
            return;
        }
        if (AntiChestGui.mc.player.openContainer instanceof ContainerChest) {
            AntiChestGui.mc.player.closeScreen();
        }
    }
}

