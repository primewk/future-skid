package me.akaishin.cracked.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.modules.hud.HUD;
import me.akaishin.cracked.features.modules.render.NoRender;

@Mixin(value = {GuiIngame.class})
public class MixinGuiIngame
        extends Gui {
    @Shadow
    @Final
    public GuiNewChat persistantChatGUI;

    @Inject(method = {"renderPortal"}, at = {@At(value = "HEAD")}, cancellable = true)
    protected void renderPortalHook(float n, ScaledResolution scaledResolution, CallbackInfo info) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().portal.getValue().booleanValue()) {
            info.cancel();
        }
    }

    @Inject(method = {"renderPumpkinOverlay"}, at = {@At(value = "HEAD")}, cancellable = true)
    protected void renderPumpkinOverlayHook(ScaledResolution scaledRes, CallbackInfo info) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().pumpkin.getValue().booleanValue()) {
            info.cancel();
        }
    }

    @Inject(method = {"renderPotionEffects"}, at = {@At(value = "HEAD")}, cancellable = true)
    protected void renderPotionEffectsHook(ScaledResolution scaledRes, CallbackInfo info) {
        if (Cracked.moduleManager != null && !HUD.getInstance().potionIcons.getValue().booleanValue()) {
            info.cancel();
        }
    }
}

