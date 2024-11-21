//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\akais\Desktop\Deobfuscador Mapping 1.12.2\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

package me.akaishin.cracked.features.modules.chat.commandv;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.chat.commandv.utils.Timer;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.apache.commons.lang3.RandomStringUtils;

public class MessageSpammer extends Module {
   private final Timer timer = new Timer();
   private Setting<String> custom = this.register(new Setting("Custom", "custom"));
   private Setting<Integer> random = this.register(new Setting("Random", 1, 1, 20));
   private Setting<Integer> delay = this.register(new Setting("Delay", 100, 0, 10000));

   public MessageSpammer() {
      super("SpammerTest", "MessageSpam", Module.Category.CHAT, true, false, false);
   }

   public void onTick() {
      if (!fullNullCheck()) {
         if (this.timer.passedMs((long)(Integer)this.delay.getValue())) {
            mc.player.connection.sendPacket(new CPacketChatMessage((String)this.custom.getValue() + "[" + RandomStringUtils.randomAlphanumeric((Integer)this.random.getValue()) + "]"));
            this.timer.reset();
         }
      }
   }

   public void onLogin() {
      if (this.isEnabled()) {
         this.disable();
         this.enable();
      }

   }
}
