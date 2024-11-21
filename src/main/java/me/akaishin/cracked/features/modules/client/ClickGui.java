package me.akaishin.cracked.features.modules.client;

import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.ClientEvent;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;

public class ClickGui extends Module
{
    public static ClickGui INSTANCE;
    public Setting<Boolean> colorSync;
    public Setting<Boolean> outline;
    public Setting<Boolean> outlineNew;
    public Setting<Float> outlineThickness;
    public Setting<Integer> o_red;
    public Setting<Integer> o_green;
    public Setting<Integer> o_blue;
    public Setting<Integer> o_alpha;
    public Setting<Boolean> rainbowRolling;
    public Setting<String> prefix;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> hoverAlpha;
    public Setting<Integer> alpha;
    public Setting<Boolean> blurEffect;
    public Setting<Boolean> snowing;
    public Setting<Boolean> gradiant;
    public Setting<Integer> backgroundAlpha;
    public Setting<Boolean> future;
    public Setting<Boolean> customFov;
    public Setting<Float> fov;
    public Setting<Boolean> openCloseChange;
    public Setting<String> open;
    public Setting<String> close;
    public Setting<String> moduleButton;
    public Setting<Boolean> devSettings;
    public Setting<Integer> topRed;
    public Setting<Integer> topGreen;
    public Setting<Integer> topBlue;
    public Setting<Integer> topAlpha;
    public Setting<Boolean> frameSettings;
    public Setting<Integer> frameRed;
    public Setting<Integer> frameGreen;
    public Setting<Integer> frameBlue;
    public Setting<Integer> frameAlpha;
    public Setting<Integer> newred;
    public Setting<Integer> newgreen;
    public Setting<Integer> newblue;
    public Setting<Integer> moduleListUpdates;
    public Setting<Boolean> backgroundScreen;
    public Setting<Boolean> logoWaifu;
    public Setting<Float> xWaifu;
    public Setting<Float> yWaifu;
    public Setting<Integer> heightWaifu;
    public Setting<Integer> widthWaifu;
    
    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT, true, false, false);
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", false));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", false));
        this.outlineNew = (Setting<Boolean>)this.register(new Setting<Boolean>("OutlineNew", false)); //add demars outline
        this.outlineThickness = (Setting<Float>)this.register(new Setting<Float>("LineThickness",2.5f, 0.5f, 5.0f, v -> this.outlineNew.getValue()));
        this.o_red = (Setting<Integer>)this.register(new Setting<Object>("OutlineRed", 135, 0, 255, v -> this.outlineNew.getValue()));
        this.o_green = (Setting<Integer>)this.register(new Setting<Object>("OutlineGreen", 135, 0, 255, v -> this.outlineNew.getValue()));
        this.o_blue = (Setting<Integer>)this.register(new Setting<Object>("OutlineBlue", 255, 0, 255, v -> this.outlineNew.getValue()));
        this.o_alpha = (Setting<Integer>)this.register(new Setting<Object>("OutlineAlpha", 255, 0, 255, v -> this.outlineNew.getValue()));
        this.rainbowRolling = (Setting<Boolean>)this.register(new Setting("RollingRainbow", false, v -> this.colorSync.getValue() && Colors.INSTANCE.rainbow.getValue()));
        this.prefix = (Setting<String>)this.register((Setting)new Setting<String>("Prefix", ".").setRenderName(true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", 0, 0, 255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", 154, 0, 255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", 0, 0, 255));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", 85, 0, 255));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", 200, 0, 255));
        this.blurEffect = (Setting<Boolean>)this.register(new Setting("Blur", false));
        this.snowing = (Setting<Boolean>)this.register(new Setting("Snowing", false));
        this.gradiant = (Setting<Boolean>)this.register(new Setting("gradiant", true));
        this.backgroundAlpha = (Setting<Integer>)this.register(new Setting("BackgroundAlpha", 28, 0, 255));
        this.backgroundScreen = (Setting<Boolean>)this.register(new Setting("BackgroundScreen", false));

        this.future = (Setting<Boolean>)this.register(new Setting("Future", true, "like future"));

        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", false));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", 150.0f, (-180.0f), 180.0f, v -> this.customFov.getValue()));

        this.openCloseChange = (Setting<Boolean>)this.register(new Setting("Open/Close", false));
        this.open = (Setting<String>)this.register((Setting)new Setting<String>("Open:", "", v -> this.openCloseChange.getValue()).setRenderName(true));
        this.close = (Setting<String>)this.register((Setting)new Setting<String>("Close:", "", v -> this.openCloseChange.getValue()).setRenderName(true));
        this.moduleButton = (Setting<String>)this.register((Setting)new Setting<String>("Buttons:", "", v -> !this.openCloseChange.getValue()).setRenderName(true));
        this.devSettings = (Setting<Boolean>)this.register(new Setting("TopSetting", true));
        this.topRed = (Setting<Integer>)this.register(new Setting("TopRed", 0, 0, 255, v -> this.devSettings.getValue()));
        this.topGreen = (Setting<Integer>)this.register(new Setting("TopGreen", 154, 0, 255, v -> this.devSettings.getValue()));
        this.topBlue = (Setting<Integer>)this.register(new Setting("TopBlue", 0, 0, 255, v -> this.devSettings.getValue()));
        this.topAlpha = (Setting<Integer>)this.register(new Setting("TopAlpha", 85, 0, 255, v -> this.devSettings.getValue()));
        this.frameSettings = (Setting<Boolean>)this.register(new Setting("FrameSetting", false));
        this.frameRed = (Setting<Integer>)this.register(new Setting("FrameRed", 255, 0, 255, v -> this.frameSettings.getValue()));
        this.frameGreen = (Setting<Integer>)this.register(new Setting("FrameGreen", 255, 0, 255, v -> this.frameSettings.getValue()));
        this.frameBlue = (Setting<Integer>)this.register(new Setting("FrameBlue", 255, 0, 255, v -> this.frameSettings.getValue()));
        this.frameAlpha = (Setting<Integer>)this.register(new Setting("FrameAlpha", 255, 0, 255, v -> this.frameSettings.getValue()));
        this.newred = (Setting<Integer>)this.register(new Setting("SideRed", 255, 0, 255));
        this.newgreen = (Setting<Integer>)this.register(new Setting("SideGreen", 255, 0, 255));
        this.newblue = (Setting<Integer>)this.register(new Setting("SideBlue", 255, 0, 255));
        this.moduleListUpdates = (Setting<Integer>)this.register(new Setting<Integer>("ALUpdates", 1000, 0, 1000));
        //Logo Gui
        this.logoWaifu = (Setting<Boolean>)this.register(new Setting("Waifu", true));
        this.xWaifu = (Setting<Float>)this.register(new Setting("X", 728.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
        this.yWaifu = (Setting<Float>)this.register(new Setting("Y", 255.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
        
        this.heightWaifu = (Setting<Integer>)this.register(new Setting("Height",267, 0, 1000, v -> this.logoWaifu.getValue()));
        this.widthWaifu = (Setting<Integer>)this.register(new Setting("Width", 207, 0, 1000, v -> this.logoWaifu.getValue()));

        this.setInstance();
    }
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Cracked.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to \u00A7a" + Cracked.commandManager.getPrefix());
            }
            Cracked.colorCrackManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }
    
    @Override
    public void onEnable() {
        ClickGui.mc.displayGuiScreen((GuiScreen)CrackedHackGui.getClickGui());
        if (this.blurEffect.getValue()) {
            ClickGui.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }
    
    @Override
    public void onLoad() {
        if (this.colorSync.getValue()) {
            Cracked.colorCrackManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), this.hoverAlpha.getValue());
        }
        else {
            Cracked.colorCrackManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        }
        Cracked.commandManager.setPrefix(this.prefix.getValue());
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof CrackedHackGui)) {
            this.disable();
            if (ClickGui.mc.entityRenderer.getShaderGroup() != null) {
                ClickGui.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (ClickGui.mc.currentScreen instanceof CrackedHackGui) {
            ClickGui.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
}
