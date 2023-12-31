package net.stroke.client.commands;

import net.minecraft.client.Minecraft;

public class BaseCommand {
	public Minecraft mc = Minecraft.getMinecraft();
	
	public String chatName;
	
	public int argsLength;
	
	public BaseCommand(String name, int argsLength) {
		this.chatName = "." + name;
		this.argsLength = argsLength;
	}
	
	public boolean onChatMessage(String message) {
		return false;
	}
}
