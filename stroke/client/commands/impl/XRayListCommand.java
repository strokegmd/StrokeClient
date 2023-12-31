package net.stroke.client.commands.impl;

import net.minecraft.block.Block;
import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.render.XRay;
import net.stroke.client.util.FriendsManager;

public class XRayListCommand extends BaseCommand {
	public XRayListCommand() {
		super("xray list", 2);
	}
	
	public boolean onChatMessage(String message) {
		if(!message.contains(this.chatName)) {
			return false;
		}
		
		String blocksNumber = Integer.toString(XRay.instance.blocks.size());
		
		String blocks = "";
		for(Block block : XRay.instance.blocks) {
			String name = block.getLocalizedName();
			blocks += name + ", ";
		}
		
		if(blocks.length() > 0) {
			blocks = blocks.substring(0, blocks.length() - 2);
		}
		
		StrokeClient.sendChatMessage("Blocks (" + blocksNumber + "): " + blocks);
		return true;
	}
}
