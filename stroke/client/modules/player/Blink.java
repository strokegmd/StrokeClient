package net.stroke.client.modules.player;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Blink extends BaseModule {
	public static Blink instance;
	
	public EntityOtherPlayerMP blinkPlayer;
	
	public Blink() {
		super("Blink", "Walk out of your body", 0x00, ModuleCategory.Player, false);
		instance = this;
		// EDITS: net.minecraft.client.entity.EntityPlayerSP
	}
	
	public void onEnable() {
		blinkPlayer = new EntityOtherPlayerMP(mc.world, mc.player.getGameProfile());
		blinkPlayer.copyLocationAndAnglesFrom(mc.player);
		
		mc.world.addEntityToWorld(-1, blinkPlayer);
	}
	
	public void onDisable() {
		mc.world.removeEntityFromWorld(-1);
	}
}
