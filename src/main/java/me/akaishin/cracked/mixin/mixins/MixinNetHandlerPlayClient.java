package me.akaishin.cracked.mixin.mixins;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.DeathEvent;
import me.akaishin.cracked.util.Util;

@Mixin(value = {NetHandlerPlayClient.class})
public class MixinNetHandlerPlayClient {
    @Inject(method = {"handleEntityMetadata"}, at = {@At(value = "RETURN")}, cancellable = true)
    private void handleEntityMetadataHook(SPacketEntityMetadata packetIn, CallbackInfo info) {
        EntityPlayer player;
        Entity entity;
        if (Util.mc.world != null && (entity = Util.mc.world.getEntityByID(packetIn.getEntityId())) instanceof EntityPlayer && (player = (EntityPlayer) entity).getHealth() <= 0.0f) {
            MinecraftForge.EVENT_BUS.post((Event) new DeathEvent(player));
            if (Cracked.totemPopManager != null) {
                Cracked.totemPopManager.onDeath(player);
            }
        }
    }
}

