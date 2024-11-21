package me.akaishin.cracked.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.akaishin.cracked.features.modules.render.Nametags;
import me.akaishin.cracked.util.RenderUtil;

@Mixin(value = {RenderPlayer.class})
public class MixinRenderPlayer {
    @Inject(method = {"renderEntityName"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void renderEntityNameHook(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq, CallbackInfo info) {
        if (Nametags.getInstance().isOn()) {
            info.cancel();
        }
    }
}