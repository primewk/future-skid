package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.features.command.Command;

public class ChatClearCommand
        extends Command {
    public ChatClearCommand() {
        super("chatclear", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        mc.ingameGUI.getChatGUI().clearChatMessages(true);
    }
}