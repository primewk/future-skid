package me.akaishin.cracked.mixin.mixins.passwordobf;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.modules.chat.PasswordOBF;
import static me.akaishin.cracked.mixin.mixins.passwordobf.utils.Util.mc;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={FontRenderer.class})
public abstract class MixinFontRenderer {
    @Shadow
    protected abstract void renderStringAtPos(String var1, boolean var2);
	
    @Redirect(method = {"renderString(Ljava/lang/String;FFIZ)I"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
    public void renderStringAtPosHook(FontRenderer fontRenderer, String string, boolean bl) {
        if(Cracked.moduleManager == null){
            renderStringAtPos(string, bl);
            return;
        }
        if (Cracked.moduleManager.getModuleByClass(PasswordOBF.class).isEnabled()) {
            if(string.contains("/login") || string.contains("/register") && mc.currentScreen instanceof GuiChat) {
                StringBuilder final_string = new StringBuilder("");
                for(char cha: string.replace("/login","").replace("/register","").toCharArray()){
                    final_string.append("*");
                }
                if (string.contains("/register")){
                    renderStringAtPos("/register " + final_string, bl);
                    return;
                } else if (string.contains("/login")){
                    renderStringAtPos("/login " + final_string, bl);
                    return;
                }
            }
        }
        renderStringAtPos(string, bl);
    }
}