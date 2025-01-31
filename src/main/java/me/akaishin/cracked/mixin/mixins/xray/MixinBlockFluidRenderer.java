//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.mixin.mixins.xray;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.akaishin.cracked.features.modules.render.XRay;

import org.spongepowered.asm.mixin.injection.*;

@Mixin({ BlockFluidRenderer.class })
public class MixinBlockFluidRenderer
{
    @Inject(method = { "renderFluid" }, at = { @At("HEAD") }, cancellable = true)
    public void renderFluidHook(final IBlockAccess blockAccess, final IBlockState blockState, final BlockPos blockPos, final BufferBuilder bufferBuilder, final CallbackInfoReturnable<Boolean> info) {
        if (XRay.getInstance().isOn() && !XRay.getInstance().shouldRender(blockState.getBlock())) {
            info.setReturnValue(false);
            info.cancel();
        }
    }
}
