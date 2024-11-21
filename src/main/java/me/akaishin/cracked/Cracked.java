package me.akaishin.cracked;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import me.akaishin.cracked.util.Iconwindows.IconUtil;
import me.akaishin.cracked.util.soundutil.SoundUtill;
import me.akaishin.cracked.event.EventProcessor;
import me.akaishin.cracked.features.future.guimain.GuiCustomMainScreen;
import me.akaishin.cracked.features.modules.client.RPC;
import me.akaishin.cracked.features.notification.util.font.CFontRenderer5;
import me.akaishin.cracked.manager.*;
//import me.akaishin.cracked.protect.hwid.manager.HWIDManager;
//import me.akaishin.cracked.protect.verifi.DiscordVerifyUser;
//import me.akaishin.cracked.protect.verifi.data.AntiDump;
//import me.akaishin.cracked.protect.verifi.data.WifiCheck;
//import me.akaishin.cracked.protect.verifi.data.antivm.VMDetector;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.awt.*;
import java.util.Objects;

@Mod(modid = "cracked", name = "Cracked", version = "1.0")
public class Cracked {
    public static final String MODID = "cracked";
    public static final String MODNAME = "Cracked";
    public static final String MODVER = "1.0";
    public static final Logger LOGGER;
    //AuthGui
    public static boolean isOpenAuthGui;
	public static EventProcessor eventProcessor;
	public static boolean launchSent;


    public static String getVersion() {
        return MODVER;
    }

	//public static EventBus dispatcher;
    public static ModuleManager moduleManager;
    public static GuiCustomMainScreen customMainScreen;//mainmenu
    public static ShaderColorManager shaderColorManager;
    public static ColorManagerF colorManagerf;
	public static MovementManager movementManager;
    public static SpeedManager speedManager;
    public static PositionManager positionManager;
    public static RotationManager rotationManager;
    public static CommandManager commandManager;
    public static EventManager eventManager;
    public static ConfigManager configManager;
    public static FileManager fileManager;
    public static FriendManager friendManager;
    public static TextManager textManager;
    public static ColorManager colorManager;
    public static ColorCrackManager colorCrackManager;
    public static ServerManager serverManager;
    public static PotionManager potionManager;
    public static InventoryManager inventoryManager;
    public static TimerManager timerManager;
    public static PacketManager packetManager;
    public static ReloadManager reloadManager;
    public static TotemPopManager totemPopManager;
    public static HoleManager holeManager;
    public static NotificationManager notificationManager;
    public static SafetyManager safetyManager;
    public static ColorFutureClient colorFutureClient;
    public static ColorChamsManager colorChamsManager;
    public static ColorExeterGuiManager colorExeterGuiManager;
    public static CFontRenderer5 fontRenderer5; //Font Register Notifier
    @Mod.Instance
    public static Cracked INSTANCE;
    private static boolean unloaded;
    //AuthGui
    public static boolean lauchSent;


    public static void load() {
        Cracked.LOGGER.info("\n\nLoading Cracked");
        //Auth verify user
		/*if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			DiscordVerifyUser.sendDebugOrDumpDetect();
			Minecraft.getMinecraft().shutdown();
		}*/
        //unload 
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        //Font Notifier
        try {
                    Font verdanapro5 = Font.createFont( Font.TRUETYPE_FONT, Objects.requireNonNull(Cracked.class.getResourceAsStream("/assets/minecraft/fonts/Monsterrat.ttf")));
            verdanapro5 = verdanapro5.deriveFont( 12.f );
            fontRenderer5 = new CFontRenderer5( verdanapro5, true, true );
        } catch ( Exception e ) {
            e.printStackTrace( );
            return;
        }
        //continue normal
        colorExeterGuiManager = new ColorExeterGuiManager();
        shaderColorManager = new ShaderColorManager();
        colorChamsManager = new ColorChamsManager();
        colorFutureClient = new ColorFutureClient();
        totemPopManager = new TotemPopManager();
        timerManager = new TimerManager();
        packetManager = new PacketManager();
        serverManager = new ServerManager();
        colorManager = new ColorManager();
        colorManagerf = new ColorManagerF();
        colorCrackManager = new ColorCrackManager();
        textManager = new TextManager();
        moduleManager = new ModuleManager();
		movementManager = new MovementManager();
        speedManager = new SpeedManager();
        rotationManager = new RotationManager();
        positionManager = new PositionManager();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        configManager = new ConfigManager();
        fileManager = new FileManager();
        friendManager = new FriendManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        holeManager = new HoleManager();
        notificationManager = new NotificationManager();
        safetyManager = new SafetyManager();
        Cracked.LOGGER.info("Initialized Managers");
        moduleManager.init();
        Cracked.LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        Cracked.LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        totemPopManager.init();
        timerManager.init();
        if (moduleManager.getModuleByClass(RPC.class).isEnabled()) {
            DiscordPresence.start();
        }
        Cracked.LOGGER.info("Cracked initialized!\n");
    }


