package net.stroke.client.util;

import java.util.ArrayList;

public class FriendsManager {
	public static ArrayList<String> friends = new ArrayList<String>();
	
	public static void addFriend(String username) {
		friends.add(username);
	}
	
	public static void removeFriend(String username) {
		friends.remove(username);
	}
	
	public static boolean isFriend(String username) {
		return friends.contains(username);
	}
}
