package me.akaishin.cracked.features.modules.render;

import me.akaishin.cracked.event.events.TurnEvent;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CameraLock extends Module {
    private float dYaw = 0F;
    private float dPitch = 0F;
    private final Setting<Boolean> autoThirdPerson = this.register(new Setting<>("AutoThirdPerson", true));

    public CameraLock() {
        super("CameraLock", "Lets you look around", Module.Category.RENDER, true, false, false);
    }

    public void onEnable() {
        dYaw = 0;
        dPitch = 0;

        if (autoThirdPerson.getValue()) {
            mc.gameSettings.thirdPersonView = 1;
        }
    }

    public void onDisable() {
        if (autoThirdPerson.getValue()) {
            mc.gameSettings.thirdPersonView = 0;
        }
    }

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if (mc.gameSettings.thirdPersonView > 0) {
            event.setYaw(event.getYaw() + dYaw);
            event.setPitch(event.getPitch() + dPitch);
        }
    }

    @SubscribeEvent
    public void onTurnEvent(TurnEvent event) {
        if (mc.gameSettings.thirdPersonView > 0) {
            dYaw = (float) ((double) dYaw + (double) event.getYaw() * 0.15D);
            dPitch = (float) ((double) dPitch - (double) event.getPitch() * 0.15D);
            dPitch = MathHelper.clamp(dPitch, -90.0F, 90.0F);
            event.setCanceled(true);
        }
    }
}