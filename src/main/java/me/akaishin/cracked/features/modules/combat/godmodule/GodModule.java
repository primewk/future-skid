package me.akaishin.cracked.features.modules.combat.godmodule;

import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.event.events.PacketEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.akaishin.cracked.features.modules.Module;

import java.util.Arrays;

public class GodModule extends Module {
    public static GodModule INSTANCE;
    public static int biggestEntityID = -1337;
    public Setting<Boolean> antiKick = this.register(new Setting<Boolean>("IllegalCancel", true));
    public Setting<Integer> attacks = this.register(new Setting<Integer>("Iteration", 2, 1, 20));
    public Setting<Boolean> swing = this.register(new Setting<Boolean>("SwingPacket", true));
    public Setting<Boolean> setDead = this.register(new Setting<Boolean>("SetDead", false));
    public Setting<Boolean> debug = this.register(new Setting<Boolean>("Debug", false));
    public Setting<Integer> delay = this.register(new Setting<Integer>("Delay", 0, 0, 200));

    public GodModule() {
        super("GodModule", "Makes crystals faster.", Module.Category.COMBAT, true, false, false);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        if (mc.world != null) {
            setHighestID();
        }
    }

    @Override
    public void onLogout() {
        biggestEntityID = -1337;
    }

    @Override
    public void onDisable() {
        biggestEntityID = -1337;
    }

    @Override
    public void onRenderTick() {
        if (debug.getValue()) {
            for (Entity entity : mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderCrystal)) continue;
                entity.setCustomNameTag(String.valueOf(entity.entityId));
                entity.setAlwaysRenderNameTag(true);
            }
        }
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock packet = event.getPacket();
            if (mc.player.getHeldItem(packet.getHand()).getItem() instanceof ItemEndCrystal) {
                // do prediction FUCK YOU
                if (antiKick.getValue() && playerHoldingIllegal()) {
                    return;
                }
                setHighestID();
                for (int x = 1; x < attacks.getValue(); ++x) {
                    attack(biggestEntityID + x);
                }
            }
        }
    }

    private void setHighestID() {
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity.getEntityId() <= biggestEntityID) continue;
            biggestEntityID = entity.getEntityId();
        }
    }

    private boolean playerHoldingIllegal() {
        for (Entity player : mc.world.loadedEntityList) {
            if (!(player instanceof EntityPlayer)) {
                continue;
            }
            if (((EntityPlayer) player).getHealth() <= 0.0f) {
                continue;
            }
            if (player.isDead) {
                continue;
            }
            if (isHoldingIllegal((EntityPlayer) player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isHoldingIllegal(EntityPlayer player) {
        Item[] illegalItems = new Item[]{Items.ENDER_EYE, Items.POTIONITEM, Items.LINGERING_POTION, Items.SPLASH_POTION, Items.EXPERIENCE_BOTTLE, Items.STRING, Items.BOW, Items.ENDER_PEARL, Items.BOAT, Items.ACACIA_BOAT, Items.BIRCH_BOAT, Items.DARK_OAK_BOAT, Items.JUNGLE_BOAT, Items.SPRUCE_BOAT, Items.EGG};
        return (Arrays.asList(illegalItems).contains(player.getHeldItemMainhand().getItem()) || Arrays.asList(illegalItems).contains(player.getHeldItemOffhand().getItem()));
    }

    private void attack(int entityID) {
        Entity entity = mc.world.getEntityByID(entityID);
        if (entity == null || entity instanceof EntityEnderCrystal) {
            UseThread useThread = new UseThread(entityID, this.delay.getValue());
            if (delay.getValue() == 0) {
                useThread.run();
            } else {
                useThread.start();
            }
        }
    }

    private void setCheckID(int id) {
        if (id > biggestEntityID) {
            biggestEntityID = id;
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSpawnObject) {
            this.setCheckID(((SPacketSpawnObject) event.getPacket()).getEntityID());
        } else if (event.getPacket() instanceof SPacketSpawnExperienceOrb) {
            this.setCheckID(((SPacketSpawnExperienceOrb) event.getPacket()).getEntityID());
        } else if (event.getPacket() instanceof SPacketSpawnPlayer) {
            this.setCheckID(((SPacketSpawnPlayer) event.getPacket()).getEntityID());
        } else if (event.getPacket() instanceof SPacketSpawnGlobalEntity) {
            this.setCheckID(((SPacketSpawnGlobalEntity) event.getPacket()).getEntityId());
        } else if (event.getPacket() instanceof SPacketSpawnPainting) {
            this.setCheckID(((SPacketSpawnPainting) event.getPacket()).getEntityID());
        } else if (event.getPacket() instanceof SPacketSpawnMob) {
            this.setCheckID(((SPacketSpawnMob) event.getPacket()).getEntityID());
        }
    }
}
