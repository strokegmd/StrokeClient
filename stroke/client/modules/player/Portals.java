package net.stroke.client.modules.player;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Portals extends BaseModule {
	public static Portals instance;
	
	public Portals() {
		super("Portals", "Allows you to open gui in nether portals", 0x00, ModuleCategory.Player, false);
		instance = this;
		// EDITS: net.minecraft.client.entity.EntityPlayerSP
	}
}
