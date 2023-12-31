package net.stroke.client.modules.misc;

import org.lwjgl.opengl.Display;

import net.stroke.client.StrokeClient;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;
import net.stroke.client.modules.ModuleManager;

public class SelfDestruct extends BaseModule {
	public SelfDestruct() {
		super("SelfDestruct", "Removes all traces of client in your game", 0x00, ModuleCategory.Misc, false);
	}
	
	public void onEnable() {
		StrokeClient.destructed = true;
		
		Display.setTitle("Minecraft 1.12.2");
		for(BaseModule module : ModuleManager.modules) {
			module.keybind = 0x00;
			module.enabled = false;
			module.onDisable();
		}
	}
}
