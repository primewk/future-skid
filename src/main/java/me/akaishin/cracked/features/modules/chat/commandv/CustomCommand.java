//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.modules.chat.commandv;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.network.play.client.CPacketChatMessage;

public class CustomCommand extends Module {
   private Setting<String> custom = this.register(new Setting("Custom", "/help "));

   public CustomCommand() {
      super("CustomCommand", "Message command custom", Module.Category.CHAT, true, false, false);
   }

   public void onTick() {
      if (!CustomCommand.fullNullCheck()) {
         CustomCommand.mc.player.connection.sendPacket(new CPacketChatMessage((String)this.custom.getValue()));
         this.disable();
      }
   }
}
