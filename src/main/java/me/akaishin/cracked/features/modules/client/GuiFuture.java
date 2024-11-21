//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.modules.client;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.ClientEvent;
import me.akaishin.cracked.features.future.guinew.gui.util.Util;
import me.akaishin.cracked.features.command.Command;
import  me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiFuture extends Module {
   public static GuiFuture INSTANCE = new GuiFuture();
   public Setting<Boolean> colorSync = register(new Setting("Sync", Boolean.valueOf(false)));
   public Setting<Boolean> outline = register(new Setting("Outline", Boolean.valueOf(false)));
   public Setting<Boolean> rainbowRolling = register(new Setting("RollingRainbow", Boolean.valueOf(false), v -> (((Boolean)this.colorSync.getValue()).booleanValue() && ((Boolean)Colors.INSTANCE.rainbow.getValue()).booleanValue())));
   public Setting<String> prefix = register((new Setting("Prefix", ".")).setRenderName(true));
   public Setting<Boolean> outline2 = register(new Setting("Outline2", Boolean.valueOf(false)));
   public Setting<Float> outlineThickness = register(new Setting("LineThickness", Float.valueOf(1.0F), Float.valueOf(0.5F), Float.valueOf(5.0F), v -> ((Boolean)this.outline2.getValue()).booleanValue()));
   public Setting<Integer> o_red = register(new Setting("OutlineRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline2.getValue()).booleanValue()));
   public Setting<Integer> o_green = register(new Setting("OutlineGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline2.getValue()).booleanValue()));
   public Setting<Integer> o_blue = register(new Setting("OutlineBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline2.getValue()).booleanValue()));
   public Setting<Integer> o_alpha = register(new Setting("OutlineAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.outline2.getValue()).booleanValue()));
   public Setting<Boolean> snowing = register(new Setting("Snowing", Boolean.valueOf(true)));
   public Setting<Boolean> moduleOutline = register(new Setting("Module Outline", Boolean.valueOf(false)));
   public Setting<Integer> moRed = register(new Setting("Module OutlineRed", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.moduleOutline.getValue()).booleanValue()));
   public Setting<Integer> moGreen = register(new Setting("Module OutlineGreen", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.moduleOutline.getValue()).booleanValue()));
   public Setting<Integer> moBlue = register(new Setting("Module OutlineBlue", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.moduleOutline.getValue()).booleanValue()));
   public Setting<Integer> moAlpha = register(new Setting("Module OutlineAlpha", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.moduleOutline.getValue()).booleanValue()));
   public Setting<Boolean> openCloseChange = register(new Setting("Open/Close", Boolean.valueOf(true)));
   public Setting<String> open = register((new Setting("Open:", "+", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
   public Setting<String> close = register((new Setting("Close:", "-", v -> ((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
   public Setting<String> moduleButton = register((new Setting("Buttons:", "", v -> !((Boolean)this.openCloseChange.getValue()).booleanValue())).setRenderName(true));
   public Setting<Boolean> toparrow = register(new Setting("TopArrow", Boolean.valueOf(false)));
   public Setting<Integer> red = register(new Setting("Red", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> green = register(new Setting("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> blue = register(new Setting("Blue", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> hoverAlpha = register(new Setting("Alpha", Integer.valueOf(130), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> b_red = register(new Setting("BackgroundRed", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> b_green = register(new Setting("BackgroundGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> b_blue = register(new Setting("BackgroundBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> b_alpha = register(new Setting("BackgroundAlpha", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> d_red = register(new Setting("DisabledRed", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> d_green = register(new Setting("DisabledGreen", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> d_blue = register(new Setting("DisabledBlue", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> d_alpha = register(new Setting("DisabledAlpha", Integer.valueOf(40), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> alpha = register(new Setting("HoverAlpha", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Boolean> dark = register(new Setting("BackGround Dark", Boolean.valueOf(false)));
   public Setting<Boolean> frameSettings = register(new Setting("FrameSetting", Boolean.valueOf(false)));
   public Setting<Integer> frameRed = register(new Setting("FrameRed", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.frameSettings.getValue()).booleanValue()));
   public Setting<Integer> frameGreen = register(new Setting("FrameGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.frameSettings.getValue()).booleanValue()));
   public Setting<Integer> frameBlue = register(new Setting("FrameBlue", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.frameSettings.getValue()).booleanValue()));
   public Setting<Integer> frameAlpha = register(new Setting("FrameAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.frameSettings.getValue()).booleanValue()));
   public Setting<Boolean> blurEffect = register(new Setting("Blur", Boolean.valueOf(false)));
   public Setting<Boolean> gradiant = register(new Setting("Gradiant", Boolean.valueOf(false)));
   public Setting<Integer> gradiantred = register(new Setting("GradiantRed", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.gradiant.getValue()).booleanValue()));
   public Setting<Integer> gradiantgreen = register(new Setting("GradiantGreen", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.gradiant.getValue()).booleanValue()));
   public Setting<Integer> gradiantblue = register(new Setting("GradiantBlue", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.gradiant.getValue()).booleanValue()));
   public Setting<Integer> gradiantalpha = register(new Setting("GradiantAlpha", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.gradiant.getValue()).booleanValue()));
   public Setting<Boolean> customFov = register(new Setting("CustomFov", Boolean.valueOf(false)));
   public Setting<Float> fov = register(new Setting("Fov", Float.valueOf(150.0F), Float.valueOf(-180.0F), Float.valueOf(180.0F), v -> ((Boolean)this.customFov.getValue()).booleanValue()));
   public Setting<Boolean> devSettings = register(new Setting("DevSettings", Boolean.valueOf(true)));
   public Setting<Integer> topRed = register(new Setting("TopRed", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
   public Setting<Integer> topGreen = register(new Setting("TopGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
   public Setting<Integer> topBlue = register(new Setting("TopBlue", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
   public Setting<Integer> topAlpha = register(new Setting("TopAlpha", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.devSettings.getValue()).booleanValue()));
   public Setting<Boolean> particles = register(new Setting("Particles", Boolean.valueOf(true)));
   public Setting<Integer> particleLength = register(new Setting("ParticleLength", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(300), v -> ((Boolean)this.particles.getValue()).booleanValue()));
   public Setting<Integer> particlered = register(new Setting("ParticleRed", Integer.valueOf(170), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.particles.getValue()).booleanValue()));
   public Setting<Integer> particlegreen = register(new Setting("ParticleGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.particles.getValue()).booleanValue()));
   public Setting<Integer> particleblue = register(new Setting("ParticleBlue", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), v -> ((Boolean)this.particles.getValue()).booleanValue()));
   public Setting<Integer> textRed = register(new Setting("EnabledTextRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textGreen = register(new Setting("EnabledTextGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textBlue = register(new Setting("EnabledTextBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textAlpha = register(new Setting("EnabledTextAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textRed2 = register(new Setting("DisabledTextRed", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textGreen2 = register(new Setting("DisabledTextGreen", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textBlue2 = register(new Setting("DisabledTextBlue", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Integer> textAlpha2 = register(new Setting("DisabledTextAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255)));
   public Setting<Boolean> aa = register(new Setting("idk", Boolean.valueOf(false)));
   public Setting<Boolean> rainbow = register(new Setting("Rainbow", Boolean.valueOf(false)));
   public Setting<rainbowMode> rainbowModeHud = register(new Setting("HRainbowMode", rainbowMode.Static, v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
   public Setting<rainbowModeArray> rainbowModeA = register(new Setting("ARainbowMode", rainbowModeArray.Static, v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
   public Setting<Integer> rainbowHue = register(new Setting("Delay", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(600), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
   public Setting<Float> rainbowBrightness = register(new Setting("Brightness ", Float.valueOf(150.0F), Float.valueOf(1.0F), Float.valueOf(255.0F), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
   public Setting<Float> rainbowSaturation = register(new Setting("Saturation", Float.valueOf(150.0F), Float.valueOf(1.0F), Float.valueOf(255.0F), v -> ((Boolean)this.rainbow.getValue()).booleanValue()));
   public Setting<Boolean> future = (Setting<Boolean>)this.register(new Setting("Future", true, "like future"));
   public Setting<Boolean> backgroundScreen = (Setting<Boolean>)this.register(new Setting("BackgroundScreen", true));
   public Setting<Boolean> logoWaifu = (Setting<Boolean>)this.register(new Setting("Waifu", true));
   public Setting<Float> xWaifu = (Setting<Float>)this.register(new Setting("X", 728.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
   public Setting<Float> yWaifu = (Setting<Float>)this.register(new Setting("Y", 255.0f, 0.0f, 1000.0f, v -> this.logoWaifu.getValue()));
   public Setting<Integer> heightWaifu = (Setting<Integer>)this.register(new Setting("Height",267, 0, 1000, v -> this.logoWaifu.getValue()));
   public Setting<Integer> widthWaifu = (Setting<Integer>)this.register(new Setting("Width", 207, 0, 1000, v -> this.logoWaifu.getValue()));
 

   public GuiFuture() {
      super("GuiFuture", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
      this.setInstance();
   }

   public static GuiFuture getInstance() {
      if (GuiFuture.INSTANCE == null) {
          GuiFuture.INSTANCE = new GuiFuture();
      }
      return GuiFuture.INSTANCE;
  }
  
  private void setInstance() {
      GuiFuture.INSTANCE = this;
  }
  

   public void onUpdate() {
      if ((Boolean)this.customFov.getValue()) {
         GuiFuture.mc.gameSettings.setOptionFloatValue(Options.FOV, (Float)this.fov.getValue());
      }

   }

   @SubscribeEvent
   public void onSettingChange(ClientEvent event) {
      if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
         if (event.getSetting().equals(this.prefix)) {
            Cracked.commandManager.setPrefix((String)this.prefix.getPlannedValue());
            Command.sendMessage("Prefix set to §a" + Cracked.commandManager.getPrefix());
         }

         Cracked.colorManagerf.setColor((Integer)this.red.getPlannedValue(), (Integer)this.green.getPlannedValue(), (Integer)this.blue.getPlannedValue(), (Integer)this.hoverAlpha.getPlannedValue());
      }

   }

   public void onEnable() {
      GuiFuture.mc.displayGuiScreen((GuiScreen)FutureNewGui.getClickGuiFutureX());
      if ((Boolean)this.blurEffect.getValue()) {
         GuiFuture.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
      }

   }

   public void onLoad() {
      if ((Boolean)this.colorSync.getValue()) {
         Cracked.colorManagerf.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), (Integer)this.hoverAlpha.getValue());
      } else {
         Cracked.colorManagerf.setColor((Integer)this.red.getValue(), (Integer)this.green.getValue(), (Integer)this.blue.getValue(), (Integer)this.hoverAlpha.getValue());
      }

      Cracked.commandManager.setPrefix((String)this.prefix.getValue());
   }

   public void onTick() {
      if (!(GuiFuture.mc.currentScreen instanceof FutureNewGui)) {
         this.disable();
      }

   }

   public void onDisable() {
      if (GuiFuture.mc.currentScreen instanceof FutureNewGui) {
         GuiFuture.mc.displayGuiScreen((GuiScreen)null);
      }

   }

   public static enum rainbowMode {
      Static,
      Sideway;
   }

   public static enum rainbowModeArray {
      Static,
      Up;
   }

   static {
      GuiFuture.INSTANCE = new GuiFuture();
  }
}
