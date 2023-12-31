package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class NoWeather extends BaseModule {
	public static NoWeather instance;
	
	public NoWeather() {
		super("NoWeather", "Disables bad weather", 0x00, ModuleCategory.Render, false);
		instance = this;
		// EDITS: net.minecraft.world.World
	}
}
