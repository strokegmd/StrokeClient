package net.stroke.client.modules.render;

import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class ViewModel extends BaseModule {
	public static ViewModel instance;
	
	public ViewModel() {
		super("ViewModel", "Changes position of your hands", 0x00, ModuleCategory.Render, false);
		instance = this;
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Left X", this, 0, -2, 2, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Left Y", this, 0.2, -2, 2, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Left Z", this, 0.2, -2, 2, false));
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Right X", this, 0, -2, 2, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Right Y", this, 0.2, -2, 2, false));
		StrokeClient.instance.settingsManager.rSetting(new Setting("Right Z", this, 0.2, -2, 2, false));
		
		// EDITS: net.minecraft.client.renderer.ItemRenderer
	}
}
