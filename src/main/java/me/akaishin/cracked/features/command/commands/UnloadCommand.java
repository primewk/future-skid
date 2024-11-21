package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;

public class UnloadCommand
        extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Cracked.unload(true);
    }
}

