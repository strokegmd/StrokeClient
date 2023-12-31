package net.stroke.client.commands.impl;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;

public class HelpCommand extends BaseCommand {
	public HelpCommand() {
		super("help", 0);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.split(" ")[0].equals(this.chatName)) {
			return false;
		}
		
		StrokeClient.sendChatMessage(".help - Display this message.");
		StrokeClient.sendChatMessage(".bind <module> <key> - Binds a module to a key.");
		StrokeClient.sendChatMessage(".friends add <username> - Adds a friend.");
		StrokeClient.sendChatMessage(".friends remove <username> - Removes a friend.");
		StrokeClient.sendChatMessage(".friends list - Shows nothing.");
		StrokeClient.sendChatMessage(".xray add <block id> - Adds a block to XRay list.");
		StrokeClient.sendChatMessage(".xray remove <block id> - Removes a block from XRay list.");
		StrokeClient.sendChatMessage(".xray list - Shows a list of XRay blocks.");
		StrokeClient.sendChatMessage(".toggle <module> - Toggles a module.");
		StrokeClient.sendChatMessage(".modules - Shows all modules.");
		
		return true;
	}
}