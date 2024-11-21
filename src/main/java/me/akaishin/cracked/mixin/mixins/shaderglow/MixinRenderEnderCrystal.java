package me.akaishin.cracked.mixin.mixins.shaderglow;

import java.awt.Color;
import me.akaishin.cracked.features.modules.render.ShaderGlow.ShaderGlow;
import me.akaishin.cracked.features.modules.render.ShaderGlow.utilities.ColorUtil;
import me.akaishin.cracked.features.modules.render.ShaderGlow.utilities.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderEnderCrystal.class})
public class MixinRenderEnderCrystal {
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;

    @Inject(method={"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at={@At(value="RETURN")}, cancellable=true)
    public void IdoRender(EntityEnderCrystal var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.fancyGraphics = false;
        if (ShaderGlow.INSTANCE().isEnabled() && ((Boolean)ShaderGlow.INSTANCE().other.getValue()).booleanValue()) {
            float var11 = (float)var1.innerRotation + var9;
            GlStateManager.pushMatrix();
            GlStateManager.translate(var2, var4, var6);
            Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(ENDER_CRYSTAL_TEXTURES);
            float var12 = MathHelper.sin(var11 * 0.2f) / 2.0f + 0.5f;
            var12 += var12 * var12;
            GL11.glLineWidth(5.0f);
            float ageInTicks2 = var12 * 0.2f;
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            } else {
                this.modelEnderCrystalNoBase.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderOne(((Float)ShaderGlow.INSTANCE().lineWidth.getValue()).floatValue());
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            } else {
                this.modelEnderCrystalNoBase.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderTwo();
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            } else {
                this.modelEnderCrystalNoBase.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            }
            Color color = (Boolean)ShaderGlow.INSTANCE().colorSync.getValue() != false ? new Color(ColorUtil.rainbow((Integer)ShaderGlow.INSTANCE().rainbowHue.getValue()).getRed(), ColorUtil.rainbow((Integer)ShaderGlow.INSTANCE().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow((Integer)ShaderGlow.INSTANCE().rainbowHue.getValue()).getBlue(), (Integer)ShaderGlow.INSTANCE().alpha.getValue()) : new Color((Integer)ShaderGlow.INSTANCE().red.getValue(), (Integer)ShaderGlow.INSTANCE().green.getValue(), (Integer)ShaderGlow.INSTANCE().blue.getValue(), (Integer)ShaderGlow.INSTANCE().alpha.getValue());
            RenderUtil.renderThree();
            RenderUtil.renderFour(color);
            RenderUtil.setColor(color);
            if (var1.shouldShowBottom()) {
                this.modelEnderCrystal.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            } else {
                this.modelEnderCrystalNoBase.render(var1, 0.0f, var11 * 3.0f, ageInTicks2, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderFive();
            GlStateManager.popMatrix();
        }
    }
}
 