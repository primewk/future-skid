package me.akaishin.cracked.manager;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.command.commands.*;
import me.akaishin.cracked.features.modules.chat.ModuleTools;
import me.akaishin.cracked.util.TextUtil;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandManager
        extends Feature {
    private final ArrayList<Command> commands = new ArrayList();
		private String clientMessage = "\u00A7b\u00A7l[\u00A7a\u00A7lCracked\u00A7b\u00A7l]\u00A7r ";
    private String prefix = ".";

    public CommandManager() {
        super("Command");
        this.commands.add(new BindCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new XrayCommand());
		this.commands.add(new NameMCCommand());
        this.commands.add(new PrefixCommand());
		this.commands.add(new FakePlayerCommand());
		this.commands.add(new BrowseCommand());
		this.commands.add(new OpenFolderCommand());
		this.commands.add(new ChatClearCommand());
        this.commands.add(new ConfigCommand());
		this.commands.add(new ClearRamCommand());
		this.commands.add(new CoordsCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new UnloadCommand());
        this.commands.add(new ReloadSoundCommand());
        this.commands.add(new PeekCommand());
		this.commands.add(new ToggleCommand());
        this.commands.add(new BookCommand());
        this.commands.add(new CrashCommand());
        this.commands.add(new HistoryCommand());
    }

    public static String[] removeElement(String[] input, int indexToDelete) {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < input.length; ++i) {
            if (i == indexToDelete) continue;
            result.add(input[i]);
        }
        return result.toArray(input);
    }

    private static String strip(String str, String key) {
        if (str.startsWith(key) && str.endsWith(key)) {
            return str.substring(key.length(), str.length() - key.length());
        }
        return str;
    }

    public void executeCommand(String command) {
        String[] parts = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String name = parts[0].substring(1);
        String[] args = CommandManager.removeElement(parts, 0);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) continue;
            args[i] = CommandManager.strip(args[i], "\"");
        }
        for (Command c : this.commands) {
            if (!c.getName().equalsIgnoreCase(name)) continue;
            c.execute(parts);
            return;
        }
        Command.sendMessage("Unknown command. Try 'help' for a list of commands.");
    }

    public Command getCommandByName(String name) {
        for (Command command : this.commands) {
            if (!command.getName().equals(name)) continue;
            return command;
        }
        return null;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
