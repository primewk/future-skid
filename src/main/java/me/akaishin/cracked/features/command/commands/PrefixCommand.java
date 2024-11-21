package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.client.ClickGui;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage("\u00a7cSpecify a new prefix.");
            return;
        }
        Cracked.moduleManager.getModuleByClass(ClickGui.class).prefix.setValue(commands[0]);
        Command.sendMessage("Prefix set to \u00a7a" + Cracked.commandManager.getPrefix());
    }
}

