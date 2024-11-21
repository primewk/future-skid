package me.akaishin.cracked.event;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.auth.ui.AuthGui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventProcessor {
    private final Minecraft mc = Minecraft.getMinecraft();
	
	public EventProcessor() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
    @SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (!(event.getGui() instanceof AuthGui) && Cracked.isOpenAuthGui) {
			event.setCanceled(true);
		}
	}

    public void onInit() {
        mc.displayGuiScreen(new AuthGui());
        Cracked.isOpenAuthGui = true;
    }
}