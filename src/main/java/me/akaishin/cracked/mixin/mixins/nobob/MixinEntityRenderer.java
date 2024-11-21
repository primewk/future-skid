package me.akaishin.cracked.mixin.mixins.nobob;

import me.akaishin.cracked.features.modules.render.NoBob;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "applyBobbing", at = @At("HEAD"), cancellable = true)
    public void applyBobbing(float f, CallbackInfo ci) {
        if (NoBob.INSTANCE.isEnabled()) {
            ci.cancel();
        }
    }

}
