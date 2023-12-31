package net.stroke.client.modules.player;

import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Timer extends BaseModule {
	public Timer() {
		super("Timer", "Increases speed of your game", 0x00, ModuleCategory.Player, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Speed", this, 1.1, 0.1, 50, false));
	}
	
	public void onUpdate() {
		double speed = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Speed").getValDouble();
		
		mc.timer = new net.minecraft.util.Timer((float) (20.0f * speed));
	}
	
	public void onDisable() {
		mc.timer = new net.minecraft.util.Timer(20.0f);
	}
}
