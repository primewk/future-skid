package me.akaishin.cracked.features.modules.chat;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.akaishin.cracked.event.events.PacketEvent;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.util.Timer;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
	
public class AntiUnicode
        extends Module {
	public Setting<Integer> maxSymbolCount = this.register(new Setting<Integer>("MaxSymbolCount", 100, 1, 250));
	public Setting<Boolean> notify = this.register(new Setting<Boolean>("Notify", true));
    private final Timer delay = new Timer();
	
	public AntiUnicode() {
        super("AntiUnicode", "Stops large unicode messages from being seen in chat", Module.Category.CHAT, true, false, false);
    }
	
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketChat) {
            String text = ((SPacketChat) event.getPacket()).chatComponent.getFormattedText();
            int symbolCount = 0;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (this.isSymbol(c)) symbolCount++;
                if (symbolCount > this.maxSymbolCount.getValue().intValue()) {
                    if (this.notify.getValue().booleanValue() && this.delay.passed(10)) {
                        Command.sendMessage("[AntiUnicode] " + ChatFormatting.GREEN + "Message blocked!");
                        this.delay.reset();
                    }
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }

    private boolean isSymbol(char charIn) {
        return !((charIn >= 65 && charIn <= 90) || (charIn >= 97 && charIn <= 122)) && !(charIn >= 48 && charIn <= 57);
    }
}