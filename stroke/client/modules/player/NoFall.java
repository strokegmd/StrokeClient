package net.stroke.client.modules.player;

import net.minecraft.network.play.client.CPacketPlayer;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class NoFall extends BaseModule {
	public NoFall() {
		super("NoFall", "Prevents you from getting fall damage", 0x00, ModuleCategory.Player, false);
	}
	
	public void onUpdate() {
		if(mc.player.fallDistance > 1.0f) {
			mc.player.connection.sendPacket(new CPacketPlayer(true));
		}
	}
}
