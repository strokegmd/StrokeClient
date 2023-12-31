package net.stroke.client.commands.impl;

import net.minecraft.block.Block;
import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.render.XRay;
import net.stroke.client.util.FriendsManager;

public class XRayAddCommand extends BaseCommand {
	public XRayAddCommand() {
		super("xray add", 3);
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
		
		String name = args[2];
		if(Block.getBlockFromName(name) != null) {
			Block block = Block.getBlockFromName(name);
			if(!XRay.instance.blocks.contains(block)) {
				XRay.instance.blocks.add(block);
				StrokeClient.sendChatMessage("Sucessfully added " + block.getLocalizedName() + " to XRay blocks list!");
				mc.renderGlobal.loadRenderers();
			} else {
				StrokeClient.sendChatMessage("This block is already in XRay blocks list.");
			}
		} else {
			StrokeClient.sendChatMessage("Cannot find this block.");
		}
		
		return true;
	}
}
