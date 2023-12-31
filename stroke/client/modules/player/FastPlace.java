package net.stroke.client.modules.player;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class FastPlace extends BaseModule {
	public FastPlace() {
		super("FastPlace", "Allows you to quickly place blocks", 0x00, ModuleCategory.Player, false);
	}
	
	public void onUpdate() {
		mc.rightClickDelayTimer = 0;
	}
}
