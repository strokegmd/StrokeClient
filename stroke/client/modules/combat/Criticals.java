package net.stroke.client.modules.combat;

import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.CPacketPlayer;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Criticals extends BaseModule {
	public static Criticals instance;
	
	public Criticals() {
		super("Criticals", "Deal critical damage everytime you hit", 0x00, ModuleCategory.Combat, false);
		instance = this;
	}
	
	public void doCrit() {
		if(!mc.player.isInWater() && !mc.player.isInsideOfMaterial(Material.LAVA) && mc.player.onGround) {
			double posX = mc.player.posX;
			double posY = mc.player.posY;
			double posZ = mc.player.posZ;
					
			mc.player.connection.sendPacket(new CPacketPlayer.Position(posX, posY + 0.0625D, posZ, true));
			mc.player.connection.sendPacket(new CPacketPlayer.Position(posX, posY, posZ, false));
			mc.player.connection.sendPacket(new CPacketPlayer.Position(posX, posY + 1.1E-5D, posZ, false));
			mc.player.connection.sendPacket(new CPacketPlayer.Position(posX, posY, posZ, false));
		}
	}
}