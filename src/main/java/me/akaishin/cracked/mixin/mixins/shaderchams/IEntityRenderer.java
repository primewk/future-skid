package me.akaishin.cracked.mixin.mixins.shaderchams;

import org.spongepowered.asm.mixin.gen.Invoker;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={EntityRenderer.class})
public interface IEntityRenderer {
    
    @Invoker("setupCameraTransform")
    void invokeSetupCameraTransform(final float p0, final int p1);
    
    @Invoker("renderHand")
    void invokeRenderHand(final float p0, final int p1);
}
