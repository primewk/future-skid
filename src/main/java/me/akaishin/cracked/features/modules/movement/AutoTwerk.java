package me.akaishin.cracked.features.modules.movement;
 
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.client.Minecraft;
 
public class AutoTwerk
        extends Module {
    public AutoTwerk() {
        super("AutoTwerk", "Makes you dance like the devil him self", Module.Category.MOVEMENT, true, false, false);
    }
	
    public static void theTwerkSupport() {
        Minecraft mc = Minecraft.getMinecraft();
    }
	
    public void onUpdate() {
        AutoTwerk.mc.gameSettings.keyBindSneak.pressed = true;
        if (AutoTwerk.mc.player.isSneaking()) {
            AutoTwerk.mc.gameSettings.keyBindSneak.pressed = false;
        }
    }
}