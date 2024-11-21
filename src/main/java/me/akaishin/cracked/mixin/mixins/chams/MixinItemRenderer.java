package me.akaishin.cracked.mixin.mixins.chams;

import java.awt.Color;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.event.events.RenderItemInFirstPersonEvent;
import me.akaishin.cracked.features.modules.render.chams.Chams;
import me.akaishin.cracked.util.chams.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemRenderer.class})
public abstract class MixinItemRenderer {
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection = true;

    @Shadow
    public abstract void renderItemInFirstPerson(AbstractClientPlayer var1, float var2, float var3, EnumHand var4, float var5, ItemStack var6, float var7);

    @Redirect(method={"renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/ItemRenderer;renderItemSide(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;Z)V"))
    public void renderItemInFirstPerson(ItemRenderer itemRenderer, EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded) {
        RenderItemInFirstPersonEvent eventPre = new RenderItemInFirstPersonEvent(entitylivingbaseIn, heldStack, transform, leftHanded, 0);
        MinecraftForge.EVENT_BUS.post(eventPre);
        if (!eventPre.isCanceled()) {
            itemRenderer.renderItemSide(entitylivingbaseIn, eventPre.getStack(), eventPre.getTransformType(), leftHanded);
        }
        RenderItemInFirstPersonEvent eventPost = new RenderItemInFirstPersonEvent(entitylivingbaseIn, heldStack, transform, leftHanded, 1);
        MinecraftForge.EVENT_BUS.post(eventPost);
    }

    @Inject(method={"renderFireInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderFireInFirstPersonHook(CallbackInfo info) {
    }

    @Inject(method={"renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderItemInFirstPersonHook(AbstractClientPlayer player, float partialTicks, float rotationPitch, EnumHand hand, float swingProgress, ItemStack stack, float equippedProgress, CallbackInfo info) {
        Chams mod = Chams.INSTANCE;
        if (this.injection) {
            info.cancel();
            boolean isFriend = Cracked.friendManager.isFriend(player.getName()); // is friend 
            this.injection = false;
            if (mod.isOn() && mod.self.getValue() && hand == EnumHand.MAIN_HAND && stack.isEmpty()) {
                Color color;
                if (mod.model.getValue() == Chams.Model.VANILLA) {
                    this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
                } else if (mod.model.getValue() == Chams.Model.XQZ) {
                    GL11.glEnable(32823);
                    GlStateManager.enablePolygonOffset();
                    GL11.glPolygonOffset(1.0f, -1000000.0f);
                    if (mod.modelColorEnable.getValue().booleanValue()) {
                        color = isFriend ? Cracked.colorChamsManager.getFriendColor((Integer) mod.modelColorAlpha.getValue()) : new Color(mod.modelColorRed.getValue(),(Integer) mod.modelColorGreen.getValue(),(Integer) mod.modelColorBlue.getValue(),(Integer) mod.modelColorAlpha.getValue());
                        RenderUtil.glColor(color);
                    }
                    this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
                    GL11.glDisable(32823);
                    GlStateManager.disablePolygonOffset();
                    GL11.glPolygonOffset(1.0f, 1000000.0f);
                }
                if (mod.wireframe.getValue()) {
                    color = isFriend ? Cracked.colorChamsManager.getFriendColor( (mod.lineColorEnable.getValue()).booleanValue() ? mod.lineColorAlpha.getValue() : mod.colorAlpha.getValue()) : (( mod.lineColorEnable.getValue()) ? new Color(mod.lineColorRed.getValue(), mod.lineColorGreen.getValue(), mod.lineColorBlue.getValue(), mod.lineColorAlpha.getValue()) : new Color(mod.colorRed.getValue(), mod.colorGreen.getValue(), mod.colorBlue.getValue(), mod.colorAlpha.getValue()));
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
                    this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
                    GL11.glPopAttrib();
                    GL11.glPopMatrix();
                }
                if (mod.fill.getValue()) {
                        color = isFriend ? Cracked.colorChamsManager.getFriendColor((Integer) mod.colorAlpha.getValue()) : new Color(mod.colorRed.getValue(),(Integer) mod.colorGreen.getValue(),(Integer) mod.colorBlue.getValue(),(Integer) mod.colorAlpha.getValue());
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
                    this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
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
                    float f = (float)player.ticksExisted + this.mc.getRenderPartialTicks();
                    this.mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                    for (int i = 0; i < 2; ++i) {
                        GlStateManager.matrixMode(5890);
                        GlStateManager.loadIdentity();
                        GL11.glScalef(1.0f, 1.0f, 1.0f);
                        GlStateManager.rotate(30.0f - (float)i * 60.0f, 0.0f, 0.0f, 1.0f);
                        GlStateManager.translate(0.0f, f * (0.001f + (float)i * 0.003f) * 20.0f, 0.0f);
                        GlStateManager.matrixMode(5888);
                        this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
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
                this.renderItemInFirstPerson(player, partialTicks, rotationPitch, hand, swingProgress, stack, equippedProgress);
            }
            this.injection = true;
        }
    }

    @Inject(method={"rotateArm"}, at={@At(value="HEAD")}, cancellable=true)
    public void rotateArmHook(float partialTicks, CallbackInfo info) {
    }



    @Inject(method={"transformSideFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo cancel) {
    }

    @Inject(method={"transformEatFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    private void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, ItemStack stack, CallbackInfo cancel) {
    }



    @Inject(method={"renderOverlays"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderOverlaysInject(float partialTicks, CallbackInfo ci) {
    }

}

