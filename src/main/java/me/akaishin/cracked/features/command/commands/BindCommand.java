package me.akaishin.cracked.features.command.commands;

import org.lwjgl.input.Keyboard;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Bind;

public class BindCommand
        extends Command {
    public BindCommand() {
        super("bind", new String[]{"<module>", "<bind>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            BindCommand.sendMessage("Please specify a module.");
            return;
        }
        String rkey = commands[1];
        String moduleName = commands[0];
        Module module = Cracked.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            BindCommand.sendMessage("Unknown module '" + module + "'!");
            return;
        }
        if (rkey == null) {
            BindCommand.sendMessage(module.getName() + " is bound to &b" + module.getBind().toString());
            return;
        }
        int key = Keyboard.getKeyIndex(rkey.toUpperCase());
        if (rkey.equalsIgnoreCase("none")) {
            key = -1;
        }
        if (key == 0) {
            BindCommand.sendMessage("Unknown key '" + rkey + "'!");
            return;
        }
        module.bind.setValue(new Bind(key));
        BindCommand.sendMessage("Bind for &b" + module.getName() + "&r set to &b" + rkey.toUpperCase());
    }
}

