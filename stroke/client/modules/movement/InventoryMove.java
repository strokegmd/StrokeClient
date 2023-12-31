package net.stroke.client.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class InventoryMove extends BaseModule {
	public InventoryMove() {
		super("InventoryMove", "Allows you to move when gui opened", 0x00, ModuleCategory.Movement, false);
	}
	
	public void onUpdate() {
		KeyBinding[] binds = {mc.gameSettings.keyBindForward,
							  mc.gameSettings.keyBindBack,
							  mc.gameSettings.keyBindLeft,
							  mc.gameSettings.keyBindRight,
							  mc.gameSettings.keyBindSprint,
							  mc.gameSettings.keyBindJump,
							  mc.gameSettings.keyBindSneak};
		
		for(KeyBinding bind : binds) {
			if(mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)) {
				bind.setKeyBindState(bind.getKeyCode(), Keyboard.isKeyDown(bind.getKeyCode()));
			}
		}
	}
}
