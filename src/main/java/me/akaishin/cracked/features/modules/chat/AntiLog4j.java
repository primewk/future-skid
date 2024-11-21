//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.modules.chat;

import me.akaishin.cracked.event.events.PacketEvent;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiLog4j extends Module {
   public AntiLog4j() {
      super("AntiLog4j", "Stops you from registering text containing what the log4j exploit text would contain", Module.Category.CHAT, true, false, false);
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void onPacketRecieve(PacketEvent.Receive event) {
      String text;
      if (event.getPacket() instanceof SPacketChat && ((text = ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText()).contains("${") || text.contains("$<") || text.contains("$:-") || text.contains("jndi:ldap"))) {
         event.setCanceled(true);
      }

   }
}
