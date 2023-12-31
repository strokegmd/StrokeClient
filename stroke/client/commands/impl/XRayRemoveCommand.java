package net.stroke.client.commands.impl;

import net.minecraft.block.Block;
import net.stroke.client.StrokeClient;
import net.stroke.client.commands.BaseCommand;
import net.stroke.client.commands.CommandManager;
import net.stroke.client.modules.render.XRay;
import net.stroke.client.util.FriendsManager;

public class XRayRemoveCommand extends BaseCommand {
	public XRayRemoveCommand() {
		super("xray remove", 3);
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
			if(XRay.instance.blocks.contains(Block.getBlockFromName(name))) {
				XRay.instance.blocks.remove(Block.getBlockFromName(name));
				StrokeClient.sendChatMessage("Sucessfully removed " + Block.getBlockFromName(name).getLocalizedName() + " from XRay blocks list!");
				mc.renderGlobal.loadRenderers();
			} else {
				StrokeClient.sendChatMessage("Cannot find this block in XRay blocks list.");
			}
		} else {
			StrokeClient.sendChatMessage("Cannot find this block.");
		}
		
		return true;
	}
}
