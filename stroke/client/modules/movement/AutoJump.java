package net.stroke.client.modules.movement;

import net.minecraft.client.settings.KeyBinding;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AutoJump extends BaseModule {
	public AutoJump() {
		super("AutoJump", "Automatically jumps for you", 0x00, ModuleCategory.Movement, false);
		
		StrokeClient.instance.settingsManager.rSetting(new Setting("Delay", this, 2, 1, 10, true));
	}
	
	public void onUpdate() {
		double delay = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Delay").getValDouble();
		
		KeyBinding[] binds = {mc.gameSettings.keyBindForward,
				  			  mc.gameSettings.keyBindBack,
				  			  mc.gameSettings.keyBindRight,
				  			  mc.gameSettings.keyBindLeft};
		
		for(KeyBinding bind : binds) {
			if(mc.player.onGround && bind.isKeyDown() && mc.player.ticksExisted % delay == 0) {
				mc.player.jump();
			}
		}
	}
}