    public static void unload(boolean unload) {
        LOGGER.info("\n\nUnloading Cracked");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        Cracked.onUnload();
		//dispatcher = null;
        colorExeterGuiManager = null;
        fontRenderer5 = null;
        shaderColorManager = null;
        colorFutureClient = null;
        colorChamsManager = null;
        eventManager = null;
        holeManager = null;
        timerManager = null;
        moduleManager = null;
		movementManager = null;
        totemPopManager = null;
        serverManager = null;
        colorManager = null;
        colorManagerf = null;
        colorCrackManager = null;
        textManager = null;
        speedManager = null;
        rotationManager = null;
        positionManager = null;
        commandManager = null;
        configManager = null;
        fileManager = null;
        friendManager = null;
        potionManager = null;
        inventoryManager = null;
        notificationManager = null;
        safetyManager = null;
        Cracked.LOGGER.info("Cracked unloaded!\n");
    }


    public static void reload() {
        //Auth Verify user
		/*if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			DiscordVerifyUser.sendDebugOrDumpDetect();
			Minecraft.getMinecraft().shutdown();
		}*/
        Cracked.unload(false);
        Cracked.load();
    }

    public static void onUnload() {
        //exit auth
        // DiscordVerifyUser.sendExit(); AUTHHHHHHHHHHHHHHH
        //Exit Notifier

        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(Cracked.configManager.config.replaceFirst("Cracked/", ""));
            moduleManager.onUnloadPost();
            timerManager.unload();
            unloaded = true;
        }
    }


    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        //Verify auth user
		/*if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			DiscordVerifyUser.sendDebugOrDumpDetect();
			Minecraft.getMinecraft().shutdown();
		}*/
        //System Tray
        SystemTray.sysTray();
        //GeneralAudio
        SoundUtill.playSound(new ResourceLocation("textures/audio/main.wav"));
        Cracked.LOGGER.info("============================================");
        Cracked.LOGGER.info("......Cracked Client Paid 25$ price AGS.....");
        Cracked.LOGGER.info(".......CLIENT ULTRA MINECRAFT ANARCHY.......");
        Cracked.LOGGER.info("============================================");
        //Verify User Data
        //DiscordVerifyUser.sendLaunch(); AUTHHHHHHHHHHHHHHH
        //Notifi User In Minecraft

        //auth Audio
        // KeyManager.hwidCheck(); //key check auth AUTHHHHHHHHHHHHHHH
        // HWIDManager.hwidCheck(); // HWID CHECK AUTHHHHHHHHHHHHHHH
        SoundUtill.playSound(new ResourceLocation("textures/audio/auth.wav"));
    }

//ICON WINDOWS //
private void setWindowsIcon() {
    Cracked.setWindowIcon();
}

@Mod.EventHandler
public static void setWindowIcon() {
    if (Util.getOSType() != Util.EnumOS.OSX) {
        try (InputStream inputStream16x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/icons/icon-16x.png");
            InputStream inputStream32x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/icons/icon-32x.png");
            InputStream inputStream64x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/icons/icon-64x.png"); 
            InputStream inputStream128x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/icons/icon-128x.png");
            InputStream inputStream256x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/icons/icon-256x.png")) {
            ByteBuffer[] icons = new ByteBuffer[]{IconUtil.INSTANCE.readImageToBuffer(inputStream16x), IconUtil.INSTANCE.readImageToBuffer(inputStream32x), IconUtil.INSTANCE.readImageToBuffer(inputStream64x), IconUtil.INSTANCE.readImageToBuffer(inputStream128x), IconUtil.INSTANCE.readImageToBuffer(inputStream256x)};
            Display.setIcon(icons);
        } catch (Exception e) {
            Cracked.LOGGER.error("Couldn't set Windows Icon", e);
        }
    }
}

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        //Check final info
		/*if (WifiCheck.getConnectionCheck()) {
			Minecraft.getMinecraft().shutdown();
		}
		AntiDump.check();
		if (VMDetector.isVM() || VMDetector.isRunningOnVM()) {
			DiscordVerifyUser.sendDebugOrDumpDetect();
			Minecraft.getMinecraft().shutdown();
		}*/
        this.setWindowsIcon();
        //Cracked Loader verify user ok
        //DiscordVerifyUser.sendLogginSuccess(); AUTHHHHHHHHHHHHHHH
        //Enable AuthGui
        eventProcessor = new EventProcessor();//-----
        eventProcessor.onInit();//-------------------
        //Sound Util Auth Pass
        SoundUtill.playSound(new ResourceLocation("textures/audio/auth_success.wav"));
        Display.setTitle(" [ Cracked | v1.0 ] -> " + Minecraft.getMinecraft().getSession().getUsername() + "");
        load();
        //convertir audio mp3 etc a wav o que tenga soporte a java audio
        //SoundUtill.playSound(new ResourceLocation("textures/audio/loaded.wav"));
        SoundUtill.playSound(new ResourceLocation("textures/audio/load_success.wav"));
    }
    
    static {
        LOGGER = LogManager.getLogger("Cracked");
        Cracked.unloaded = false;
    }
}
