package me.akaishin.cracked.features.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.misc.FriendSettings;
import me.akaishin.cracked.util.Util;
import net.minecraft.network.play.client.CPacketChatMessage;

import java.util.Map;
import java.util.UUID;

public class FriendCommand
        extends Command {
    public FriendCommand() {
        super("friend", new String[]{"<add/del/name/clear>", "<name>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            if (Cracked.friendManager.getFriends().isEmpty()) {
                FriendCommand.sendMessage("You currently dont have any friends added.");
            } else {
                FriendCommand.sendMessage("Friends: ");
                for (Map.Entry<String, UUID> entry : Cracked.friendManager.getFriends().entrySet()) {
                    FriendCommand.sendMessage(entry.getKey());
                }
            }
            return;
        }
        if (commands.length == 2) {
            switch (commands[0]) {
                case "reset": {
                    Cracked.friendManager.onLoad();
                    FriendCommand.sendMessage("Friends got reset.");
                    return;
                }
            }
            FriendCommand.sendMessage(commands[0] + (Cracked.friendManager.isFriend(commands[0]) ? " is friended." : " isn't friended."));
            return;
        }
        if (commands.length >= 2) {
            switch (commands[0]) {
                case "add": {
                    Cracked.friendManager.addFriend(commands[1]);
                    FriendCommand.sendMessage(ChatFormatting.GREEN + commands[1] + " has been friended");
                    if (FriendSettings.getInstance().notify.getValue()) {
                        Util.mc.player.connection.sendPacket(new CPacketChatMessage("/w " + commands[1] + " I just added you to my friends list on hack!"));
                    }
                    return;
                }
                case "del": {
                    Cracked.friendManager.removeFriend(commands[1]);
                    if (FriendSettings.getInstance().notify.getValue()) {
                        Util.mc.player.connection.sendPacket(new CPacketChatMessage("/w " + commands[1] + " I just removed you from my friends list on hack!"));
                    }
                    FriendCommand.sendMessage(ChatFormatting.RED + commands[1] + " has been unfriended");
                    return;
                }
            }
            FriendCommand.sendMessage("Unknown Command, try friend add/del (name)");
        }
    }
}


