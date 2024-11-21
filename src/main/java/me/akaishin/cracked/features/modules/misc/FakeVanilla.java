package me.akaishin.cracked.features.modules.misc;

import io.netty.buffer.Unpooled;
import me.akaishin.cracked.event.events.PacketEvent;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.mixin.mixins.accessors.ICPacketCustomPayload;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FakeVanilla extends Module {
    public FakeVanilla() {
        super("FakeVanilla", "Prevents client from sending forge signature", Module.Category.MISC,true,false,false);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (!mc.isIntegratedServerRunning()) {
            if (event.getPacket().getClass().getName().equals("net.minecraftforge.fml.common.network.internal.FMLProxyPacket")) {
                event.setCanceled(true);
            } else if (event.getPacket() instanceof CPacketCustomPayload) {
                if (((CPacketCustomPayload) event.getPacket()).getChannelName().equalsIgnoreCase("MC|Brand")) {
                    ((ICPacketCustomPayload) event.getPacket()).setData(new PacketBuffer(Unpooled.buffer()).writeString("vanilla"));
                }
            }
        }
    }
}