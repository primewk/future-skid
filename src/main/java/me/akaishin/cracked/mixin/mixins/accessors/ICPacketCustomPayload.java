package me.akaishin.cracked.mixin.mixins.accessors;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketCustomPayload.class)
public interface ICPacketCustomPayload {
    @Accessor(value = "data")
    void setData(PacketBuffer data);
}