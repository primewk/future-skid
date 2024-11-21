package me.akaishin.cracked.features.command.commands;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.Module;

public class ToggleCommand
        extends Command {
    public ToggleCommand() {
        super("toggle", new String[]{"<toggle>", "<module>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 2) {
            String name = commands[0].replaceAll("_", " ");
            Module module = Cracked.moduleManager.getModuleByName(name);
            if (module != null) {
                module.toggle();
            } else {
                Command.sendMessage("Unable to find a module with that name!");
            }
        } else {
            Command.sendMessage("Please provide a valid module name!");
        }
    }
}