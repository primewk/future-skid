package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.player.FakePlayer;

public class FakePlayerCommand extends Command {

    public FakePlayerCommand() { super("fakeplayer"); }

    @Override
    public void execute(String[] commands) {
        FakePlayer.getInstance().enable();
    }
}