package me.akaishin.cracked.features.modules.render;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.ClientEvent;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OneChunk
        extends Module {
    private static OneChunk INSTANCE = new OneChunk();
    public Setting<Boolean> oneChunk = this.register(new Setting<Boolean>("OneChunk", false));

    public OneChunk() {
        super("OneChunk", "OneChunk ++FPS", Module.Category.RENDER, false, false, true);
        this.setInstance();
    }

    public static OneChunk getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OneChunk();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2) {
            if (this.oneChunk.getPlannedValue().booleanValue()) 
                OneChunk.mc.gameSettings.renderDistanceChunks = 1;
            }
        }
    
}
