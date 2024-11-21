//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package me.akaishin.cracked.features.modules.player;

import net.minecraft.client.entity.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import com.mojang.realmsclient.gui.*;

import me.akaishin.cracked.*;
import me.akaishin.cracked.event.events.*;
import me.akaishin.cracked.features.command.*;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.util.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

public class FakePlayer extends Module
{
    private static FakePlayer INSTANCE;
    private final Setting<Boolean> pops;
    private final Setting<Boolean> totemPopParticle;
    private final Setting<Boolean> totemPopSound;
    public Setting<Boolean> move;
    public Setting<Type> type;
    public Setting<Integer> chaseX;
    public Setting<Integer> chaseY;
    public Setting<Integer> chaseZ;
    public EntityOtherPlayerMP fakePlayer;
    
    public FakePlayer() {
        super("FakePlayer", "Spawns a FakePlayer for testing.", Module.Category.PLAYER, true, false, false);
        this.pops = (Setting<Boolean>)this.register(new Setting("TotemPops", true));
        this.totemPopParticle = (Setting<Boolean>)this.register(new Setting("TotemPopParticle", true));
        this.totemPopSound = (Setting<Boolean>)this.register(new Setting("TotemPopSound", true));
        this.move = (Setting<Boolean>)this.register(new Setting("Move", true));
        this.type = (Setting<Type>)this.register(new Setting("MovementMode", Type.NONE, v -> this.move.getValue()));
        this.chaseX = (Setting<Integer>)this.register(new Setting("ChaseX", 4, 1, 120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
        this.chaseY = (Setting<Integer>)this.register(new Setting("ChaseY", 4, 1, 120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
        this.chaseZ = (Setting<Integer>)this.register(new Setting("ChaseZ", 4, 1, 120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
    }
    
    public static FakePlayer getInstance() {
        if (FakePlayer.INSTANCE == null) {
            FakePlayer.INSTANCE = new FakePlayer();
        }
        return FakePlayer.INSTANCE;
    }
    
    public void onEnable() {
        if (FakePlayer.mc.world == null || FakePlayer.mc.player == null) {
            this.disable();
        }
        else {
            final UUID playerUUID = FakePlayer.mc.player.getUniqueID();
            (this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString(playerUUID.toString()), FakePlayer.mc.player.getDisplayNameString()))).copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
            this.fakePlayer.inventory.copyInventory(FakePlayer.mc.player.inventory);
            FakePlayer.mc.world.addEntityToWorld(-7777, (Entity)this.fakePlayer);
            Command.sendMessage(ChatFormatting.GREEN + "FakePlayer spawned");
        }
    }
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.type.getValue().equals(Type.CHASE)) {
            this.fakePlayer.posX = FakePlayer.mc.player.posX + this.chaseX.getValue();
            this.fakePlayer.posY = this.chaseY.getValue();
            this.fakePlayer.posZ = FakePlayer.mc.player.posZ + this.chaseZ.getValue();
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (this.fakePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion explosion = (SPacketExplosion)event.getPacket();
            if (this.fakePlayer.getDistance(explosion.getX(), explosion.getY(), explosion.getZ()) <= 15.0) {
                final double damage = DamageUtil.calculateDamage(explosion.getX(), explosion.getY(), explosion.getZ(), (Entity)this.fakePlayer);
                if (damage > 0.0 && this.pops.getValue()) {
                    this.fakePlayer.setHealth((float)(this.fakePlayer.getHealth() - MathHelper.clamp(damage, 0.0, 999.0)));
                }
            }
        }
    }
    
    public void travel(final float strafe, final float vertical, final float forward) {
        final double d0 = this.fakePlayer.posY;
        float f1 = 0.8f;
        float f2 = 0.02f;
        float f3 = (float)EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.fakePlayer);
        if (f3 > 3.0f) {
            f3 = 3.0f;
        }
        if (!this.fakePlayer.onGround) {
            f3 *= 0.5f;
        }
        if (f3 > 0.0f) {
            f1 += (0.54600006f - f1) * f3 / 3.0f;
            f2 += (this.fakePlayer.getAIMoveSpeed() - f2) * f3 / 4.0f;
        }
        this.fakePlayer.moveRelative(strafe, vertical, forward, f2);
        this.fakePlayer.move(MoverType.SELF, this.fakePlayer.motionX, this.fakePlayer.motionY, this.fakePlayer.motionZ);
        final EntityOtherPlayerMP fakePlayer = this.fakePlayer;
        fakePlayer.motionX *= f1;
        final EntityOtherPlayerMP fakePlayer2 = this.fakePlayer;
        fakePlayer2.motionY *= 0.800000011920929;
        final EntityOtherPlayerMP fakePlayer3 = this.fakePlayer;
        fakePlayer3.motionZ *= f1;
        if (!this.fakePlayer.hasNoGravity()) {
            final EntityOtherPlayerMP fakePlayer4 = this.fakePlayer;
            fakePlayer4.motionY -= 0.02;
        }
        if (this.fakePlayer.collidedHorizontally && this.fakePlayer.isOffsetPositionInLiquid(this.fakePlayer.motionX, this.fakePlayer.motionY + 0.6000000238418579 - this.fakePlayer.posY + d0, this.fakePlayer.motionZ)) {
            this.fakePlayer.motionY = 0.30000001192092896;
        }
    }
    
    public void onDisable() {
        if (this.fakePlayer != null && FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-7777);
            Command.sendMessage(ChatFormatting.GREEN + "FakePlayer remove");
            this.fakePlayer = null;
        }
    }
    
    private void fakePop(final Entity entity) {
        if (this.totemPopParticle.getValue()) {
            FakePlayer.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, 30);
        }
        if (this.totemPopSound.getValue()) {
            FakePlayer.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
        }
    }
    
    static {
        FakePlayer.INSTANCE = new FakePlayer();
    }
    
    public enum Type
    {
        NONE, 
        CHASE, 
        STATIC;
    }
}
