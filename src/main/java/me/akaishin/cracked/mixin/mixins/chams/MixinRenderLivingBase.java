package me.akaishin.cracked.mixin.mixins.chams;

import java.awt.Color;
import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.modules.render.chams.Chams;
import me.akaishin.cracked.util.chams.RenderUtil;
import me.akaishin.cracked.util.chams.Wrapper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderLivingBase.class})
public abstract class MixinRenderLivingBase<T extends EntityLivingBase>
extends Render<T> {
    private float prevRenderYawOffset;
    private float renderYawOffset;
    private float prevRotationYawHead;
    private float rotationYawHead;
    private float prevRotationPitch;
    private float rotationPitch;

    protected MixinRenderLivingBase(RenderManager renderManager) {
        super(renderManager);
    }


    @Inject(method={"renderModel"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift=At.Shift.BEFORE)}, cancellable=true)
    private void renderModelHook(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo info) {
        Chams mod = Chams.INSTANCE;
        RenderLivingBase renderLiving = RenderLivingBase.class.cast(this);
        ModelBase model = renderLiving.getMainModel();
        if (mod.isOn() && entity instanceof EntityPlayer) {
            Color color;
            float newLimbSwingAmount;
            info.cancel();
            boolean isFriend = Cracked.friendManager.isFriend(entity.getName());
            float newLimbSwing = mod.noInterp.getValue() ? 0.0f : limbSwing;
            float f = newLimbSwingAmount = mod.noInterp.getValue() ? 0.0f : limbSwingAmount;
            if (mod.sneak.getValue()) {
                entity.setSneaking(true);
            }
            if (!mod.self.getValue() && entity instanceof EntityPlayerSP) {
                model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                return;
            }
            if (mod.model.getValue() == Chams.Model.VANILLA) {
                model.render(entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            } else if (mod.model.getValue() == Chams.Model.XQZ) {
                GL11.glEnable(32823);
                GlStateManager.enablePolygonOffset();
                GL11.glPolygonOffset(1.0f, -1000000.0f);
                if (mod.modelColorEnable.getValue().booleanValue()) {
                    color = isFriend ? Cracked.colorChamsManager.getFriendColor(mod.modelColorAlpha.getValue()) : new Color(mod.modelColorRed.getValue(), mod.modelColorGreen.getValue(), mod.modelColorBlue.getValue(), mod.modelColorAlpha.getValue());
                    RenderUtil.glColor(color);
                }
                model.render(entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glDisable(32823);
                GlStateManager.disablePolygonOffset();
                GL11.glPolygonOffset(1.0f, 1000000.0f);
            }
            if (mod.wireframe.getValue()) {
                color = isFriend ? Cracked.colorChamsManager.getFriendColor(mod.lineColorEnable.getValue().booleanValue() ? mod.lineColorAlpha.getValue() : mod.colorAlpha.getValue()) : (mod.lineColorEnable.getValue().booleanValue() ? new Color(mod.lineColorRed.getValue(), mod.lineColorGreen.getValue(), mod.lineColorBlue.getValue(), mod.lineColorAlpha.getValue()) : new Color(mod.colorRed.getValue(), mod.colorGreen.getValue(), mod.colorBlue.getValue(), mod.colorAlpha.getValue()));
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6913);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glDisable(2929);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GlStateManager.blendFunc(770, 771);
                RenderUtil.glColor(color);
                GlStateManager.glLineWidth(mod.lineWidth.getValue());
                model.render(entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
            if (mod.fill.getValue()) {
                color = isFriend ? Cracked.colorChamsManager.getFriendColor(mod.colorAlpha.getValue()) : new Color(mod.colorRed.getValue(), mod.colorGreen.getValue(), mod.colorBlue.getValue(), mod.colorAlpha.getValue());
                GL11.glPushAttrib(1048575);
                GL11.glDisable(3008);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glLineWidth(1.5f);
                GL11.glEnable(2960);
                if (mod.xqz.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                RenderUtil.glColor(color);
                model.render(entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                if (mod.xqz.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
                GL11.glEnable(3042);
                GL11.glEnable(2896);
                GL11.glEnable(3553);
                GL11.glEnable(3008);
                GL11.glPopAttrib();
            }
            if (mod.glint.getValue()) {
                color = isFriend ? Cracked.colorChamsManager.getFriendColor(mod.colorAlpha.getValue()) : new Color(mod.colorRed.getValue(), mod.colorGreen.getValue(), mod.colorBlue.getValue(), mod.colorAlpha.getValue());
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6914);
                GL11.glDisable(2896);
                GL11.glDepthRange(0.0, 0.1);
                GL11.glEnable(3042);
                RenderUtil.glColor(color);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
                float f2 = (float) entity.ticksExisted + Wrapper.mc.getRenderPartialTicks();
                Wrapper.mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                for (int i = 0; i < 2; ++i) {
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GL11.glScalef(1.0f, 1.0f, 1.0f);
                    GlStateManager.rotate(30.0f - (float)i * 60.0f, 0.0f, 0.0f, 1.0f);
                    GlStateManager.translate(0.0f, f2 * (0.001f + (float)i * 0.003f) * 20.0f, 0.0f);
                    GlStateManager.matrixMode(5888);
                    model.render(entity, newLimbSwing, newLimbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                }
                GlStateManager.matrixMode(5890);
                GlStateManager.loadIdentity();
                GlStateManager.matrixMode(5888);
                GL11.glDisable(3042);
                GL11.glDepthRange(0.0, 1.0);
                GL11.glEnable(2896);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
        } else {
            model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
}

