package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Wallhack extends BaseModule {
	public static Wallhack instance;
	
	public Wallhack() {
		super("Wallhack", "Allows you to see entities through blocks", 0x00, ModuleCategory.Render, false);
		instance = this;
	}
}
