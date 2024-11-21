package me.akaishin.cracked.mixin.mixins.mainmenu;

import net.minecraft.client.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import me.akaishin.cracked.features.future.guimain.GuiCustomMainScreen;
import me.akaishin.cracked.features.modules.client.MainMenu;

import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Shadow
    public abstract void displayGuiScreen(@Nullable final GuiScreen p0);
    
    @Inject(method = { "runTick()V" }, at = { @At("RETURN") })
    private void runTick(final CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu && (boolean)MainMenu.getInstance().mainScreen.getValue()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiCustomMainScreen());
        }
    }
    
    @Inject(method = { "displayGuiScreen" }, at = { @At("HEAD") })
    private void displayGuiScreen(final GuiScreen screen, final CallbackInfo ci) {
        if (screen instanceof GuiMainMenu) {
            this.displayGuiScreen((GuiScreen)new GuiCustomMainScreen());
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)//MainMenu
    public void onGuiOpened(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu || (event.getGui() == null && me.akaishin.cracked.util.mainmenu.Util.mc.player == null)) {
            event.setGui((GuiScreen)new GuiCustomMainScreen());
        }
    }
}
