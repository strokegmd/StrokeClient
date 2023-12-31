package net.stroke.client.modules.movement;

import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class AirJump extends BaseModule {
	public AirJump() {
		super("AirJump", "Allows you to infinitly jumping in air", 0x00, ModuleCategory.Movement, false);
	}
	
	public void onUpdate() {
        if(mc.gameSettings.keyBindJump.isPressed()) {
        	mc.player.jump();
        }
	}
}
