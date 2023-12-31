package net.stroke.client.modules.movement;

import net.minecraft.entity.Entity;
import net.stroke.client.StrokeClient;
import net.stroke.client.clickgui.Setting;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class EntitySpeed extends BaseModule {
	public EntitySpeed() {
		super("EntitySpeed", "Increases speed of riding entity", 0x00, ModuleCategory.Movement, false);
		StrokeClient.instance.settingsManager.rSetting(new Setting("Speed", this, 1, 1, 5, false));
	}

	public void onUpdate() {
		double speed = StrokeClient.instance.settingsManager.getSettingByName(this.name, "Speed").getValDouble();
		
		if(mc.player.ridingEntity != null) {
			mc.player.ridingEntity.motionX *= speed / 2.0f;
			mc.player.ridingEntity.motionZ *= speed / 2.0f;
		}
	}
}
