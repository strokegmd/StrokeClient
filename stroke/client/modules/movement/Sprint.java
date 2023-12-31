package net.stroke.client.modules.movement;

import net.minecraft.client.settings.KeyBinding;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class Sprint extends BaseModule {
	public Sprint() {
		super("Sprint", "Automatically enabling sprint", 0x00, ModuleCategory.Movement, false);
	}
	
	public void onRender() {
		KeyBinding[] binds = {mc.gameSettings.keyBindForward,
				  			  mc.gameSettings.keyBindBack,
				  			  mc.gameSettings.keyBindRight,
				  			  mc.gameSettings.keyBindLeft};
		
		for(KeyBinding bind : binds) {
			if(bind.isKeyDown() && !mc.player.isSneaking()) {
				mc.player.setSprinting(true);
			}
		}
	}
}
