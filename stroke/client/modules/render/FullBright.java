package net.stroke.client.modules.render;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class FullBright extends BaseModule {
	public static FullBright instance;
	
	public static float defaultGamma = 1.0f;
	public float fullGamma = 1E6F;
	
	public FullBright() {
		super("FullBright", "No more darkness", 0x00, ModuleCategory.Render, false);
		instance = this;
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = fullGamma;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = defaultGamma;
	}
}
