package me.akaishin.cracked.features.modules.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.client.ClickGui;

import me.akaishin.cracked.features.setting.Bind;
import me.akaishin.cracked.features.setting.Setting;

public class MCF
        extends Module {
    private final Setting<Boolean> middleClick = this.register(new Setting<Boolean>("MiddleClick", true));
    private final Setting<Boolean> keyboard = this.register(new Setting<Boolean>("Keyboard", false));
    private final Setting<Boolean> server = this.register(new Setting<Boolean>("Server", true));
    private final Setting<Bind> key = this.register(new Setting<Object>("KeyBind", new Bind(-1), v -> this.keyboard.getValue()));
    private boolean clicked = false;

    public MCF() {
        super("MCF", "Middleclick Friends.", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked && this.middleClick.getValue().booleanValue() && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        } else {
            this.clicked = false;
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (this.keyboard.getValue().booleanValue() && Keyboard.getEventKeyState() && !(MCF.mc.currentScreen instanceof CrackedHackGui) && this.key.getValue().getKey() == Keyboard.getEventKey()) {
            this.onClick();
        }
    }

    private void onClick() {
        Entity entity;
        RayTraceResult result = MCF.mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY && (entity = result.entityHit) instanceof EntityPlayer) {
            if (Cracked.friendManager.isFriend(entity.getName())) {
                Cracked.friendManager.removeFriend(entity.getName());
                Command.sendMessage("\u00a7c" + entity.getName() + "\u00a7r" + " unfriended.");
                if (this.server.getValue().booleanValue()) {
                    MCF.mc.player.connection.sendPacket(new CPacketChatMessage("@Serverprefix" + ClickGui.getInstance().prefix.getValue()));
                    MCF.mc.player.connection.sendPacket(new CPacketChatMessage("@Server" + ClickGui.getInstance().prefix.getValue() + "friend del " + entity.getName()));
                }
            } else {
                Cracked.friendManager.addFriend(entity.getName());
                Command.sendMessage("\u00a7b" + entity.getName() + "\u00a7r" + " friended.");
                if (this.server.getValue().booleanValue()) {
                    MCF.mc.player.connection.sendPacket(new CPacketChatMessage("@Serverprefix" + ClickGui.getInstance().prefix.getValue()));
                    MCF.mc.player.connection.sendPacket(new CPacketChatMessage("@Server" + ClickGui.getInstance().prefix.getValue() + "friend add " + entity.getName()));
                }
            }
        }
        this.clicked = true;
    }
}

