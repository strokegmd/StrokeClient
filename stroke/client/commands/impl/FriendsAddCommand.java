package net.stroke.client.commands.impl;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.util.FriendsManager;

public class FriendsAddCommand extends BaseCommand {
	public FriendsAddCommand() {
		super("friends add", 3);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.contains(this.chatName)) {
			return false;
		}
		
		String[] args = CommandManager.getArguments(message);
		if(args.length < this.argsLength || args.length > this.argsLength) {
			StrokeClient.sendChatMessage("Invalid length of command. Please use .help to get more information!");
			return true;
		}
		
		String username = args[2];
		if(!FriendsManager.friends.contains(username)) {
			FriendsManager.addFriend(username);
			StrokeClient.sendChatMessage("Sucessfully added " + username + " to friends list!");
		} else {
			StrokeClient.sendChatMessage(username + " is already in friends list!");
		}
		
		return true;
	}
}
