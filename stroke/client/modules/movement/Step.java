package net.stroke.client.modules.movement;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Step extends BaseModule {
	public Step() {
		super("Step", "Allows you to go higher without jumping", 0x00, ModuleCategory.Movement, false);
	}
	
	public void onEnable() {
		mc.player.stepHeight = 1;
	}
	
	public void onDisable() {
		mc.player.stepHeight = 0.5f;
	}
}
