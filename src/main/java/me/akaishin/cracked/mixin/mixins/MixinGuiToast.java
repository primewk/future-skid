package me.akaishin.cracked.mixin.mixins;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.GuiToast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.akaishin.cracked.features.modules.render.NoRender;

@Mixin(value = {GuiToast.class})
public class MixinGuiToast {
    @Inject(method = {"drawToast"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void drawToastHook(ScaledResolution resolution, CallbackInfo info) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().advancements.getValue().booleanValue()) {
            info.cancel();
        }
    }
}

