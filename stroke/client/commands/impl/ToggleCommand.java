package net.stroke.client.commands.impl;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleManager;

public class ToggleCommand extends BaseCommand {
	public ToggleCommand() {
		super("toggle", 2);
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
		for(BaseModule module : ModuleManager.modules) {
			if(module.name.equalsIgnoreCase(moduleName)) {
				module.toggle();
				if(module.enabled) {
					StrokeClient.sendChatMessage("Toggled " + module.name + " [§aON§r]!");
				} else {
					StrokeClient.sendChatMessage("Toggled " + module.name + " [§cOFF§r]!");
				}
				return true;
			}
		}
		
		StrokeClient.sendChatMessage("Unknown module");
		return true;
	}
}
