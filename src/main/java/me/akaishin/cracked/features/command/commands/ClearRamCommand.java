package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.features.command.Command;

public class ClearRamCommand
        extends Command {
    public ClearRamCommand() {
        super("clearram");
    }

    @Override
    public void execute(String[] commands) {
        System.gc();
        Command.sendMessage("Finished clearing the ram.", false);
    }
}