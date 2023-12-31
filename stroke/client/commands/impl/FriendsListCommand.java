package net.stroke.client.commands.impl;

import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.util.FriendsManager;

public class FriendsListCommand extends BaseCommand {
	public FriendsListCommand() {
		super("friends list", 2);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.contains(this.chatName)) {
			return false;
		}
		
		String friendsNumber = Integer.toString(FriendsManager.friends.size());
		
		String friends = "";
		for(String friend : FriendsManager.friends) {
			friends += friend + ", ";
		}
		
		if(friends.length() > 0) {
			friends = friends.substring(0, friends.length() - 2);
		}
		
		StrokeClient.sendChatMessage("Friends (" + friendsNumber + "): " + friends);
		return true;
	}
}
