package net.stroke.client.modules.movement;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class NoSlowDown extends BaseModule {
	public static NoSlowDown instance;
	
	public NoSlowDown() {
		super("NoSlowDown", "Prevents being slowed down when you are eating", 0x00, ModuleCategory.Movement, false);
		instance = this;
		// EDITS: net.minecraft.entity.player.EntityPlayerSP
	}
}
