package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("You can use following commands: ");
        for (Command command : Cracked.commandManager.getCommands()) {
            HelpCommand.sendMessage(Cracked.commandManager.getPrefix() + command.getName());
        }
    }
}