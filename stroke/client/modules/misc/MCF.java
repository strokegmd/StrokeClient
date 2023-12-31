package net.stroke.client.modules.misc;

import org.lwjgl.input.Mouse;

import net.minecraft.entity.player.EntityPlayer;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.util.FriendsManager;

public class MCF extends BaseModule {
	public MCF() {
		super("MCF", "Automatically adding pointed entity to your friends (use wheel)", 0x00, ModuleCategory.Misc, false);
	}
	
	public void onUpdate() {
		if(Mouse.isButtonDown(2) && mc.pointedEntity instanceof EntityPlayer) {
			String name = mc.pointedEntity.getName();
			String command = ".friends add " + name;
			if(!FriendsManager.isFriend(name)) {
				mc.player.sendChatMessage(command);
			}
		}
	}
}
