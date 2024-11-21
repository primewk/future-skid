package me.akaishin.cracked.features.modules.client;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.ClientEvent;
import me.akaishin.cracked.features.future.guifutureold.futuregui.FutureGuiExeter;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FutureGuiB8 extends Module
{
    public static FutureGuiB8 INSTANCE;
           public Setting<Boolean> colorSync = this.register(new Setting("Sync", Boolean.valueOf(false)));
           public Setting<Integer> red = this.register(new Setting("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
           public Setting<Integer> green = this.register(new Setting("Green", Integer.valueOf(145), Integer.valueOf(0), Integer.valueOf(255)));
           public Setting<Integer> blue = this.register(new Setting("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
           public Setting<Integer> alpha = this.register(new Setting("Alpha", Integer.valueOf(176), Integer.valueOf(0), Integer.valueOf(255)));
           public Setting<Integer> hoverAlpha = this.register(new Setting("HoverAlpha", Integer.valueOf(128), Integer.valueOf(0), Integer.valueOf(255)));
           public Setting<Boolean> future = (Setting<Boolean>)this.register(new Setting("Future", true, "like future"));
           public Setting<Boolean> backgroundScreen = (Setting<Boolean>)this.register(new Setting("BackgroundScreen", false));
           public Setting<Boolean> logoWaifu = (Setting<Boolean>)this.register(new Setting("Waifu", true));
           public Setting<Float> xWaifu = (Setting<Float>)this.register(new Setting("X", 728.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
           public Setting<Float> yWaifu = (Setting<Float>)this.register(new Setting("Y", 255.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
           public Setting<Integer> heightWaifu = (Setting<Integer>)this.register(new Setting("Height",267, 0, 1000, v -> this.logoWaifu.getValue()));
           public Setting<Integer> widthWaifu = (Setting<Integer>)this.register(new Setting("Width", 207, 0, 1000, v -> this.logoWaifu.getValue()));

           
    public FutureGuiB8() {
        super("FutureGuiB8", "Opens the ClickGui", Category.CLIENT, true, false, false);
         this.setInstance();
    }
    
    public static FutureGuiB8 getInstance() {
        if (FutureGuiB8.INSTANCE == null) {
            FutureGuiB8.INSTANCE = new FutureGuiB8();
        }
        return FutureGuiB8.INSTANCE;
    }
    
    private void setInstance() {
        FutureGuiB8.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        /*if (this.customFov.getValue()) {
            FutureGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }*/
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            Cracked.colorExeterGuiManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());

    }
}
    
    @Override
    public void onEnable() {
        FutureGuiB8.mc.displayGuiScreen((GuiScreen)FutureGuiExeter.getFutureGuiOld());
       /*  if (this.blurEffect.getValue()) {
            FutureGui.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }*/
    }
    
    @Override
    public void onLoad() {
        if (this.colorSync.getValue()) {
            Cracked.colorExeterGuiManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), this.hoverAlpha.getValue());
        }
        else {
            Cracked.colorExeterGuiManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        }
        //Cracked.commandManager.setPrefix(this.prefix.getValue());
    }
    
    @Override
    public void onTick() {
        if (!(FutureGuiB8.mc.currentScreen instanceof FutureGuiExeter)) {
            this.disable();
            if (FutureGuiB8.mc.entityRenderer.getShaderGroup() != null) {
                FutureGuiB8.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (FutureGuiB8.mc.currentScreen instanceof FutureGuiExeter) {
            FutureGuiB8.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    static {
        FutureGuiB8.INSTANCE = new FutureGuiB8();
    }
}