package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Cracked.reload();
    }
}

