package me.akaishin.cracked.mixin.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.akaishin.cracked.features.modules.combat.Offhand;

@Mixin(value = {ItemFood.class})
public class MixinItemFood {
    @Inject(method = {"onItemUseFinish"}, at = {@At(value = "RETURN")}, cancellable = true)
    public void onItemUseFinishHook(ItemStack stack, World worldIn, EntityLivingBase entityLiving, CallbackInfoReturnable<ItemStack> info) {
        Offhand.getInstance().onItemFinish(stack, entityLiving);
    }
}

