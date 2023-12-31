package net.stroke.client.commands.impl;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleManager;

public class ModulesCommand extends BaseCommand {
	public ModulesCommand() {
		super("modules", 0);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.split(" ")[0].equals(this.chatName)) {
			return false;
		}
		
		for (BaseModule module : ModuleManager.modules) {
			String formatted = String.format("%s - %s", module.name, module.tooltip);
			StrokeClient.sendChatMessage(formatted);
		}
		
		return true;
	}
}