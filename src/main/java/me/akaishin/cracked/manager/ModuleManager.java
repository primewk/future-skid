package me.akaishin.cracked.manager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;

import com.mojang.text2speech.Narrator;

import me.akaishin.cracked.event.events.Render2DEvent;
import me.akaishin.cracked.event.events.Render3DEvent;
import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.chat.AntiLog4j;
import me.akaishin.cracked.features.modules.chat.AntiSpam;
import me.akaishin.cracked.features.modules.chat.AntiUnicode;
import me.akaishin.cracked.features.modules.chat.AutoGG;
import me.akaishin.cracked.features.modules.chat.AutoKit;
import me.akaishin.cracked.features.modules.chat.ChatModifier;
import me.akaishin.cracked.features.modules.chat.EntityNotifier;
import me.akaishin.cracked.features.modules.chat.ModuleTools;
import me.akaishin.cracked.features.modules.chat.Notifications;
import me.akaishin.cracked.features.modules.chat.PasswordOBF;
import me.akaishin.cracked.features.modules.chat.PopLagger;
import me.akaishin.cracked.features.modules.chat.UnicodeLag;
import me.akaishin.cracked.features.modules.chat.commandv.CustomCommand;
import me.akaishin.cracked.features.modules.chat.commandv.MessageSpammer;
import me.akaishin.cracked.features.modules.client.*;
import me.akaishin.cracked.features.modules.combat.*;
import me.akaishin.cracked.features.modules.combat.antiburrow.AntiBurrow;
import me.akaishin.cracked.features.modules.combat.burrow.Burrow;
import me.akaishin.cracked.features.modules.combat.godmodule.GodModule;
import me.akaishin.cracked.features.modules.exploit.*;
import me.akaishin.cracked.features.modules.exploit.instantminer.InstantMine;
import me.akaishin.cracked.features.modules.exploit.phase.Phase;
import me.akaishin.cracked.features.modules.exploit.salc1dupe.AutoSalDupe;
import me.akaishin.cracked.features.modules.hud.Components;
import me.akaishin.cracked.features.modules.hud.Csgo;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.modules.hud.Inventory;
import me.akaishin.cracked.features.modules.hud.Logo;
import me.akaishin.cracked.features.modules.misc.*;
import me.akaishin.cracked.features.modules.misc.antif3.F3Obfuscated;
import me.akaishin.cracked.features.modules.movement.*;
import me.akaishin.cracked.features.modules.player.*;
import me.akaishin.cracked.features.modules.render.*;
import me.akaishin.cracked.features.modules.render.ShaderGlow.ShaderGlow;
import me.akaishin.cracked.features.modules.render.chams.Chams;
import me.akaishin.cracked.features.modules.render.futurechams.FutureChams;
import me.akaishin.cracked.features.modules.render.guianimate.GuiAnimation;
import me.akaishin.cracked.features.modules.render.tracersold.TracerOld;
import me.akaishin.cracked.features.notification.NotificationManager;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ModuleManager
        extends Feature {
            //add static arraylist modules
    public static ArrayList<Module> modules = new ArrayList();
    public List<Module> sortedModules = new ArrayList<Module>();
    public List<Module> alphabeticallySortedModules = new ArrayList<Module>();
    public Map<Module, Color> moduleColorMap = new HashMap<Module, Color>();

    public void init() {
        this.modules.add(new ClickGui());
        this.modules.add(new FutureChams());
        this.modules.add(new FutureGuiB8());
        this.modules.add (new NotificationManager());
        this.modules.add (new CameraLock());
        this.modules.add (new CornerClip());
        this.modules.add(new TabFriends());
        this.modules.add(new GuiFuture());
        this.modules.add(new FutureGui());
        this.modules.add(new F3Obfuscated());
        this.modules.add(new AntiLog4j());
        this.modules.add(new CustomCommand());
        this.modules.add(new MessageSpammer());
        this.modules.add(new AntiChestGui());
        this.modules.add(new NoBob());
        this.modules.add(new InventoryMove());
        this.modules.add(new ItemPhysics());
        this.modules.add(new EntityNotifier());
        this.modules.add(new Chams());
        this.modules.add(new GuiAnimation());
        this.modules.add(new Capes());
        this.modules.add(new Phase());    
        this.modules.add(new InstantMine());
        this.modules.add(new AntiBurrow());
        this.modules.add(new ReverseStep());
        this.modules.add(new ShaderGlow());
        this.modules.add(new TracerOld());
        this.modules.add(new MainMenu());
        //this.modules.add(new FutureGui());
        this.modules.add(new PasswordOBF());
        this.modules.add(new FrameDupe());
        this.modules.add(new Crasher());
        this.modules.add(new GhastNotifier());
        this.modules.add(new StorageESP());
        this.modules.add(new AutoSalDupe());
        this.modules.add(new FakePlayer());
        this.modules.add(new PluginsExploit());
        this.modules.add(new PluginsGrabber());
        this.modules.add(new UnicodeLag());
        this.modules.add(new AntiAFK());
        this.modules.add(new AutoMine());
        this.modules.add(new JumpCircle());
        this.modules.add(new GodModule());
        this.modules.add(new Particles());
        this.modules.add(new PopLagger());
        this.modules.add(new Logo());
        this.modules.add(new Inventory());
		this.modules.add(new ModuleTools());
        this.modules.add(new FriendSettings());
	    this.modules.add(new OffhandRewrite());
        this.modules.add(new Surround());
        this.modules.add(new AutoTrap());
        this.modules.add(new CrystalAura());
        this.modules.add(new AutoCrystal());
        this.modules.add(new OneChunk());
        this.modules.add(new AutoKit());
        this.modules.add(new Criticals());
        this.modules.add(new Killaura());
        this.modules.add(new HoleFiller());
        this.modules.add(new AutoWeb());
        this.modules.add(new ChatModifier());
        this.modules.add(new AutoRespawn());
        this.modules.add(new Zoom());
        this.modules.add(new XRay());
        this.modules.add(new ThirdPerson());
        this.modules.add(new IceSpeed());
        this.modules.add(new AutoWalk());
        this.modules.add(new NarratorAu());
        this.modules.add(new MCF());
        this.modules.add(new AutoReconnect());
        this.modules.add(new RPC());
        this.modules.add(new AutoGG());
        this.modules.add(new FastFall());
        this.modules.add(new Strafe());
        this.modules.add(new Velocity());
        this.modules.add(new Speed());
        this.modules.add(new Step());
        this.modules.add(new AntiVoid());
        this.modules.add(new ElytraFlight());
        this.modules.add(new NoSlowDown());
        this.modules.add(new TimerSpeed());
        this.modules.add(new FastUse());
        this.modules.add(new Freecam());
        this.modules.add(new Speedmine());
        this.modules.add(new MultiTask());
        this.modules.add(new XCarry());
        this.modules.add(new Replenish());
        this.modules.add(new MCP());
        this.modules.add(new Yaw());
        this.modules.add(new NoRender());
        this.modules.add(new Fullbright());
        this.modules.add(new CameraClip());
        this.modules.add(new ChinaHat());
        this.modules.add(new ESP());
        this.modules.add(new HoleESP());
        this.modules.add(new BlockHighlight());
        this.modules.add(new Trajectories());
        this.modules.add(new LogoutSpots());
        this.modules.add(new VoidESP());
        this.modules.add(new CrystalModify());
        this.modules.add(new Notifications());
        this.modules.add(new HUD());
        this.modules.add(new ToolTips());
        this.modules.add(new FontMod());
        this.modules.add(new Components());
        this.modules.add(new Colors());
        this.modules.add(new PopChams());
        this.modules.add(new Burrow());
        this.modules.add(new BowSpam());
        this.modules.add(new Flatten());
        this.modules.add(new Quiver());
        this.modules.add(new Nametags());
        this.modules.add(new SilentXP());
        this.modules.add(new AutoArmor());
        this.modules.add(new Sprint());
        this.modules.add(new ViewModel());
        this.modules.add(new Anchor());
        this.modules.add(new NoEntityTrace());
        this.modules.add(new Blink());
        this.modules.add(new NoSoundLag());
        this.modules.add(new FastProjectiles());
        this.modules.add(new BoatFly());
        this.modules.add(new PacketFly());
        this.modules.add(new NoFall());
        this.modules.add(new BowAim());
        this.modules.add(new Flight());
        this.modules.add(new Offhand());
        this.modules.add(new SkyColor());
        this.modules.add(new Suicide());
        this.modules.add(new ChorusLag());
        this.modules.add(new CowDupeExploit());
        this.modules.add(new PingSpoof());
        this.modules.add(new PenisESP());
        this.modules.add(new CevBreaker());
        this.modules.add(new PistonAura());
        this.modules.add(new PacketEat());
        this.modules.add(new AntiPing());
        this.modules.add(new AutoTwerk());
        this.modules.add(new ChorusPostpone());
        this.modules.add(new Clip());
        this.modules.add(new Trails());
        this.modules.add(new TickShift());
	    this.modules.add(new Csgo());
	    this.modules.add(new AutoBuilder());
	    this.modules.add(new Scaffold());
	    this.modules.add(new BreadCrumbs());
	    this.modules.add(new Aspect());
	    this.modules.add(new AntiBan());
	    this.modules.add(new SpeedNew());
	    this.modules.add(new FakeVanilla());
	    this.modules.add(new ManualIllegalStack());
	    this.modules.add(new AntiVanish());
	    this.modules.add(new PhaseWalk());
	    this.modules.add(new EntityDesync());
	    this.modules.add(new NoWeb());
	    this.modules.add(new ExplosionChams());
	    this.modules.add(new PearlBait());
	    this.modules.add(new BurrowESP());
    	this.modules.add(new AntiHunger());
    	this.modules.add(new KeyChorus());
    	this.modules.add(new PistonPush());
    	this.modules.add(new AntiSpam());
	    this.modules.add(new EnchantColor());
    	this.modules.add(new Blocker());
	    this.modules.add(new MountBypass());
    	this.modules.add(new AutoFrameDupe());
	    this.modules.add(new AutoCraftDupe());
	    this.modules.add(new SolidBlock());
	    this.modules.add(new StashFinder());
	    this.modules.add(new KillEffect());
	    this.modules.add(new HoleSnap());
	    this.modules.add(new Shaders());
	    this.modules.add(new AntiUnicode());  

        this.moduleColorMap.put(this.getModuleByClass(AntiUnicode.class), new Color(22, 0, 118));
        this.moduleColorMap.put(this.getModuleByClass(Shaders.class), new Color(133, 0, 118));
        this.moduleColorMap.put(this.getModuleByClass(HoleSnap.class), new Color(0, 255, 255));
		this.moduleColorMap.put(this.getModuleByClass(KillEffect.class), new Color(87, 109, 223));
	    this.moduleColorMap.put(this.getModuleByClass(StashFinder.class), new Color(0, 0, 223));
		this.moduleColorMap.put(this.getModuleByClass(SolidBlock.class), new Color(46, 89, 0));
		this.moduleColorMap.put(this.getModuleByClass(AutoCraftDupe.class), new Color(28, 0, 189));
		this.moduleColorMap.put(this.getModuleByClass(AutoFrameDupe.class), new Color(0, 173, 94));
		this.moduleColorMap.put(this.getModuleByClass(MountBypass.class), new Color(91, 31, 194));
		this.moduleColorMap.put(this.getModuleByClass(Blocker.class), new Color(63, 0, 158));
		this.moduleColorMap.put(this.getModuleByClass(EnchantColor.class), new Color(66, 119, 9));
		this.moduleColorMap.put(this.getModuleByClass(AntiSpam.class), new Color(192, 225, 0));
		this.moduleColorMap.put(this.getModuleByClass(PistonPush.class), new Color(112, 33, 0));
		this.moduleColorMap.put(this.getModuleByClass(KeyChorus.class), new Color(90, 0, 121));
		this.moduleColorMap.put(this.getModuleByClass(AntiHunger.class), new Color(155, 99, 231));
		this.moduleColorMap.put(this.getModuleByClass(BurrowESP.class), new Color(76, 175, 253));
		this.moduleColorMap.put(this.getModuleByClass(PearlBait.class), new Color(21, 132, 212));
		this.moduleColorMap.put(this.getModuleByClass(ExplosionChams.class), new Color(72, 123, 82));
		this.moduleColorMap.put(this.getModuleByClass(NoWeb.class), new Color(12, 12, 135));
		this.moduleColorMap.put(this.getModuleByClass(EntityDesync.class), new Color(174, 44, 92));
		this.moduleColorMap.put(this.getModuleByClass(PhaseWalk.class), new Color(122, 77, 34));
		this.moduleColorMap.put(this.getModuleByClass(AntiVanish.class), new Color(142, 88, 25));
	    this.moduleColorMap.put(this.getModuleByClass(ManualIllegalStack.class), new Color(51, 152, 236));
		this.moduleColorMap.put(this.getModuleByClass(FakeVanilla.class), new Color(64, 134, 245));
		this.moduleColorMap.put(this.getModuleByClass(SpeedNew.class), new Color(124, 156, 62));
		this.moduleColorMap.put(this.getModuleByClass(AntiBan.class), new Color(47, 232, 67));
		this.moduleColorMap.put(this.getModuleByClass(Aspect.class), new Color(96, 114, 27));
		this.moduleColorMap.put(this.getModuleByClass(BreadCrumbs.class), new Color(182, 218, 153));
		this.moduleColorMap.put(this.getModuleByClass(Scaffold.class), new Color(163, 63, 95));
		this.moduleColorMap.put(this.getModuleByClass(AutoBuilder.class), new Color(35, 145, 187));
		this.moduleColorMap.put(this.getModuleByClass(Csgo.class), new Color(167, 0, 211));
        this.moduleColorMap.put(this.getModuleByClass(TickShift.class), new Color(171, 18, 26));
        this.moduleColorMap.put(this.getModuleByClass(Trails.class), new Color(131, 21, 66));
        this.moduleColorMap.put(this.getModuleByClass(Clip.class), new Color(136, 72, 219));
        this.moduleColorMap.put(this.getModuleByClass(ChorusPostpone.class), new Color(43, 165, 216));
        this.moduleColorMap.put(this.getModuleByClass(AutoTwerk.class), new Color(97, 173, 162));
        this.moduleColorMap.put(this.getModuleByClass(AntiPing.class), new Color(172, 133, 192));
        this.moduleColorMap.put(this.getModuleByClass(PacketEat.class), new Color(215, 81, 248));
        this.moduleColorMap.put(this.getModuleByClass(PistonAura.class), new Color(85, 94, 58));
        this.moduleColorMap.put(this.getModuleByClass(CevBreaker.class), new Color(255, 68, 183));
        this.moduleColorMap.put(this.getModuleByClass(PenisESP.class), new Color(52, 148, 38));
        this.moduleColorMap.put(this.getModuleByClass(PingSpoof.class), new Color(246, 124, 137));
        this.moduleColorMap.put(this.getModuleByClass(CowDupeExploit.class), new Color(174, 64, 83));
        this.moduleColorMap.put(this.getModuleByClass(ChorusLag.class), new Color(35, 28, 192));
        this.moduleColorMap.put(this.getModuleByClass(Suicide.class), new Color(36, 147, 128));
        this.moduleColorMap.put(this.getModuleByClass(SkyColor.class), new Color(123, 32, 13));
        this.moduleColorMap.put(this.getModuleByClass(Flight.class), new Color(73, 21, 92));
        this.moduleColorMap.put(this.getModuleByClass(BowAim.class), new Color(62, 98, 71));
        this.moduleColorMap.put(this.getModuleByClass(NoFall.class), new Color(123, 9, 200));
        this.moduleColorMap.put(this.getModuleByClass(PacketFly.class), new Color(211, 82, 112));
        this.moduleColorMap.put(this.getModuleByClass(BoatFly.class), new Color(67, 111, 38));
        this.moduleColorMap.put(this.getModuleByClass(FastProjectiles.class), new Color(199, 91, 251));
        this.moduleColorMap.put(this.getModuleByClass(NoSoundLag.class), new Color(57, 133, 192));
        this.moduleColorMap.put(this.getModuleByClass(Blink.class), new Color(61, 255, 0));
        this.moduleColorMap.put(this.getModuleByClass(NoEntityTrace.class), new Color(215, 17, 72));
        this.moduleColorMap.put(this.getModuleByClass(Anchor.class), new Color(0, 51, 192));
        this.moduleColorMap.put(this.getModuleByClass(ViewModel.class), new Color(81, 62, 1));
        this.moduleColorMap.put(this.getModuleByClass(Sprint.class), new Color(135, 52, 216));
        this.moduleColorMap.put(this.getModuleByClass(SilentXP.class), new Color(52, 236, 25));
        this.moduleColorMap.put(this.getModuleByClass(AutoArmor.class), new Color(65, 62, 252));
        this.moduleColorMap.put(this.getModuleByClass(PopChams.class), new Color(84, 152, 76));
        this.moduleColorMap.put(this.getModuleByClass(BowSpam.class), new Color(57, 23, 83));
        this.moduleColorMap.put(this.getModuleByClass(Burrow.class), new Color(32, 125, 19));
        this.moduleColorMap.put(this.getModuleByClass(Flatten.class), new Color(163, 34, 39));
        this.moduleColorMap.put(this.getModuleByClass(Quiver.class), new Color(128, 204, 79));
        this.moduleColorMap.put(this.getModuleByClass(CrystalAura.class), new Color(255, 15, 43));
        this.moduleColorMap.put(this.getModuleByClass(AutoTrap.class), new Color(193, 49, 244));
        this.moduleColorMap.put(this.getModuleByClass(Criticals.class), new Color(204, 151, 184));
        this.moduleColorMap.put(this.getModuleByClass(HoleFiller.class), new Color(166, 55, 110));
        this.moduleColorMap.put(this.getModuleByClass(Killaura.class), new Color(255, 37, 0));
		this.moduleColorMap.put(this.getModuleByClass(OffhandRewrite.class), new Color(185, 212, 144));
        this.moduleColorMap.put(this.getModuleByClass(Offhand.class), new Color(185, 212, 144));
        this.moduleColorMap.put(this.getModuleByClass(Surround.class), new Color(100, 0, 150));
        this.moduleColorMap.put(this.getModuleByClass(AutoWeb.class), new Color(11, 161, 121));
        this.moduleColorMap.put(this.getModuleByClass(AutoGG.class), new Color(240, 49, 110));
        this.moduleColorMap.put(this.getModuleByClass(AutoReconnect.class), new Color(17, 85, 153));
        this.moduleColorMap.put(this.getModuleByClass(ChatModifier.class), new Color(255, 59, 216));
        this.moduleColorMap.put(this.getModuleByClass(MCF.class), new Color(17, 85, 255));
        this.moduleColorMap.put(this.getModuleByClass(RPC.class), new Color(0, 64, 255));
        this.moduleColorMap.put(this.getModuleByClass(ToolTips.class), new Color(209, 125, 156));
        this.moduleColorMap.put(this.getModuleByClass(BlockHighlight.class), new Color(103, 182, 224));
        this.moduleColorMap.put(this.getModuleByClass(CameraClip.class), new Color(247, 169, 107));
        this.moduleColorMap.put(this.getModuleByClass(ChinaHat.class), new Color(34, 152, 34));
        this.moduleColorMap.put(this.getModuleByClass(ESP.class), new Color(255, 27, 155));
        this.moduleColorMap.put(this.getModuleByClass(Fullbright.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(HoleESP.class), new Color(95, 83, 130));
        this.moduleColorMap.put(this.getModuleByClass(LogoutSpots.class), new Color(2, 135, 134));
        this.moduleColorMap.put(this.getModuleByClass(Nametags.class), new Color(98, 82, 223));
        this.moduleColorMap.put(this.getModuleByClass(NoRender.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(Trajectories.class), new Color(98, 18, 223));
        this.moduleColorMap.put(this.getModuleByClass(VoidESP.class), new Color(68, 178, 142));
        this.moduleColorMap.put(this.getModuleByClass(ElytraFlight.class), new Color(55, 161, 201));
        this.moduleColorMap.put(this.getModuleByClass(NoSlowDown.class), new Color(61, 204, 78));
        this.moduleColorMap.put(this.getModuleByClass(Speed.class), new Color(55, 161, 196));
        this.moduleColorMap.put(this.getModuleByClass(AntiVoid.class), new Color(86, 53, 98));
        this.moduleColorMap.put(this.getModuleByClass(Step.class), new Color(144, 212, 203));
        this.moduleColorMap.put(this.getModuleByClass(Strafe.class), new Color(0, 204, 255));
        this.moduleColorMap.put(this.getModuleByClass(Velocity.class), new Color(115, 134, 140));
        this.moduleColorMap.put(this.getModuleByClass(FastFall.class), new Color(1, 134, 140));
        this.moduleColorMap.put(this.getModuleByClass(FakePlayer.class), new Color(37, 192, 170));
        this.moduleColorMap.put(this.getModuleByClass(FastUse.class), new Color(217, 118, 37));
        this.moduleColorMap.put(this.getModuleByClass(Freecam.class), new Color(206, 232, 128));
        this.moduleColorMap.put(this.getModuleByClass(MCP.class), new Color(153, 68, 170));
        this.moduleColorMap.put(this.getModuleByClass(MultiTask.class), new Color(17, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(Replenish.class), new Color(153, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(Speedmine.class), new Color(152, 166, 113));
        this.moduleColorMap.put(this.getModuleByClass(TimerSpeed.class), new Color(255, 133, 18));
        this.moduleColorMap.put(this.getModuleByClass(XCarry.class), new Color(254, 161, 51));
        this.moduleColorMap.put(this.getModuleByClass(Yaw.class), new Color(115, 39, 141));
        this.moduleColorMap.put(this.getModuleByClass(Colors.class), new Color(135, 133, 26));
        this.moduleColorMap.put(this.getModuleByClass(Components.class), new Color(135, 26, 26));
        this.moduleColorMap.put(this.getModuleByClass(FontMod.class), new Color(135, 26, 88));
        this.moduleColorMap.put(this.getModuleByClass(HUD.class), new Color(110, 26, 135));
        this.moduleColorMap.put(this.getModuleByClass(Notifications.class), new Color(170, 153, 255));
        for (Module module : this.modules) {
            module.animation.start();
        }
    }
	
	public <T extends Module> T getModuleT(Class<T> clazz) {
        return (T)((Module)this.modules.stream().filter(module -> module.getClass() == clazz).map(module -> module).findFirst().orElse(null));
    }


    public static Module getModuleByName(String name) {
        for (Module module : modules) {
          if (!module.getName().equalsIgnoreCase(name))
            continue; 
          return module;
        } 
        return null;
      }
/* //remove 
    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }*/

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T) module;
        }
        return null;
    }

    public void enableModule(Class clazz) {
        Object module = this.getModuleByClass(clazz);
        if (module != null) {
            ((Module) module).enable();
        }
    }

    public void disableModule(Class clazz) {
        Object module = this.getModuleByClass(clazz);
        if (module != null) {
            ((Module) module).disable();
        }
    }

    public void enableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }

    public boolean isModuleEnabled(String name) {
        Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }

    public boolean isModuleEnabled(Class clazz) {
        Object module = this.getModuleByClass(clazz);
        return module != null && ((Module) module).isOn();
    }

    public Module getModuleByDisplayName(String displayName) {
        for (Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isEnabled() && !module.isSliding()) continue;
            enabledModules.add(module);
        }
        return enabledModules;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesCategory = new ArrayList<Module>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modulesCategory.add(module);
            }
        });
        return modulesCategory;
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(((EventBus) MinecraftForge.EVENT_BUS)::register);
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void sortModules(boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect(Collectors.toList());
    }

    public void alphabeticallySortModules() {
        this.alphabeticallySortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(Module::getDisplayName)).collect(Collectors.toList());
    }

    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }

    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) {
            module.enabled.setValue(false);
        }
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof CrackedHackGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }

    public List<Module> getAnimationModules(Module.Category category) {
        ArrayList<Module> animationModules = new ArrayList<Module>();
        for (Module module : this.getEnabledModules()) {
            if (module.getCategory() != category || module.isDisabled() || !module.isSliding() || !module.isDrawn())
                continue;
            animationModules.add(module);
        }
        return animationModules;
    }
}
