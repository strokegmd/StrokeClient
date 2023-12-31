package net.stroke.client.commands.impl;

import org.lwjgl.input.Keyboard;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleManager;

public class BindCommand extends BaseCommand {
	public BindCommand() {
		super("bind", 3);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.split(" ")[0].equals(this.chatName)) {
			return false;
		}
		
		String[] args = CommandManager.getArguments(message);
		if(args.length < this.argsLength || args.length > this.argsLength) {
			StrokeClient.sendChatMessage("Invalid length of command. Please use .help to get more information!");
			return true;
		}
		
		String moduleName = args[1];
		String key = args[2].toUpperCase();
		
		for(BaseModule module : ModuleManager.modules) {
			if(module.name.equalsIgnoreCase(moduleName)) {
				int index = Keyboard.getKeyIndex(key);
				if(index != 0) {
					module.keybind = index;
					StrokeClient.sendChatMessage("Sucessfully bound " + module.name + " to " + key + "!");
				} else {
					StrokeClient.sendChatMessage("Can't bind " + module.name + " to " + key + "!");
				}
				
				return true;
			}
		}
		
		StrokeClient.sendChatMessage("Unknown module");
		return true;
	}
}
